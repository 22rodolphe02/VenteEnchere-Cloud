\c postgres;

drop database enchere;

create database enchere;

\c enchere;

drop table admin cascade ;
drop table client cascade ;
drop table mouvementSolde cascade ;
drop table solde cascade ;
drop table categorie cascade ;
drop table enchere cascade ;
drop table offre cascade ;
drop table vendu cascade ;

create table admin (
                       id serial primary key ,
                       email varchar(50) not null unique ,
                       mdp varchar(100) not null ,
                       solde double precision default 0
);
insert into admin (email, mdp) values ('admin@gmail.com', 'admin');

create table client (
                        id serial primary key ,
                        nom varchar(50),
                        prenom varchar(50),
                        email varchar(50),
                        mdp varchar(100)
);
insert into client (nom, prenom, email, mdp) values
                                                 ('Eric', 'Georges', 'eric@gmail.com', 'eric'),
                                                 ('Rodolphe', 'Tsiafara', 'rodolphe@gmail.com', 'rodolphe'),
                                                 ('Hary', 'Lovanomena', 'hary@gmail.com', 'hary'),
                                                 ('Billy', 'Marley', 'billy@gmail.com', 'billy');

create table mouvementSolde (
                                id serial primary key ,
                                nom varchar(50) not null unique
);
insert into mouvementSolde (nom) values
                                     ('depot'),
                                     ('retrait');

create table solde (
                       id serial primary key ,
                       idClient int not null ,
                       idMouvementSolde int not null ,
                       montant double precision default 0,
                       valider boolean default false
);
insert into solde (idClient, idMouvementSolde, montant) values
                                                            (1, 1, 500000),
                                                            (3, 1, 500000),
                                                            (4, 1, 700000),
                                                            (2, 1, 600000),
                                                            (3, 1, 5500000),
                                                            (4, 1, 7070000);


create table categorie (
                           id serial primary key ,
                           categorie varchar(50) not null unique
);
insert into categorie (categorie) values
                                      ('music'),
                                      ('art'),
                                      ('moto'),
                                      ('voiture'),
                                      ('football'),
                                      ('jeux');

create table enchere (
                         id serial primary key ,
                         produit varchar(100) not null ,
                         description text not null ,
                         prixMin double precision not null ,
                         dateDebut timestamp not null default now(),
                         duree time not null ,
                         idCategorie int not null ,
                         idClient int not null ,
                         fini boolean default false
);
alter table enchere add foreign key (idCategorie) references categorie(id);
alter table enchere add foreign key (idClient) references client(id);


insert into enchere (produit, description, prixMin, dateDebut, duree, idCategorie, idClient) values
                                                                                                 ('Maillot', 'lionnel messi', 2000, '2023-01-24 06:00:00', '10:00:00', 5, 1),
                                                                                                 ('Ballon football', 'Coupe du monde 2014', 1000, '2023-01-24 10:00:00', '08:00:00', 5, 2),
                                                                                                 ('Guitare', 'guitare base', 500, '2023-01-23 14:00:00', '05:00:00', 1, 1),
                                                                                                 ('Peinture', 'Couleur varie', 1500, '2023-01-25 07:00:00', '06:00:00', 2, 3);
insert into enchere (produit, description, prixMin,dateDebut, duree, idCategorie, idClient) values
                                                                                                ('Jocande',          'Un tableau de Leonard de Vinci',                      10000,      '2023-02-03 11:15:00',  '06:15:00', 2, 2),
                                                                                                ('Album PLATINE',    'Les chansons de Rihana',                              75,         '2023-02-03 12:31:00',  '05:30:00', 1, 2),
                                                                                                ('Album OR',         'Les chansons de Rihana',                              80,         '2023-02-03 09:42:00',  '06:10:00', 1, 1),
                                                                                                ('Maillot de messi',          'Coupe du monde 2014',                        100,        '2023-02-03 10:23:00',  '05:30:00', 5, 3),
                                                                                                ('Maillot de CR7',          'Coupe du monde 2014',                          112,        '2023-02-03 18:10:00',  '05:30:00', 5, 3),
                                                                                                ('Ferrari 960',          'Champion du grand 8',                             120000,     '2023-02-03 20:52:00',  '04:15:00', 4, 1),
                                                                                                ('Maillot de Zidane',          'Coupe du monde 1995',                       2000,       '2023-02-04 07:04:00',  '07:45:00', 5, 2),
                                                                                                ('Royals midigthon', 'Moto pour le Roading',                                5000,       '2023-02-04 21:16:00',  '07:00:00', 3, 3),
                                                                                                ('Porsche 4001',          'Le porsche de Grantorino',                       132000,     '2023-02-04 19:15:00',  '04:00:00', 4, 1),
                                                                                                ('Statue de bronze', 'Statue de Zeus en bronze',                            30000,      '2023-02-04 11:38:00',  '06:30:00', 2, 3),
                                                                                                ('Harlinton 1998',   'Moto de Elvis Presley',                               15000,      '2023-02-04 08:24:00',  '09:15:00', 3, 1),
                                                                                                ('Nitendo switch serie 3',   'Console de jeu portable 2015',                220,        '2023-02-04 06:22:00',  '10:20:00', 6, 3),
                                                                                                ('Lamborghini seie X',      'Voiture de sport',                             100000,     '2023-02-04 12:39:00',  '08:15:00', 4, 2),
                                                                                                ('Xbox series X',    'Console de jeu puissant par Microsoft',               500,        '2023-02-05 13:46:00',  '11:10:00', 6, 1),
                                                                                                ('Le jardin perdu',          'Un tableau de Picasso',                       32000,      '2023-02-05 11:15:00',  '06:15:00', 2, 2),
                                                                                                ('Album redemption',    'Les chansons de Maitre Gims',                      120,        '2023-02-05 12:31:00',  '05:30:00', 1, 2),
                                                                                                ('Album XOX',         'Les chansons de Ed Sheeran',                         65,         '2023-02-06 09:42:00',  '06:10:00', 1, 1),
                                                                                                ('Maillot de Jordan',          'NBA 2010',                                  300,        '2023-02-06 10:23:00',  '05:30:00', 5, 3),
                                                                                                ('Maillot de Curry',          'NBA 2019',                                   112,        '2023-02-06 18:10:00',  '05:30:00', 5, 3),
                                                                                                ('Ferrari Jaguar',          'Champion du grand 8 2020',                     120000,     '2023-02-06 20:52:00',  '04:15:00', 4, 1),
                                                                                                ('Maillot de Federer',          'Championnat golf 2018',                    96,         '2023-02-06 07:04:00',  '07:45:00', 5, 2),
                                                                                                ('Royals Alsy ', 'Moto pour le Roading X',                                  5000,       '2023-02-07 21:16:00',  '07:00:00', 3, 3),
                                                                                                ('Porsche serie Alpha',          'Le porsche de Grantorino',                132000,     '2023-02-07 19:15:00',  '04:00:00', 4, 1),
                                                                                                ('Ile rouge', 'Illustration de Bentolini',                                  30000,      '2023-02-07 11:38:00',  '06:30:00', 2, 3),
                                                                                                ('Neg14 20',   'Moto de Chuck Norris',                                      15000,      '2023-02-07 08:24:00',  '09:15:00', 3, 1),
                                                                                                ('Nitendo switch serie Zelda',   'Console de jeu portable',                 220,        '2023-02-07 06:22:00',  '10:20:00', 6, 3),
                                                                                                ('Lamborghini noir matte',      'Voiture de sport',                         100000,     '2023-02-08 12:39:00',  '08:15:00', 4, 2),
                                                                                                ('Xbox series X 360 turbo',    'Console de jeu puissant par Microsoft',     500,        '2023-02-09 13:46:00',  '11:10:00', 6, 1)
;

-- select ((dateDebut+duree) - now()) as dureeRestant from enchere;

create table offre (
                       id serial primary key ,
                       idEnchere int not null ,
                       idClient int not null ,
                       date timestamp default now() ,
                       montant double precision not null
);
alter table offre add foreign key (idClient) references client(id);
alter table offre add foreign key (idEnchere) references enchere(id);
insert into offre (idEnchere, idClient, date, montant) values
                                                           (3, 2, '2023-01-23 09:00:00', 1000),
                                                           (3, 3, '2023-01-23 09:05:00', 2000),
                                                           (3, 4, '2023-01-23 09:10:00', 3000),
                                                           (1, 2, '2023-01-24 09:00:00', 1000),
                                                           (1, 3, '2023-01-24 09:05:00', 2000),
                                                           (1, 4, '2023-01-24 09:10:00', 3000),
                                                           (2, 2, '2023-01-24 09:00:00', 1000),
                                                           (2, 3, '2023-01-24 09:05:00', 2000),
                                                           (2, 4, '2023-01-24 09:10:00', 3000);


create table vendu (
    idOffre int not null unique
);
alter table vendu add foreign key (idOffre) references offre(id);
/*insert into vendu values
                      (3);*/





-- select ((dateDebut+duree) - now()) as dureeRestant from enchere order by id asc;



-- drop table teste;
-- create table teste (
--     id serial primary key ,
--     nom varchar(10) not null
-- );

CREATE TABLE token(
                      id SERIAL PRIMARY KEY ,
                      token VARCHAR(256) NOT NULL,
                      datecreation TIMESTAMP NOT NULL DEFAULT NOW(),
                      dateexpiration TIMESTAMP NOT NULL
);

CREATE TABLE token_user(
                           id SERIAL PRIMARY KEY,
                           id_token INT NOT NULL ,
                           id_admin INT,
                           id_user INT,
                           FOREIGN KEY (id_token) REFERENCES token(id),
                           FOREIGN KEY (id_admin) REFERENCES admin(id),
                           FOREIGN KEY (id_user) REFERENCES client(id)
);

-- SELECT CASE when dateFin > CURRENT_TIMESTAMP THEN 'En cours' when dateFin<CURRENT_TIMESTAMP THEN 'Expire' END as delaiVente FROM Vente WHERE id = '" + this.id + "'"

