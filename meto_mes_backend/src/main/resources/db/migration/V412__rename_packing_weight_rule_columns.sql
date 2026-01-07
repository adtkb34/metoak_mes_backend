ALTER TABLE packing_weight_rule
    CHANGE COLUMN full_box_quantity spec_quantity INT NOT NULL COMMENT '规格数量',
    CHANGE COLUMN single_product_weight unit_weight DECIMAL(10,3) NOT NULL COMMENT '单个产品重量',
    CHANGE COLUMN full_box_package_weight tare_weight DECIMAL(10,3) NOT NULL COMMENT '皮重',
    CHANGE COLUMN allowed_deviation weight_tolerance DECIMAL(10,3) NOT NULL COMMENT '重量允许误差';
