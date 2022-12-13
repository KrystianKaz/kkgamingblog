package com.GamingBlog.gamingblog.service;

import com.GamingBlog.gamingblog.exception.RequestNotFoundException;
import com.GamingBlog.gamingblog.model.News;
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
class NewsServiceTest {

    @Autowired
    private NewsService newsService;

    @Mock
    private Pageable pageable;

    @Test
    void should_return_list_of_all_News() {
        // given+when
        List<News> newsList = newsService.listNews();

        // then
        assertNotNull(newsList);
        assertEquals(2, newsList.size());
    }

    @Test
    void all_News_should_be_pageable() {
        // given+when
        Page<News> newsPage = newsService.pageAllNews(pageable);

        // then
        assertEquals(newsPage.getSize(), 2);
    }

    @Test
    void list_should_be_sorted_from_newest_to_oldest() {

        // given
        News news1 = new News(4, "News 4 Title", "News 4 desc", LocalTime.now(),
                LocalDate.now(), "test", "default.jpg", null);
        News news2 = new News(3, "News 3 Title", "News 3 desc", LocalTime.now(),
                LocalDate.now(), "admin", "default.jpg", null);

        // when
        newsService.addNews(news2);
        newsService.addNews(news1);
        List<News> newsList = newsService.listNews();

        // then
        assertEquals(newsList.get(0).getNewsTitle(), news1.getNewsTitle());
        assertEquals(newsList.get(1).getNewsTitle(), news2.getNewsTitle());
        assertEquals(newsList.get(2).getNewsTitle(), "News 2 Title");
        assertEquals(newsList.get(3).getNewsTitle(), "News 1 Title");

    }

    @Test
    void should_return_news_post_with_given_id() {
        // given+when
        News news = newsService.getNewsPostById(2L);

        //then
        assertEquals(news.getNewsTitle(), "News 2 Title");
    }

    @Test
    void should_throw_RequestNotFoundException_when_news_post_with_given_id_not_found() {
        // given + when
        long id = 3;
        Throwable exception = assertThrows(RequestNotFoundException.class,
                () -> newsService.getNewsPostById(id));

        //then
        assertEquals("Could not find request with id: " + id, exception.getMessage());
    }

    @Test
    void should_be_able_to_edit_data_of_given_news_id() {
        // given
        long id = 1;
        News newsBeforeEdit = newsService.getNewsPostById(id);
        News newsAfterEdit = newsService.getNewsPostById(id);

        // when
        newsAfterEdit.setNewsTitle("Title changed");

        //then
        assertNotEquals(newsBeforeEdit.getNewsTitle(), newsAfterEdit.getNewsTitle());
        assertNotEquals(newsBeforeEdit.getNewsTitle(), "Title changed");
        assertEquals(newsAfterEdit.getNewsTitle(), "Title changed");
    }

    @Test
    void should_be_able_to_delete_news_post_giving_id() {
        // given
        List<News> newsListBeforeDelete = newsService.listNews();
        long id = 1;

        // when
        newsService.deleteElementInNews(id);
        List<News> newsListAfterDelete = newsService.listNews();

        // then
        assertNotEquals(newsListBeforeDelete, newsListAfterDelete);
        assertEquals(newsListBeforeDelete.size(), 2);
        assertEquals(newsListAfterDelete.size(), 1);
    }

    @Test
    void should_be_able_to_edit_post() {
        // given
        long id = 1;
        News newsById = newsService.getNewsPostById(id);

        // when
        newsById.setNewsTitle("New Title");
        newsById.setDescription("New description");
        newsById.setNewsAuthor("John Doe");
        newsService.editNews(newsById);
        News newsPostByIdAfterEdit = newsService.getNewsPostById(id);

        // then
        assertAll("Group of assertions for edited News Post",
                () -> assertEquals(newsPostByIdAfterEdit.getNewsTitle(), "New Title"),
                () -> assertEquals(newsPostByIdAfterEdit.getDescription(), "New description"),
                () -> assertEquals(newsPostByIdAfterEdit.getNewsAuthor(), "John Doe")
        );
    }
}