-- 检查列是否存在
SELECT
    COLUMN_NAME
INTO
    @col_exists
FROM
    INFORMATION_SCHEMA.COLUMNS
WHERE
    TABLE_SCHEMA = DATABASE()
    AND TABLE_NAME = 'mo_spc_config'
    AND COLUMN_NAME = 'user_name';

-- 如果列不存在，则添加列
SET @sqlstmt = IF(@col_exists IS NULL,
                  'ALTER TABLE mo_spc_config ADD COLUMN user_name VARCHAR(64);',
                  'SELECT ''Column already exists.'' AS message');

-- 执行 SQL 语句
PREPARE stmt FROM @sqlstmt;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- 检查用户是否存在
SELECT 1 INTO @user_exists
FROM mysql.user
WHERE user = 'readonly_szdev' AND host = '%';

-- 如果用户不存在，则创建用户
SET @sqlstmt = IF(@user_exists IS NULL,
                  'CREATE USER ''readonly_szdev''@''%'' IDENTIFIED BY ''123456'';',
                  'SELECT ''User already exists.'' AS message');

-- 执行 SQL 语句
PREPARE stmt FROM @sqlstmt;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

GRANT SELECT, INSERT, UPDATE, DELETE ON mo_spc_config TO 'readonly_szdev'@'%';
