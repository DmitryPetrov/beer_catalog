\! chcp 1251

CREATE DATABASE beer_catalog;
\c beer_catalog;

CREATE TABLE beer (
	id BIGSERIAL PRIMARY KEY,
	rate INTEGER CONSTRAINT rate_check CHECK (rate >= 1 AND rate <= 100),
	count INTEGER CONSTRAINT count_check CHECK (count > 0),
	star BOOLEAN,
	craft BOOLEAN,
	name VARCHAR(80) UNIQUE,
	description VARCHAR(200),
	photo VARCHAR(100)
	);
	
CREATE TABLE style (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(80) UNIQUE NOT NULL
	);
	
CREATE TABLE country (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(80) UNIQUE NOT NULL
	);
	
CREATE TABLE brewery (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(80) UNIQUE NOT NULL
	);
	
CREATE TABLE snack (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(80) UNIQUE NOT NULL
	);
	
CREATE TABLE beer_style (
	id_beer BIGINT,
	id_style BIGINT,
	PRIMARY KEY (id_beer, id_style),
	FOREIGN KEY (id_beer) REFERENCES beer(id) ON DELETE CASCADE,
	FOREIGN KEY (id_style) REFERENCES style(id) ON DELETE CASCADE
	);
	
CREATE TABLE beer_country (
	id_beer BIGINT,
	id_country BIGINT,
	PRIMARY KEY (id_beer, id_country),
	FOREIGN KEY (id_beer) REFERENCES beer(id) ON DELETE CASCADE,
	FOREIGN KEY (id_country) REFERENCES country(id) ON DELETE CASCADE
	);
	
CREATE TABLE beer_brewery (
	id_beer BIGINT,
	id_brewery BIGINT,
	PRIMARY KEY (id_beer, id_brewery),
	FOREIGN KEY (id_beer) REFERENCES beer(id) ON DELETE CASCADE,
	FOREIGN KEY (id_brewery) REFERENCES brewery(id) ON DELETE CASCADE
	);
	
CREATE TABLE beer_snack (
	id_beer BIGINT,
	id_snack BIGINT,
	PRIMARY KEY (id_beer, id_snack),
	FOREIGN KEY (id_beer) REFERENCES beer(id) ON DELETE CASCADE,
	FOREIGN KEY (id_snack) REFERENCES snack(id) ON DELETE CASCADE
	);
	
CREATE TABLE style_snack (
	id_style BIGINT,
	id_snack BIGINT,
	PRIMARY KEY (id_style, id_snack),
	FOREIGN KEY (id_style) REFERENCES style(id) ON DELETE CASCADE,
	FOREIGN KEY (id_snack) REFERENCES snack(id) ON DELETE CASCADE
	);




CREATE TABLE app_user (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(80) UNIQUE NOT NULL,
	encrypted_password TEXT NOT NULL
	);

CREATE TABLE role (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(80) UNIQUE NOT NULL
	);

CREATE TABLE user_role (
	id_user BIGINT NOT NULL, 
	id_role BIGINT NOT NULL,
	PRIMARY KEY (id_user, id_role),
	FOREIGN KEY (id_user) REFERENCES app_user(id) ON DELETE CASCADE,
	FOREIGN KEY (id_role) REFERENCES role(id) ON DELETE CASCADE
	);
  
CREATE TABLE user_beer (
	id_user BIGINT,
	id_beer BIGINT,
	rate INTEGER CONSTRAINT rate_check CHECK (rate >= 1 AND rate <= 100),
	count INTEGER CONSTRAINT count_check CHECK (count > 0),
	star BOOLEAN,
	description VARCHAR(200),
	FOREIGN KEY (id_user) REFERENCES app_user(id) ON DELETE CASCADE,
	FOREIGN KEY (id_beer) REFERENCES beer(id) ON DELETE CASCADE,
	PRIMARY KEY (id_user, id_beer)
	);  
  
  
  
  
-- Used by Spring Remember Me API.  
CREATE TABLE persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,    
);




INSERT INTO beer (rate, count, star, craft, name, description, photo) VALUES  
(50, 1, FALSE, FALSE, 'сидр №1', 'сидр №1 описание', ''),
(65, 1, FALSE, FALSE, 'сидр №2', 'сидр №2 описание', ''),
(75, 2, FALSE, FALSE, 'сидр №3', 'сидр №3 описание', ''),
(50, 1, FALSE, FALSE, 'лаггер №1', 'лаггер №1 описание', ''),
(65, 1, FALSE, FALSE, 'лаггер №2', 'лаггер №2 описание', ''),
(75, 2, FALSE, FALSE, 'лаггер №3', 'лаггер №3 описание', ''),
(50, 1, FALSE, FALSE, 'эль №1', 'эль №1 описание', ''),
(65, 1, FALSE, FALSE, 'эль №2', 'эль №2 описание', ''),
(75, 2, FALSE, FALSE, 'эль №3', 'эль №3 описание', ''),
(50, 1, FALSE, FALSE, 'стаут №1', 'стаут №1 описание', ''),
(65, 1, FALSE, FALSE, 'стаут №2', 'стаут №2 описание', ''),
(75, 2, FALSE, FALSE, 'стаут №3', 'стаут №3 описание', ''),
(50, 1, FALSE, FALSE, 'IPA №1', 'IPA №1 описание', ''),
(65, 1, FALSE, FALSE, 'IPA №2', 'IPA №2 описание', ''),
(75, 2, FALSE, FALSE, 'IPA №3', 'IPA №3 описание', ''),
(50, 1, FALSE, FALSE, 'пшеничное №1', 'пшеничное №1 описание', ''),
(65, 1, FALSE, FALSE, 'пшеничное №2', 'пшеничное №2 описание', ''),
(75, 2, FALSE, FALSE, 'пшеничное №3', 'пшеничное №3 описание', ''),
(50, 1, FALSE, FALSE, 'вишневое №1', 'вишневое №1 описание', ''),
(65, 1, FALSE, FALSE, 'вишневое №2', 'вишневое №2 описание', ''),
(75, 2, FALSE, FALSE, 'вишневое №3', 'вишневое №3 описание', '');

INSERT INTO brewery(name) VALUES
('ПИВОВАРНЯ №1'),
('ПИВОВАРНЯ №2'),
('ПИВОВАРНЯ №3');

INSERT INTO country(name) VALUES
('Russia'),
('France'),
('England');

INSERT INTO style(name) VALUES
('сидр'),
('лаггер'),
('эль'),
('стаут'),
('IPA'),
('пшеничное'),
('вишневое');

INSERT INTO snack(name) VALUES
('сыр'),
('сухари'),
('орехи'),
('рыба');

INSERT INTO beer_brewery(id_beer, id_brewery) VALUES
(2, 1),
(3, 1),
(7, 3),
(6, 2),
(10, 2),
(11, 2);

INSERT INTO beer_country(id_beer, id_country) VALUES
(1, 1), (2, 1), (3, 1),
(4, 1), (5, 2), (6, 1),
(7, 2), (8, 1), (9, 3),
(10, 1), (11, 1), (12, 3),
(13, 3), (14, 1), (15, 3),
(16, 1), (17, 2), (18, 1),
(19, 1), (20, 3), (21, 2);

INSERT INTO beer_style(id_beer, id_style) VALUES
(1, 1), (2, 1), (3, 1),
(4, 2), (5, 2), (6, 2),
(7, 3), (8, 3), (9, 3),
(10, 4), (11, 4), (12, 4),
(13, 5), (14, 5), (15, 5),
(16, 6), (17, 6), (18, 6),
(19, 7), (20, 7), (21, 7);

INSERT INTO style_snack(id_style, id_snack) VALUES
(2, 1),
(2, 2),
(2, 4),
(4, 3),
(4, 2);

INSERT INTO beer_snack(id_beer, id_snack) VALUES
(1, 1), (2, 1), (3, 1),
(4, 2), (5, 2), (6, 2),
(7, 3), (8, 3), (9, 3),
(10, 4), (11, 4), (12, 4),
(13, 3), (14, 3), (15, 3),
(16, 1), (17, 2), (18, 3),
(19, 1), (20, 3), (21, 4);




INSERT INTO app_user (name, encrypted_password) VALUES
('admin', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu'),
('user', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu');

INSERT INTO role (name) VALUES
('admin'),
('user');
 
INSERT INTO user_role (id_user, id_role) VALUES
(1, 1),
(1, 2),
(2, 2);

INSERT INTO user_beer (id_user, id_beer, rate, count, star, description) VALUES
(2, 1, 65, 2, FALSE, 'пиво №1 пользовательское описание'),
(2, 2, 30, 1, FALSE, 'пиво №2 пользовательское описание'),
(2, 5, 90, 9, TRUE, 'пиво №5 пользовательское описание'),
(2, 8, 75, 1, FALSE, 'пиво №8 пользовательское описание');
	

