package com.alan.springbasic;

import com.alan.springbasic.discount.DiscountPolicy;
import com.alan.springbasic.discount.FixDiscountPolicy;
import com.alan.springbasic.discount.RateDiscountPolicy;
import com.alan.springbasic.member.MemberRepository;
import com.alan.springbasic.member.MemberService;
import com.alan.springbasic.member.MemberServiceImpl;
import com.alan.springbasic.member.MemoryMemberRepository;
import com.alan.springbasic.order.OrderService;
import com.alan.springbasic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {

        return  new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

}
