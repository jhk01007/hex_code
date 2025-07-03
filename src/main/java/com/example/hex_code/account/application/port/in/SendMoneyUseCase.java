package com.example.hex_code.account.application.port.in;


import java.security.PublicKey;

public interface SendMoneyUseCase {

    public boolean sendMoney(SendMoneyCommand command);
}
