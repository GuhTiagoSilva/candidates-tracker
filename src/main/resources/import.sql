INSERT INTO tb_role (authority) VALUES ('ROLE_WORKER');
INSERT INTO tb_role (authority) VALUES ('ROLE_RECRUITER');

INSERT INTO tb_user (first_name, last_name, email, password, cpf, is_open_to_work, role_id) VALUES ('Alex', 'Green', 'alex@gmail.com', '$2a$12$PWugiEfMzTk71bKDL6AdXOaEEXNSrfiP6FdODa6G.NX75Khgyu7Ue', '123.456.789-11', true, 1);
INSERT INTO tb_user (first_name, last_name, email, password, cpf, is_open_to_work, role_id) VALUES ('Bob', 'Brown', 'bob@gmail.com', '$2a$12$PWugiEfMzTk71bKDL6AdXOaEEXNSrfiP6FdODa6G.NX75Khgyu7Ue', '124.456.789-12', null, 2);