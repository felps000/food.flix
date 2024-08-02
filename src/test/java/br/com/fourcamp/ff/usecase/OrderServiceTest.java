package br.com.fourcamp.ff.usecase;

import br.com.fourcamp.ff.models.Order;
import br.com.fourcamp.ff.models.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class OrderServiceTest {
    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void testAddOrder() {
        User user = new User("Test User", "(11)99999-9999", "123.456.789-00", "Password@1", "Rua Teste, 123", "Pergunta", "Resposta");
        Map<String, List<String>> restaurantOrders = new HashMap<>();
        List<String> items = new ArrayList<>();
        items.add("Item 1");
        restaurantOrders.put("Restaurant 1", items);
        Order order = new Order(user, restaurantOrders, 100.0, 10.0, 110.0);

        orderService.addOrder(order);
        List<Order> orders = orderService.getOrders();
        assertEquals("Deve haver 1 pedido na lista", 1, orders.size());
        assertEquals("O pedido adicionado deve ser o mesmo", order, orders.get(0));
    }

    @Test
    public void testGetOrders() {
        List<Order> orders = orderService.getOrders();
        assertNotNull("A lista de pedidos não deve ser nula", orders);
        assertEquals("A lista de pedidos deve estar vazia inicialmente", 0, orders.size());
    }
}
