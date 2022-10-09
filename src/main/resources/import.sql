INSERT INTO tb_role (authority) VALUES ('ROLE_WORKER');
INSERT INTO tb_role (authority) VALUES ('ROLE_RECRUITER');

INSERT INTO tb_user (first_name, last_name, email, password, cpf, is_open_to_work, role_id) VALUES ('Gustavo', 'Silva', 'gustavo.filho.gts@gmail.com', '$2a$12$PWugiEfMzTk71bKDL6AdXOaEEXNSrfiP6FdODa6G.NX75Khgyu7Ue', '123.456.789-11', true, 1);
INSERT INTO tb_user (first_name, last_name, email, password, cpf, is_open_to_work, role_id) VALUES ('Gustavo', 'Tiago', 'gustavo.tiago.gts@gmail.com', '$2a$12$PWugiEfMzTk71bKDL6AdXOaEEXNSrfiP6FdODa6G.NX75Khgyu7Ue', '124.456.789-12', false, 2);

INSERT INTO tb_skill (skill_name) values ('Java');
INSERT INTO tb_skill (skill_name) values ('C#');
INSERT INTO tb_skill (skill_name) values ('Angular 2+');
INSERT INTO tb_skill (skill_name) values ('Spring Boot');
INSERT INTO tb_skill (skill_name) values ('PostgreSQL');
INSERT INTO tb_skill (skill_name) values ('Arduino');
INSERT INTO tb_skill (skill_name) values ('Python');
INSERT INTO tb_skill (skill_name) values ('Oracle Database');
INSERT INTO tb_skill (skill_name) values ('SQL Server');
INSERT INTO tb_skill (skill_name) values ('Spring Data JPA');
INSERT INTO tb_skill (skill_name) values ('Spring Cloud');
INSERT INTO tb_skill (skill_name) values ('Spring Security');

INSERT INTO tb_test (title, skill_id, user_id, number_of_questions_to_be_approved) VALUES ('Java Professional', 1, null, 3);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('C# Professional', 2, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('Angular Professional', 3, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('Spring Boot Professional', 4, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('PostgreSQL Professional', 5, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('Arduino Professional', 6, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('Python Professional', 7, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('Oracle Database Professional', 8, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('SQL Server Professional', 9, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('Spring Data JPA Professional', 10, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('Spring Cloud Professional', 11, null);
-- INSERT INTO tb_test (title, skill_id, user_id) VALUES ('Spring Security Professional', 12, null);

INSERT INTO tb_question (question, test_id) VALUES ('What is Java?', 1);
INSERT INTO tb_question (question, test_id) VALUES ('What Java is used for?', 1);
INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications Java can develop?', 1);
INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn Java?', 1);
INSERT INTO tb_question (question, test_id) VALUES ('Is Java a good programming language?', 1);

INSERT INTO tb_answer(answer_description, question_id) VALUES ('A programming Language', 1);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('A fruit', 1);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('A vegetable', 1);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('A juice', 1);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('A cup', 1);

INSERT INTO tb_answer(answer_description, question_id) VALUES ('To Drink', 2);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('To develop software', 2);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('To eat', 2);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('To travel', 2);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('To run', 2);

INSERT INTO tb_answer(answer_description, question_id) VALUES ('Web Applications', 3);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('Mobile Applications', 3);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('All of alternatives are correct', 3);

INSERT INTO tb_answer(answer_description, question_id) VALUES ('Nothing', 4);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('Programming Logic', 4);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('Just Java', 4);

INSERT INTO tb_answer(answer_description, question_id) VALUES ('No', 5);
INSERT INTO tb_answer(answer_description, question_id) VALUES ('Yes', 5);

INSERT INTO tb_answer_question (question_id, answer_id) VALUES (1, 1);
INSERT INTO tb_answer_question (question_id, answer_id) VALUES (2, 7);
INSERT INTO tb_answer_question (question_id, answer_id) VALUES (3, 13);
INSERT INTO tb_answer_question (question_id, answer_id) VALUES (4, 15);
INSERT INTO tb_answer_question (question_id, answer_id) VALUES (5, 18);

-- INSERT INTO tb_question (question, test_id) VALUES ('What is C#?', 2);
-- INSERT INTO tb_question (question, test_id) VALUES ('What C# is used for?', 2);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications C# can develop?', 2);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn C#?', 2);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is C# a good programming language?', 2);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is Angular?', 3);
-- INSERT INTO tb_question (question, test_id) VALUES ('What Angular is used for?', 3);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications Angular can develop?', 3);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn Angular?', 3);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is Angular a good programming language?', 3);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is Spring Boot?', 4);
-- INSERT INTO tb_question (question, test_id) VALUES ('What Spring Boot is used for?', 4);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications Spring Boot can develop?', 4);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn Spring Boot?', 4);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is Spring Boot a good programming language?', 4);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is PostgreSQL?', 5);
-- INSERT INTO tb_question (question, test_id) VALUES ('What PostgreSQL is used for?', 5);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications PostgreSQL can develop?', 5);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn PostgreSQL?', 5);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is PostgreSQL a good programming language?', 5);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is Arduino?', 6);
-- INSERT INTO tb_question (question, test_id) VALUES ('What Arduino is used for?', 6);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications Arduino can develop?', 6);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn Arduino?', 6);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is Arduino a good programming language?', 6);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is Python?', 7);
-- INSERT INTO tb_question (question, test_id) VALUES ('What Python is used for?', 7);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications Python can develop?', 7);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn Python?', 7);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is Python a good programming language?', 7);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is Oracle Database?', 8);
-- INSERT INTO tb_question (question, test_id) VALUES ('What Oracle Database is used for?', 8);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications Oracle Database can develop?', 8);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn Oracle Database?', 8);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is Oracle Database a good programming language?', 8);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is SQL Server?', 9);
-- INSERT INTO tb_question (question, test_id) VALUES ('What SQL Server is used for?', 9);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications SQL Server can develop?', 9);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn SQL Server?', 9);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is SQL Server a good programming language?', 9);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is Spring Data JPA?', 10);
-- INSERT INTO tb_question (question, test_id) VALUES ('What Spring Data JPA is used for?', 10);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications Spring Data JPA can develop?', 10);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn Spring Data JPA?', 10);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is Spring Data JPA a good programming language?', 10);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is Spring Cloud?', 11);
-- INSERT INTO tb_question (question, test_id) VALUES ('What Spring Cloud is used for?', 11);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications Spring Cloud can develop?', 11);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn Spring Cloud?', 11);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is Spring Cloud a good programming language?', 11);
--
-- INSERT INTO tb_question (question, test_id) VALUES ('What is Spring Security?', 11);
-- INSERT INTO tb_question (question, test_id) VALUES ('What Spring Security is used for?', 11);
-- INSERT INTO tb_question (question, test_id) VALUES ('What kind of applications Spring Security can develop?', 11);
-- INSERT INTO tb_question (question, test_id) VALUES ('What should you know before learn Spring Security?', 11);
-- INSERT INTO tb_question (question, test_id) VALUES ('Is Spring Security a good programming language?', 11);
