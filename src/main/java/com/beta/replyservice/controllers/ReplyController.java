package com.beta.replyservice.controllers;

import com.beta.replyservice.message.ReplyMessage;
import com.beta.replyservice.message.ReplyMessageService;
import com.beta.replyservice.message.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${com.beta.reply-message.path}")
@Slf4j
public class ReplyController {

    private final ReplyMessageService replyMessageService;

    @Autowired
    public ReplyController(ReplyMessageService replyMessageService) {
        this.replyMessageService = replyMessageService;
    }

    @GetMapping("/reply")
    public ResponseEntity<ReplyMessage> replying() {
        log.warn("Request received with an empty message!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ReplyMessage("An empty message has been provided!"));
    }

    @GetMapping("/reply/{message}")
    public ResponseEntity<ReplyMessage> replying(@PathVariable String message) {
        log.debug("Request received to transform text message!");
        Result result = this.replyMessageService.transformMessage(message);
        return ResponseEntity.status(result.getStatusCode()).body(new ReplyMessage(result.getMessage()));
    }
}
