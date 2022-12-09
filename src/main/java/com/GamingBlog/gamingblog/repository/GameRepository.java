package com.GamingBlog.gamingblog.repository;

import com.GamingBlog.gamingblog.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
