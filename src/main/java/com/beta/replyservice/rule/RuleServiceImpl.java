package com.beta.replyservice.rule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RuleServiceImpl implements RuleService {

    private final RuleRepository ruleRepository;

    @Autowired
    public RuleServiceImpl(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    public Rule getRuleByIndex(int index) {
        return this.ruleRepository.findOneByIndex(index);
    }

    public List<Rule> getRulesByIndices(List<Integer> indices) {
        return this.ruleRepository.findAllByIndexIn(indices);
    }

    public List<RuleDAO> getRules() {
        List<Rule> rules = this.ruleRepository.findAll();
        return rules.stream().map(
                rule -> new RuleDAO(rule.getIndex(), rule.getOperation(), rule.getDescription()))
                .collect(Collectors.toList());
    }
}
