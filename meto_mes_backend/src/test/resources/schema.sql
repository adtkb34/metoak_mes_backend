CREATE TABLE mo_params_base (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type TINYINT NOT NULL,
    material_code VARCHAR(20),
    step_type_no VARCHAR(10),
    created_by VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE mo_params_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    base_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    version_major INT NOT NULL,
    version_minor INT NOT NULL,
    version_patch INT NOT NULL,
    params CLOB NOT NULL,
    is_active TINYINT NOT NULL,
    created_by VARCHAR(200) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_params_detail_base_id FOREIGN KEY (base_id) REFERENCES mo_params_base (id)
);
