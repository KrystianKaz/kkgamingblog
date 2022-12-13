INSERT INTO comment(id, username, comment_time, comment_date, comment_content, game_id, news_id)
values (1, 'admin', '12:12:00', '2022-01-01', 'Comment1 content', 1, null),
       (2, 'test', '12:12:01', '2022-01-01', 'Comment2 content', null, 1),
       (3, 'admin', '12:12:02', '2022-01-01', 'Comment3 content', 1, null),
       (4, 'user', '12:12:03', '2022-01-01', 'Comment4 content', null, 1),
       (5, 'test', '12:12:04', '2022-01-01', 'Comment5 content', 2, null),
       (6, 'user', '12:12:05', '2022-01-01', 'Comment6 content', null, 2);

INSERT INTO game(id, game_title, description, review_time, review_date, review_author, rating, image_name)
values (1, 'Game 1 Title', 'Game 1 description', '12:10:01', '2022-01-02', 'admin', 50, 'default.jpg'),
       (2, 'Medieval Dynasty', '<b>Medieval Dynasty</b> to mieszanka symulatorów życia z serii Dynasty z RPG oraz survivalem od polskiego studia Render Cube. Zaczynamy jako skromny łowca w czasach średniowiecza starający się przetrwać w dzikiej głuszy, by z czasem założyć własne miasto. Medieval Dynasty to intrygująca gra z pogranicza RPG akcji i symulatora życia z elementami survivalu oraz strategii, stworzona przez polskie studio Render Cube. Jest to druga produkcja łódzkiego zespołu i zgoła odmienna od jego debiutanckiego projektu: wyścigów gokartów Monster League z 2018 roku. Tym razem Polacy stworzyli realistyczną grę osadzoną w średniowieczu, trochę przypominającą czeskie Kingdom Come: Deliverance. Tytuł jest też kolejną odsłoną cyklu Dynasty firmy Toplitz Productions, aczkolwiek wyraźnie odmienną od swoich poprzedników.<br><br><b>Fabuła</b><br>Głównym bohaterem jest osiemnastoletni chłop, który po śmierci rodziców opuszcza rodzinny dom, aby znaleźć pracę i wejść w dorosłość. Protagonista liczy na pomoc doświadczonego wuja, niestety okazuje się, że krewny nie żyje już od jakiegoś czasu. Dzięki pomocy lokalnego kasztelana młodzieniec otrzymuje kawałek ziemi i postanawia sam zadbać o swoją przyszłość, mając ambitny plan założenia własnej osady i zdobycia renomy w okolicy. Przy okazji pozna też tajemniczą przeszłość swojego wuja.<br><p style="text-align:right;"><i>Źródło: https://www.gry-online.pl/gry/medieval-dynasty/z05b63</i></p>', '12:10:02', '2022-01-03', 'admin', 65, 'medieval.jpg');

INSERT INTO news(id, news_title, description, news_time, news_date, news_author, news_image)
values (1, 'News 1 Title', 'News 1 description', '12:10:02', '2022-01-01', 'admin', 'default.jpg'),
       (2, 'News 2 Title', 'News 2 description', '12:10:03', '2022-01-03', 'test', 'default.jpg');

INSERT INTO user(id, email, username, password, active, account_creation_time, account_creation_date, user_roles,
                 image_name)
values (1, 'admin@admin.com', 'admin', '$10$PQB8Tfj9nBwXfGgUgz0HnujdO6W0mrSw6iNjR.hn.5otxA11dFZW2', true, '10:12:12', '2022-01-01', 'ADMIN', 'admin.jpg'),
       (2, 'user@user.com', 'user', '$2a$10$.xVn1nlHBVb0YKSkEmdMYOFQAAW1you99ElCrf4kZx35ghp9CT6CW', true, '10:12:12', '2022-01-01', 'USER', 'user.jpg'),
       (3, 'test@test.com', 'test', '$2a$10$dfA2xjjoQNeQI3vxKEw1KeMeaEmFlfhPUnsXRTLpxGQZyEQZFt6vG', false, '10:12:12', '2022-01-01', 'USER', 'testuser.jpg');

