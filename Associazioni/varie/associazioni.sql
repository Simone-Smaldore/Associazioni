
    create table Associazione (
        id int8 not null,
        annoFondazione int4 not null,
        codice varchar(255) unique,
        nome varchar(255),
        primary key (id)
    );

    create table Iscrizione (
        id int8 not null,
        dataIscrizione date,
        associazione_id int8,
        persona_id int8,
        primary key (id)
    );

    create table Persona (
        id int8 not null,
        codiceFiscale varchar(255) unique,
        cognome varchar(255),
        eta int4 not null,
        maschio boolean not null,
        nome varchar(255),
        regione varchar(255),
        primary key (id)
    );

    alter table Iscrizione 
        add constraint FKB85F5287415098FF 
        foreign key (persona_id) 
        references Persona;

    alter table Iscrizione 
        add constraint FKB85F5287CC3B79D5 
        foreign key (associazione_id) 
        references Associazione;

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value int4 
    ) ;
