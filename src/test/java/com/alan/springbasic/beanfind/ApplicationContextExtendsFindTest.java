package com.alan.springbasic.beanfind;

import com.alan.springbasic.AppConfig;
import com.alan.springbasic.discount.DiscountPolicy;
import com.alan.springbasic.discount.FixDiscountPolicy;
import com.alan.springbasic.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다")
    void findBeanByParentTypeDuplicate(){
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다.")
    void findBeanByParentTypeAndBeanName(){

        DiscountPolicy rateDiscountPolicy = ac.getBean("discountPolicy", DiscountPolicy.class);
        assertInstanceOf(RateDiscountPolicy.class, rateDiscountPolicy);
    }

    @Test
    @DisplayName("특정 하위타입으로 조회 (권장하지 않는 방법)")
    void findBeanBySpecificType(){

        DiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        assertInstanceOf(RateDiscountPolicy.class, rateDiscountPolicy);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findBeansAllByParentType(){

        Map<String, DiscountPolicy> discountPolicyMap = ac.getBeansOfType(DiscountPolicy.class);
        assertEquals(2, discountPolicyMap.size());
    }


    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findBeansAllByObjectType(){

        Map<String, Object> discountPolicyMap = ac.getBeansOfType(Object.class);
        for (String key : discountPolicyMap.keySet()){
            System.out.println("key = "+key+" value = "+discountPolicyMap.get(key));
            System.out.println("beansOfType = "+discountPolicyMap);
        }
    }



    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }

}
