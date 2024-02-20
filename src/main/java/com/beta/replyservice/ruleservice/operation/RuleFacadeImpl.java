package com.beta.replyservice.ruleservice.operation;

import com.beta.replyservice.ruleservice.Rule;
import com.beta.replyservice.ruleservice.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
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
                return null;
        }
    }

    @Override
    public String transform(String string, List<Integer> opIndexes) {
        StringBuilder mutableString = new StringBuilder(string);
        List<Rule> rules = ruleService.getRulesByIndices(opIndexes);
        if (rules.size() != opIndexes.size()) {
            return null;
        }
        for (int index: opIndexes) {
            Optional<Rule> rule = rules.stream()
                    .filter(obj -> obj.getIndex() == index)
                    .findFirst();
            if (rule.isPresent()) {
                String modifiedString = this.applyOperation(mutableString.toString(), rule.get().getOperation());
                if (modifiedString != null) {
                    mutableString = new StringBuilder(modifiedString);
                }
            }
        }
        return mutableString.toString();
    }
}
