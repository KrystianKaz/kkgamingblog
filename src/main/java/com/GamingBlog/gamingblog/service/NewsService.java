package com.GamingBlog.gamingblog.service;

import com.GamingBlog.gamingblog.exception.RequestNotFoundException;
import com.GamingBlog.gamingblog.model.News;
import com.GamingBlog.gamingblog.repository.NewsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class NewsService {

    private final NewsRepository newsRepository;

    public NewsService(final NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<News> listNews() {
        List<News> newsList = newsRepository.findAll();
        Collections.reverse(newsList);
        return newsList;
    }

    public Page<News> pageAllNews(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }

    public void addNews(News news) {
        newsRepository.save(news);
    }

    public News getNewsPostById(Long id) {
        return newsRepository.findById(id).orElseThrow(() -> new RequestNotFoundException(id));
    }

    public void editNews(News news) {
        newsRepository.save(news);
    }

    public void deleteElementInNews(Long id) {
        newsRepository.deleteById(id);
    }
}
