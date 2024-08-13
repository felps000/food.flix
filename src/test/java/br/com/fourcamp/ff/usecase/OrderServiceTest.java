package br.com.fourcamp.ff.usecase;

import br.com.fourcamp.ff.model.Order;
import br.com.fourcamp.ff.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderServiceTest {

    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void testAddOrder() {
        User user = new User("Test User", "(11)99999-9999", "123.456.789-00", "Password@123", "Test Street, 123", "Test Question", "Test Answer");
        Map<String, List<String>> restaurantOrders = new HashMap<>();
        restaurantOrders.put("Test Restaurant", List.of("Item1", "Item2"));
        Order order = new Order(user, restaurantOrders, 50.0, 10.0, 60.0);

        orderService.addOrder(order);

        List<Order> orders = orderService.getOrders();
        assertTrue(orders.contains(order));
    }

    @Test
    public void testGetOrders() {
        User user = new User("Test User", "(11)99999-9999", "123.456.789-00", "Password@123", "Test Street, 123", "Test Question", "Test Answer");
        Map<String, List<String>> restaurantOrders = new HashMap<>();
        restaurantOrders.put("Test Restaurant", List.of("Item1", "Item2"));
        Order order = new Order(user, restaurantOrders, 50.0, 10.0, 60.0);

        orderService.addOrder(order);

        List<Order> orders = orderService.getOrders();
        assertEquals(1, orders.size());
        assertEquals(order, orders.get(0));
    }
}
