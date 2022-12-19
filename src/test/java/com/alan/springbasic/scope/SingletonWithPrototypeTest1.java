package com.alan.springbasic.scope;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertEquals(prototypeBean1.getCount(), 1);
    }

    @Test
    @DisplayName("싱글톤 타입빈에 프로토타입을 주입해서 사용하면 프로토타입은 매번 생성되지 않고 최초 주입된 프로토타입 빈만 사용하게 된다. 즉 프로토타입으로서의 기능을 못함.")
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean = ac.getBean(ClientBean.class);
        int count1 = clientBean.logic();
        assertEquals(count1, 1);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count2 = clientBean1.logic();
        assertEquals(count2, 1);

    }

    @Scope("singleton") //default
    static class ClientBean {
        @Autowired
        private Provider<PrototypeBean> prototypeBeanObjectProvider;


        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanObjectProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount(){
            this.count++;
        }
        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy "+ this);
        }
    }
}
