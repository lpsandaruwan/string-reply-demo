package com.beta.replyservice.controllers;

import com.beta.replyservice.message.ReplyMessageService;
import com.beta.replyservice.message.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class ReplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReplyMessageService replyMessageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testReplyingWithEmptyMessage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/reply")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("An empty message has been provided!"));
    }

    @Test
    void testReplyingWithMessage() throws Exception {
        // Given
        String message = "13-hello";
        Result result = new Result(true, 200, "OLLEH");
        when(replyMessageService.transformMessage(message)).thenReturn(result);

        // When and Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/reply/{message}", message)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("OLLEH"));
    }
}
