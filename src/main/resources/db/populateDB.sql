DELETE FROM vote_notes;
DELETE FROM user_roles;
DELETE FROM restaurant;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin'),
  ('User2', 'user2@yandex.ru', 'password');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002);

INSERT INTO restaurant (name) VALUES
  ('Строганофф'),
  ('Terrassa'),
  ('Столовая'),
  ('McDonalds');


INSERT INTO vote_notes (date, restaurant_id, user_id) VALUES
  ('2015-05-30', 100003, 100000),
  ('2015-05-30', 100004, 100001),
  ('2015-05-30', 100005, 100002),
  ('2015-05-30', 100006, 100000),
  ('2015-05-30', 100006, 100001),
  ('2015-05-30', 100003, 100002);