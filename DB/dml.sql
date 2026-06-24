INSERT INTO users (username, password, role)
VALUES
('admin', '123456', 'admin'),
('alice', '123456', 'user'),
('bob', '123456', 'user'),
('cindy', '123456', 'user'),
('david', '123456', 'user');

INSERT INTO vote (vote_name, create_time)
VALUES
('最喜歡的程式語言', NOW()),
('最喜歡的前端框架', NOW()),
('午餐要吃什麼', NOW());


INSERT INTO vote_option (option_name, total_count, vote_id)
VALUES
-- vote 1
('Java', 0, 1),
('Python', 0, 1),
('C++', 0, 1),

-- vote 2
('Vue', 0, 2),
('React', 0, 2),
('Angular', 0, 2),

-- vote 3
('麥當勞', 0, 3),
('火鍋', 0, 3),
('便當', 0, 3);