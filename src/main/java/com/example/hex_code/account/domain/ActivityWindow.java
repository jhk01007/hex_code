package com.example.hex_code.account.domain;

import com.example.hex_code.account.domain.Account;
import com.example.hex_code.account.domain.Activity;
import com.example.hex_code.account.domain.Money;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 계좌 활동의 창(window)을 나타냅니다.
 */
public class ActivityWindow {

    /**
     * 이 창에 포함된 계좌 활동들의 목록입니다.
     */
    private List<Activity> activities;

    /**
     * 이 창에서 가장 처음 발생한 활동의 시간입니다.
     */
    public LocalDateTime getStartTimestamp() {
        return activities.stream()
                .min(Comparator.comparing(Activity::getTimestamp))
                .orElseThrow(IllegalStateException::new)
                .getTimestamp();
    }

    /**
     * 이 창에서 가장 마지막에 발생한 활동의 시간입니다.
     * @return 마지막 활동의 시간
     */
    public LocalDateTime getEndTimestamp() {
        return activities.stream()
                .max(Comparator.comparing(Activity::getTimestamp))
                .orElseThrow(IllegalStateException::new)
                .getTimestamp();
    }

    /**
     * 이 창에 포함된 모든 활동의 금액을 합산하여 잔액을 계산합니다.
     */
    public Money calculateBalance(Account.AccountId accountId) {
        Money depositBalance = activities.stream()
                .filter(a -> a.getTargetAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        Money withdrawalBalance = activities.stream()
                .filter(a -> a.getSourceAccountId().equals(accountId))
                .map(Activity::getMoney)
                .reduce(Money.ZERO, Money::add);

        return Money.add(depositBalance, withdrawalBalance.negate());
    }

    /**
     * 활동 리스트로 ActivityWindow를 생성합니다.
     */
    public ActivityWindow(@NonNull List<Activity> activities) {
        this.activities = activities;
    }

    /**
     * 가변 인자로 ActivityWindow를 생성합니다.
     */
    public ActivityWindow(@NonNull Activity... activities) {
        this.activities = new ArrayList<>(Arrays.asList(activities));
    }

    /**
     * 활동 목록을 읽기 전용 리스트로 반환합니다.
     */
    public List<Activity> getActivities() {
        return Collections.unmodifiableList(this.activities);
    }

    /**
     * 활동을 이 창에 추가합니다.
     */
    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }
}