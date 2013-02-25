--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.3
-- Dumped by pg_dump version 9.2.3
-- Started on 2013-02-18 17:24:21

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE "DEF";
--
-- TOC entry 2095 (class 1262 OID 24576)
-- Name: DEF; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "DEF" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Polish_Poland.1250' LC_CTYPE = 'Polish_Poland.1250';


ALTER DATABASE "DEF" OWNER TO postgres;

\connect "DEF"

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 6 (class 2615 OID 25629)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 192 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2097 (class 0 OID 0)
-- Dependencies: 192
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 169 (class 1259 OID 25632)
-- Name: account_types; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE account_types (
    acc_type_id integer NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.account_types OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 25630)
-- Name: account_types_acc_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE account_types_acc_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.account_types_acc_type_id_seq OWNER TO postgres;

--
-- TOC entry 2098 (class 0 OID 0)
-- Dependencies: 168
-- Name: account_types_acc_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE account_types_acc_type_id_seq OWNED BY account_types.acc_type_id;


--
-- TOC entry 171 (class 1259 OID 25644)
-- Name: accounts; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE accounts (
    acc_id integer NOT NULL,
    org_id integer NOT NULL,
    name character varying(2048) NOT NULL,
    acc_type_id integer NOT NULL,
    symbol character varying(5) NOT NULL,
    description character varying(2048),
    currency_id integer NOT NULL,
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.accounts OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 25642)
-- Name: accounts_acc_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE accounts_acc_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.accounts_acc_id_seq OWNER TO postgres;

--
-- TOC entry 2099 (class 0 OID 0)
-- Dependencies: 170
-- Name: accounts_acc_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE accounts_acc_id_seq OWNED BY accounts.acc_id;


--
-- TOC entry 173 (class 1259 OID 25656)
-- Name: currencies; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE currencies (
    currency_id integer NOT NULL,
    name character varying(2048) NOT NULL,
    symbol character varying(5) NOT NULL,
    description character varying(2048),
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.currencies OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 25654)
-- Name: currencies_currency_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE currencies_currency_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.currencies_currency_id_seq OWNER TO postgres;

--
-- TOC entry 2100 (class 0 OID 0)
-- Dependencies: 172
-- Name: currencies_currency_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE currencies_currency_id_seq OWNED BY currencies.currency_id;


--
-- TOC entry 191 (class 1259 OID 25851)
-- Name: org_type_asg; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE org_type_asg (
    org_asg_id integer NOT NULL,
    org_id integer NOT NULL,
    org_type_id integer NOT NULL,
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.org_type_asg OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 25849)
-- Name: org_type_asg_org_asg_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE org_type_asg_org_asg_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.org_type_asg_org_asg_id_seq OWNER TO postgres;

--
-- TOC entry 2101 (class 0 OID 0)
-- Dependencies: 190
-- Name: org_type_asg_org_asg_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE org_type_asg_org_asg_id_seq OWNED BY org_type_asg.org_asg_id;


--
-- TOC entry 175 (class 1259 OID 25668)
-- Name: organization_types; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organization_types (
    org_type_id integer NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    lang character varying(5) NOT NULL,
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.organization_types OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 25666)
-- Name: organization_types_org_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE organization_types_org_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organization_types_org_type_id_seq OWNER TO postgres;

--
-- TOC entry 2102 (class 0 OID 0)
-- Dependencies: 174
-- Name: organization_types_org_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE organization_types_org_type_id_seq OWNED BY organization_types.org_type_id;


--
-- TOC entry 177 (class 1259 OID 25680)
-- Name: organizations; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organizations (
    org_id integer NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.organizations OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 25678)
-- Name: organizations_org_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE organizations_org_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.organizations_org_id_seq OWNER TO postgres;

--
-- TOC entry 2103 (class 0 OID 0)
-- Dependencies: 176
-- Name: organizations_org_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE organizations_org_id_seq OWNED BY organizations.org_id;


--
-- TOC entry 179 (class 1259 OID 25692)
-- Name: schedule_groups; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE schedule_groups (
    sgr_id integer NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    active character varying(1) NOT NULL,
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.schedule_groups OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 25690)
-- Name: schedule_groups_sgr_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE schedule_groups_sgr_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.schedule_groups_sgr_id_seq OWNER TO postgres;

--
-- TOC entry 2104 (class 0 OID 0)
-- Dependencies: 178
-- Name: schedule_groups_sgr_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE schedule_groups_sgr_id_seq OWNED BY schedule_groups.sgr_id;


--
-- TOC entry 181 (class 1259 OID 25704)
-- Name: schedule_types; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE schedule_types (
    sch_type_id integer NOT NULL,
    sch_type_chr character varying(1) NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    active character varying(1) NOT NULL,
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.schedule_types OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 25702)
-- Name: schedule_types_sch_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE schedule_types_sch_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.schedule_types_sch_type_id_seq OWNER TO postgres;

--
-- TOC entry 2105 (class 0 OID 0)
-- Dependencies: 180
-- Name: schedule_types_sch_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE schedule_types_sch_type_id_seq OWNED BY schedule_types.sch_type_id;


--
-- TOC entry 183 (class 1259 OID 25716)
-- Name: schedules; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE schedules (
    sch_id integer NOT NULL,
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

--
-- TOC entry 182 (class 1259 OID 25714)
-- Name: schedules_sch_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE schedules_sch_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.schedules_sch_id_seq OWNER TO postgres;

--
-- TOC entry 2106 (class 0 OID 0)
-- Dependencies: 182
-- Name: schedules_sch_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE schedules_sch_id_seq OWNED BY schedules.sch_id;


--
-- TOC entry 185 (class 1259 OID 25728)
-- Name: trx_headers; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE trx_headers (
    trx_id integer NOT NULL,
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

--
-- TOC entry 184 (class 1259 OID 25726)
-- Name: trx_headers_trx_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE trx_headers_trx_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.trx_headers_trx_id_seq OWNER TO postgres;

--
-- TOC entry 2107 (class 0 OID 0)
-- Dependencies: 184
-- Name: trx_headers_trx_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE trx_headers_trx_id_seq OWNED BY trx_headers.trx_id;


--
-- TOC entry 187 (class 1259 OID 25740)
-- Name: trx_lines; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE trx_lines (
    trx_line_id integer NOT NULL,
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

--
-- TOC entry 186 (class 1259 OID 25738)
-- Name: trx_lines_trx_line_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE trx_lines_trx_line_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.trx_lines_trx_line_id_seq OWNER TO postgres;

--
-- TOC entry 2108 (class 0 OID 0)
-- Dependencies: 186
-- Name: trx_lines_trx_line_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE trx_lines_trx_line_id_seq OWNED BY trx_lines.trx_line_id;


--
-- TOC entry 189 (class 1259 OID 25752)
-- Name: trx_types; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE trx_types (
    trx_type_id integer NOT NULL,
    name character varying(2048) NOT NULL,
    description character varying(2048),
    lang character varying(5),
    creation_date timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.trx_types OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 25750)
-- Name: trx_types_trx_type_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE trx_types_trx_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.trx_types_trx_type_id_seq OWNER TO postgres;

--
-- TOC entry 2109 (class 0 OID 0)
-- Dependencies: 188
-- Name: trx_types_trx_type_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE trx_types_trx_type_id_seq OWNED BY trx_types.trx_type_id;


--
-- TOC entry 1993 (class 2604 OID 25635)
-- Name: acc_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY account_types ALTER COLUMN acc_type_id SET DEFAULT nextval('account_types_acc_type_id_seq'::regclass);


--
-- TOC entry 1995 (class 2604 OID 25647)
-- Name: acc_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accounts ALTER COLUMN acc_id SET DEFAULT nextval('accounts_acc_id_seq'::regclass);


--
-- TOC entry 1997 (class 2604 OID 25659)
-- Name: currency_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY currencies ALTER COLUMN currency_id SET DEFAULT nextval('currencies_currency_id_seq'::regclass);


--
-- TOC entry 2015 (class 2604 OID 25854)
-- Name: org_asg_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY org_type_asg ALTER COLUMN org_asg_id SET DEFAULT nextval('org_type_asg_org_asg_id_seq'::regclass);


--
-- TOC entry 1999 (class 2604 OID 25671)
-- Name: org_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organization_types ALTER COLUMN org_type_id SET DEFAULT nextval('organization_types_org_type_id_seq'::regclass);


--
-- TOC entry 2001 (class 2604 OID 25683)
-- Name: org_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organizations ALTER COLUMN org_id SET DEFAULT nextval('organizations_org_id_seq'::regclass);


--
-- TOC entry 2003 (class 2604 OID 25695)
-- Name: sgr_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule_groups ALTER COLUMN sgr_id SET DEFAULT nextval('schedule_groups_sgr_id_seq'::regclass);


--
-- TOC entry 2005 (class 2604 OID 25707)
-- Name: sch_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedule_types ALTER COLUMN sch_type_id SET DEFAULT nextval('schedule_types_sch_type_id_seq'::regclass);


--
-- TOC entry 2007 (class 2604 OID 25719)
-- Name: sch_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedules ALTER COLUMN sch_id SET DEFAULT nextval('schedules_sch_id_seq'::regclass);


--
-- TOC entry 2009 (class 2604 OID 25731)
-- Name: trx_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY trx_headers ALTER COLUMN trx_id SET DEFAULT nextval('trx_headers_trx_id_seq'::regclass);


--
-- TOC entry 2011 (class 2604 OID 25743)
-- Name: trx_line_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY trx_lines ALTER COLUMN trx_line_id SET DEFAULT nextval('trx_lines_trx_line_id_seq'::regclass);


--
-- TOC entry 2013 (class 2604 OID 25755)
-- Name: trx_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY trx_types ALTER COLUMN trx_type_id SET DEFAULT nextval('trx_types_trx_type_id_seq'::regclass);


--
-- TOC entry 2068 (class 0 OID 25632)
-- Dependencies: 169
-- Data for Name: account_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY account_types (acc_type_id, name, description, lang, creation_date) FROM stdin;
1	Gotówka	Konto reprezentuj¹ce œrodki zgromadzone poza instutucjami finansowymi	PL	2013-02-17 16:29:07.113
2	ROR	Rachunek oszczêdnoœciowo-rozliczeniowy	PL	2013-02-17 16:30:09.868
3	Rachunek maklerski	Rachunek maklerski	PL	2013-02-17 16:30:59.309
4	Konto oszczêdnoœciowe	Konto oszczêdnoœciowe	PL	2013-02-17 16:31:32.624
5	Kredyt hipoteczny	Kredyt hipoteczny	PL	2013-02-17 16:31:54.669
6	Kredyt gotówkowy	Kredyt gotówkowy	PL	2013-02-17 16:47:50.343
7	Karta kredytowa	Karta kredytowa	PL	2013-02-17 16:48:00.767
\.


--
-- TOC entry 2110 (class 0 OID 0)
-- Dependencies: 168
-- Name: account_types_acc_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('account_types_acc_type_id_seq', 7, true);


--
-- TOC entry 2070 (class 0 OID 25644)
-- Dependencies: 171
-- Data for Name: accounts; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY accounts (acc_id, org_id, name, acc_type_id, symbol, description, currency_id, creation_date) FROM stdin;
2	1	eKonto	2	EKN	eKonto	1	2013-02-17 16:44:39.174
4	2	Gotówka	1	GOT	Gotówka	1	2013-02-17 16:46:19.314
\.


--
-- TOC entry 2111 (class 0 OID 0)
-- Dependencies: 170
-- Name: accounts_acc_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('accounts_acc_id_seq', 4, true);


--
-- TOC entry 2072 (class 0 OID 25656)
-- Dependencies: 173
-- Data for Name: currencies; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY currencies (currency_id, name, symbol, description, lang, creation_date) FROM stdin;
1	Z³otówka	PLN	Polski z³oty	PL	2013-02-17 16:33:51.945
2	Frank szwajcarski	CHF	Frank szwajcarski	PL	2013-02-17 16:34:18.213
3	Dolar	USD	Dolar amerykañski	PL	2013-02-17 16:34:39.642
4	Euro	EUR	Euro	PL	2013-02-17 16:34:55.078
\.


--
-- TOC entry 2112 (class 0 OID 0)
-- Dependencies: 172
-- Name: currencies_currency_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('currencies_currency_id_seq', 4, true);


--
-- TOC entry 2090 (class 0 OID 25851)
-- Dependencies: 191
-- Data for Name: org_type_asg; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY org_type_asg (org_asg_id, org_id, org_type_id, creation_date) FROM stdin;
1	1	1	2013-02-18 17:21:09.052
2	2	3	2013-02-18 17:21:16.876
\.


--
-- TOC entry 2113 (class 0 OID 0)
-- Dependencies: 190
-- Name: org_type_asg_org_asg_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('org_type_asg_org_asg_id_seq', 2, true);


--
-- TOC entry 2074 (class 0 OID 25668)
-- Dependencies: 175
-- Data for Name: organization_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY organization_types (org_type_id, name, description, lang, creation_date) FROM stdin;
1	Bank	Bank	PL	2013-02-17 16:39:24.447
2	Organizacja techniczna	Organizacja techniczna	PL	2013-02-17 16:40:20.959
3	Osoba fizyczna	Osoba fizyczna	PL	2013-02-17 16:40:50.669
4	Kontrahent	Kontrahent	PL	2013-02-17 16:41:26.408
\.


--
-- TOC entry 2114 (class 0 OID 0)
-- Dependencies: 174
-- Name: organization_types_org_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('organization_types_org_type_id_seq', 4, true);


--
-- TOC entry 2076 (class 0 OID 25680)
-- Dependencies: 177
-- Data for Name: organizations; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY organizations (org_id, name, description, creation_date) FROM stdin;
1	mBank	mBank	2013-02-17 16:42:22.438
2	Pawe³ Kosmólski	Pawe³ Kosmólski	2013-02-17 16:45:34.472
\.


--
-- TOC entry 2115 (class 0 OID 0)
-- Dependencies: 176
-- Name: organizations_org_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('organizations_org_id_seq', 2, true);


--
-- TOC entry 2078 (class 0 OID 25692)
-- Dependencies: 179
-- Data for Name: schedule_groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY schedule_groups (sgr_id, name, description, active, lang, creation_date) FROM stdin;
\.


--
-- TOC entry 2116 (class 0 OID 0)
-- Dependencies: 178
-- Name: schedule_groups_sgr_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('schedule_groups_sgr_id_seq', 1, false);


--
-- TOC entry 2080 (class 0 OID 25704)
-- Dependencies: 181
-- Data for Name: schedule_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY schedule_types (sch_type_id, sch_type_chr, name, description, active, lang, creation_date) FROM stdin;
\.


--
-- TOC entry 2117 (class 0 OID 0)
-- Dependencies: 180
-- Name: schedule_types_sch_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('schedule_types_sch_type_id_seq', 1, false);


--
-- TOC entry 2082 (class 0 OID 25716)
-- Dependencies: 183
-- Data for Name: schedules; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY schedules (sch_id, sgr_id, name, description, sch_type_id, start_date, day_interval, period_day, status, creation_date) FROM stdin;
\.


--
-- TOC entry 2118 (class 0 OID 0)
-- Dependencies: 182
-- Name: schedules_sch_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('schedules_sch_id_seq', 1, false);


--
-- TOC entry 2084 (class 0 OID 25728)
-- Dependencies: 185
-- Data for Name: trx_headers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY trx_headers (trx_id, org_id, sch_id, trx_type_id, description, trx_date, currency_id, ex_rate_type, ex_rate, ex_date, amount, status, creation_date) FROM stdin;
\.


--
-- TOC entry 2119 (class 0 OID 0)
-- Dependencies: 184
-- Name: trx_headers_trx_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('trx_headers_trx_id_seq', 1, false);


--
-- TOC entry 2086 (class 0 OID 25740)
-- Dependencies: 187
-- Data for Name: trx_lines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY trx_lines (trx_line_id, trx_id, acc_id, description, trx_amount, base_amount, dt_ct, status, creation_date) FROM stdin;
\.


--
-- TOC entry 2120 (class 0 OID 0)
-- Dependencies: 186
-- Name: trx_lines_trx_line_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('trx_lines_trx_line_id_seq', 1, false);


--
-- TOC entry 2088 (class 0 OID 25752)
-- Dependencies: 189
-- Data for Name: trx_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY trx_types (trx_type_id, name, description, lang, creation_date) FROM stdin;
\.


--
-- TOC entry 2121 (class 0 OID 0)
-- Dependencies: 188
-- Name: trx_types_trx_type_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('trx_types_trx_type_id_seq', 1, false);


--
-- TOC entry 2018 (class 2606 OID 25641)
-- Name: account_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY account_types
    ADD CONSTRAINT account_types_pkey PRIMARY KEY (acc_type_id);


--
-- TOC entry 2020 (class 2606 OID 25653)
-- Name: accounts_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY accounts
    ADD CONSTRAINT accounts_pkey PRIMARY KEY (acc_id);


--
-- TOC entry 2025 (class 2606 OID 25665)
-- Name: currencies_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY currencies
    ADD CONSTRAINT currencies_pkey PRIMARY KEY (currency_id);


--
-- TOC entry 2053 (class 2606 OID 25857)
-- Name: org_type_asg_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY org_type_asg
    ADD CONSTRAINT org_type_asg_pkey PRIMARY KEY (org_asg_id);


--
-- TOC entry 2027 (class 2606 OID 25841)
-- Name: organization_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organization_types
    ADD CONSTRAINT organization_types_pkey PRIMARY KEY (org_type_id);


--
-- TOC entry 2029 (class 2606 OID 25689)
-- Name: organizations_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organizations
    ADD CONSTRAINT organizations_pkey PRIMARY KEY (org_id);


--
-- TOC entry 2031 (class 2606 OID 25701)
-- Name: schedule_groups_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY schedule_groups
    ADD CONSTRAINT schedule_groups_pkey PRIMARY KEY (sgr_id);


--
-- TOC entry 2033 (class 2606 OID 25713)
-- Name: schedule_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY schedule_types
    ADD CONSTRAINT schedule_types_pkey PRIMARY KEY (sch_type_id);


--
-- TOC entry 2037 (class 2606 OID 25725)
-- Name: schedules_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY schedules
    ADD CONSTRAINT schedules_pkey PRIMARY KEY (sch_id);


--
-- TOC entry 2043 (class 2606 OID 25737)
-- Name: trx_headers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY trx_headers
    ADD CONSTRAINT trx_headers_pkey PRIMARY KEY (trx_id);


--
-- TOC entry 2047 (class 2606 OID 25749)
-- Name: trx_lines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY trx_lines
    ADD CONSTRAINT trx_lines_pkey PRIMARY KEY (trx_line_id);


--
-- TOC entry 2049 (class 2606 OID 25761)
-- Name: trx_types_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY trx_types
    ADD CONSTRAINT trx_types_pkey PRIMARY KEY (trx_type_id);


--
-- TOC entry 2021 (class 1259 OID 25762)
-- Name: fki_acc_currency_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_acc_currency_id_fk ON accounts USING btree (currency_id);


--
-- TOC entry 2044 (class 1259 OID 25763)
-- Name: fki_acc_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_acc_id_fk ON trx_lines USING btree (acc_id);


--
-- TOC entry 2022 (class 1259 OID 25764)
-- Name: fki_acc_org_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_acc_org_id_fk ON accounts USING btree (org_id);


--
-- TOC entry 2023 (class 1259 OID 25765)
-- Name: fki_acc_type_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_acc_type_id_fk ON accounts USING btree (acc_type_id);


--
-- TOC entry 2050 (class 1259 OID 25863)
-- Name: fki_asg_org_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_asg_org_id_fk ON org_type_asg USING btree (org_id);


--
-- TOC entry 2051 (class 1259 OID 25869)
-- Name: fki_asg_org_type_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_asg_org_type_id_fk ON org_type_asg USING btree (org_type_id);


--
-- TOC entry 2038 (class 1259 OID 25766)
-- Name: fki_currency_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_currency_id_fk ON trx_headers USING btree (currency_id);


--
-- TOC entry 2039 (class 1259 OID 25767)
-- Name: fki_org_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_org_id_fk ON trx_headers USING btree (org_id);


--
-- TOC entry 2034 (class 1259 OID 25769)
-- Name: fki_sch_type_id; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_sch_type_id ON schedules USING btree (sch_type_id);


--
-- TOC entry 2040 (class 1259 OID 25770)
-- Name: fki_schd_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_schd_id_fk ON trx_headers USING btree (sch_id);


--
-- TOC entry 2035 (class 1259 OID 25771)
-- Name: fki_sgr_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_sgr_id_fk ON schedules USING btree (sgr_id);


--
-- TOC entry 2045 (class 1259 OID 25772)
-- Name: fki_trx_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_trx_id_fk ON trx_lines USING btree (trx_id);


--
-- TOC entry 2041 (class 1259 OID 25773)
-- Name: fki_trx_type_id_fk; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX fki_trx_type_id_fk ON trx_headers USING btree (trx_type_id);


--
-- TOC entry 2054 (class 2606 OID 25774)
-- Name: acc_currency_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accounts
    ADD CONSTRAINT acc_currency_id_fk FOREIGN KEY (currency_id) REFERENCES currencies(currency_id);


--
-- TOC entry 2063 (class 2606 OID 25779)
-- Name: acc_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY trx_lines
    ADD CONSTRAINT acc_id_fk FOREIGN KEY (acc_id) REFERENCES accounts(acc_id);


--
-- TOC entry 2055 (class 2606 OID 25784)
-- Name: acc_org_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accounts
    ADD CONSTRAINT acc_org_id_fk FOREIGN KEY (org_id) REFERENCES organizations(org_id);


--
-- TOC entry 2056 (class 2606 OID 25789)
-- Name: acc_type_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY accounts
    ADD CONSTRAINT acc_type_id_fk FOREIGN KEY (acc_type_id) REFERENCES account_types(acc_type_id);


--
-- TOC entry 2066 (class 2606 OID 25858)
-- Name: asg_org_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY org_type_asg
    ADD CONSTRAINT asg_org_id_fk FOREIGN KEY (org_id) REFERENCES organizations(org_id);


--
-- TOC entry 2065 (class 2606 OID 25864)
-- Name: asg_org_type_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY org_type_asg
    ADD CONSTRAINT asg_org_type_id_fk FOREIGN KEY (org_type_id) REFERENCES organization_types(org_type_id);


--
-- TOC entry 2059 (class 2606 OID 25794)
-- Name: currency_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY trx_headers
    ADD CONSTRAINT currency_id_fk FOREIGN KEY (currency_id) REFERENCES currencies(currency_id);


--
-- TOC entry 2060 (class 2606 OID 25799)
-- Name: org_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY trx_headers
    ADD CONSTRAINT org_id_fk FOREIGN KEY (org_id) REFERENCES organizations(org_id);


--
-- TOC entry 2057 (class 2606 OID 25809)
-- Name: sch_type_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedules
    ADD CONSTRAINT sch_type_id_fk FOREIGN KEY (sch_type_id) REFERENCES schedule_types(sch_type_id);


--
-- TOC entry 2061 (class 2606 OID 25814)
-- Name: schd_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY trx_headers
    ADD CONSTRAINT schd_id_fk FOREIGN KEY (sch_id) REFERENCES schedules(sch_id);


--
-- TOC entry 2058 (class 2606 OID 25819)
-- Name: sgr_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY schedules
    ADD CONSTRAINT sgr_id_fk FOREIGN KEY (sgr_id) REFERENCES schedule_groups(sgr_id);


--
-- TOC entry 2064 (class 2606 OID 25824)
-- Name: trx_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY trx_lines
    ADD CONSTRAINT trx_id_fk FOREIGN KEY (trx_id) REFERENCES trx_headers(trx_id);


--
-- TOC entry 2062 (class 2606 OID 25829)
-- Name: trx_type_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY trx_headers
    ADD CONSTRAINT trx_type_id_fk FOREIGN KEY (trx_type_id) REFERENCES trx_types(trx_type_id);


--
-- TOC entry 2096 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-02-18 17:24:21

--
-- PostgreSQL database dump complete
--

