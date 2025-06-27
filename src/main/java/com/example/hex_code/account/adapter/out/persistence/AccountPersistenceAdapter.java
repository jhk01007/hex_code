package com.example.hex_code.account.adapter.out.persistence;

import com.example.hex_code.account.application.port.out.LoadAccountPort;
import com.example.hex_code.account.application.port.out.UpdateAccountStatePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {

    private final SpringDataAccountRepository springDataAccountRepository;

}
