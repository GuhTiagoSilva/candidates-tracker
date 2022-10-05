INSERT INTO tb_role (authority) VALUES ('ROLE_WORKER');
INSERT INTO tb_role (authority) VALUES ('ROLE_RECRUITER');

INSERT INTO tb_user (first_name, last_name, email, password, cpf, is_open_to_work, role_id) VALUES ('Alex', 'Green', 'alex@gmail.com', '12345678', '123.456.789-11', true, 1);
INSERT INTO tb_user (first_name, last_name, email, password, cpf, is_open_to_work, role_id) VALUES ('Bob', 'Brown', 'bob@gmail.com', '12345678', '124.456.789-12', null, 2);