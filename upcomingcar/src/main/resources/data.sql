
DELETE FROM users;
DELETE FROM cars;

INSERT INTO users (user_id, username, password, user_role) VALUES
  (1, 'admin',  '$2a$10$uF1x7qk3YQnZ2vX5b6QkUe6m9XGfGqHcQK3ZkQZB8Kf4xv8vZ6E2S', 'ADMIN'), -- bcrypt for 'adminPassword'
  (2, 'piyush', '$2a$10$uF1x7qk3YQnZ2vX5b6QkUe6m9XGfGqHcQK3ZkQZB8Kf4xv8vZ6E2S', 'USER');  -- same hash for example

INSERT INTO cars (car_id, car_name) VALUES
  (1, 'Renault Kwid'),
  (2, 'Renault Duster');
