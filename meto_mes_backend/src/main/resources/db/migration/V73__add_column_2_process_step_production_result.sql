SET @table := 'mo_process_step_production_result';
SET @column := 'add_time';

SELECT COUNT(*)
INTO @exists
FROM information_schema.columns
WHERE table_schema = DATABASE()
  AND table_name = @table
  AND column_name = @column;

SET @sql = IF(@exists = 0,
    CONCAT('ALTER TABLE ', @table, ' ADD COLUMN ', @column, ' datetime DEFAULT CURRENT_TIMESTAMP;'),
    'SELECT "Column already exists";'
);

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;