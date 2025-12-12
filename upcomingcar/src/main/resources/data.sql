--------------------------------------------------
-- USERS TABLE SEED DATA
--------------------------------------------------
INSERT INTO users1 (user_id, name, username, password, user_role)
VALUES
  (1, 'Admin User', 'admin',
   '$2a$10$uF1x7qk3YQnZ2vX5b6QkUe6m9XGfGqHcQK3ZkQZB8Kf4xv8vZ6E2S', 
   'ADMIN'),

  (2, 'Piyush Kumar', 'piyush',
   '$2a$10$uF1x7qk3YQnZ2vX5b6QkUe6m9XGfGqHcQK3ZkQZB8Kf4xv8vZ6E2S', 
   'USER');


--------------------------------------------------
-- CARS TABLE SEED DATA
--------------------------------------------------
INSERT INTO cars1 (car_id, car_name, car_type, model_no, fuel_type, transmission_type, price)
VALUES
  (1, 'Kwid', 'Hatchback', 'KW-2025', 'Petrol', 'Manual', 450000),
  (2, 'Duster', 'SUV', 'DU-2025', 'Diesel', 'Automatic', 900000);


--------------------------------------------------
-- IMAGES TABLE SEED DATA
--------------------------------------------------
INSERT INTO images (image_id, name, type, pic_byte)
VALUES
  (1, 'kwid-front', 'image/jpeg', NULL),
  (2, 'duster-side', 'image/jpeg', NULL);


--------------------------------------------------
-- CAR_IMAGES (MANY-TO-MANY LINK) SEED DATA
--------------------------------------------------
INSERT INTO car_images (id, car_id, image_id)
VALUES
  (1, 1, 1),
  (2, 2, 2);
