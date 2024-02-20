package com.beta.replyservice.rule.operation;

import com.beta.replyservice.message.Result;

import java.util.List;

public interface RuleFacade {

    Result transform(String string, List<Integer> opIndexes);
}
