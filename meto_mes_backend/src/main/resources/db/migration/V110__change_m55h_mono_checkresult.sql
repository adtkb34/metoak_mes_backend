ALTER TABLE final_check_mono_m55h
    MODIFY COLUMN check_result INT NULL,

    CHANGE COLUMN version_mcu version_mcu_ref VARCHAR(64) NULL,
    CHANGE COLUMN version_product version_product_ref VARCHAR(64) NULL,

    ADD COLUMN version_mcu_std VARCHAR(64) NULL AFTER version_mcu_ref,
    ADD COLUMN version_product_std VARCHAR(64) NULL AFTER version_product_ref;