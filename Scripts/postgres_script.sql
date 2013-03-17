-- TEST
/*
DROP SCHEMA public cascade;
CREATE SCHEMA public;
ALTER SCHEMA public OWNER TO postgres;
CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';

*/
select 1;


CREATE TABLE account_types (
    id integer PRIMARY KEY UNIQUE NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.account_types OWNER TO postgres;

CREATE SEQUENCE account_types_acc_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.account_types_acc_type_id_seq OWNER TO postgres;

ALTER SEQUENCE account_types_acc_type_id_seq OWNED BY account_types.id;



CREATE TABLE accounts (
    id integer PRIMARY KEY UNIQUE NOT NULL,
    org_id integer NOT NULL,
    name character varying(2048) NOT NULL,
    acc_type_id integer NOT NULL,
    symbol character varying(5) NOT NULL,
    description character varying(2048),
    currency_id integer NOT NULL,
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.accounts OWNER TO postgres;

CREATE SEQUENCE accounts_acc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.accounts_acc_id_seq OWNER TO postgres;

ALTER SEQUENCE accounts_acc_id_seq OWNED BY accounts.id;



CREATE TABLE currencies (
    id integer PRIMARY KEY UNIQUE NOT NULL,
    name character varying(2048) NOT NULL,
    symbol character varying(5) NOT NULL,
    description character varying(2048),
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.currencies OWNER TO postgres;

CREATE SEQUENCE currencies_currency_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.currencies_currency_id_seq OWNER TO postgres;

ALTER SEQUENCE currencies_currency_id_seq OWNED BY currencies.id;



CREATE TABLE org_type_asg (
    id integer PRIMARY KEY UNIQUE NOT NULL,
    org_id integer NOT NULL,
    org_type_id integer NOT NULL,
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.org_type_asg OWNER TO postgres;

CREATE SEQUENCE org_type_asg_org_asg_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.org_type_asg_org_asg_id_seq OWNER TO postgres;

ALTER SEQUENCE org_type_asg_org_asg_id_seq OWNED BY org_type_asg.id;



CREATE TABLE organization_types (
    id integer PRIMARY KEY UNIQUE NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    lang character varying(5) NOT NULL,
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.organization_types OWNER TO postgres;

CREATE SEQUENCE organization_types_org_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.organization_types_org_type_id_seq OWNER TO postgres;

ALTER SEQUENCE organization_types_org_type_id_seq OWNED BY organization_types.id;



CREATE TABLE organizations (
    id integer PRIMARY KEY UNIQUE  NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.organizations OWNER TO postgres;

CREATE SEQUENCE organizations_org_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.organizations_org_id_seq OWNER TO postgres;

ALTER SEQUENCE organizations_org_id_seq OWNED BY organizations.id;



CREATE TABLE schedule_groups (
    id integer PRIMARY KEY UNIQUE NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    active character varying(1) NOT NULL,
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.schedule_groups OWNER TO postgres;

CREATE SEQUENCE schedule_groups_sgr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.schedule_groups_sgr_id_seq OWNER TO postgres;

ALTER SEQUENCE schedule_groups_sgr_id_seq OWNED BY schedule_groups.id;



CREATE TABLE schedule_types (
   	id integer PRIMARY KEY UNIQUE NOT NULL,
    sch_type_chr character varying(1) NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    active character varying(1) NOT NULL,
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.schedule_types OWNER TO postgres;

CREATE SEQUENCE schedule_types_sch_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.schedule_types_sch_type_id_seq OWNER TO postgres;

ALTER SEQUENCE schedule_types_sch_type_id_seq OWNED BY schedule_types.id;



CREATE TABLE schedules (
    id integer PRIMARY KEY UNIQUE NOT NULL,
    sgr_id integer NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    sch_type_id integer,
    start_date date,
    day_interval integer,
    period_day integer,
    status character varying(50),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.schedules OWNER TO postgres;

CREATE SEQUENCE schedules_sch_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.schedules_sch_id_seq OWNER TO postgres;

ALTER SEQUENCE schedules_sch_id_seq OWNED BY schedules.id;



CREATE TABLE trx_headers (
    id integer PRIMARY KEY UNIQUE NOT NULL,
    org_id integer NOT NULL,
    sch_id integer,
    trx_type_id integer NOT NULL,
    description character varying(2048),
    trx_date date,
    currency_id integer,
    ex_rate_type integer,
    ex_rate numeric(21,5),
    ex_date date,
    amount numeric(21,5),
    status character varying(50),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.trx_headers OWNER TO postgres;

CREATE SEQUENCE trx_headers_trx_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.trx_headers_trx_id_seq OWNER TO postgres;

ALTER SEQUENCE trx_headers_trx_id_seq OWNED BY trx_headers.id;



CREATE TABLE trx_lines (
    id integer PRIMARY KEY UNIQUE NOT NULL,
    trx_id integer NOT NULL,
    acc_id integer NOT NULL,
    description character varying(2048),
    trx_amount numeric(21,5) NOT NULL,
    base_amount numeric(21,5) NOT NULL,
    dt_ct character varying(2) NOT NULL,
    status character varying(50),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.trx_lines OWNER TO postgres;

CREATE SEQUENCE trx_lines_trx_line_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.trx_lines_trx_line_id_seq OWNER TO postgres;


ALTER SEQUENCE trx_lines_trx_line_id_seq OWNED BY trx_lines.id;




CREATE TABLE trx_types (
   id integer PRIMARY KEY UNIQUE NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);

ALTER TABLE public.trx_types OWNER TO postgres;

CREATE SEQUENCE trx_types_trx_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.trx_types_trx_type_id_seq OWNER TO postgres;

ALTER SEQUENCE trx_types_trx_type_id_seq OWNED BY trx_types.id;




ALTER TABLE ONLY account_types ALTER COLUMN id SET DEFAULT nextval('account_types_acc_type_id_seq'::regclass);

ALTER TABLE ONLY accounts ALTER COLUMN id SET DEFAULT nextval('accounts_acc_id_seq'::regclass);

ALTER TABLE ONLY currencies ALTER COLUMN id SET DEFAULT nextval('currencies_currency_id_seq'::regclass);

ALTER TABLE ONLY org_type_asg ALTER COLUMN oid SET DEFAULT nextval('org_type_asg_org_asg_id_seq'::regclass);

ALTER TABLE ONLY organization_types ALTER COLUMN id SET DEFAULT nextval('organization_types_org_type_id_seq'::regclass);

ALTER TABLE ONLY organizations ALTER COLUMN id SET DEFAULT nextval('organizations_org_id_seq'::regclass);

ALTER TABLE ONLY schedule_groups ALTER COLUMN id SET DEFAULT nextval('schedule_groups_sgr_id_seq'::regclass);

ALTER TABLE ONLY schedule_types ALTER COLUMN id SET DEFAULT nextval('schedule_types_sch_type_id_seq'::regclass);

ALTER TABLE ONLY schedules ALTER COLUMN id SET DEFAULT nextval('schedules_sch_id_seq'::regclass);

ALTER TABLE ONLY trx_headers ALTER COLUMN id SET DEFAULT nextval('trx_headers_trx_id_seq'::regclass);

ALTER TABLE ONLY trx_lines ALTER COLUMN id SET DEFAULT nextval('trx_lines_trx_line_id_seq'::regclass);

ALTER TABLE ONLY trx_types ALTER COLUMN id SET DEFAULT nextval('trx_types_trx_type_id_seq'::regclass);



INSERT INTO account_types (name, description, lang) VALUES ('Gotówka','Konto reprezentujące środki zgromadzone poza instutucjami finansowymi','PL');
INSERT INTO account_types (name, description, lang) VALUES ('ROR','Rachunek oszczędnościowo-rozliczeniowy','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Rachunek maklerski','Rachunek maklerski','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Konto oszczędnościowe','Konto oszczędnościowe','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Kredyt hipoteczny','Kredyt hipoteczny','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Kredyt gotówkowy','Kredyt gotówkowy','PL');
INSERT INTO account_types (name, description, lang) VALUES ('Karta kredytowa','Karta kredytowa','PL');


--SELECT pg_catalog.setval('account_types_acc_type_id_seq', 7, true);


INSERT INTO currencies (name, symbol, description, lang) VALUES ('Złotówka','PLN','Polski złoty','PL');
INSERT INTO currencies (name, symbol, description, lang) VALUES ('Frank szwajcarski','CHF','Frank szwajcarski','PL');
INSERT INTO currencies (name, symbol, description, lang) VALUES ('Dolar','USD','Dolar','PL');
INSERT INTO currencies (name, symbol, description, lang) VALUES ('Euro','EUR','Euro','PL');


--SELECT pg_catalog.setval('currencies_currency_id_seq', 4, true);



INSERT INTO organization_types (name, description, lang) VALUES ('Bank','Bank','PL');
INSERT INTO organization_types (name, description, lang) VALUES ('Organizacja techniczna','Organizacja techniczna','PL');
INSERT INTO organization_types (name, description, lang) VALUES ('Osoba fizyczna','Osoba fizyczna','PL');
INSERT INTO organization_types (name, description, lang) VALUES ('Kontrahent','Kontrahent','PL');


--SELECT pg_catalog.setval('organization_types_org_type_id_seq', 4, true);



INSERT INTO organizations ( name, description) VALUES ('mBank','mBank');
INSERT INTO organizations ( name, description) VALUES ('Paweł Kosmólski','Paweł Kosmólski');


--SELECT pg_catalog.setval('organizations_org_id_seq', 2, true);




INSERT INTO accounts (org_id, name, acc_type_id, symbol, description, currency_id) VALUES (1,'eKonto',2,'EKN','eKonto',1);
INSERT INTO accounts (org_id, name, acc_type_id, symbol, description, currency_id) VALUES (2,'Gotówka',1,'GOT','Gotówka',1);


--SELECT pg_catalog.setval('accounts_acc_id_seq', 4, true);



INSERT INTO org_type_asg (org_id, org_type_id) VALUES (1,1);
INSERT INTO org_type_asg (org_id, org_type_id) VALUES (2,3);

--SELECT pg_catalog.setval('org_type_asg_org_asg_id_seq', 2, true);
COMMIT;

/*
ALTER TABLE ONLY account_types
    ADD CONSTRAINT account_types_pkey PRIMARY KEY (id);



ALTER TABLE ONLY accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (id);



ALTER TABLE ONLY currencies
    ADD CONSTRAINT currencies_pkey PRIMARY KEY (id);


ALTER TABLE ONLY org_type_asg
    ADD CONSTRAINT org_type_asg_pkey PRIMARY KEY (id);


ALTER TABLE ONLY organization_types
    ADD CONSTRAINT organization_types_pkey PRIMARY KEY (id);



ALTER TABLE ONLY organizations
    ADD CONSTRAINT organizations_pkey PRIMARY KEY (id);



ALTER TABLE ONLY schedule_groups
    ADD CONSTRAINT schedule_groups_pkey PRIMARY KEY (id);



ALTER TABLE ONLY schedule_types
    ADD CONSTRAINT schedule_types_pkey PRIMARY KEY (id);

ALTER TABLE ONLY schedules
    ADD CONSTRAINT schedules_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trx_headers
    ADD CONSTRAINT trx_headers_pkey PRIMARY KEY (id);


ALTER TABLE ONLY trx_lines
    ADD CONSTRAINT trx_lines_pkey PRIMARY KEY (id);

ALTER TABLE ONLY trx_types
    ADD CONSTRAINT trx_types_pkey PRIMARY KEY (id);
*/


REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;

