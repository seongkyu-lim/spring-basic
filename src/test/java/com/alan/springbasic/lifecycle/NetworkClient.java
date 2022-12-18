package com.alan.springbasic.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출 url ="+ url);
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void connect(){
        System.out.println("connect: "+url);
    }

    public void call(String message){
        System.out.println("call: "+url+" message = "+ message);
    }

    public void disconnect(){
        System.out.println("close: "+ url);
    }

    @PostConstruct
    public void init() throws Exception {
        //의존관계 주입이 끝날 때 호출되는 메소드.
        System.out.println("afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("destroy");
        disconnect();
    }
}
