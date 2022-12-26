package com.site.gamingblog.service;

import com.site.gamingblog.model.Comment;
import com.site.gamingblog.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(final CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> listCommentsOfTheGamePost() {
        List<Comment> commentList = commentRepository.findAll()
                .stream()
                .filter(gid -> gid.getGameId() != null)
                .collect(Collectors.toList());
        Collections.reverse(commentList);
        return commentList;
    }

    public List<Comment> listCommentsOfTheNewsPost() {
        List<Comment> commentList = commentRepository.findAll()
                .stream()
                .filter(nid -> nid.getNewsId() != null)
                .collect(Collectors.toList());
        Collections.reverse(commentList);
        return commentList;
    }

    public void addComment(Comment comment,String name) {
        comment.setUsername(name);
        commentRepository.save(comment);
    }

    public List<Comment> getCommentsByGameId(List<Comment> commentList, Long id) {
        return commentList.stream()
                .filter(gid -> gid.getGameId().equals(id))
                .collect(Collectors.toList());
    }

    public List<Comment> getCommentsByNewsId(List<Comment> commentList, Long id) {
        return commentList.stream()
                .filter(gid -> gid.getNewsId().equals(id))
                .collect(Collectors.toList());
    }


    public void deleteCommentsByGameId(List<Comment> commentList, Long id) {
        List<Comment> commentsByGameId = getCommentsByGameId(commentList, id);
        commentRepository.deleteAll(commentsByGameId);
    }

    public void deleteCommentsByNewsId(List<Comment> commentList, Long id) {
        List<Comment> commentsByNewsId = getCommentsByNewsId(commentList, id);
        commentRepository.deleteAll(commentsByNewsId);
    }

    //USED in game.jsp to show number of comments
    public int getCommentsQuantityToTheGamePost(Long attribute) {
        return commentRepository.findAll().stream()
                .filter(gid -> gid.getGameId() != null)
                .filter(gid -> gid.getGameId().equals(attribute))
                .toList().size();
    }

    //USED in news.jsp to show number of comments
    public int getCommentsQuantityToTheNews(Long attribute) {
        return commentRepository.findAll().stream()
                .filter(gid -> gid.getNewsId() != null)
                .filter(gid -> gid.getNewsId().equals(attribute))
                .toList().size();
    }


    public List<Comment> getCommentsToThePostIntoSublist(List<Comment> comments, int number) {
        return comments.subList(0 + number * 5, Math.min(5 + number * 5, comments.size()));
    }


    public Page<Comment> pageFromListOfCommentsToGivenPost(List<Comment> commentList) {
        Pageable pageable = PageRequest.of(0, 5);
        return new PageImpl<>(commentList, pageable, commentList.size());
    }

}
