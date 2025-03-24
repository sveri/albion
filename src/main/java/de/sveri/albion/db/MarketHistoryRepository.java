package de.sveri.albion.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import de.sveri.albion.entity.MarketHistory;
import de.sveri.albion.entity.MarketHistoryNats;

@Repository
public class MarketHistoryRepository {

	public static final Long TICK_MINUITOR = Long.valueOf("62136892800000");

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertMarketHistory(MarketHistoryNats marketHistory) {
		String sql = "INSERT INTO market_history (item_amount, silver_amount, item_id, location, quality, timestamp, aggregation) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE id=id ";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				MarketHistory entry = marketHistory.marketHistories().get(i);
				Timestamp orderTime = new Timestamp((entry.timestamp() / 10000) - TICK_MINUITOR);

//				LocalDate today = LocalDate.now();
//
//				// Get yesterday's date
//				LocalDate yesterday = today.minusDays(1);
//
//				// Convert Timestamp to LocalDate
//				LocalDateTime dateTime = timestamp.toLocalDateTime();
//				LocalDate timestampDate = dateTime.toLocalDate();

//				 Compare the dates
//				if (timestampDate.isEqual(today)) {
//					System.out.println("The timestamp is today.");
//				} else if (timestampDate.isEqual(yesterday)) {
//					System.out.println("The timestamp is yesterday.");
//				} else {
//					System.out.println("The timestamp is neither today nor yesterday.");
//				}

				ps.setLong(1, entry.itemAmount());
				ps.setLong(2, entry.silverAmount());
				ps.setString(3, marketHistory.albionIdString());
				ps.setInt(4, marketHistory.locationid());
				ps.setInt(5, marketHistory.qualityLevel());
//				Timestamp.from(Instant.ofEpochMilli(entry.timestamp()));
				ps.setTimestamp(6, orderTime);
				ps.setInt(7, marketHistory.timescale());
//				ps.setLong(5, entry.unitPriceSilver());
//				ps.setLong(6, entry.amount());
//				ps.setString(7, entry.auctionType());
//				if (entry.expires().startsWith("3024") || entry.expires().startsWith("3025")
//						|| entry.expires().startsWith("3023")) {
//					ps.setNull(8, Types.TIMESTAMP);
//				} else {
//					ps.setTimestamp(8, Timestamp.from(LocalDateTime.parse(entry.expires()).toInstant(ZoneOffset.UTC)));
//				}
//				ps.setInt(9, entry.locationid());
				ps.setTimestamp(10, Timestamp.valueOf(LocalDateTime.now()));
			}

			@Override
			public int getBatchSize() {
				return marketHistory.marketHistories().size();
			}
		});
	}

}
