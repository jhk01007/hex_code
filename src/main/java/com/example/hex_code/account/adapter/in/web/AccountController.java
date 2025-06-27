package com.example.hex_code.account.adapter.in.web;

import com.example.hex_code.account.application.port.in.SendMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final SendMoneyUseCase sendMoneyUseCase;
}
