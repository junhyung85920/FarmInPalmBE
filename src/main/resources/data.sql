use `farm-in-palm`;

-- INSERT INTO article (article_id, article_title, article_content, article_date, article_image_path)
-- VALUES
--     (1, 'Introduction to Sustainable Farming', 'Sustainable farming practices can increase yield and reduce environmental impact.', '2024-11-01', '/images/articles/farming1.jpg'),
--     (2, '10 Tips for Efficient Irrigation', 'Implementing efficient irrigation techniques can save water and promote plant health.', '2024-11-02', '/images/articles/irrigation.jpg'),
--     (3, 'Understanding Crop Rotation Benefits', 'Crop rotation helps maintain soil health by balancing nutrients.', '2024-11-03', '/images/articles/crop_rotation.jpg'),
--     (4, 'Best Practices for Greenhouse Farming', 'Greenhouse farming allows for controlled environments, improving crop quality.', '2024-11-04', '/images/articles/greenhouse.jpg'),
--     (5, 'Introduction to Organic Pest Control', 'Organic pest control methods reduce the need for harmful chemicals.', '2024-11-05', '/images/articles/organic_pest_control.jpg');

INSERT INTO stock (stock_id, stockName, stockQuantity, stockUnit)
VALUES
    (1, 'Fertilizer', 100, 'kg'),
    (2, 'Pesticide', 50, 'L'),
    (3, 'Seeds', 200, 'kg'),
    (4, 'Irrigation System', 10, 'unit'),
    (5, 'Greenhouse', 5, 'unit');

INSERT INTO monitor (id, monitorName, temperature, humidity, groundTemperature, groundHumidity, co2Concentration, date)
VALUES
    (1, 'Monitor A', 22.5, 60.2, 18.3, 55.0, 400.5, '2024-11-15 08:30:00'),
    (2, 'Monitor B', 23.1, 58.8, 17.9, 53.2, 420.3, '2024-11-15 08:45:00'),
    (3, 'Monitor C', 21.8, 65.3, 18.5, 57.5, 415.0, '2024-11-15 09:00:00'),
    (4, 'Monitor D', 24.0, 63.5, 19.1, 60.3, 410.2, '2024-11-15 09:15:00'),
    (5, 'Monitor E', 22.7, 61.0, 18.8, 56.1, 405.8, '2024-11-15 09:30:00'),
    (6, 'Monitor F', 23.4, 64.2, 18.6, 58.7, 398.6, '2024-11-15 09:45:00'),
    (7, 'Monitor G', 22.2, 62.4, 17.7, 55.8, 407.1, '2024-11-15 10:00:00'),
    (8, 'Monitor H', 21.9, 59.5, 18.2, 54.3, 412.9, '2024-11-15 10:15:00'),
    (9, 'Monitor I', 23.0, 60.0, 18.4, 57.0, 418.4, '2024-11-15 10:30:00'),
    (10, 'Monitor J', 22.8, 63.1, 19.0, 59.2, 401.3, '2024-11-15 10:45:00');

INSERT INTO event (event_id, eventTitle, eventStartDate, eventStartDay, eventEndDate, eventEndDay)
VALUES
    (1, 'Farmers Market', '2024-11-01', 'Monday', '2024-11-05', 'Friday'),
    (2, 'Workshop on Sustainable Farming', '2024-11-02', 'Tuesday', '2024-11-06', 'Saturday'),
    (3, 'Irrigation Training', '2024-11-03', 'Wednesday', '2024-11-07', 'Sunday'),
    (4, 'Crop Rotation Seminar', '2024-11-04', 'Thursday', '2024-11-08', 'Monday'),
    (5, 'Greenhouse Farming Workshop', '2024-11-05', 'Friday', '2024-11-09', 'Tuesday');

