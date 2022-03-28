package com.mock.jwttesting.service;

import com.mock.jwttesting.model.PayloadResponse;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.util.Base64URL;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.ParseException;

import static com.nimbusds.jwt.SignedJWT.parse;
import static java.util.Arrays.asList;

@Service
public class TokenService {

    public PayloadResponse addTokenItem(String token, String key, String value) throws ParseException {
        token = token.replaceAll("^Bearer ?", "");
        Base64URL[] parts = parse(token).getParsedParts();
        JSONObject json = parse(token).getPayload().toJSONObject();
        json.put(key, value);
        Base64URL newPayload = new Payload(json).toBase64URL();
        return rebuildToken(parts[0], newPayload, parts[2]);
    }

    public PayloadResponse addTokenList(String token, String key, String... values) throws ParseException {
        token = token.replaceAll("^Bearer ?", "");
        Base64URL[] parts = parse(token).getParsedParts();
        JSONObject json = parse(token).getPayload().toJSONObject();
        JSONArray array = new JSONArray();
        array.addAll(asList(values));
        json.put(key, array);
        Base64URL newPayload = new Payload(json).toBase64URL();
        return rebuildToken(parts[0], newPayload, parts[2]);
    }

    public PayloadResponse removeTokenItem(String token, String key) throws ParseException {
        token = token.replaceAll("^Bearer ?", "");
        Base64URL[] parts = parse(token).getParsedParts();
        JSONObject json = parse(token).getPayload().toJSONObject();
        json.remove(key);
        Base64URL newPayload = new Payload(json).toBase64URL();
        return rebuildToken(parts[0], newPayload, parts[2]);
    }

    private PayloadResponse rebuildToken(Base64URL header, Base64URL payload, Base64URL signature) {
        String newToken = new StringBuilder()
                .append(header)
                .append(".")
                .append(payload)
                .append(".")
                .append(signature)
                .toString();
        return new PayloadResponse(newToken);
    }

}
