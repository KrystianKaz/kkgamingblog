package com.GamingBlog.gamingblog.service;

import com.GamingBlog.gamingblog.exception.RequestNotFoundException;
import com.GamingBlog.gamingblog.model.Game;
import com.GamingBlog.gamingblog.repository.GameRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(final GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> listGames() {
        List<Game> gameList = gameRepository.findAll();
        Collections.reverse(gameList);
        return gameList;
    }

    public Page<Game> pageAllGames(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    public void addGame(Game game) {
        gameRepository.save(game);
    }

    public Game getPostById(Long id) {
        return gameRepository.findById(id).orElseThrow(() -> new RequestNotFoundException(id));
    }

    public void editGamePost(Game game) {
        gameRepository.save(game);
    }

    public void deleteElementInGames(Long id) {
        gameRepository.deleteById(id);
    }
}
