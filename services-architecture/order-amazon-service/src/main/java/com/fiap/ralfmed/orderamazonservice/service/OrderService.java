package com.fiap.ralfmed.orderamazonservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.fiap.ralfmed.orderamazonservice.cache.MySimpleCache;
import com.fiap.ralfmed.orderamazonservice.entity.Order;
import com.fiap.ralfmed.orderamazonservice.entity.OrderLine;
import com.fiap.ralfmed.orderamazonservice.entity.Product;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
	@Autowired
	private DiscoveryClient discoveryClient;

	public Order createOrder(Order order) {
		for (OrderLine orderLine : order.getOrderLineList()) {
			Product product = MySimpleCache.get(orderLine.getProductId());
			if(product == null) {
				product = getProductFromService(orderLine);
			}
			order.setTotalOrder(order.getTotalOrder() + (orderLine.getQuantity() * product.getPrice()));
		}
		order.setStatus("Created");
		return order;
	}

	private Product getProductFromService(OrderLine orderLine) {
		List<ServiceInstance> instances = discoveryClient.getInstances("productservice");
		Product product = null;
		if (instances.size() == 0) {
			throw new RuntimeException();
		} else {
			RestTemplate restTemplate = new RestTemplate();
			String uri = String.format("%s/product/%s",
					instances.get(0).getUri().toString(), orderLine.getProductId());
			ResponseEntity<Product> restExchange = restTemplate.exchange(uri,
					HttpMethod.GET, null, Product.class,orderLine.getProductId());
			product = restExchange.getBody();
			MySimpleCache.put(product);
		}
		return product;
	}

}
