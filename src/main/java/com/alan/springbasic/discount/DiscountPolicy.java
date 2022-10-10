package com.alan.springbasic.discount;

import com.alan.springbasic.member.Member;

public interface DiscountPolicy {

    int discount(Member member, int price);

}
