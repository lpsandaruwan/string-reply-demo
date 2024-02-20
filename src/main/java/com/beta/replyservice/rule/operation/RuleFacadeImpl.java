package com.beta.replyservice.rule.operation;

import com.beta.replyservice.message.Result;
import com.beta.replyservice.rule.Rule;
import com.beta.replyservice.rule.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class RuleFacadeImpl implements RuleFacade {

    private final Operations operations;
    private final RuleService ruleService;

    @Autowired
    public RuleFacadeImpl(Operations operations, RuleService ruleService) {
        this.operations = operations;
        this.ruleService = ruleService;
    }

    private String applyOperation(String string, String operation) {
        switch (operation) {
            case "REVERSE":
                return this.operations.toReverseString(string);
            case "MD5":
                return this.operations.toMd5String(string);
            case "UPPERCASE":
                return this.operations.toUpperCase(string);
            default:
                log.error("Invalid operation code provided: {}", operation);
                return null;
        }
    }

    @Override
    public Result transform(String string, List<Integer> opIndexes) {
        StringBuilder mutableString = new StringBuilder(string);
        List<Rule> rules = ruleService.getRulesByIndices(opIndexes);
        if (rules.size() != opIndexes.size()) {
            return new Result(false, 400, "Invalid operation indices!");
        }
        for (int index : opIndexes) {
            Optional<Rule> rule = rules.stream()
                    .filter(obj -> obj.getIndex() == index)
                    .findFirst();
            if (rule.isPresent()) {
                String modifiedString = this.applyOperation(mutableString.toString(), rule.get().getOperation());
                if (modifiedString != null) {
                    mutableString = new StringBuilder(modifiedString);
                } else {
                    log.debug("string to transform: {}", mutableString);
                    log.error("Error occurred while transforming text!");
                    return new Result(false, 500, "Internal server error!");
                }
            } else {
                log.error("Could not find rule for index {}", index);
                return new Result(false, 400, "Invalid operation indices!");
            }
        }
        log.info("Successfully transformed the text: {}", string);
        return new Result(true, 200, mutableString.toString());
    }
}
