package com.lin.webflux.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WebRoutes {

    private UserHandler userHandler;

    public WebRoutes(UserHandler userHandler) {
        this.userHandler = userHandler;
    }
    @Bean
    public RouterFunction<ServerResponse> people() {
    	return RouterFunctions.route(RequestPredicates.GET("/hello"), userHandler::helloWorld);
    }
    @Bean
    public RouterFunction<ServerResponse> insert() {
    	RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.GET("/"), userHandler::test);
    	
		return route;
    }
    @Bean
    public RouterFunction<ServerResponse> insertOnce() {
    	//insert once
    	RouterFunction<ServerResponse> route = RouterFunctions.route(RequestPredicates.GET("/insertonce"), userHandler::insertonce);
    	return route;
    }
    @Bean
    public RouterFunction<ServerResponse> checkTransaction() {
    	RouterFunction<ServerResponse> route  = RouterFunctions.route(RequestPredicates.GET("/checktransaction"), userHandler::checkTransaction);
    	// select a number from a table first , 9000
    	
    	// update the number , and commit transaction number++;
    	// select the number form the table again ,and check if the target number(9001) is correct.
    	// output the result
    	return route;
    }
}
