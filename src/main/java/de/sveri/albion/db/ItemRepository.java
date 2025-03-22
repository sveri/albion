package de.sveri.albion.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.sveri.albion.entity.Item;

@Repository
public class ItemRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void insertItems(List<Item> items) {
		String sql = "INSERT INTO items (item_index, unique_name, name_en, name_de, desc_en, desc_de) "
				+ " VALUES (?, ?, ?, ?, ?, ?); ";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Item item = items.get(i);
				ps.setString(1, item.index());
				ps.setString(2, item.uniqueName());

				String nameEn = "";
				String nameDe = "";
				String descEn = "";
				String descDe = "";

				if (item.localizedNames() != null) {
					if (item.localizedNames().en() != null) {
						nameEn = item.localizedNames().en();
					}
				}

				if (item.localizedNames() != null) {
					if (item.localizedNames().de() != null) {
						nameDe = item.localizedNames().de();
					}
				}

				if (item.localizedDescriptions() != null) {
					if (item.localizedDescriptions().en() != null) {
						descEn = item.localizedDescriptions().en();
					}
				}

				if (item.localizedDescriptions() != null) {
					if (item.localizedDescriptions().de() != null) {
						descDe = item.localizedDescriptions().de();
					}
				}
				ps.setString(3, nameEn);
				ps.setString(4, nameDe);
				ps.setString(5, descEn);
				ps.setString(6, descDe);
			}

			@Override
			public int getBatchSize() {
				return items.size();
			}
		});
	}

}
