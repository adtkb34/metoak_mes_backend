CREATE TABLE IF NOT EXISTS mo_tag_material_code (
    id INT PRIMARY KEY AUTO_INCREMENT,
    project_name VARCHAR(100) NOT NULL COMMENT '项目名称',
    material_code VARCHAR(50) NOT NULL COMMENT '物料代码',
    whole_machine_code VARCHAR(50) COMMENT '整机代码',
    serial_prefix VARCHAR(20) COMMENT '流水号前缀',
    process_code VARCHAR(10) COMMENT '工艺代码(最后一个字母)',
    created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uk_project_material (project_name, material_code)
) COMMENT '物料编码整机工艺关联表';

INSERT INTO mo_tag_material_code (project_name, material_code, whole_machine_code, process_code) VALUES
('宇通房车（二合一）', '900.00053', 'YT', 'A'),
('宇通房车（三合一）', '900.00054', 'YT', 'B'),
('GST乘用车', '900.00056', 'GST', 'C'),
('GST乘用车', '900.01072', 'GST', 'D'),
('GST商用车', '1000.01043', 'GST', 'E'),
('GST商用车', '1000.01044', 'GST', 'F'),
('矩芯', '1000.00012', 'JX', 'G'),
('BSD盲区相机', '1000.01035', 'BSD', 'H'),
('707相机', '900.00031', '707', 'I'),
('U60标准相机', '900.00017', 'S330_', 'A'),
('U60标准相机', '900.00018', 'S330S', 'A'),
('U60标准相机', '900.01104', 'S335_', 'A'),
('网口相机', '900.01060', 'WK', 'M'),
('割草机相机（SK）', '900.00083', 'S315', 'B'),
('GST商用车（三目）', '900.00092', 'GST3', 'B'),
('惠达相机', '10000.00065', 'S515', 'B');