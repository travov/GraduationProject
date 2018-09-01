DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurants;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (login, password) VALUES
  ('User', '{noop}password'),
  ('Admin', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);


INSERT INTO restaurants (name) VALUES
  ('1st restaurant'),
  ('2nd restaurant');

INSERT INTO dishes (day, restaurant_id, name, price) VALUES
  ('2015-05-30', 100002, 'ice-cream', 200),
  ('2015-05-30', 100002, 'sweet pie', 300),
  ('2015-05-30', 100002, 'green tea', 50),
  ('2015-05-30', 100002, 'soup', 100),
  ('2015-05-31', 100003, 'pizza', 500),
  ('2015-05-31', 100003, 'meat', 300),
  ('2015-05-31', 100003, 'chicken burger', 150),
  ('2015-05-31', 100003, 'big burger', 200);

INSERT INTO votes (user_id, restaurant_id, voted) VALUES
  (100001, 100003, '2018-03-20'),
  (100001, 100002, '2015-02-02'),
  (100000, 100002, '2014-05-30'),
  (100000, 100003, '2017-07-08');
