create table public.user (
    id character(36) NOT NULL PRIMARY KEY,
    firstname character varying(40),
    lastname character varying(40) NOT NULL,
    genderid character(1),
    createdby varchar(36) NOT NULL,
    createddate varchar(255),
    updatedby varchar(36) NOT NULL,
    updateddate varchar(255)
);

create table employee (
    id character(36) NOT NULL PRIMARY KEY,
    userid character(36) NOT NULL,
    createdby varchar(36) NOT NULL,
    createddate varchar(255),
    updatedby varchar(36) NOT NULL,
    updateddate varchar(255)
);

create table affiliate (
    id character(36) NOT NULL PRIMARY KEY,
    userid character(36) NOT NULL,
    createdby varchar(36) NOT NULL,
    createddate varchar(255),
    updatedby varchar(36) NOT NULL,
    updateddate varchar(255)
);

create table customer (
    id character(36) NOT NULL PRIMARY KEY,
    userid character(36) NOT NULL,
    createdby varchar(36) NOT NULL,
    createddate varchar(255),
    updatedby varchar(36) NOT NULL,
    updateddate varchar(255)
);


create table product (
    id character(36) NOT NULL PRIMARY KEY,
    productname character varying(40) NOT NULL,
    price integer,
    description character varying(200),
    createdby varchar(36) NOT NULL,
    createddate varchar(255),
    updatedby varchar(36) NOT NULL,
    updateddate varchar(255)
);