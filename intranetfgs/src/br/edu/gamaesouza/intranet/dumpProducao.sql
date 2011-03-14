SET NAMES UTF8;
SET GLOBAL table_lock_wait_timeout = 10;
INSERT INTO Pessoa (id,email,login,matricula,nome,senha) VALUES  (1,"admin@admin.com","admin",123123123,"Administrador","admin");
INSERT INTO Professor VALUES  (1);
INSERT INTO Rule VALUES (1,"RULE_CURSO_NOVO");
INSERT INTO Rule VALUES (2,"RULE_CURSO_LISTA");
INSERT INTO Rule VALUES (3,"RULE_EVENTO_NOVO");
INSERT INTO Rule VALUES (4,"RULE_EVENTO_ALTERA");
INSERT INTO Rule VALUES (5,"RULE_EVENTO_DELETA");
INSERT INTO Rule VALUES (6,"RULE_EVENTO_LISTA");
INSERT INTO Rule VALUES (7,"RULE_DISCIPLINA_LISTA");
INSERT INTO Rule VALUES (8,"RULE_DISCIPLINA_NOVO");
INSERT INTO Rule VALUES (9,"RULE_DISCIPLINA_SALVA");
INSERT INTO Rule VALUES (10,"RULE_DISCIPLINA_ALTERA");
INSERT INTO Rule VALUES (11,"RULE_NOTICIA_LISTA");
INSERT INTO Rule VALUES (12,"RULE_NOTICIA_SALVA");
INSERT INTO Rule VALUES (13,"RULE_PROFESSOR_SALVA");
INSERT INTO Rule VALUES (14,"RULE_PROFESSOR_LISTA");
INSERT INTO Rule VALUES (15,"RULE_PROFESSOR_ALTERA");
INSERT INTO Rule VALUES (16,"RULE_UPLOAD_NOVO");
INSERT INTO Rule VALUES (17,"RULE_UPLOAD_LISTA");
INSERT INTO Rule VALUES (18,"RULE_UPLOAD_DELETA");
INSERT INTO Rule VALUES (19,"RULE_NOTICIA_ALTERA");
INSERT INTO Rule VALUES (20,"RULE_NOTICIA_DELETE");
INSERT INTO Rule VALUES (21,"RULE_CURSO_DELETA");
INSERT INTO Rule VALUES (22,"RULE_CURSO_ALTERA");
INSERT INTO Rule VALUES (23,"RULE_PROFESSOR_DELETE");
INSERT INTO Rule VALUES (24,"RULE_DISCIPLINA_DELETE");
INSERT INTO Rule VALUES (25,"RULE_DISCIPLINA_LETIVA_SAVE");
INSERT INTO Rule VALUES (26,"RULE_DISCIPLINA_LETIVA_LISTA");
INSERT INTO Rule VALUES (27,"RULE_DISCIPLINA_LETIVA_ALTERA");
INSERT INTO Rule VALUES (28,"RULE_DISCIPLINA_LETIVA_DELETE");
INSERT INTO pessoa_rule VALUES (1,1);
INSERT INTO pessoa_rule VALUES (1,2);
INSERT INTO pessoa_rule VALUES (1,3);
INSERT INTO pessoa_rule VALUES (1,4);
INSERT INTO pessoa_rule VALUES (1,5);
INSERT INTO pessoa_rule VALUES (1,6);
INSERT INTO pessoa_rule VALUES (1,7);
INSERT INTO pessoa_rule VALUES (1,8);
INSERT INTO pessoa_rule VALUES (1,9);
INSERT INTO pessoa_rule VALUES (1,10);
INSERT INTO pessoa_rule VALUES (1,11);
INSERT INTO pessoa_rule VALUES (1,12);
INSERT INTO pessoa_rule VALUES (1,13);
INSERT INTO pessoa_rule VALUES (1,14);
INSERT INTO pessoa_rule VALUES (1,15);
INSERT INTO pessoa_rule VALUES (1,16);
INSERT INTO pessoa_rule VALUES (1,17);
INSERT INTO pessoa_rule VALUES (1,18);
INSERT INTO pessoa_rule VALUES (1,19);
INSERT INTO pessoa_rule VALUES (1,20);
INSERT INTO pessoa_rule VALUES (1,21);
INSERT INTO pessoa_rule VALUES (1,22);
INSERT INTO pessoa_rule VALUES (1,23);
INSERT INTO pessoa_rule VALUES (1,24);
INSERT INTO pessoa_rule VALUES (1,25);
INSERT INTO pessoa_rule VALUES (1,26);
INSERT INTO pessoa_rule VALUES (1,27);
INSERT INTO pessoa_rule VALUES (1,28);

INSERT INTO Curso (id,nome) VALUES  (1,"Sistema de Informa��o");
INSERT INTO Curso (id,nome) VALUES  (2,"Marketing");
INSERT INTO Curso (id,nome) VALUES  (3,"Direito");
INSERT INTO Curso (id,nome) VALUES  (4,"Turismo");
INSERT INTO Curso (id,nome) VALUES  (5,"Neg�cios Imobili�rios");
INSERT INTO Curso (id,nome) VALUES  (6,"Gest�o Comercial");
INSERT INTO Curso (id,nome) VALUES  (7,"Redes de Computadores");