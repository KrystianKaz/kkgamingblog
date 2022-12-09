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
public class GameController {

    private final GameService gameService;
    private final StorageService storageService;
    private final CommentService commentService;
    private final NewsService newsService;
    private final UserService userService;

    @GetMapping("/games")
    public String games(Model model, @PageableDefault(size = 4) Pageable pageable,
                        @RequestParam(defaultValue = "0") int page, Principal principal) {

        List<Game> gameList = gameService.listGames();
        model.addAttribute("game", gameList);

        List<Comment> commentsList = commentService.listCommentsOfTheGamePost();
        model.addAttribute("commentsList", commentsList);
        model.addAttribute("comments", commentService);
        model.addAttribute("gameTitleById", gameService);
        model.addAttribute("pageNumber", page);

        model.addAttribute("service", userService);

        Page<Game> gamePages = gameService.pageAllGames(pageable);
        model.addAttribute("totalPages", gamePages.getTotalPages());

        List<News> newsList = newsService.listNews();
        model.addAttribute("news", newsList);

        int newsSize = newsService.listNews().size();
        model.addAttribute("newsSize", newsSize);


        if(principal != null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }

        return "game";
    }

    @GetMapping("/post/{id}")
    public String readGamePost(@PathVariable("id") Long id, Model model,
                               @RequestParam(defaultValue = "0") int page, Principal principal) {

        Game game = gameService.getPostById(id);
        model.addAttribute("gamePost", game);

        List<Comment> comments = commentService.listCommentsOfTheGamePost();
        List<Comment> commentsByGameId = commentService.getCommentsByGameId(comments, id);
        model.addAttribute("com", commentService
                .getCommentsToThePostIntoSublist(commentsByGameId, page));

        model.addAttribute("service", userService);

        Page<Comment> commentPage = commentService.pageFromListOfCommentsToGivenPost(commentsByGameId);
        model.addAttribute("data", commentPage.getContent());
        model.addAttribute("pageNumber", page);
        model.addAttribute("totalPages", commentPage.getTotalPages());
        model.addAttribute("totalElements", commentPage.getTotalElements());
        model.addAttribute("size", commentPage.getNumberOfElements());

        if(principal != null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }

        return "singleGamePost";
    }


    // Create
    @PostMapping("/addGame")
    public RedirectView addGamePost(@ModelAttribute Game game) {
        gameService.addGame(game);
        return new RedirectView("/games");
    }

    //Read
    @GetMapping("/addGame")
    public String addGamePost(Model model) {
        Path root = Paths.get("src", "main/webapp/resources/assets/img/post");
        List<String> strings = storageService.listFilesNames(root).stream().toList();
        model.addAttribute("allFiles", strings);
        return "/game/addGame";
    }

    @PostMapping("/addCommentToTheGamePost")
    public RedirectView comment(Comment comment, Principal principal) {
        commentService.addComment(comment, principal.getName());
        String path = "/post/" + comment.getGameId();
        return new RedirectView(path);
    }

    @GetMapping("/gamesList")
    public String editGamePost(Pageable pageable, Model model, Principal principal) {
        Page<Game> gamePageList = gameService.pageAllGames(pageable);
        model.addAttribute("games", gamePageList.getContent());

        if(principal != null) {
            String name = principal.getName();
            model.addAttribute("user", name);
        }

        return "game/editGame-list";
    }

    @GetMapping("/editGame/{id}")
    public String editGamePost(@PathVariable("id") Long id, Model model) {
        Path root = Paths.get("src", "main/webapp/resources/assets/img/post");
        List<String> strings = storageService.listFilesNames(root).stream().toList();
        model.addAttribute("allFiles", strings);
        Game game = gameService.getPostById(id);
        model.addAttribute("editGamePost", game);
        return "game/editGame";
    }

    @PostMapping("/editGame/{id}")
    public RedirectView editGamePost(Game game) {
        gameService.editGamePost(game);
        return new RedirectView("/games");
    }

    //DELETE CURRENT GAME POST (COMMENTS INCLUDED!!)
    @PostMapping("/deleteGame/{id}")
    public RedirectView deleteGamePostWithComments(@PathVariable("id") Long id) {
        List<Comment> commentList = commentService.listCommentsOfTheGamePost();
        List<Comment> commentsByGameId = commentService.getCommentsByGameId(commentList, id);
        commentService.deleteCommentsByGameId(commentsByGameId, id);
        gameService.deleteElementInGames(id);
        return new RedirectView("/editGame");
    }
}
