begin transaction;

insert into Persona(id, codiceFiscale, nome, cognome, eta, regione, maschio) values(1, 'AAA', 'Simone', 'Smaldore', 21, 'Basilicata', true);
insert into Persona(id, codiceFiscale, nome, cognome, eta, regione, maschio) values(2, 'BBB', 'Mario', 'Smaldore', 59, 'Basilicata', true);
insert into Persona(id, codiceFiscale, nome, cognome, eta, regione, maschio) values(3, 'CCC', 'Valentino', 'Smaldore', 24, 'Basilicata', true);
insert into Persona(id, codiceFiscale, nome, cognome, eta, regione, maschio) values(4, 'DDD', 'Erminia', 'Passavanti', 59, 'Puglia', false);


insert into Associazione(id, annoFondazione, codice, nome) values(1, 2017, 'CDC', 'Casa di carta');
insert into Associazione(id, annoFondazione, codice, nome) values(2, 1919, 'SPR', 'Sport');
insert into Associazione(id, annoFondazione, codice, nome) values(3, 1940, 'CUC', 'Cucina');

insert into Iscrizione(id, dataIscrizione, associazione_id, persona_id) values(1,'2018-03-27', 1, 1);
insert into Iscrizione(id, dataIscrizione, associazione_id, persona_id) values(2,'2019-06-25', 1, 2);
insert into Iscrizione(id, dataIscrizione, associazione_id, persona_id) values(3,'2016-07-27', 2, 1);
insert into Iscrizione(id, dataIscrizione, associazione_id, persona_id) values(4,'2015-04-26', 2, 2);
insert into Iscrizione(id, dataIscrizione, associazione_id, persona_id) values(5,'2014-05-23', 2, 3);
insert into Iscrizione(id, dataIscrizione, associazione_id, persona_id) values(6,'2000-06-15', 3, 2);
insert into Iscrizione(id, dataIscrizione, associazione_id, persona_id) values(7,'2001-07-07', 3, 4);

insert into hibernate_sequences values('Associazione', 1);
insert into hibernate_sequences values('Persona', 1);
insert into hibernate_sequences values('Iscrizione', 1);

commit;
