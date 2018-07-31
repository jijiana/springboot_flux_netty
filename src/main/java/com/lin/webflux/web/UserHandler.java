package com.lin.webflux.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.lin.webflux.entity.User;
import com.lin.webflux.service.IUserService;

import reactor.core.publisher.Mono;

@Service
public class UserHandler {

    @Autowired
	IUserService userService;
    
    Mono<ServerResponse> helloWorld(ServerRequest request){
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("hello world"));
    }
    
    Mono<ServerResponse> test(ServerRequest request){
    	userService.register();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("db register test was finished."));
    }
    
    Mono<ServerResponse> insertonce(ServerRequest request){
    	User n = new User();
		n.setName("name" );
		n.setEmail("email" );
		n.setDescription(
				"setDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescriptionsetDescription"
						);
		n.setAddress(
				"addressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddressaddress"
						);
		n.setLastUpdate(new Date().toString());
		n.setSex("" );
		n.setCount(1l);
		
    	userService.saveOnce(n);
    	
    	return ServerResponse.ok()
    			.contentType(MediaType.TEXT_PLAIN)
    			.body(BodyInserters.fromObject("db insertonce was finished."));
    }
    
    Mono<ServerResponse> checkTransaction(ServerRequest request){
    	
		Long id = Long.valueOf(request.queryParam("id").get());
		try{
			userService.checkTransactional(id);
		}catch(Exception e){
			e.printStackTrace();
			return Mono.error(new RuntimeException("test checkTransaction error"));
		}
		return ServerResponse.ok()
    			.contentType(MediaType.TEXT_PLAIN)
    			.body(BodyInserters.fromObject("test checkTransaction was finished."));
    }


}
