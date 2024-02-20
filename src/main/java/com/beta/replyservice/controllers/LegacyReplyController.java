package com.beta.replyservice.controllers;

import com.beta.replyservice.message.ReplyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("${com.beta.reply-message.path.legacy}")
@Slf4j
public class LegacyReplyController {

	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		log.debug("Legacy: Request received to transform text message!");
		return new ReplyMessage(message);
	}
}