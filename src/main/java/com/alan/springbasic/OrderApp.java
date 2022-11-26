package com.alan.springbasic;

import com.alan.springbasic.member.Grade;
import com.alan.springbasic.member.Member;
import com.alan.springbasic.member.MemberService;
import com.alan.springbasic.order.Order;
import com.alan.springbasic.order.OrderService;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = "+ order);

    }
}
