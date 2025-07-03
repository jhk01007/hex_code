package com.example.hex_code.account.application.port.service;

import com.example.hex_code.account.application.port.in.SendMoneyCommand;
import com.example.hex_code.account.application.port.in.SendMoneyUseCase;
import com.example.hex_code.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class SendMoneyService implements SendMoneyUseCase {

    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        // TODO: 비즈니스  규칙 검증
        // TODO: 모델 상태 조작
        // TODO: 출력값 반환
        return false;
    }

}