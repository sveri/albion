package de.sveri.albion.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.sveri.albion.entity.World;

@Repository
public class WorldRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void insertWorld(List<World> worlds) {
		String sql = "INSERT INTO world (world_index, unique_name) VALUES (?, ?); ";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				World world = worlds.get(i);
				ps.setString(1, world.index());
				ps.setString(2, world.uniqueName());
			}

			@Override
			public int getBatchSize() {
				return worlds.size();
			}
		});
	}

}
