package com.mock.jwttesting.controller;

import com.mock.jwttesting.model.ItemRequest;
import com.mock.jwttesting.model.ListRequest;
import com.mock.jwttesting.model.PayloadResponse;
import com.mock.jwttesting.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    private TokenService service;

    @PostMapping("/add/item")
    public ResponseEntity<PayloadResponse> addTokenItem(@RequestHeader("Authorization") String token,
                                                        @RequestBody ItemRequest request) throws ParseException {
        PayloadResponse payload = service.addTokenItem(token, request.getKey(), request.getValue());
        return ResponseEntity.ok(payload);
    }

    @PostMapping("/add/list")
    public ResponseEntity<PayloadResponse> addTokenList(@RequestHeader("Authorization") String token,
                                                        @RequestBody ListRequest request) throws ParseException {
        PayloadResponse payload = service.addTokenList(token, request.getKey(), request.getValues());
        return ResponseEntity.ok(payload);
    }

    @PostMapping("/remove/item")
    public ResponseEntity<PayloadResponse> removeTokenItem(@RequestHeader("Authorization") String token,
                                                           @RequestBody ItemRequest request) throws ParseException {
        PayloadResponse payload = service.removeTokenItem(token, request.getKey());
        return ResponseEntity.ok(payload);
    }
}
