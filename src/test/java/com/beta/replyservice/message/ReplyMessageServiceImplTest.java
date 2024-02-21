package com.beta.replyservice.message;

import com.beta.replyservice.rule.operation.RuleFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReplyMessageServiceImplTest {

    @Autowired
    private ReplyMessageService replyMessageService;

    @Mock
    private RuleFacade ruleFacade;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTransformMessage() {
        // Given
        String inputString = "13-hello";
        String transformedString = "OLLEH";
        Result expectedResult = new Result(true, 200, transformedString);
        when(ruleFacade.transform("hello", Arrays.asList(1, 2))).thenReturn(expectedResult);

        // When
        Result result = replyMessageService.transformMessage(inputString);

        // Then
        assertEquals(expectedResult, result);
    }
}
