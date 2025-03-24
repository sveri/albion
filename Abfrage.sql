DROP TABLE ar_internal_metadata, gold_prices, market_history, market_orders, market_orders_expired, market_stats, schema_migrations, users;
-- SELECT * FROM market_history order by timestamp DESC LIMIT 100;
-- SELECT * FROM market_history where item_id LIKE 'T5%mercenary%' order by timestamp DESC LIMIT 10;
-- SELECT DISTINCT item_id FROM market_history WHERE item_id LIKE 'T5%j%';market_ordersmarket_ordersmarket_ordersmarket_ordersmarket_orders
-- SELECT DISTINCT item_id FROM market_history  WHERE item_id LIKE '%T5_HEAD_LEATHER_SET2%';
-- SELECT * FROM items WHERE name_en LIKE 'adept%mercenary%jacket%';
-- SELECT * FROM market_history AS m WHERE m.timestamp >= NOW() - INTERVAL 3 DAY LIMIT 10;
-- SELECT * FROM market_history AS m INNER JOIN  items AS i ON m.item_id = i.unique_name WHERE i.name_en LIKE 'adept%mercenary%jacket%' LIMIT 10;
-- SELECT * FROM market_orders AS m WHERE m.created_at >= NOW() - INTERVAL 3 DAY LIMIT 10;
-- SELECT COUNT(*) FROM market_history WHERE item_id LIKE 'T1_MEAL_SOUP';

SELECT  AVG(silver_amount / item_amount) AS last_7_days_avg FROM market_history 
	WHERE location = 2004  AND item_id LIKE 'T5_ARMOR_LEATHER_SET3' && timestamp >= NOW() - INTERVAL 2 DAY;

SELECT * FROM items WHERE name_en LIKE '%assassin%jacket%';

SELECT  *, silver_amount / item_amount FROM market_history 
	WHERE location = 2004  AND item_id LIKE 'T5_ARMOR_LEATHER_SET3' && timestamp >= NOW() - INTERVAL 6 DAY ORDER BY TIMESTAMP DESC;
	
SELECT * FROM market_orders WHERE location = 2004;

SELECT * FROM market_history WHERE item_amount = 220 AND silver_amount = 1556618;