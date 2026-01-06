ALTER TABLE `mo_params_detail`
    MODIFY `base_id` BIGINT NULL COMMENT '关联 param_base.id，多对一',
    MODIFY `description` TEXT NULL COMMENT '版本说明，可记录变更内容',
    MODIFY `version_major` INT NULL COMMENT '版本：主号',
    MODIFY `version_minor` INT NULL COMMENT '版本：副号',
    MODIFY `version_patch` INT NULL COMMENT '版本：修订号',
    MODIFY `created_by` VARCHAR(200) NULL COMMENT '创建人，用于审计';
