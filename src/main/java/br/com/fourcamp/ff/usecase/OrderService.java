package br.com.fourcamp.ff.usecase;

import br.com.fourcamp.ff.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
}
