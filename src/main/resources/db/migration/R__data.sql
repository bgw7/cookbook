INSERT INTO CHEF (id, name) VALUES (1, 'Tom');
INSERT INTO CHEF (id, name) VALUES (2, 'Jerry');


INSERT INTO CHEF_INGREDIENT (chef_id, name, quantity, state) VALUES (1, 'green bell pepper', 2, 'diced');
INSERT INTO CHEF_INGREDIENT (chef_id, name, quantity, state) VALUES (2, 'garlic', 5, 'chopped');

INSERT INTO RECIPE (id, chef_id, name) VALUES (1, 1, 'tasty bell pepper');
INSERT INTO RECIPE (id, chef_id, name) VALUES (2, 2, 'yummy garlic');

INSERT INTO INGREDIENT (id, name, quantity, state) VALUES (1, 'green bell pepper', 2, 'diced');
INSERT INTO INGREDIENT (id, name, quantity, state) VALUES (2, 'garlic', 5, 'chopped');

INSERT INTO RECIPE_TO_INGREDIENT (recipe_id, ingredient_id) VALUES (1, 1);
INSERT INTO RECIPE_TO_INGREDIENT (recipe_id, ingredient_id) VALUES (2, 2);