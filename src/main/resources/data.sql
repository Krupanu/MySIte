INSERT INTO users
VALUES (1, 'user@test.com', 'Test User', '$2a$10$5TBQSyLUhDmepe1Li7M3/uIUg40dUsPqoRU01ENRlcU93.BEo0gCu'),
(2, 'admin@test.com','Test Admin', '$2a$10$5TBQSyLUhDmepe1Li7M3/uIUg40dUsPqoRU01ENRlcU93.BEo0gCu');

INSERT INTO roles
VALUES (1, 'ROLE_USER'), (2, 'ROLE_ADMIN');

INSERT INTO users_roles
VALUES (1, 1), (2, 2);