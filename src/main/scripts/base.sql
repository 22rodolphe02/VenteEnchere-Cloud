\c postgres;

drop database enchere;

create database enchere;

\c enchere;

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
    idClient int not null ,
    idMouvementSolde int not null ,
    montant double precision default 0,
    valider boolean default false
);
insert into solde (idClient, idMouvementSolde, montant) values
                                                            (1, 1, 100000),
                                                            (3, 1, 300000),
                                                            (4, 1, 400000);


create table categorie (
    id serial primary key ,
    categorie varchar(50) not null unique
);
insert into categorie (categorie) values
                                      ('music'),
                                      ('art'),
                                      ('moto'),
                                      ('voiture'),
                                      ('football');


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
    ('Maillot', 'lionnel messi', 2000, '2023-01-18 06:00:00', '02:30:00', 5, 1),
    ('Ballon football', 'Coupe du monde 2014', 1000, '2023-01-18 07:00:00', '02:30:30', 5, 1);


-- select ((dateDebut+duree) - now()) as dureeRestant from enchere;


create table offre (
    id serial primary key ,
    idEnchere int not null ,
    idClient int not null ,
    date timestamp default now() ,
    montant double precision
);
alter table offre add foreign key (idClient) references client(id);
alter table offre add foreign key (idEnchere) references enchere(id);
insert into offre (idEnchere, idClient, date, montant) values
                                                         (1, 2, '2023-01-01 09:00:00', 1000),
                                                         (1, 3, '2023-01-01 09:05:00', 2000),
                                                         (1, 4, '2023-01-01 09:10:00', 3000);

create table vendu (
    idEnchere int not null ,
    idOffre int not null
);
alter table vendu add foreign key (idOffre) references offre(id);
alter table vendu add foreign key (idEnchere) references enchere(id);





select ((dateDebut+duree) - now()) as dureeRestant from enchere order by id asc;



-- SELECT CASE when dateFin > CURRENT_TIMESTAMP THEN 'En cours' when dateFin<CURRENT_TIMESTAMP THEN 'Expire' END as delaiVente FROM Vente WHERE id = '" + this.id + "'"

