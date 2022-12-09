package com.GamingBlog.gamingblog.controller;

import com.GamingBlog.gamingblog.model.Comment;
import com.GamingBlog.gamingblog.model.Game;
import com.GamingBlog.gamingblog.model.News;
import com.GamingBlog.gamingblog.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class NewsController {

    private final NewsService newsService;
    private final StorageService storageService;
    private final CommentService commentService;
    private final GameService gameService;
    private final UserService userService;


    @GetMapping("/news")
    public String news(Model model, @PageableDefault(size = 4) Pageable pageable,
                       @RequestParam(defaultValue = "0") int page, Principal principal) {

        List<News> newsList = newsService.listNews();
        model.addAttribute("news", newsList);


        List<Comment> commentsList = commentService.listCommentsOfTheNewsPost();
        model.addAttribute("commentsList", commentsList);
        model.addAttribute("comments", commentService);
        model.addAttribute("newsTitleById", newsService);
        model.addAttribute("pageNumber", page);

        model.addAttribute("service", userService);

        Page<News> newsPages = newsService.pageAllNews(pageable);
        model.addAttribute("totalPages", newsPages.getTotalPages());

        List<Game> gameList = gameService.listGames();
        model.addAttribute("games", gameList);

        int gamesSize = gameService.listGames().size();
        model.addAttribute("gamesSize", gamesSize);

        if(principal != null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }

        return "news";
    }

    @GetMapping("/news/{id}")
    public String readNewsPost(@PathVariable("id") Long id, Model model,
                               @RequestParam(defaultValue = "0") int page, Principal principal) {

        News news = newsService.getNewsPostById(id);
        model.addAttribute("news", news);

        List<Comment> comments = commentService.listCommentsOfTheNewsPost();
        List<Comment> commentsByNewsId = commentService.getCommentsByNewsId(comments, id);
        model.addAttribute("com", commentService
                .getCommentsToThePostIntoSublist(commentsByNewsId, page));

        model.addAttribute("service", userService);

        Page<Comment> commentPage = commentService.pageFromListOfCommentsToGivenPost(commentsByNewsId);
        model.addAttribute("data", commentPage.getContent());
        model.addAttribute("pageNumber", page);
        model.addAttribute("totalPages", commentPage.getTotalPages());
        model.addAttribute("totalElements", commentPage.getTotalElements());
        model.addAttribute("size", commentPage.getNumberOfElements());

        if(principal != null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }

        return "singleNewsPost";
    }

    @GetMapping("/addNews")
    public String addGamePost(Model model) {
        Path root = Paths.get("src", "main/webapp/resources/assets/img/post");
        List<String> strings = storageService.listFilesNames(root).stream().toList();
        model.addAttribute("allFiles", strings);
        return "/news/addNews";
    }

    @PostMapping("/addNews")
    public RedirectView addNewsPost(@ModelAttribute News news) {
        newsService.addNews(news);
        return new RedirectView("/news");
    }

    @PostMapping("/addCommentToTheNews")
    public RedirectView comment(Comment comment, Principal principal) {
        commentService.addComment(comment, principal.getName());
        String path = "/news/" + comment.getNewsId();
        return new RedirectView(path);
    }

    @GetMapping("/newsList")
    public String editGamePost(Pageable pageable, Model model, Principal principal) {
        Page<News> newsPageList = newsService.pageAllNews(pageable);
        model.addAttribute("news", newsPageList.getContent());

        if(principal != null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }

        return "news/editNews-list";
    }

    @GetMapping("/editNews/{id}")
    public String editGamePost(@PathVariable("id") Long id, Model model) {
        Path root = Paths.get("src", "main/webapp/resources/assets/img/post");
        List<String> strings = storageService.listFilesNames(root).stream().toList();
        model.addAttribute("allFiles", strings);
        News news = newsService.getNewsPostById(id);
        model.addAttribute("editNewsPost", news);
        return "news/editNews";
    }

    @PostMapping("/editNews/{id}")
    public RedirectView editGamePost(News news) {
        newsService.editNews(news);
        return new RedirectView("/news");
    }

    //DELETE CURRENT NEWS POST (COMMENTS INCLUDED!!)
    @PostMapping("/deleteNews/{id}")
    public RedirectView deleteGamePostWithComments(@PathVariable("id") Long id) {
        List<Comment> commentList = commentService.listCommentsOfTheNewsPost();
        List<Comment> commentsByNewsId = commentService.getCommentsByNewsId(commentList, id);
        commentService.deleteCommentsByNewsId(commentsByNewsId, id);
        newsService.deleteElementInNews(id);
        return new RedirectView("/newsList");
    }
}
