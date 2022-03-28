package com.mock.jwttesting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mock.jwttesting.model.ItemRequest;
import com.mock.jwttesting.model.ListRequest;
import com.mock.jwttesting.model.PayloadResponse;
import com.mock.jwttesting.service.TokenService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
public class TokenControllerTest {

    @InjectMocks
    private TokenController controller;

    @Mock
    private TokenService service;

    private MockMvc mockMvc;

    private String TOKEN;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(controller).build();
        TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    }

    @SneakyThrows
    @Test
    public void shouldAddNewTokenItem() {
        ItemRequest request = new ItemRequest("newKey", "newValue");
        PayloadResponse response = new PayloadResponse(TOKEN);

        when(service.addTokenItem(anyString(), anyString(), anyString())).thenReturn(response);

        mockMvc.perform(post("/token/add/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void shouldAddNewTokenList() {
        ListRequest request = new ListRequest("newList", new String[]{"item0", "item1"});
        PayloadResponse response = new PayloadResponse(TOKEN);

        when(service.addTokenList(anyString(), anyString(), anyString(), anyString())).thenReturn(response);

        mockMvc.perform(post("/token/add/list")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void shouldRemoveSomeTokenItem() {
        ItemRequest request = new ItemRequest("name", "");
        PayloadResponse response = new PayloadResponse(TOKEN);

        when(service.removeTokenItem(anyString(), anyString())).thenReturn(response);

        mockMvc.perform(post("/token/remove/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request))
                .header("Authorization", TOKEN))
                .andExpect(status().isOk());
    }

    private String toJson(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{}";
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return json;
        } finally {
            return json;
        }
    }

}
