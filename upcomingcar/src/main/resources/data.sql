-- Clear existing rows (order matters because of foreign keys)


-- Insert sample users
INSERT INTO users1 (user_Id, name, username, password, user_role) VALUES
  (1, 'Admin User', 'admin',  '$2a$10$uF1x7qk3YQnZ2vX5b6QkUe6m9XGfGqHcQK3ZkQZB8Kf4xv8vZ6E2S', 'ADMIN'), -- bcrypt for 'adminPassword'
  (2, 'Piyush Kumar', 'piyush', '$2a$10$uF1x7qk3YQnZ2vX5b6QkUe6m9XGfGqHcQK3ZkQZB8Kf4xv8vZ6E2S', 'USER');

-- Insert sample images
INSERT INTO images (image_Id, file_name, file_type, image_data) VALUES
  (1, 'kwid.jpg', 'image/jpeg', NULL),
  (2, 'duster.jpg', 'image/jpeg', NULL);

-- Insert sample cars linked to users and images
INSERT INTO cars1 (car_Id, car_Name, color, car_Type, model_No, price, user_Id, image_Id) VALUES
  (1, 'Renault Kwid', 'Red', 'Hatchback', 'KW123', 400000, 1, 1),
  (2, 'Renault Duster', 'Blue', 'SUV', 'DU456', 900000, 2, 2);

-- Insert sample car_images table (if you’re using CarImage separately)
INSERT INTO car_images (car_image_id, car_name, price, image_id) VALUES
  (1, 'Renault Kwid', 400000, 1),
  (2, 'Renault Duster', 900000, 2);