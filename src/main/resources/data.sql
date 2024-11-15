use `farm-in-palm`;

INSERT INTO user (name)
VALUES
    ('John Doe'),
    ('Jane Doe'),
    ('Alice Smith'),
    ('Bob Johnson'),
    ('Eve Williams'),
    ('Michael Brown'),
    ('Sarah Davis'),
    ('David Miller'),
    ('Laura Wilson'),
    ('James Moore');

INSERT INTO stock (stock_id, stock_name, stock_quantity, stock_unit, user_id)
VALUES
    (1, 'Fertilizer', 100.1, 'kg', 1),
    (2, 'Pesticide', 50.4, 'L', 1),
    (3, 'Seeds', 200.0, 'kg', 1),
    (4, 'Irrigation System', 10.0, 'unit', 1),
    (5, 'Greenhouse', 5.0, 'unit', 1);

INSERT INTO monitor (monitor_id, monitor_name, temperature, humidity, ground_temperature, ground_humidity, co2concentration, date, user_id)
VALUES
    (1, 'Monitor A', 22.5, 60.2, 18.3, 55.0, 400.5, '2024-11-15 08:30:00', 1),
    (2, 'Monitor B', 23.1, 58.8, 17.9, 53.2, 420.3, '2024-11-15 08:45:00', 1),
    (3, 'Monitor C', 21.8, 65.3, 18.5, 57.5, 415.0, '2024-11-15 09:00:00', 1),
    (4, 'Monitor D', 24.0, 63.5, 19.1, 60.3, 410.2, '2024-11-15 09:15:00', 1),
    (5, 'Monitor E', 22.7, 61.0, 18.8, 56.1, 405.8, '2024-11-15 09:30:00', 1),
    (6, 'Monitor F', 23.4, 64.2, 18.6, 58.7, 398.6, '2024-11-15 09:45:00', 1),
    (7, 'Monitor G', 22.2, 62.4, 17.7, 55.8, 407.1, '2024-11-15 10:00:00', 1),
    (8, 'Monitor H', 21.9, 59.5, 18.2, 54.3, 412.9, '2024-11-15 10:15:00', 1),
    (9, 'Monitor I', 23.0, 60.0, 18.4, 57.0, 418.4, '2024-11-15 10:30:00', 1),
    (10, 'Monitor J', 22.8, 63.1, 19.0, 59.2, 401.3, '2024-11-15 10:45:00', 1);

INSERT INTO event (event_id, event_title, event_start_date, event_start_day, event_end_date, event_end_day, user_id)
VALUES
    (1, 'Farmers Market', '2024-11-01', 'Monday', '2024-11-05', 'Friday', 1),
    (2, 'Workshop on Sustainable Farming', '2024-11-02', 'Tuesday', '2024-11-06', 'Saturday', 1),
    (3, 'Irrigation Training', '2024-11-03', 'Wednesday', '2024-11-07', 'Sunday', 1),
    (4, 'Crop Rotation Seminar', '2024-11-04', 'Thursday', '2024-11-08', 'Monday', 1),
    (5, 'Greenhouse Farming Workshop', '2024-11-05', 'Friday', '2024-11-09', 'Tuesday', 1);

