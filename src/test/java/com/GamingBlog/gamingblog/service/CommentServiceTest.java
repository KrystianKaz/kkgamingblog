package com.GamingBlog.gamingblog.service;

import com.GamingBlog.gamingblog.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(value = "/db.migration/init.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/db.migration/clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Test
    void should_return_all_comments_to_Games() {
        //given + when
        List<Comment> comments = commentService.listCommentsOfTheGamePost();

        // then
        assertEquals(comments.size(), 3);
    }

    @Test
    void should_return_all_comments_to_News() {
        //given + when
        List<Comment> comments = commentService.listCommentsOfTheNewsPost();

        // then
        assertEquals(comments.size(), 3);
    }

    @Test
    void should_add_Comment() {
        // given
        String username = "John Doe";
        Comment comment = new Comment(7, username, LocalTime.now(), LocalDate.now(),
                "comment 7 content", 1L, null, null, null);

        // when
        commentService.addComment(comment, username);
        List<Comment> gameComments = commentService.listCommentsOfTheGamePost();

        // then
        assertEquals(gameComments.size(), 4);
        assertEquals(gameComments.get(0).getUsername(), "John Doe");
    }

    @Test
    void should_return_all_comments_to_certain_Game_post() {
        // given
        List<Comment> comments = commentService.listCommentsOfTheGamePost();
        long id1 = 1;
        long id2 = 2;

        // when
        List<Comment> commentsByGameId1 = commentService.getCommentsByGameId(comments, id1);
        List<Comment> commentsByGameId2 = commentService.getCommentsByGameId(comments, id2);

        // then
        assertEquals(commentsByGameId1.size(), 2);
        assertEquals(commentsByGameId2.size(), 1);
    }

    @Test
    void should_return_all_comments_to_certain_News_post() {
        // given
        List<Comment> comments = commentService.listCommentsOfTheNewsPost();
        long id1 = 1;
        long id2 = 2;

        // when
        List<Comment> commentsByNewsId1 = commentService.getCommentsByNewsId(comments, id1);
        List<Comment> commentsByNewsId2 = commentService.getCommentsByNewsId(comments, id2);

        // then
        assertEquals(commentsByNewsId1.size(), 2);
        assertEquals(commentsByNewsId2.size(), 1);
    }

    @Test
    void should_delete_all_comments_of_certain_Game_post() {
        // given
        List<Comment> comments = commentService.listCommentsOfTheGamePost();
        long id1 = 1;
        long id2 = 2;

        // when
        commentService.deleteCommentsByGameId(comments, id1);
        List<Comment> commentsAfterDelete = commentService.listCommentsOfTheGamePost();
        List<Comment> commentsByGameId1 = commentService.getCommentsByGameId(commentsAfterDelete, id1);
        List<Comment> commentsByGameId2 = commentService.getCommentsByGameId(commentsAfterDelete, id2);

        // then
        assertEquals(commentsByGameId1.size(), 0);
        assertEquals(commentsByGameId2.size(), 1);
    }

    @Test
    void should_delete_all_comments_of_certain_News_post() {
        // given
        List<Comment> comments = commentService.listCommentsOfTheNewsPost();
        long id1 = 1;
        long id2 = 2;

        // when
        commentService.deleteCommentsByNewsId(comments, id1);
        List<Comment> commentsAfterDelete = commentService.listCommentsOfTheNewsPost();
        List<Comment> commentsByNewsId1 = commentService.getCommentsByNewsId(commentsAfterDelete, id1);
        List<Comment> commentsByNewsId2 = commentService.getCommentsByNewsId(commentsAfterDelete, id2);

        // then
        assertEquals(commentsByNewsId1.size(), 0);
        assertEquals(commentsByNewsId2.size(), 1);
    }

    @Test
    void should_return_comments_list_quantity_to_certain_Game_post() {
        // given
        long id = 1;

        // when
        int commentsQuantityToTheGamePost = commentService.getCommentsQuantityToTheGamePost(id);

        // then
        assertEquals(commentsQuantityToTheGamePost, 2);
    }

    @Test
    void should_return_comments_list_quantity_to_certain_News_post() {
        // given
        long id = 2;

        // when
        int commentsQuantityToTheNewsPost = commentService.getCommentsQuantityToTheNews(id);

        // then
        assertEquals(commentsQuantityToTheNewsPost, 1);
    }

    @Test
    void should_sort_all_comments_to_certain_post_and_divide_them_into_5_element_page() {
        // given
        long id = 1;
        int page1 = 0;
        int page2 = 1;

        Comment comment1 = new Comment(7, "admin", LocalTime.now(), LocalDate.now(),
                "comment 7 content", 1L, null, null, null);
        Comment comment2 = new Comment(8, "admin", LocalTime.now(), LocalDate.now(),
                "comment 8 content", 1L, null, null, null);
        Comment comment3 = new Comment(9, "admin", LocalTime.now(), LocalDate.now(),
                "comment 9 content", 1L, null, null, null);
        Comment comment4 = new Comment(10, "admin", LocalTime.now(), LocalDate.now(),
                "comment 10 content", 1L, null, null, null);
        Comment comment5 = new Comment(11, "admin", LocalTime.now(), LocalDate.now(),
                "comment 11 content", 1L, null, null, null);

        commentService.addComment(comment1, "admin");
        commentService.addComment(comment2, "admin");
        commentService.addComment(comment3, "admin");
        commentService.addComment(comment4, "admin");
        commentService.addComment(comment5, "admin");

        List<Comment> comments = commentService.listCommentsOfTheGamePost();
        List<Comment> commentsByGameId = commentService.getCommentsByGameId(comments, id);

        // when
        Page<Comment> commentPage = commentService.pageFromListOfCommentsToGivenPost(commentsByGameId);
        List<Comment> commentsToThePostIntoSublistPage1 = commentService.getCommentsToThePostIntoSublist(commentsByGameId, page1);
        List<Comment> commentsToThePostIntoSublistPage2 = commentService.getCommentsToThePostIntoSublist(commentsByGameId, page2);

        // then
        assertAll("Group of assertions to paging and sorting",
                () -> assertEquals(commentsToThePostIntoSublistPage1.size(), 5),
                () -> assertEquals(commentsToThePostIntoSublistPage2.size(), 2),
                () -> assertEquals(commentPage.getTotalPages(), 2),
                () -> assertEquals(commentPage.getNumberOfElements(), 7),
                () -> assertEquals(commentPage.getNumberOfElements(), 7)
        );
    }

}