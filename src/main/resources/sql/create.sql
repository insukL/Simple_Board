DROP TABLE IF EXISTS simple_board_database;
CREATE DATABASE IF NOT EXISTS simple_board_database;
DROP TABLE IF EXISTS simple_board_database.users;
create table simple_board_database.users(
  id varchar(50) primary key,
  password varchar(50) not null,
  nickname varchar(50) not null,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp
);

DROP TABLE IF EXISTS simple_board_database.boards;
create table simple_board_database.boards(
  id bigint unsigned auto_increment primary key,
  title varchar(100) not null,
  content varchar(2000) not null,
  author_id varchar(50) not null,
  deleted bit(1) default 0,
  created_at timestamp default current_timestamp,
  updated_at timestamp default current_timestamp
);