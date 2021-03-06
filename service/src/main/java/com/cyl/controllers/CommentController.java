package com.cyl.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cyl.enums.ErrorCode;
import com.cyl.enums.ErrorMessage;
import com.cyl.enums.Read;
import com.cyl.exceptions.UnAuthorizedException;
import com.cyl.models.Comment;
import com.cyl.models.User;
import com.cyl.services.AuthenticationUserService;
import com.cyl.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by cyl on 2019-03-03.
 */
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private AuthenticationUserService authenticationUserService;

    @ResponseBody
    @GetMapping("/comments/{goodsId}")
    public ResponseEntity<?> getComments(@PathVariable Integer goodsId) {
        List list = commentService.getComments(goodsId);
        return ResponseEntity.ok(list);
    }

    /**
     * @param userId
     * @param replyId
     * @param jsonNode {
    "content" : "12121111111111sdwd",
    "goodsId" : 1,
    "reply_comment_id" : 2
    }
     * @return
     */
    @ResponseBody
    @PostMapping("/comment/add/{userId}/reply/{replyId}")
    public ResponseEntity<?> addComment(@PathVariable(value = "userId") Integer userId,
                                        @PathVariable(value = "replyId") Integer replyId,
                                        @RequestBody JsonNode jsonNode) {
        Comment comment = new Comment();
        User commentUser = authenticationUserService.getUser(userId);
        User replyUser = authenticationUserService.getUser(replyId);
        try {
            comment = new ObjectMapper().readValue(jsonNode.traverse(), Comment.class);
//            String content = jsonNode.path("content").textValue();
//            Integer goodsId = Integer.valueOf(jsonNode.path("goodsId").textValue());
//            comment.setContent(content);
//            comment.setGoodsId(goodsId);




//            comment.setReplyCommentId(replyCommentId);
            comment.setCommentDate(new Date());
            comment.setUser(commentUser);
            comment.setReplyUser(replyUser);
            comment.setRead(Read.UNREAD.getRead());
        } catch (Exception e) {
            throw new UnAuthorizedException(ErrorCode.JSON_TO_OBJECT_ERROR, ErrorMessage.ERROR_CHANGE_TYPE);
        }
        commentService.addComment(comment);
        return ResponseEntity.ok(null);
    }


    @ResponseBody
    @PostMapping("/comment/delete/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "commentId") Integer commentId) {

        Comment comment = commentService.getOneComment(commentId);
        commentService.deleteComment(comment);
        return ResponseEntity.ok(null);
    }

    @ResponseBody
    @GetMapping("/comments/unread/count/{userId}")
    public ResponseEntity<?> getUnreadCount(@PathVariable Integer userId) {
        Long count = commentService.getUnreadCount(userId);
        return ResponseEntity.ok(count);
    }

    @ResponseBody
    @GetMapping("/comments/unread/{userId}")
    public ResponseEntity<?> getUnreadComment(@PathVariable Integer userId) {
        List list = commentService.getUnreadComment(userId);
        return ResponseEntity.ok(list);
    }

    @ResponseBody
    @GetMapping("/comments/isRead/{commentId}")
    public ResponseEntity<?> commentIsRead(@PathVariable Integer commentId) {
        commentService.commentIsRead(commentId);
        return ResponseEntity.ok(null);
    }


    /*某用户的所有评论
    * */
    @ResponseBody
    @GetMapping("/comments/user/{userId}")
    public ResponseEntity<?> getUserComment(@PathVariable Integer userId) {
        List list = commentService.getUserComment(userId);
        return ResponseEntity.ok(list);
    }


}
