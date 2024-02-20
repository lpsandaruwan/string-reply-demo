package com.beta.replyservice.rule;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RuleDAO {

    private Integer index;

    private String operation;

    private String description;
}
