package com.alan.springbasic.discount;

import com.alan.springbasic.member.Grade;
import com.alan.springbasic.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){

        Member member = new Member(1L, "memberVIP", Grade.VIP);

        int discount = rateDiscountPolicy.discount(member, 10000);

        assertEquals(1000, discount);

    }

    @Test
    @DisplayName("BASIC은 10% 할인이 적용안되어야 한다.")
    void vip_x(){

        Member member = new Member(1L, "memberVIP", Grade.BASIC);

        int discount = rateDiscountPolicy.discount(member, 10000);

        assertEquals(0, discount);

    }
}