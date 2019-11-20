create table DIVISION
(
    ID number not null
        constraint DIVISON_pk
            primary key,
    NAME varchar(50) not null,
    ORIGINAL_NAME varchar(50),
    FIRST_NAME varchar(50),
    LAST_NAME varchar(50),
    PARENT_ID number
);
alter table DIVISION add foreign key (PARENT_ID) REFERENCES DIVISION(ID);
create sequence DIVISION_SEQ start with 1 increment by 1;

create table CONTACTPERSON
(
    ID number not null
        constraint CONTACTPERSON_pk
            primary key,
    EMAIL varchar(50),
    MOBILE_PHONE_NUMBER varchar(50),
    TELEPHONE_NUMBER varchar(50),
    STREET varchar(50),
    STREET_NR varchar(10),
    POSTAL_CODE varchar(50)
);
create sequence CONTACT_PERSON_SEQ start with 1 increment by 1;

create table PARKINGLOT
(
	ID number not null
		constraint PARKINGLOT_pk
			primary key,
	NAME varchar(50) not null,
	PARKING_CATEGORY varchar(50) not null,
	CAPACITY int not null,
	CONTACT_PERSON_ID number not null,
	DIVISION_ID number not null
);
alter table PARKINGLOT add foreign key (DIVISION_ID) REFERENCES DIVISION(ID);
alter table PARKINGLOT add foreign key (CONTACT_PERSON_ID) REFERENCES CONTACTPERSON(ID);
create sequence PARKINGLOT_SEQ start with 1 increment by 1;

create table MEMBER
(
    ID number not null
        constraint MEMBER_pk
            primary key,
    FIRST_NAME varchar(50),
    LAST_NAME varchar(50),
    STREET varchar(50),
    HOUSE_NUMBER varchar(10),
    ZIP varchar(10),
    COUNTRY varchar(50),
    PHONENUMBER varchar(50),
    EMAIL varchar(50),
    PLATE_NUMBER varchar(50),
    ISSUING_COUNTRY varchar(50),
    REGISTRATION_DATE date,
    MEMBERSHIP_LEVEL varchar(50)
);
create sequence MEMBER_SEQ start with 1 increment by 1;

create table ALLOCATION
(
    ID number not null
        constraint ALLOCATION_pk
            primary key,
    MEMBER_ID number not null,
    PARKINGLOT_ID number not null,
    START_TIME date,
    STOP_TIME date
);

alter table ALLOCATION add foreign key (MEMBER_ID) REFERENCES MEMBER(ID);
alter table ALLOCATION add foreign key (PARKINGLOT_ID) REFERENCES PARKINGLOT(ID);
create sequence ALLOCATION_SEQ start with 1 increment by 1;

