package br.com.fourcamp.ff.model;

import java.util.List;
import java.util.Map;

public record Order(User user, Map<String, List<String>> restaurantOrders, double totalPrice, double deliveryFee,
                    double finalTotalPrice) {
}
