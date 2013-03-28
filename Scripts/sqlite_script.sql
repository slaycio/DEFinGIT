
CREATE TABLE account_types (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    name varchar(2048) NOT NULL,
    description varchar(2048),
    lang varchar(5),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);




CREATE TABLE accounts (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    org_id integer NOT NULL,
    name varchar(2048) NOT NULL,
    acc_type_id integer NOT NULL,
    symbol varchar(5) NOT NULL,
    description varchar(2048),
    currency_id integer NOT NULL,
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);




CREATE TABLE currencies (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    name varchar(2048) NOT NULL,
    symbol varchar(5) NOT NULL,
    description varchar(2048),
    lang varchar(5),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);




CREATE TABLE org_type_asg (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    org_id integer NOT NULL,
    org_type_id integer NOT NULL,
   creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);



CREATE TABLE organization_types (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    name varchar(2048) NOT NULL,
    description varchar(2048),
    lang varchar(5),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);





CREATE TABLE organizations (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    name varchar(2048) NOT NULL,
    description varchar(2048),
   creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);



CREATE TABLE schedule_groups (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    name varchar(2048) NOT NULL,
    description varchar(2048),
    active varchar(1) NOT NULL,
    lang varchar(5),
   creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);



CREATE TABLE schedule_types (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    name varchar(2048) NOT NULL,
    description varchar(2048),
    lang varchar(5),
   creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);



CREATE TABLE schedules (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    sgr_id integer NOT NULL,
    name varchar(2048) NOT NULL,
    description varchar(2048),
    sch_type_id integer,
    start_date date,
    day_interval integer,
    period_day integer,
    status varchar(50),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE trx_headers (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    org_id integer NOT NULL,
    sch_id integer,
    trx_type_id integer NOT NULL,
    description varchar(2048),
    trx_date date,
    currency_id integer,
    ex_rate_type integer,
    ex_rate numeric(21,5),
    ex_date date,
    amount numeric(21,5),
    status cvarchar(50),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE trx_lines (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    trx_id integer NOT NULL,
    acc_id integer NOT NULL,
    description varchar(2048),
    trx_amount numeric(21,5) NOT NULL,
    base_amount numeric(21,5) NOT NULL,
    dt_ct varchar(2) NOT NULL,
    status varchar(50),
   creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);


CREATE TABLE trx_types (
    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
    name varchar(2048) NOT NULL,
    description varchar(2048),
    lang varchar(5),
    creation_date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL
);





INSERT INTO account_types (name, description, lang) VALUES ('Gotówka','Konto reprezentujące środki zgromadzone poza instutucjami finansowymi','PL');
INSERT INTO account_types (name, description, lang) VALUES ('ROR','Rachunek oszczędnościowo-rozliczeniowy','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Rachunek maklerski','Rachunek maklerski','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Konto oszczędnościowe','Konto oszczędnościowe','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Kredyt hipoteczny','Kredyt hipoteczny','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Kredyt gotówkowy','Kredyt gotówkowy','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Karta kredytowa','Karta kredytowa','PL');



INSERT INTO currencies (name, symbol, description, lang) VALUES ('Złotówka','PLN','Polski złoty','PL');
INSERT INTO currencies (name, symbol, description, lang) VALUES ('Frank szwajcarski','CHF','Frank szwajcarski','PL');
INSERT INTO currencies (name, symbol, description, lang) VALUES ('Dolar','USD','Dolar','PL');
INSERT INTO currencies (name, symbol, description, lang) VALUES ('Euro','EUR','Euro','PL');



INSERT INTO organization_types (name, description, lang) VALUES ('Bank','Bank','PL');
INSERT INTO organization_types (name, description, lang) VALUES ('Organizacja techniczna','Organizacja techniczna','PL');
INSERT INTO organization_types (name, description, lang) VALUES ('Osoba fizyczna','Osoba fizyczna','PL');
INSERT INTO organization_types (name, description, lang) VALUES ('Kontrahent','Kontrahent','PL');



INSERT INTO organizations ( name, description) VALUES ('mBank','mBank');
INSERT INTO organizations ( name, description) VALUES ('Paweł Kosmólski','Paweł Kosmólski');



INSERT INTO accounts (org_id, name, acc_type_id, symbol, description, currency_id) VALUES (1,'eKonto',2,'EKN','eKonto',1);
INSERT INTO accounts (org_id, name, acc_type_id, symbol, description, currency_id) VALUES (2,'Gotówka',1,'GOT','Gotówka',1);



INSERT INTO org_type_asg (org_id, org_type_id) VALUES (1,1);
INSERT INTO org_type_asg (org_id, org_type_id) VALUES (2,3);


--------------------
select * from currencies;

