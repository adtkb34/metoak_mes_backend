CREATE TABLE IF NOT EXISTS final_check_mono_m55h (
    id INT AUTO_INCREMENT PRIMARY KEY,
    datetime DATETIME,
    sn VARCHAR(64), 
    error_code INT,
    operator VARCHAR(64),

    image_ok BOOLEAN,
    image_path VARCHAR(255),
    check_result BOOLEAN,
    can0_ok BOOLEAN,
    can1_ok BOOLEAN,

    version_adas VARCHAR(64),
    version_spi VARCHAR(64),
    version_mcu VARCHAR(64),

    focus DOUBLE,
    baseline DOUBLE
);
