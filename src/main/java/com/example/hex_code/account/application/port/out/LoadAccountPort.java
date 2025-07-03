package com.example.hex_code.account.application.port.out;

import com.example.hex_code.account.domain.Account;
import com.example.hex_code.account.domain.Account.AccountId;

import java.time.LocalDateTime;

public interface LoadAccountPort {
    Account loadAccount(AccountId accountId, LocalDateTime now);
}
