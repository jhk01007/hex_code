package com.example.hex_code.account.domain;

import com.example.hex_code.account.domain.Account;
import com.example.hex_code.account.domain.Money;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * {@link Account} 간의 금전 이체 활동을 나타냅니다.
 */
@Value
@RequiredArgsConstructor
public class Activity {

    private ActivityId id;

    /**
     * 이 활동을 소유한 계좌입니다.
     */
    @NonNull
    private final Account.AccountId ownerAccountId;

    /**
     * 출금된(차감된) 계좌입니다.
     */
    @NonNull
    private final Account.AccountId sourceAccountId;

    /**
     * 입금된(가산된) 계좌입니다.
     */
    @NonNull
    private final Account.AccountId targetAccountId;

    /**
     * 이 활동이 발생한 시간입니다.
     */
    @NonNull
    private final LocalDateTime timestamp;

    /**
     * 계좌 간에 이체된 금액입니다.
     */
    @NonNull
    private final Money money;

    public Activity(
            @NonNull Account.AccountId ownerAccountId,
            @NonNull Account.AccountId sourceAccountId,
            @NonNull Account.AccountId targetAccountId,
            @NonNull LocalDateTime timestamp,
            @NonNull Money money) {
        this.id = null;
        this.ownerAccountId = ownerAccountId;
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.timestamp = timestamp;
        this.money = money;
    }

    /**
     * 활동의 고유 ID를 나타냅니다.
     */
    @Value
    public static class ActivityId {
        private final Long value;
    }

}