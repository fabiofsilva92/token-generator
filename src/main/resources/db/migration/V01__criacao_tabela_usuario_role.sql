CREATE TABLE tb_usuario(
id BIGINT AUTO_INCREMENT PRIMARY KEY ,
login VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL
);

CREATE TABLE tb_roles(
usuario_id BIGINT,
roles INTEGER NOT NULL,
FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id)
);


INSERT INTO tb_usuario
(login, password)
values
    ('admin@admin.com', '$2a$12$QWqpbKZ4030NyWav.zhWFOSkjptsr5.RyIqiCCyDJOKIwudBZiitO'),
    ('usuario@gmail.com', '$2a$12$QWqpbKZ4030NyWav.zhWFOSkjptsr5.RyIqiCCyDJOKIwudBZiitO');


INSERT INTO tb_roles
(usuario_id, roles)
values
    (1, 1),
    (1, 2),
    (2, 1);
