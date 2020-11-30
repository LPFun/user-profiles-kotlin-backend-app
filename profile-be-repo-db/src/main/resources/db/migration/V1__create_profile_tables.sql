CREATE TABLE "profile_personal_table"
(
    "id"           varchar(50) NOT NULL,
    "first_name"   TEXT        NOT NULL,
    "middle_name"  TEXT        NOT NULL,
    "last_name"    TEXT        NOT NULL,
    "display_name" TEXT        NOT NULL,
    "phone"        TEXT        NOT NULL,
    "email"        TEXT        NOT NULL,
    "birth_day"    TEXT        NOT NULL,
    "country"      TEXT        NOT NULL,
    "city"         TEXT        NOT NULL,
    CONSTRAINT "profile_personal_table_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );

CREATE TABLE "profile_education_table"
(
    "id" varchar(50) NOT NULL,
    CONSTRAINT "profile_education_table_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );

CREATE TABLE "additional_education_table"
(
    "id"                  varchar(50) NOT NULL,
    "profile_id"          varchar(50) NOT NULL,
    "name_of_institution" TEXT        NOT NULL,
    "course_name"         TEXT        NOT NULL,
    "year_of_completion"  TEXT        NOT NULL,
    CONSTRAINT "additional_education_table_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );

CREATE TABLE "main_education_table"
(
    "id"         varchar(50) NOT NULL,
    "profile_id" varchar(50) NOT NULL,
    "university" TEXT        NOT NULL,
    "department" TEXT        NOT NULL,
    "speciality" TEXT        NOT NULL,
    "year"       TEXT        NOT NULL,
    CONSTRAINT "main_education_table_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );

CREATE TABLE "profile_skills_tech_table"
(
    "id" uuid NOT NULL,
    CONSTRAINT "profile_skills_tech_table_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );

CREATE TABLE "profile_db_table"
(
    "id"         uuid NOT NULL,
    "profile_id" uuid NOT NULL,
    "data_base"  TEXT NOT NULL,
    CONSTRAINT "profile_db_table_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );

CREATE TABLE "profile_os_table"
(
    "id"               uuid NOT NULL,
    "profile_id"       uuid NOT NULL,
    "operating_system" TEXT NOT NULL,
    CONSTRAINT "profile_os_table_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );

CREATE TABLE "profile_specialization_table"
(
    "id"           uuid NOT NULL,
    "profile_id"   uuid NOT NULL,
    "category"     TEXT NOT NULL,
    "sub_category" TEXT NOT NULL,
    CONSTRAINT "profile_specialization_table_pk" PRIMARY KEY ("id")
) WITH (
      OIDS= FALSE
    );

ALTER TABLE "additional_education_table"
    ADD CONSTRAINT "additional_education_table_fk0" FOREIGN KEY ("profile_id") REFERENCES "profile_education_table" ("id");

ALTER TABLE "main_education_table"
    ADD CONSTRAINT "main_education_table_fk0" FOREIGN KEY ("profile_id") REFERENCES "profile_education_table" ("id");

ALTER TABLE "profile_db_table"
    ADD CONSTRAINT "profile_db_table_fk0" FOREIGN KEY ("profile_id") REFERENCES "profile_skills_tech_table" ("id");

ALTER TABLE "profile_os_table"
    ADD CONSTRAINT "profile_os_table_fk0" FOREIGN KEY ("profile_id") REFERENCES "profile_skills_tech_table" ("id");

ALTER TABLE "profile_specialization_table"
    ADD CONSTRAINT "profile_specialization_table_fk0" FOREIGN KEY ("profile_id") REFERENCES "profile_skills_tech_table" ("id");
