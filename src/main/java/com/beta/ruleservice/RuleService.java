package com.beta.ruleservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RuleService {

    private final RuleRepository ruleRepository;

    @Autowired
    public RuleService(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }


    public Rule getRuleByIndex(int index) {
        return this.ruleRepository.findOneByIndex(index);
    }
}
