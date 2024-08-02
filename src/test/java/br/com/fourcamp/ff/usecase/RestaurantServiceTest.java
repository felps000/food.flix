package br.com.fourcamp.ff.usecase;

import br.com.fourcamp.ff.models.Restaurant;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RestaurantServiceTest {
    private RestaurantService restaurantService;

    @Before
    public void setUp() {
        restaurantService = new RestaurantService();
    }

    @Test
    public void testGetRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        assertNotNull("A lista de restaurantes não deve ser nula", restaurants);
        assertEquals("A lista de restaurantes deve conter 6 restaurantes", 6, restaurants.size());

        // Verificando os nomes dos restaurantes
        assertEquals("McDonald's", restaurants.get(0).getName());
        assertEquals("Burger King", restaurants.get(1).getName());
        assertEquals("Hino Sushi", restaurants.get(2).getName());
        assertEquals("Pizza Hut", restaurants.get(3).getName());
        assertEquals("Spoleto", restaurants.get(4).getName());
        assertEquals("Mania de Churrasco", restaurants.get(5).getName());
    }
}
