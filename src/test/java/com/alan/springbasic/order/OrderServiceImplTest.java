package com.alan.springbasic.order;

import com.alan.springbasic.discount.FixDiscountPolicy;
import com.alan.springbasic.member.Grade;
import com.alan.springbasic.member.Member;
import com.alan.springbasic.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "NAME", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 1000);
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}