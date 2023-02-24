CREATE TABLE tb_role(
id BIGINT AUTO_INCREMENT PRIMARY KEY ,
nome VARCHAR(255) NOT NULL
);

CREATE TABLE tb_usuario(
id BIGINT AUTO_INCREMENT PRIMARY KEY ,
login VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL,
role_id BIGINT,
FOREIGN KEY (role_id) REFERENCES tb_role(id)
);

INSERT INTO tb_role
(nome)
values
    ('ADMIN'),
    ('USUARIO');

INSERT INTO tb_usuario
(login, password, role_id)
values
    ('admin@admin.com', '$2a$12$QWqpbKZ4030NyWav.zhWFOSkjptsr5.RyIqiCCyDJOKIwudBZiitO', '1'),
    ('usuario@gmail.com', '$2a$12$QWqpbKZ4030NyWav.zhWFOSkjptsr5.RyIqiCCyDJOKIwudBZiitO', '2');
