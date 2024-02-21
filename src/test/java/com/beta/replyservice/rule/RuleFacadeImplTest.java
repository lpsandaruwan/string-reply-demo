package com.beta.replyservice.rule;

import com.beta.replyservice.message.Result;
import com.beta.replyservice.rule.operation.Operations;
import com.beta.replyservice.rule.operation.RuleFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class RuleFacadeImplTest {

    @Autowired
    private RuleFacade ruleFacade;

    @Mock
    private Operations operations;

    @Mock
    private RuleService ruleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTransform() {
        // Given
        String inputString = "hello";
        List<Integer> opIndexes = Arrays.asList(1, 3);
        Rule rule1 = new Rule();
        rule1.setId(1L);
        rule1.setOperation("REVERSE");
        rule1.setIndex(1);

        Rule rule2 = new Rule();
        rule1.setId(2L);
        rule1.setOperation("MD5");
        rule1.setIndex(2);

        Rule rule3 = new Rule();
        rule1.setId(3L);
        rule1.setOperation("MD5");
        rule1.setIndex(3);

        List<Rule> rules = Arrays.asList(rule1, rule2, rule3);
        when(ruleService.getRulesByIndices(opIndexes)).thenReturn(rules);
        when(operations.toReverseString(inputString)).thenReturn("olleh");
        when(operations.toMd5String("olleh")).thenReturn("5d41402abc4b2a76b9719d911017c592");
        when(operations.toUpperCase("olleh")).thenReturn("OLLEH");

        // When
        Result result = ruleFacade.transform(inputString, opIndexes);

        // Then
        assertNotNull(result);
        assertEquals(true, result.getSuccess());
        assertEquals(200, result.getStatusCode());
        assertEquals("OLLEH", result.getMessage());
    }
}
