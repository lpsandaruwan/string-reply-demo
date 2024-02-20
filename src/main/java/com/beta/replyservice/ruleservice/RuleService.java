package com.beta.replyservice.ruleservice;

import com.beta.replyservice.ruleservice.operation.RuleFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RuleService {

    private final RuleRepository ruleRepository;
    private final RuleFacadeImpl ruleFacade;

    @Autowired
    public RuleService(RuleRepository ruleRepository, RuleFacadeImpl ruleFacade) {
        this.ruleRepository = ruleRepository;
        this.ruleFacade = ruleFacade;
    }


    public Rule getRuleByIndex(int index) {
        return this.ruleRepository.findOneByIndex(index);
    }

    public List<Rule> getRulesByIndices(List<Integer> indices) {
        return this.ruleRepository.findAllByIndexIn(indices);
    }
}
