package com.beta.replyservice.ruleservice.operation;

import java.util.List;

public interface RuleFacade {

    String transform(String string, List<Integer> opIndexes);
}
