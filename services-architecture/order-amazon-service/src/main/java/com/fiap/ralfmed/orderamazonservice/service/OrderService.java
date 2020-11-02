package com.fiap.ralfmed.orderamazonservice.service;

import com.fiap.ralfmed.orderamazonservice.entity.Order;
import com.fiap.ralfmed.orderamazonservice.entity.Product;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

import java.util.List;


public interface OrderService {

	Order createOrder(Order order);

	void consumerProductEvent(Product event);

	Order findById(Long id);

	List<Order> getListOrder();

	Order calculateDeliveryPrice(Long id, Double distance);

}
