create table j_role (
                        id serial primary key,
                        name varchar(2000)
);

create table j_user (
                        id serial primary key,
                        name varchar(2000)
);

create table j_brand (
  id serial primary key,
  name varchar(2000)
);

create table j_car_type (
                         id serial primary key,
                         name varchar(2000)
);

create table persons (
                         id serial primary key,
                         name varchar(2000),

);

create table addresses (
                            id serial primary key,
                            street varchar(2000),
                            number varchar(2000)
);
