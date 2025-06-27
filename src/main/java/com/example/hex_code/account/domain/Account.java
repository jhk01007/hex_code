package com.example.hex_code.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 일정 금액을 보유한 계좌를 나타냅니다.
 * {@link Account} 객체는 최근의 계좌 활동(Activity)만을 포함하는 창(window)을 갖고 있습니다.
 * 계좌의 총 잔액은 이 창의 첫 번째 활동 이전의 기준 잔액(baseline balance)과
 * 활동 값들의 합으로 계산됩니다.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    /**
     * 계좌의 고유 ID입니다.
     */
    private final AccountId id;

    /**
     * 기준 잔액입니다. 이 잔액은 activityWindow의 첫 번째 활동 이전 시점의 계좌 잔액입니다.
     */
    private final Money baselineBalance;

    /**
     * 이 계좌에 대한 최신 활동들의 창입니다.
     */
    private final ActivityWindow activityWindow;

    /**
     * ID 없이 {@link Account} 엔티티를 생성합니다.
     * 아직 영속되지 않은 새 엔티티를 생성할 때 사용합니다.
     */
    public static Account withoutId(
            Money baselineBalance,
            ActivityWindow activityWindow) {
        return new Account(null, baselineBalance, activityWindow);
    }

    /**
     * ID와 함께 {@link Account} 엔티티를 생성합니다.
     * 이미 영속화된 엔티티를 복원할 때 사용합니다.
     */
    public static Account withId(
            AccountId accountId,
            Money baselineBalance,
            ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    public Optional<AccountId> getId(){
        return Optional.ofNullable(this.id);
    }

    /**
     * 활동 값들을 기준 잔액에 더하여 총 잔액을 계산합니다.
     */
    public Money calculateBalance() {
        return Money.add(
                this.baselineBalance,
                this.activityWindow.calculateBalance(this.id));
    }

    /**
     * 지정된 금액을 계좌에서 출금하려 시도합니다.
     * 성공하면, 음수 값의 새로운 활동(Activity)을 생성합니다.
     * @return 출금이 성공하면 true, 실패하면 false를 반환합니다.
     */
    public boolean withdraw(Money money, AccountId targetAccountId) {

        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawal = new Activity(
                this.id,
                this.id,
                targetAccountId,
                LocalDateTime.now(),
                money);
        this.activityWindow.addActivity(withdrawal);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(
                        this.calculateBalance(),
                        money.negate())
                .isPositiveOrZero();
    }

    /**
     * 지정된 금액을 계좌에 입금하려 시도합니다.
     * 성공하면, 양수 값의 새로운 활동(Activity)을 생성합니다.
     * @return 입금이 성공하면 true, 실패하면 false를 반환합니다.
     */
    public boolean deposit(Money money, AccountId sourceAccountId) {
        Activity deposit = new Activity(
                this.id,
                sourceAccountId,
                this.id,
                LocalDateTime.now(),
                money);
        this.activityWindow.addActivity(deposit);
        return true;
    }

    @Value
    public static class AccountId {
        private Long value;
    }

}