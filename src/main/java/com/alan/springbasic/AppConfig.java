package com.alan.springbasic;

import com.alan.springbasic.discount.DiscountPolicy;
import com.alan.springbasic.discount.FixDiscountPolicy;
import com.alan.springbasic.member.MemberRepository;
import com.alan.springbasic.member.MemberService;
import com.alan.springbasic.member.MemberServiceImpl;
import com.alan.springbasic.member.MemoryMemberRepository;
import com.alan.springbasic.order.OrderService;
import com.alan.springbasic.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return  new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }

}
