INSERT INTO comment(id, username, comment_time, comment_date, comment_content, game_id, news_id)
values (1, 'admin', '12:12:00', '2022-01-01', 'Comment1 content', 1, null),
       (2, 'test', '12:12:01', '2022-01-01', 'Comment2 content', null, 1),
       (3, 'admin', '12:12:02', '2022-01-01', 'Comment3 content', 1, null),
       (4, 'user', '12:12:03', '2022-01-01', 'Comment4 content', null, 1),
       (5, 'test', '12:12:04', '2022-01-01', 'Comment5 content', 2, null),
       (6, 'user', '12:12:05', '2022-01-01', 'Comment6 content', null, 2);

INSERT INTO game(id, game_title, description, review_time, review_date, review_author, rating, image_name)
values (1, 'Game 1 Title', 'Game 1 description', '12:10:01', '2022-01-02', 'admin', 50, 'default.jpg'),
       (2, 'Game 2 Title', 'Game 2 description', '12:10:02', '2022-01-03', 'test', 99, 'default.jpg');

INSERT INTO news(id, news_title, description, news_time, news_date, news_author, news_image)
values (1, 'News 1 Title', 'News 1 description', '12:10:02', '2022-01-01', 'admin', 'default.jpg'),
       (2, 'News 2 Title', 'News 2 description', '12:10:03', '2022-01-03', 'test', 'default.jpg');

INSERT INTO user(id, email, username, password, active, account_creation_time, account_creation_date, user_roles,
                 image_name)
values (1, 'admin@admin.com', 'admin', 'admin', true, '10:12:12', '2022-01-01', 'ADMIN', 'admin.jpg'),
       (2, 'user@user.com', 'user', 'user', true, '10:12:12', '2022-01-01', 'USER', 'user.jpg'),
       (3, 'test@test.com', 'test', 'test', false, '10:12:12', '2022-01-01', 'USER', 'test.jpg');

