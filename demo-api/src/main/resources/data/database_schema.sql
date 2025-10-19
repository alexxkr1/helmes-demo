CREATE TABLE sector (id bigint not null, label varchar(255) not null, level integer not null, parent_id bigint, primary key (id));
CREATE TABLE submission (id integer, agreed_to_terms boolean not null, name varchar(255) not null, primary key (id));
CREATE TABLE submission_sector (submission_id bigint not null, sector_id bigint not null);
