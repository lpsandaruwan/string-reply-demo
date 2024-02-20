package com.beta.replyservice.controllers;

import com.beta.replyservice.rule.RuleDAO;
import com.beta.replyservice.rule.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${com.beta.reply-message.path}")
public class RulesController {

    private final RuleService ruleService;

    @Autowired
    public RulesController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("/rules")
    public ResponseEntity<List<RuleDAO>> getAllRules() {
        List<RuleDAO> rules = this.ruleService.getRules();
        return ResponseEntity.status(200).body(rules);
    }
}
