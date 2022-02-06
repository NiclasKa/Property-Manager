# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table property (
  id                            serial not null,
  name                          varchar(255),
  address                       varchar(255),
  number                        varchar(255),
  postal_code                   integer,
  city                          varchar(255),
  country                       varchar(255),
  description                   varchar(255),
  coordinates                   varchar(255),
  constraint pk_property primary key (id)
);


# --- !Downs

drop table if exists property cascade;

