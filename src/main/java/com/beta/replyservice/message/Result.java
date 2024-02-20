package com.beta.replyservice.message;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Result {

    private Boolean success;
    private Integer statusCode;
    private String message;
}
