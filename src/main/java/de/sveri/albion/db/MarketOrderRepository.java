package de.sveri.albion.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.sveri.albion.entity.MarketOrder;

@Repository
public class MarketOrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertUser(List<MarketOrder> orders) {
		String sql = "INSERT INTO market_orders (albion_id, item_id, quality_level, "
				+ "enchantment_level, price, initial_amount, auction_type, expires, location, created_at) "
				+ "VALUES (?, ?,?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE id=id ";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MarketOrder order = orders.get(i);
				ps.setLong(1, order.id());
				ps.setString(2, order.itemTypeId());
				ps.setInt(3, order.qualityLevel());
				ps.setInt(4, order.enchantmentLevel());
				ps.setLong(5, order.unitPriceSilver());
				ps.setLong(6, order.amount());
				ps.setString(7, order.auctionType());
				if (order.expires().startsWith("'3024") || order.expires().startsWith("'3025")) {
					ps.setNull(8, Types.TIMESTAMP);
				} else {
					ps.setTimestamp(8, Timestamp.from(LocalDateTime.parse(order.expires()).toInstant(ZoneOffset.UTC)));
				}
				ps.setInt(9, order.locationid());
				ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
			}

			@Override
			public int getBatchSize() {
				return orders.size();
			}
		});
	}

}
