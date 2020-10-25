package com.fiap.ralfmed.orderamazonservice.service.impl;

import com.fiap.ralfmed.orderamazonservice.cache.MySimpleCache;
import com.fiap.ralfmed.orderamazonservice.entity.Order;
import com.fiap.ralfmed.orderamazonservice.entity.OrderLine;
import com.fiap.ralfmed.orderamazonservice.entity.Product;
import com.fiap.ralfmed.orderamazonservice.repository.OrderRepository;
import com.fiap.ralfmed.orderamazonservice.repository.ProductRepository;
import com.fiap.ralfmed.orderamazonservice.service.OrderService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@EnableBinding(Sink.class)
public class OrderServiceImpl implements OrderService {

    private DiscoveryClient discoveryClient;

    private OrderRepository orderRepository;

    private ProductRepository productRepository;

    public OrderServiceImpl(DiscoveryClient discoveryClient, OrderRepository orderRepository, ProductRepository productRepository) {
        this.discoveryClient = discoveryClient;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(Order order) {

        for(OrderLine orderLine: order.getProducts()){

            Product productCache = MySimpleCache.get(orderLine.getProductId());

            if(productCache==null){

                Product product = new Product();

                List<ServiceInstance> instances = discoveryClient.getInstances("productservice");
                RestTemplate restTemplate = new RestTemplate();
                String uri = String.format("%s/product/%s",
                        instances.get(0).getUri().toString(), orderLine.getProductId());
                ResponseEntity<Product> restExchange = restTemplate.exchange(uri,
                        HttpMethod.GET, null, Product.class, orderLine.getProductId());
                Product returnProduct = restExchange.getBody();

                product = loadingProduct(returnProduct, orderLine);

                if(verifyExistingProduct(product.getId())){
                    productRepository.save(product);
                }
                MySimpleCache.put(product);

                orderLine.setOrder_id(order);
                order = calculateOrder(order, product, orderLine);

                orderRepository.save(order);
            }else{
                order = calculateOrder(order, productCache, orderLine);
//                orderLine.setOrder_id(order);
//                orderRepository.save(order);
            }
        }

        return order;
    }

    private Order calculateOrder(Order order, Product product, OrderLine orderLine) {
        order.setTotalOrder(order.getTotalOrder() + (orderLine.getQuantity() * product.getPrice()));
        order.setStatus("Created");
        return order;
    }

    private Product loadingProduct(Product product, OrderLine orderLine){
        product.setId(orderLine.getProductId());
        product.setQuantity(orderLine.getQuantity());
        product.setPrice(product.getPrice());
        product.setDescription(product.getDescription());
        return product;
    }

    private boolean verifyExistingProduct(Integer id) {
        Optional<Product> prod =  productRepository.findById(id);
        if(prod.isPresent()){
            return false;
        }
        return true;
    }


    @Override
    public Order findById(Long id) {
        return (Order)orderRepository.findById(id).get();
    }

    @Override
    public List<Order> getListOrder() {
        return orderRepository.findAll();
    }

    @StreamListener(target = Sink.INPUT)
    @Override
    public void consumerProductEvent(@Payload Product event) {
        System.out.println("Received a product {} " + event.getId() + " Price: " +
                event.getPrice());
        MySimpleCache.put(event);
    }

}
