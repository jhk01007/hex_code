package com.example.hex_code.account.application.port.in;

import com.example.hex_code.account.domain.Account;
import com.example.hex_code.account.domain.Account.AccountId;
import com.example.hex_code.account.domain.Money;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(AccountId accountId);
}
