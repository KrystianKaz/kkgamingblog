package com.GamingBlog.gamingblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private LocalTime commentTime = LocalTime.now();

    private LocalDate commentDate = LocalDate.now();

    @Column(length = 255, nullable = false)
    private String commentContent;

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "news_id")
    private Long newsId;

    @JoinColumn(name = "game_id", insertable = false, updatable = false)
    @ManyToOne
    private Game game;

    @JoinColumn(name = "news_id", insertable = false, updatable = false)
    @ManyToOne
    private News news;

}
