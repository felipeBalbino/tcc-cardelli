create table Aluno (
        id integer not null,
        curso_id integer,
        primary key (id)
    )

    create table Arquivo (
        arq_id integer not null auto_increment,
        dataEnvio datetime,
        arq_nome varchar(255),
        arq_url varchar(255),
        disciplinaLetiva_id integer,
        professor_id integer,
        primary key (arq_id)
    )

    create table Curso (
        id integer not null auto_increment,
        nome varchar(255) unique,
        primary key (id)
    )

    create table Disciplina (
        id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    )

    create table DisciplinaLetiva (
        id integer not null auto_increment,
        ano integer,
        semestre integer,
        turno varchar(255),
        disciplina_id integer,
        primary key (id)
    )

    create table DisciplinaLetiva_Aluno (
        DisciplinaLetiva_id integer not null,
        aluno_id integer not null
    )

    create table Evento (
        id integer not null auto_increment,
        coordenacao longtext,
        datahoraFim datetime,
        datahoraInicio datetime,
        datahoraPublicacao datetime,
        local longtext,
        obs longtext,
        publicoalvo longtext,
        title longtext,
        autor_id integer,
        primary key (id)
    )

    create table Noticia (
        id integer not null auto_increment,
        datahoraPublicacao datetime,
        mensagem longtext,
        title longtext,
        autor_id integer,
        primary key (id)
    )

    create table Pessoa (
        id integer not null auto_increment,
        email varchar(255),
        login varchar(255),
        nome varchar(255),
        senha varchar(255),
        primary key (id)
    )

    create table Professor (
        id integer not null,
        primary key (id)
    )

    create table Rule (
        id integer not null auto_increment,
        nome varchar(255),
        primary key (id)
    )

    create table disciplina_curso (
        curso_id integer not null,
        disciplina_id integer not null
    )

    create table pessoa_rule (
        pessoa_id integer not null,
        regra_id integer not null
    )

    alter table Aluno 
        add index FK3C6D4CBD4628FFC (curso_id), 
        add constraint FK3C6D4CBD4628FFC 
        foreign key (curso_id) 
        references Curso (id)

    alter table Aluno 
        add index FK3C6D4CBA3D06140 (id), 
        add constraint FK3C6D4CBA3D06140 
        foreign key (id) 
        references Pessoa (id)

    alter table Arquivo 
        add index FK376CAA8DC112AA1C (professor_id), 
        add constraint FK376CAA8DC112AA1C 
        foreign key (professor_id) 
        references Professor (id)

    alter table Arquivo 
        add index FK376CAA8DE1E99AB8 (disciplinaLetiva_id), 
        add constraint FK376CAA8DE1E99AB8 
        foreign key (disciplinaLetiva_id) 
        references DisciplinaLetiva (id)

    alter table DisciplinaLetiva 
        add index FKD97687855EE27FB8 (disciplina_id), 
        add constraint FKD97687855EE27FB8 
        foreign key (disciplina_id) 
        references Disciplina (id)

    alter table DisciplinaLetiva_Aluno 
        add index FKF3B547912D3FF99C (aluno_id), 
        add constraint FKF3B547912D3FF99C 
        foreign key (aluno_id) 
        references Aluno (id)

    alter table DisciplinaLetiva_Aluno 
        add index FKF3B54791E1E99AB8 (DisciplinaLetiva_id), 
        add constraint FKF3B54791E1E99AB8 
        foreign key (DisciplinaLetiva_id) 
        references DisciplinaLetiva (id)

    alter table Evento 
        add index FK7C6CCD35E2630B68 (autor_id), 
        add constraint FK7C6CCD35E2630B68 
        foreign key (autor_id) 
        references Professor (id)

    alter table Noticia 
        add index FKE223DCE5F99F065C (autor_id), 
        add constraint FKE223DCE5F99F065C 
        foreign key (autor_id) 
        references Pessoa (id)

    alter table Professor 
        add index FK3B4FF64FA3D06140 (id), 
        add constraint FK3B4FF64FA3D06140 
        foreign key (id) 
        references Pessoa (id)

    alter table disciplina_curso 
        add index FK2C02BE09D4628FFC (curso_id), 
        add constraint FK2C02BE09D4628FFC 
        foreign key (curso_id) 
        references Curso (id)

    alter table disciplina_curso 
        add index FK2C02BE095EE27FB8 (disciplina_id), 
        add constraint FK2C02BE095EE27FB8 
        foreign key (disciplina_id) 
        references Disciplina (id)

    alter table pessoa_rule 
        add index FK64B6F094B8B40D8 (pessoa_id), 
        add constraint FK64B6F094B8B40D8 
        foreign key (pessoa_id) 
        references Pessoa (id)

    alter table pessoa_rule 
        add index FK64B6F094DD176811 (regra_id), 
        add constraint FK64B6F094DD176811 
        foreign key (regra_id) 
        references Rule (id)