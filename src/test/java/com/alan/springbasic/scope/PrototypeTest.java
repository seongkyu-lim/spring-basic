package com.alan.springbasic.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PrototypeTest {

    @Test
    void prototypeTest(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        assertNotEquals(prototypeBean2, prototypeBean1);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{

        //호출할 때마다 초기화 된다. ( 새로 생성된다. )
        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init");
        }


        //prototype 빈은 해당 메소드는 동작안한다. 왜냐하면 초기화 이후 컨테이너는 관리하지 않기 때문.
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
