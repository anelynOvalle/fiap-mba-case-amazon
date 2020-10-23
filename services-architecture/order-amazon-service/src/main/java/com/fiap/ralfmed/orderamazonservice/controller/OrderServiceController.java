package com.fiap.ralfmed.orderamazonservice.controller;

import com.fiap.ralfmed.orderamazonservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fiap.ralfmed.orderamazonservice.entity.Order;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderServiceController {

	@Autowired
	private OrderService orderService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@HystrixCommand(fallbackMethod  = "singleOrderFallback")
	public String createOrder(@RequestBody Order order) {
		Order createOrder = orderService.createOrder(order); // item 22.b
		return "Status do pedido = " + createOrder.getStatus() + ". O total do pedido é: " + createOrder.getTotalOrder();
	}

	/*@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Order findById(@PathVariable(name = "id") Long id){
		return Order.findById(id);
	}*/

	public Order singleOrderFallback(Order order) {
		return order;
	}
}
