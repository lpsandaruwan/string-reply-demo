package com.beta.replyservice.message;

import com.beta.replyservice.rule.operation.RuleFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReplyMessageServiceImpl implements ReplyMessageService {

    private final RuleFacade ruleFacade;

    @Autowired
    public ReplyMessageServiceImpl(RuleFacade ruleFacade) {
        this.ruleFacade = ruleFacade;
    }

    public Result transformMessage(String inputString) {
        String regex = "\\d{2}-[a-z0-9]*";
        if (!inputString.matches(regex)) {
            log.error("Provided message does not follow format <integer><integer>-<text>");
            return new Result(false, 400, "Invalid input string format!");
        }
        String[] inputs = inputString.split("-");
        List<Integer> opIndices = inputs[0].chars()
                .map(Character::getNumericValue)
                .boxed()
                .collect(Collectors.toList());
        if (opIndices.stream().allMatch(index -> Objects.equals(index, opIndices.get(0)))) {
            log.warn("Equal indices received for transforming!");
            return new Result(false, 400, "Equal operation indices not allowed!");
        }
        return this.ruleFacade.transform(inputs[1], opIndices);
    }
}
