package com.fiap.ralfmed.orderamazonservice.service;

import com.fiap.ralfmed.orderamazonservice.entity.Order;

import java.util.List;

public interface OrderService {

	Order createOrder(Order order);

	Order findById(Long id);

	List<Order> getListOrder();
}
