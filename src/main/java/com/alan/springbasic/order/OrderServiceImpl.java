package com.alan.springbasic.order;

import com.alan.springbasic.discount.DiscountPolicy;
import com.alan.springbasic.discount.FixDiscountPolicy;
import com.alan.springbasic.member.Member;
import com.alan.springbasic.member.MemberRepository;
import com.alan.springbasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
