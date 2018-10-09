CREATE TABLE Chef (
    id Serial PRIMARY KEY,
    name varchar(100) NOT NULL
);

CREATE TABLE Chef_Ingredient (
    id Serial PRIMARY KEY,
    chef_id integer NOT NULL,
    added TIMESTAMP DEFAULT now(),
    name varchar(100) NOT NULL,
    quantity integer NOT NULL,
    state varchar(40) NOT NULL,
    FOREIGN KEY (chef_id) REFERENCES Chef (id)
);

CREATE TABLE Ingredient (
    id Serial PRIMARY KEY,
    name varchar(100) NOT NULL,
    quantity smallint NOT NULL,
    state varchar(40) NOT NULL
);

CREATE TABLE Recipe (
    id Serial PRIMARY KEY,
    chef_id integer NOT NULL,
    name varchar(150) NOT NULL
);

CREATE TABLE Recipe_To_Ingredient (
    id Serial PRIMARY KEY,
    recipe_id smallint NOT NULL,
    ingredient_id smallint NOT NULL,
);