package com.beta.replyservice.rule;

import java.util.List;

public interface RuleService {

    Rule getRuleByIndex(int index);

    List<Rule> getRulesByIndices(List<Integer> indices);

    List<RuleDAO> getRules();
}
