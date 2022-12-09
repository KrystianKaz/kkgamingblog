package com.GamingBlog.gamingblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String newsTitle;

    @Column(length = 10000, nullable = false)
    private String description;

    private LocalTime newsTime = LocalTime.now();

    private LocalDate newsDate = LocalDate.now();

    private String newsAuthor;

    @Column(name = "news_image")
    private String file;

    @OneToMany(mappedBy = "news", fetch = FetchType.EAGER)
    private List<Comment> commentsToNews;
}
