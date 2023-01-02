DROP TABLE IF EXISTS comment, game, news, user;

CREATE TABLE comment
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    username        VARCHAR(255),
    comment_time    TIME,
    comment_date    DATE,
    comment_content VARCHAR(255) NOT NULL,
    game_id         BIGINT,
    news_id         BIGINT
);

CREATE TABLE game
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_title    VARCHAR(255),
    description   VARCHAR(10000),
    review_time   TIME,
    review_date   DATE,
    review_author VARCHAR(255),
    rating        INT,
    image_name    VARCHAR(255)
);

CREATE TABLE news
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    news_title  VARCHAR(255),
    description VARCHAR(10000),
    news_time   TIME,
    news_date   DATE,
    news_author VARCHAR(255),
    news_image  VARCHAR(255)
);

CREATE TABLE user
(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    email                 VARCHAR(60) NOT NULL UNIQUE,
    username              VARCHAR(15) NOT NULL UNIQUE,
    password              VARCHAR(80) NOT NULL,
    active                BIT,
    account_creation_time TIME,
    account_creation_date DATE,
    user_roles            ENUM('ADMIN', 'USER'),
    image_name            VARCHAR(255)
);