package com.alan.springbasic;

import com.alan.springbasic.discount.FixDiscountPolicy;
import com.alan.springbasic.member.MemberService;
import com.alan.springbasic.member.MemberServiceImpl;
import com.alan.springbasic.member.MemoryMemberRepository;
import com.alan.springbasic.order.OrderService;
import com.alan.springbasic.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return  new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
