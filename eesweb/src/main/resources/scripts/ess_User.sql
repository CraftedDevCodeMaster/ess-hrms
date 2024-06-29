
-- Admin user insert script
INSERT INTO Ess_User (user_id, emp_Id, email, first_Name, last_Name, last_Login_Date, password, login_Name)
VALUES ((SELECT next_val FROM ess_user_seq),'ADM001', 'admin@example.com', 'Admin', 'User', '2024-06-29', '$2a$10$cRkVWAGM3W70jzoWWNg1d.hY59ARu3O0eBk57/6m1k3eOoCHbu4bW', 'adminuser');

-- Checker user insert script
INSERT INTO Ess_User (user_id, emp_Id, email, first_Name, last_Name, last_Login_Date, password, login_Name)
VALUES ((SELECT next_val FROM ess_user_seq), 'CHK001', 'checker@example.com', 'Checker', 'User', '2024-06-29', '$2a$10$B4Nno122bryfS2kU9Q5fz.LIqWAWsj5dkPkDJGP96O4uFa1z5FkQ.', 'checkeruser');

-- Maker user insert script
INSERT INTO Ess_User (user_id, emp_Id, email, first_Name, last_Name, last_Login_Date, password, login_Name)
VALUES ((SELECT next_val FROM ess_user_seq), 'MKR001', 'maker@example.com', 'Maker', 'User', '2024-06-29', '$2a$10$nRwqao452ARu8jAKxHTurexPaZMhYFjBLjR7o18ukapI.Itx3WZlO', 'makeruser');
