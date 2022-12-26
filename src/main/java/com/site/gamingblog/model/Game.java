package com.site.gamingblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String gameTitle;

    @Column(length = 10000, nullable = false)
    private String description;

    private LocalTime reviewTime = LocalTime.now();

    private LocalDate reviewDate = LocalDate.now();

    private String reviewAuthor;

    @Min(value = 1, message = "Rating is too low")
    @Max(value = 100, message = "Rating is too high")
    private int rating;

    @Column(name = "image_name")
    private String file;

    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private List<Comment> comments;


}
