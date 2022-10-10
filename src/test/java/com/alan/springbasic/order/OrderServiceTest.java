package com.alan.springbasic.order;

import com.alan.springbasic.member.Grade;
import com.alan.springbasic.member.Member;
import com.alan.springbasic.member.MemberService;
import com.alan.springbasic.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertEquals(1000, order.getDiscountPrice());

    }
}
