alter table Aluno drop foreign key FK3C6D4CBD4628FFC
alter table Aluno drop foreign key FK3C6D4CBA3D06140
alter table Aluno_Hora drop foreign key FK8B22948AF1234451
alter table Aluno_Hora drop foreign key FK8B22948A2D3FF99C
alter table Arquivo drop foreign key FK376CAA8DC112AA1C
alter table Arquivo drop foreign key FK376CAA8DE1E99AB8
alter table DisciplinaLetiva drop foreign key FKD9768785C112AA1C
alter table DisciplinaLetiva drop foreign key FKD97687855EE27FB8
alter table DisciplinaLetivaHorario drop foreign key FK110EC89DCB3BFE97
alter table DisciplinaLetivaHorario drop foreign key FK110EC89DDD8B19BC
alter table DisciplinaLetivaHorario drop foreign key FK110EC89DE1E99AB8
alter table DisciplinaLetiva_Aluno drop foreign key FKF3B547912D3FF99C
alter table DisciplinaLetiva_Aluno drop foreign key FKF3B54791E1E99AB8
alter table Evento drop foreign key FK7C6CCD35E2630B68
alter table Hora drop foreign key FK2269962D3FF99C
alter table HoraAEP drop foreign key FKA49E23565C04E8F
alter table HoraComplementar drop foreign key FK4107CD8D5C04E8F
alter table HoraComplementar drop foreign key FK4107CD8D668A031C
alter table Noticia drop foreign key FKE223DCE5F99F065C
alter table Professor drop foreign key FK3B4FF64FA3D06140
alter table disciplina_curso drop foreign key FK2C02BE09D4628FFC
alter table disciplina_curso drop foreign key FK2C02BE095EE27FB8
alter table pessoa_rule drop foreign key FK64B6F094B8B40D8
alter table pessoa_rule drop foreign key FK64B6F094DD176811
drop table if exists Aluno
drop table if exists Aluno_Hora
drop table if exists Arquivo
drop table if exists Atividade
drop table if exists Curso
drop table if exists Disciplina
drop table if exists DisciplinaLetiva
drop table if exists DisciplinaLetivaHorario
drop table if exists DisciplinaLetiva_Aluno
drop table if exists Evento
drop table if exists Hora
drop table if exists HoraAEP
drop table if exists HoraComplementar
drop table if exists Horario
drop table if exists Noticia
drop table if exists Pessoa
drop table if exists Professor
drop table if exists Rule
drop table if exists disciplina_curso
drop table if exists pessoa_rule
create table Aluno (periodo integer, statusMatricula varchar(255), id integer not null, curso_id integer, primary key (id))
create table Aluno_Hora (Aluno_id integer not null, horas_id integer not null, unique (horas_id))
create table Arquivo (arq_id integer not null auto_increment, dataEnvio datetime, arq_nome varchar(255) not null, arq_url varchar(255) not null, disciplinaLetiva_id integer, professor_id integer, primary key (arq_id))
create table Atividade (id integer not null auto_increment, nome varchar(255), numeroHoras integer, primary key (id))
create table Curso (id integer not null auto_increment, cargaHorariaComplementar integer, nome varchar(255) unique, primary key (id))
create table Disciplina (id integer not null auto_increment, nome varchar(255), primary key (id))
create table DisciplinaLetiva (id integer not null auto_increment, ano integer not null, sala varchar(255) not null, semestre integer not null, turno varchar(255) not null, disciplina_id integer, professor_id integer, primary key (id))
create table DisciplinaLetivaHorario (id integer not null auto_increment, DiaSemana varchar(255), disciplinaLetiva_id integer, horario_id integer, primary key (id))
create table DisciplinaLetiva_Aluno (DisciplinaLetiva_id integer not null, aluno_id integer not null)
create table Evento (id integer not null auto_increment, coordenacao longtext, datahoraFim datetime, datahoraInicio datetime, datahoraPublicacao datetime, local longtext, obs longtext, publicoalvo longtext, title longtext not null, autor_id integer, primary key (id))
create table Hora (id integer not null auto_increment, aluno_id integer, primary key (id))
create table HoraAEP (data date, horaFim time, horaInicio time, id integer not null, primary key (id))
create table HoraComplementar (minutos integer, titulo varchar(255), id integer not null, atividade_id integer, primary key (id))
create table Horario (id integer not null auto_increment, ano integer not null, horaFim time, horaInicio time, semestre integer not null, primary key (id))
create table Noticia (id integer not null auto_increment, datahoraPublicacao datetime, mensagem longtext, title longtext not null, autor_id integer, primary key (id))
create table Pessoa (id integer not null auto_increment, dataUltimoAcesso datetime, email varchar(255) not null unique, login varchar(255) not null unique, matricula integer unique, nome varchar(255), senha varchar(255), primary key (id))
create table Professor (id integer not null, primary key (id))
create table Rule (id integer not null auto_increment, nome varchar(255) not null unique, primary key (id))
create table disciplina_curso (curso_id integer not null, disciplina_id integer not null)
create table pessoa_rule (pessoa_id integer not null, regra_id integer not null)
alter table Aluno add index FK3C6D4CBD4628FFC (curso_id), add constraint FK3C6D4CBD4628FFC foreign key (curso_id) references Curso (id)
alter table Aluno add index FK3C6D4CBA3D06140 (id), add constraint FK3C6D4CBA3D06140 foreign key (id) references Pessoa (id)
alter table Aluno_Hora add index FK8B22948AF1234451 (horas_id), add constraint FK8B22948AF1234451 foreign key (horas_id) references Hora (id)
alter table Aluno_Hora add index FK8B22948A2D3FF99C (Aluno_id), add constraint FK8B22948A2D3FF99C foreign key (Aluno_id) references Aluno (id)
alter table Arquivo add index FK376CAA8DC112AA1C (professor_id), add constraint FK376CAA8DC112AA1C foreign key (professor_id) references Professor (id)
alter table Arquivo add index FK376CAA8DE1E99AB8 (disciplinaLetiva_id), add constraint FK376CAA8DE1E99AB8 foreign key (disciplinaLetiva_id) references DisciplinaLetiva (id)
alter table DisciplinaLetiva add index FKD9768785C112AA1C (professor_id), add constraint FKD9768785C112AA1C foreign key (professor_id) references Professor (id)
alter table DisciplinaLetiva add index FKD97687855EE27FB8 (disciplina_id), add constraint FKD97687855EE27FB8 foreign key (disciplina_id) references Disciplina (id)
alter table DisciplinaLetivaHorario add index FK110EC89DCB3BFE97 (horario_id), add constraint FK110EC89DCB3BFE97 foreign key (horario_id) references DisciplinaLetivaHorario (id)
alter table DisciplinaLetivaHorario add index FK110EC89DDD8B19BC (horario_id), add constraint FK110EC89DDD8B19BC foreign key (horario_id) references Horario (id)
alter table DisciplinaLetivaHorario add index FK110EC89DE1E99AB8 (disciplinaLetiva_id), add constraint FK110EC89DE1E99AB8 foreign key (disciplinaLetiva_id) references DisciplinaLetiva (id)
alter table DisciplinaLetiva_Aluno add index FKF3B547912D3FF99C (aluno_id), add constraint FKF3B547912D3FF99C foreign key (aluno_id) references Aluno (id)
alter table DisciplinaLetiva_Aluno add index FKF3B54791E1E99AB8 (DisciplinaLetiva_id), add constraint FKF3B54791E1E99AB8 foreign key (DisciplinaLetiva_id) references DisciplinaLetiva (id)
alter table Evento add index FK7C6CCD35E2630B68 (autor_id), add constraint FK7C6CCD35E2630B68 foreign key (autor_id) references Professor (id)
alter table Hora add index FK2269962D3FF99C (aluno_id), add constraint FK2269962D3FF99C foreign key (aluno_id) references Aluno (id)
alter table HoraAEP add index FKA49E23565C04E8F (id), add constraint FKA49E23565C04E8F foreign key (id) references Hora (id)
alter table HoraComplementar add index FK4107CD8D5C04E8F (id), add constraint FK4107CD8D5C04E8F foreign key (id) references Hora (id)
alter table HoraComplementar add index FK4107CD8D668A031C (atividade_id), add constraint FK4107CD8D668A031C foreign key (atividade_id) references Atividade (id)
alter table Noticia add index FKE223DCE5F99F065C (autor_id), add constraint FKE223DCE5F99F065C foreign key (autor_id) references Pessoa (id)
alter table Professor add index FK3B4FF64FA3D06140 (id), add constraint FK3B4FF64FA3D06140 foreign key (id) references Pessoa (id)
alter table disciplina_curso add index FK2C02BE09D4628FFC (curso_id), add constraint FK2C02BE09D4628FFC foreign key (curso_id) references Curso (id)
alter table disciplina_curso add index FK2C02BE095EE27FB8 (disciplina_id), add constraint FK2C02BE095EE27FB8 foreign key (disciplina_id) references Disciplina (id)
alter table pessoa_rule add index FK64B6F094B8B40D8 (pessoa_id), add constraint FK64B6F094B8B40D8 foreign key (pessoa_id) references Pessoa (id)
alter table pessoa_rule add index FK64B6F094DD176811 (regra_id), add constraint FK64B6F094DD176811 foreign key (regra_id) references Rule (id)
