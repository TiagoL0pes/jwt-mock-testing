package com.mock.jwttesting.service;

import com.mock.jwttesting.model.PayloadResponse;
import com.nimbusds.jose.util.Base64URL;
import lombok.SneakyThrows;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.nimbusds.jwt.SignedJWT.parse;
import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {

    private TokenService service;

    private String TOKEN;

    @BeforeEach
    public void setup() {
        service = new TokenService();
        TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ." +
                "SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
    }

    @SneakyThrows
    @Test
    public void shouldAddNewTokenItem() {
        String key = "newKey";
        String value = "newValue";

        PayloadResponse response = service.addTokenItem(TOKEN, key, value);

        assertNotNull(response);
        assertTrue(isItemExist(response.getToken(), key));
    }

    @SneakyThrows
    @Test
    public void shouldAddNewTokenList() {
        String key = "newKey";
        String[] values = {"item0", "item1"};

        PayloadResponse response = service.addTokenList(TOKEN, key, values);

        assertNotNull(response);
        assertTrue(isItemExist(response.getToken(), key));
    }

    @SneakyThrows
    @Test
    public void removeTokenItem() {
        String key = "name";
        PayloadResponse response = service.removeTokenItem(TOKEN, key);

        assertNotNull(response);
        assertFalse(isItemExist(response.getToken(), key));
    }

    @SneakyThrows
    private boolean isItemExist(String token, String key) {
        Base64URL[] parts = parse(token).getParsedParts();
        JSONObject json = parse(token).getPayload().toJSONObject();
        return nonNull(json.get(key));
    }

}
