package com.example.hex_code.account.application.port.service;

import com.example.hex_code.account.application.port.in.GetAccountBalanceQuery;
import com.example.hex_code.account.application.port.out.LoadAccountPort;
import com.example.hex_code.account.domain.Account.AccountId;
import com.example.hex_code.account.domain.Money;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {

    private final LoadAccountPort loadAccountPort;

    @Override
    public Money getAccountBalance(AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
                .calculateBalance();
    }
}
