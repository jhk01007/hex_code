package com.example.hex_code.account.application.port.out;

import com.example.hex_code.account.domain.Account;

public interface UpdateAccountStatePort {
    void updateActivities(Account account);
}
