package com.example.hex_code.account.application.port;

import com.example.hex_code.account.application.port.in.SendMoneyUseCase;
import com.example.hex_code.account.application.port.out.LoadAccountPort;
import com.example.hex_code.account.application.port.out.UpdateAccountStatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendMoneyService implements SendMoneyUseCase {

    private final LoadAccountPort loadAccountPort;
    private final UpdateAccountStatePort updateAccountStatePort;
}
