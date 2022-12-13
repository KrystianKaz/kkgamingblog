package com.GamingBlog.gamingblog.service;

import com.GamingBlog.gamingblog.exception.RequestNotFoundException;
import com.GamingBlog.gamingblog.model.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(value = "/db.migration/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Mock
    Pageable pageable;

    @Test
    void should_return_all_Game_posts() {
        // given + when
        List<Game> gameList = gameService.listGames();

        // then
        assertEquals(gameList.size(), 2);
    }

    @Test
    void should_return_all_Game_posts_for_paging_and_sorting() {
        // given + when
        Page<Game> gamePage = gameService.pageAllGames(pageable);

        // then
        assertEquals(gamePage.getSize(), 2);
    }

    @Test
    void should_add_Game_post() {
        // given
        Game game = new Game(3, "Title 3", "Description 3", LocalTime.now(),
                LocalDate.now(), "John Doe", 50, "default.jpg", null);

        // when
        gameService.addGame(game);
        List<Game> gameList = gameService.listGames();

        // then
        assertEquals(gameList.size(), 3);
    }

    @Test
    void should_get_certain_Game_post_by_id() {
        // given
        long id1 = 1;
        long id2 = 2;

        // when
        Game gamePostById1 = gameService.getPostById(id1);
        Game gamePostById2 = gameService.getPostById(id2);

        // then
        assertEquals(gamePostById1.getReviewAuthor(), "admin");
        assertEquals(gamePostById2.getReviewAuthor(), "test");
    }

    @Test
    void should_be_able_to_edit_data_of_certain_Game_post() {
        // given
        long id = 1;
        Game gamePostById = gameService.getPostById(id);

        // when
        gamePostById.setGameTitle("Title Changed");
        gamePostById.setDescription("New Description");
        gamePostById.setReviewAuthor("John Doe");
        gameService.editGamePost(gamePostById);
        Game gamePostByIdAfterEdit = gameService.getPostById(id);

        // then
        assertAll("Group of assertions for edited News Post",
                () -> assertEquals(gamePostByIdAfterEdit.getGameTitle(), "Title Changed"),
                () -> assertEquals(gamePostByIdAfterEdit.getDescription(), "New Description"),
                () -> assertEquals(gamePostByIdAfterEdit.getReviewAuthor(), "John Doe")
        );
    }

    @Test
    void should_be_able_to_delete_certain_Game_post_giving_id() {
        // given
        List<Game> gameListBeforeDelete = gameService.listGames();
        long id = 1;

        // when
        gameService.deleteElementInGames(id);
        List<Game> gameListAfterDelete = gameService.listGames();

        // then
        assertEquals(gameListBeforeDelete.size(), 2);
        assertEquals(gameListAfterDelete.size(), 1);
    }

    @Test
    void should_throw_RequestNotFoundException_when_Game_post_with_given_id_not_found() {
        // given + when
        long id = 3;
        Throwable exception = assertThrows(RequestNotFoundException.class, () -> gameService.getPostById(id));

        // then
        assertEquals("Could not find request with id: " + id, exception.getMessage());
    }

}