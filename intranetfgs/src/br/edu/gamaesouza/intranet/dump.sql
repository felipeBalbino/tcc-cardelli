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
INSERT INTO Rule VALUES (29,"RULE_ALUNOS_LISTA");
INSERT INTO Rule VALUES (30,"RULE_ALUNOS_SAVE");
INSERT INTO Rule VALUES (31,"RULE_ALUNOS_ALTERA");
INSERT INTO Rule VALUES (32,"RULE_LISTA_AEP");
INSERT INTO Rule VALUES (33,"RULE_DELETA_AEP");
INSERT INTO Rule VALUES (34,"RULE_ALTERA_AEP");
INSERT INTO Rule VALUES (35,"RULE_SALVA_AEP");
INSERT INTO Rule VALUES (36,"RULE_LISTA_COMPLEMENTAR");
INSERT INTO Rule VALUES (37,"RULE_DELETA_COMPLEMENTAR");
INSERT INTO Rule VALUES (38,"RULE_ALTERA_COMPLEMENTAR");
INSERT INTO Rule VALUES (39,"RULE_SALVA_COMPLEMENTAR");
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
INSERT INTO pessoa_rule VALUES (1,29);
INSERT INTO pessoa_rule VALUES (1,30);
INSERT INTO pessoa_rule VALUES (1,31);
INSERT INTO pessoa_rule VALUES (1,32);
INSERT INTO pessoa_rule VALUES (1,33);
INSERT INTO pessoa_rule VALUES (1,34);
INSERT INTO pessoa_rule VALUES (1,35);
INSERT INTO pessoa_rule VALUES (1,36);
INSERT INTO pessoa_rule VALUES (1,37);
INSERT INTO pessoa_rule VALUES (1,38);
INSERT INTO pessoa_rule VALUES (1,39);
INSERT INTO Curso (id,nome) VALUES  (1,"Sistema de Informaaao");
INSERT INTO Curso (id,nome) VALUES  (2,"Marketing");
INSERT INTO Curso (id,nome) VALUES  (3,"Direito");
INSERT INTO Curso (id,nome) VALUES  (4,"Turismo");
INSERT INTO Curso (id,nome) VALUES  (5,"Negacios Imobiliarios");
INSERT INTO Curso (id,nome) VALUES  (6,"Gestao Comercial");
INSERT INTO Curso (id,nome) VALUES  (7,"Redes de Computadores");
INSERT INTO Disciplina (id,nome) VALUES (1,"Linguagem e Tecnicas de Programaaao I");
INSERT INTO Disciplina (id,nome) VALUES (2,"Linguagem e Tecnicas de Programaaao II");
INSERT INTO Disciplina (id,nome) VALUES (3,"Linguagem e Tecnicas de Programaaao III");
INSERT INTO Disciplina (id,nome) VALUES (4,"Linguagem e Tecnicas de Programaaao IV");
INSERT INTO Disciplina (id,nome) VALUES (5,"Legislaaao e atica");
INSERT INTO Disciplina (id,nome) VALUES (6,"Ciancia Polatica E Teoria do Estado ");	
INSERT INTO Disciplina (id,nome) VALUES (7,"Direito Processual Civil III");
INSERT INTO Disciplina (id,nome) VALUES (8,"Direito Administrativo I");	
INSERT INTO Disciplina (id,nome) VALUES (9,"Direito Processual Civil IV");
INSERT INTO Disciplina (id,nome) VALUES (10,"Direito Administrativo II");	
INSERT INTO Disciplina (id,nome) VALUES (11,"Direito Processual do Trabalho");
INSERT INTO Disciplina (id,nome) VALUES (12,"Direito Administrativo III"); 	
INSERT INTO Disciplina (id,nome) VALUES (13,"Direito Processual Penal II");
INSERT INTO Disciplina (id,nome) VALUES (14,"Direito Ambiental"); 	
INSERT INTO Disciplina (id,nome) VALUES (15,"Direito Reais");
INSERT INTO Disciplina (id,nome) VALUES (16,"Direito Constitucional I"); 	
INSERT INTO Disciplina (id,nome) VALUES (17,"Direito Tributario I");
INSERT INTO Disciplina (id,nome) VALUES (18,"Direito Constitucional II");	
INSERT INTO Disciplina (id,nome) VALUES (19,"Direito Tributario II");
INSERT INTO Disciplina (id,nome) VALUES (20,"Direito das Obrigaaaes"); 	
INSERT INTO Disciplina (id,nome) VALUES (21,"Direito Urbanastico E Registros Pablicos");
INSERT INTO Disciplina (id,nome) VALUES (22,"Direito das Sucessaes"); 	
INSERT INTO Disciplina (id,nome) VALUES (23,"Direitos Dos Contratos E Responsabilidade Civil");
INSERT INTO Disciplina (id,nome) VALUES (24,"Direito de Famalia"); 	
INSERT INTO Disciplina (id,nome) VALUES (25,"Direitos Fundamentais");
INSERT INTO Disciplina (id,nome) VALUES (26,"Direito do Consumidor"); 	
INSERT INTO Disciplina (id,nome) VALUES (27,"Disciplina da anfase Escolhida");
INSERT INTO Disciplina (id,nome) VALUES (28,"Direito do Trabalho I"); 	
INSERT INTO Disciplina (id,nome) VALUES (29,"atica Geral e Profissional");
INSERT INTO Disciplina (id,nome) VALUES (30,"Direito do Trabalho II");	
INSERT INTO Disciplina (id,nome) VALUES (31,"Filosofia Geral e do Direito");
INSERT INTO Disciplina (id,nome) VALUES (32,"Direito Empresarial I"); 	
INSERT INTO Disciplina (id,nome) VALUES (33,"Fundamentos da Economia");
INSERT INTO Disciplina (id,nome) VALUES (34,"Direito Empresarial II"); 	
INSERT INTO Disciplina (id,nome) VALUES (35,"Introduaao Ao Estudo do Direito I");
INSERT INTO Disciplina (id,nome) VALUES (36,"Direito Empresarial III"); 	
INSERT INTO Disciplina (id,nome) VALUES (37,"Introduaao Ao Estudo do Direito II");
INSERT INTO Disciplina (id,nome) VALUES (38,"Direito Empresarial IV");	
INSERT INTO Disciplina (id,nome) VALUES (39,"Introduaao as Ciancias Sociais");
INSERT INTO Disciplina (id,nome) VALUES (40,"Direito Financeiro"); 	
INSERT INTO Disciplina (id,nome) VALUES (41,"Linguagem Juradica");
INSERT INTO Disciplina (id,nome) VALUES (42,"Direito Internacional Privado"); 	
INSERT INTO Disciplina (id,nome) VALUES (43,"Metodologia da Pesquisa Juradica");
INSERT INTO Disciplina (id,nome) VALUES (44,"Direito Internacional Pablico");	
INSERT INTO Disciplina (id,nome) VALUES (45,"Pratica Juradica Cavel I");
INSERT INTO Disciplina (id,nome) VALUES (46,"Direito Penal Econamico");	
INSERT INTO Disciplina (id,nome) VALUES (47,"Pratica Juradica Cavel II");
INSERT INTO Disciplina (id,nome) VALUES (48,"Direito Penal I"); 	
INSERT INTO Disciplina (id,nome) VALUES (49,"Pratica Juradica Penal I");
INSERT INTO Disciplina (id,nome) VALUES (50,"Direito Penal II");	
INSERT INTO Disciplina (id,nome) VALUES (51,"Pratica Juradica Penal II");
INSERT INTO Disciplina (id,nome) VALUES (52,"Direito Penal III"); 	
INSERT INTO Disciplina (id,nome) VALUES (53,"Pratica Juradica Trabalhista E Administrativa");
INSERT INTO Disciplina (id,nome) VALUES (54,"Direito Penal IV");	
INSERT INTO Disciplina (id,nome) VALUES (55,"Psicologia Geral E Juradica");
INSERT INTO Disciplina (id,nome) VALUES (56,"Direito Processual Civil I"); 	
INSERT INTO Disciplina (id,nome) VALUES (57,"Sociologia Juradica");
INSERT INTO Disciplina (id,nome) VALUES (58,"Direito Processual Civil II"); 	
INSERT INTO Disciplina (id,nome) VALUES (59,"Teoria Geral do Direito Privado");
INSERT INTO Disciplina (id,nome) VALUES (60,"Direito Processual Penal I"); 	
INSERT INTO Disciplina (id,nome) VALUES (61,"Teoria Geral do Processo");
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (1,1);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (1,2);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (1,3);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (1,4);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (1,5);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (2,5);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,6);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,7);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,8);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,9);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,10);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,11);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,12);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,13);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,14);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,15);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,16);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,17);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,18);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,19);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,20);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,21);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,22);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,23);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,24);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,25);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,26);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,27);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,28);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,29);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,30);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,31);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,32);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,33);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,34);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,35);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,36);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,37);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,38);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,39);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,40);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,41);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,42);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,43);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,44);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,45);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,46);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,47);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,48);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,49);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,50);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,51);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,52);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,53);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,54);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,55);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,56);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,57);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,58);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,59);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,60);
INSERT INTO disciplina_curso (curso_id, disciplina_id) VALUES (3,61);
INSERT INTO Pessoa (id,email,login,matricula,nome,senha)  VALUES  (2,"gabrielcastilho.cardelli@gmail.com","gabriel",1232345345,"Gabriel","123456");
INSERT INTO Aluno VALUES  (2,1);
INSERT INTO Pessoa  (id,email,login,matricula,nome,senha) VALUES  (3,"phellipe86@gmail.com","taaqui",1231233,"Felipe","123456");
INSERT INTO Aluno VALUES  (3,1);
INSERT INTO Pessoa  (id,email,login,matricula,nome,senha) VALUES  (4,"phellipe@gmail.com","prof",23432446,"professor","123456");
INSERT INTO Professor VALUES  (4);

INSERT INTO DisciplinaLetiva VALUES  (1,2011,1,"manha",1,4);
INSERT INTO DisciplinaLetiva VALUES  (2,2011,1,"tarde",2,4);
INSERT INTO DisciplinaLetiva VALUES  (3,2011,2,"noite",1,4);
INSERT INTO DisciplinaLetiva VALUES  (4,2011,2,"manha",2,4);

INSERT INTO DisciplinaLetiva_Aluno VALUES  (1,2);
INSERT INTO DisciplinaLetiva_Aluno VALUES  (2,2);
INSERT INTO DisciplinaLetiva_Aluno VALUES  (1,3);
INSERT INTO DisciplinaLetiva_Aluno VALUES  (2,3);

INSERT INTO Horario VALUES(1,"2011","19:40","18:50",1);
INSERT INTO Horario VALUES(2,"2011","20:40","19:40",1);
INSERT INTO Horario VALUES(3,"2011","21:40","20:50",1);
INSERT INTO Horario VALUES(4,"2011","22:40","21:50",1);

INSERT INTO DisciplinaLetivaHorario VALUES  (1,"SEGUNDA",1,1);
INSERT INTO DisciplinaLetivaHorario VALUES  (2,"QUARTA",1,2);
INSERT INTO DisciplinaLetivaHorario VALUES  (3,"TERCA",2,3);


SELECT dl FROM DisciplinaLetivaHorario dlHorario left join dlHorario.DisciplinaLetiva dl left join dl.horario horario where dl.semestre = 1 and dl.ano = 2011 and dlHorario = "SEGUNDA";
