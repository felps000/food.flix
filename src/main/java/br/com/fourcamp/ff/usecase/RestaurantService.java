package br.com.fourcamp.ff.usecase;

import br.com.fourcamp.ff.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantService {
    private final List<Restaurant> restaurants;

    public RestaurantService() {
        this.restaurants = new ArrayList<>();
        this.restaurants.add(createMcDonalds());
        this.restaurants.add(createBurgerKing());
        this.restaurants.add(createHinoSushi());
        this.restaurants.add(createPizzaHut());
        this.restaurants.add(createSpoleto());
        this.restaurants.add(createManiaDeChurrasco());
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    private Restaurant createMcDonalds() {
        Restaurant mcDonalds = new Restaurant("McDonald's", "Av. dos Autonomistas, 896 - Centro, Osasco - SP, 06090-020");
        mcDonalds.addSandwich("Big Mac", 16.90);
        mcDonalds.addSandwich("McChicken", 13.50);
        mcDonalds.addSandwich("McCrispy Chicken Deluxe", 25.90);
        mcDonalds.addSandwich("McNífco Bacon", 26.90);
        mcDonalds.addSandwich("Cheeseburguer", 9.90);
        mcDonalds.addSandwich("Cheddar McMelt", 17.90);
        mcDonalds.addSandwich("Duplo Cheddar McMelt", 31.90);
        mcDonalds.addSandwich("Quarteirão Com Queijo", 16.90);
        mcDonalds.addSandwich("Big Tasty", 31.90);
        mcDonalds.addSide("McFritas Pequena", 10.90);
        mcDonalds.addSide("McFritas Média", 12.50);
        mcDonalds.addSide("McFritas Grande", 14.50);
        mcDonalds.addSide("McFritas Cheddar Bacon", 17.90);
        mcDonalds.addSide("Chicken McNuggets 04 Unidades", 10.90);
        mcDonalds.addSide("Chicken McNuggets 06 Unidades", 12.50);
        mcDonalds.addSide("Chicken McNuggets 10 Unidades", 14.90);
        mcDonalds.addSide("Chicken McNuggets 15 Unidades", 18.90);
        mcDonalds.addSide("Mini Salada", 8.90);
        mcDonalds.addSide("Molho Barbecue", 2.00);
        mcDonalds.addSide("Molho Ranch", 2.00);
        mcDonalds.addSide("Molho Caipira", 2.00);
        mcDonalds.addDrink("Coca-Cola 500ml", 15.90);
        mcDonalds.addDrink("Coca-Cola 300ml", 10.00);
        mcDonalds.addDrink("Fanta Guaraná 500ml", 15.90);
        mcDonalds.addDrink("Fanta Guaraná 300ml", 10.00);
        mcDonalds.addDrink("Fanta", 5.00);
        mcDonalds.addDrink("Sprite", 5.00);
        mcDonalds.addDrink("Del Valle Uva 700ml", 16.90);
        mcDonalds.addDrink("Del Valle Uva 500ml", 15.90);
        mcDonalds.addDrink("Del Valle Uva 300ml", 10.00);
        mcDonalds.addDrink("Del Valle Laranja 700ml", 16.90);
        mcDonalds.addDrink("Del Valle Laranja 500ml", 15.90);
        mcDonalds.addDrink("Del Valle Laranja 300ml", 10.00);
        mcDonalds.addDrink("Água Mineral 500ml", 7.90);
        mcDonalds.addDessert("McShake Ovomaltine 400ml", 15.90);
        mcDonalds.addDessert("McShake Chocolate 400ml", 15.90);
        mcDonalds.addDessert("McShake Morango 400ml", 15.90);
        mcDonalds.addDessert("McFlurry Ovomaltine Rocks", 14.90);
        mcDonalds.addDessert("McFlurry Diamante Negro", 15.90);
        mcDonalds.addDessert("McFlurry KitKat", 15.90);
        mcDonalds.addDessert("Sundae Calda de Chocolate", 8.90);
        mcDonalds.addDessert("Sundae Calda de Morango", 8.90);
        mcDonalds.addDessert("Torta de Maçã", 5.90);
        mcDonalds.addDessert("Torta de Banana", 5.90);
        mcDonalds.addDessert("Danoninho", 4.00);
        return mcDonalds;
    }

    private Restaurant createBurgerKing() {
        Restaurant burgerKing = new Restaurant("Burger King", "Av. dos Autonomistas, 1828 - Vila Yara, Osasco - SP, 06194-050");
        burgerKing.addSandwich("Whopper", 16.90);
        burgerKing.addSandwich("Bk Original", 15.90);
        burgerKing.addSandwich("Bk Chicken Crispy", 27.90);
        burgerKing.addSandwich("Staker Duplo Bacon", 16.90);
        burgerKing.addSandwich("Rodeio", 10.90);
        burgerKing.addSide("Onion Rings", 13.90);
        burgerKing.addSide("Bk Chicken 04 unidades", 9.90);
        burgerKing.addSide("Bk Chicken 06 unidades", 13.90);
        burgerKing.addSide("Bk Chicken 10 unidades", 17.90);
        burgerKing.addSide("Batata Frita Pequena", 9.90);
        burgerKing.addSide("Batata Frita Média", 11.90);
        burgerKing.addSide("Batata Frita Grande", 13.90);
        burgerKing.addDrink("Pepsi", 14.90);
        burgerKing.addDrink("Pepsi Diet", 14.90);
        burgerKing.addDrink("Guaraná", 14.90);
        burgerKing.addDrink("Guaraná Light", 14.90);
        burgerKing.addDrink("Soda", 14.90);
        burgerKing.addDrink("Suco Uva Natural ONE", 15.90);
        burgerKing.addDrink("Suco Laranja Natural ONE", 15.90);
        burgerKing.addDrink("Suco Maracujá Natural ONE", 15.90);
        burgerKing.addDessert("Sundae Chocolate", 8.90);
        burgerKing.addDessert("Sundae Morango", 8.90);
        burgerKing.addDessert("Brownie Recheio Brigadeiro", 9.90);
        burgerKing.addDessert("Casquinha de Chocolate", 3.50);
        burgerKing.addDessert("Casquinha de Baunilha", 3.50);
        burgerKing.addDessert("Casquinha Mista", 3.50);
        burgerKing.addDessert("Shake de Chocolate", 14.90);
        burgerKing.addDessert("Shake de Morango", 14.90);
        return burgerKing;
    }

    private Restaurant createHinoSushi() {
        Restaurant hinoSushi = new Restaurant("Hino Sushi", "Av. Dr. Carlos de Moraes Barros, 300 - Campesina, Osasco - SP, 02675-031");
        hinoSushi.addSandwich("Sushi de Salmão", 25.90);
        hinoSushi.addSandwich("Sushi de Atum", 27.90);
        hinoSushi.addSandwich("Sushi de Kani", 20.90);
        hinoSushi.addSandwich("Sushi de Camarão", 29.90);
        hinoSushi.addSandwich("Sushi de Polvo", 30.90);
        hinoSushi.addSide("Sashimi de Salmão", 35.90);
        hinoSushi.addSide("Sashimi de Atum", 37.90);
        hinoSushi.addSide("Sashimi de Kani", 30.90);
        hinoSushi.addSide("Sashimi de Camarão", 39.90);
        hinoSushi.addSide("Sashimi de Polvo", 40.90);
        hinoSushi.addDrink("Chá Verde", 5.90);
        hinoSushi.addDrink("Água Mineral", 4.90);
        hinoSushi.addDrink("Refrigerante", 5.90);
        hinoSushi.addDrink("Suco de Laranja", 6.90);
        hinoSushi.addDrink("Saquê", 15.90);
        hinoSushi.addDessert("Mochi", 8.90);
        hinoSushi.addDessert("Sorvete de Matcha", 10.90);
        hinoSushi.addDessert("Dorayaki", 7.90);
        hinoSushi.addDessert("Anmitsu", 9.90);
        hinoSushi.addDessert("Taiyaki", 6.90);
        return hinoSushi;
    }

    private Restaurant createPizzaHut() {
        Restaurant pizzaHut = new Restaurant("Pizza Hut", "Av. dos Autonomistas, 1400 - Vila Yara, Osasco - SP, 06020-010");
        pizzaHut.addSandwich("Pizza Margherita", 74.90);
        pizzaHut.addSandwich("Pizza Pepperoni", 79.90);
        pizzaHut.addSandwich("Pizza de Frango com Requeijão", 74.90);
        pizzaHut.addSandwich("Pizza de Quatro Queijos", 84.90);
        pizzaHut.addSandwich("Pizza de Calabresa", 74.90);
        pizzaHut.addSide("Breadsticks de Queijo", 17.90);
        pizzaHut.addSide("Pão de Calabresa", 17.90);
        pizzaHut.addSide("Hut Fries", 11.90);
        pizzaHut.addDrink("Coca-Cola lata 350ml", 8.90);
        pizzaHut.addDrink("Fanta Guaraná lata 350ml", 8.90);
        pizzaHut.addDrink("Fanta Laranja lata 350ml", 8.90);
        pizzaHut.addDrink("Sprite lata 350ml", 8.90);
        pizzaHut.addDrink("Budweiser Long Neck", 10.90);
        pizzaHut.addDrink("Stella Artois Long Neck", 10.90);
        pizzaHut.addDrink("Heineken Long Neck", 10.90);
        pizzaHut.addDrink("Coca-Cola 2L", 16.90);
        pizzaHut.addDrink("Fanta Guaraná 2L", 16.90);
        pizzaHut.addDrink("Fanta Laranja 2L", 16.90);
        pizzaHut.addDrink("Sprite 2L", 16.90);
        pizzaHut.addDrink("Del Valle de Uva", 8.90);
        pizzaHut.addDrink("Del Valle de Pêssego", 8.90);
        pizzaHut.addDrink("Água Mineral 500ml", 5.99);
        pizzaHut.addDessert("Cinnamon Rolls", 14.90);
        pizzaHut.addDessert("Cookie", 8.90);
        pizzaHut.addDessert("Brownie", 10.90);
        pizzaHut.addDessert("Sorvete", 12.90);
        pizzaHut.addDessert("Torta de Maçã", 9.90);
        return pizzaHut;
    }

    private Restaurant createSpoleto() {
        Restaurant spoleto = new Restaurant("Spoleto", "Av. dos Autonomistas, 1400 - Vila Yara, Osasco - SP, 06020-010");
        spoleto.addSandwich("Spaghetti ao Sugo", 22.90);
        spoleto.addSandwich("Lasanha Bolonhesa", 27.90);
        spoleto.addSandwich("Fettuccine Alfredo", 25.90);
        spoleto.addSandwich("Penne ao Pesto", 24.90);
        spoleto.addSandwich("Ravioli de Queijo", 26.90);
        spoleto.addSide("Salada Caesar", 15.90);
        spoleto.addSide("Salada Caprese", 16.90);
        spoleto.addSide("Salada de Frango", 18.90);
        spoleto.addSide("Pão de Alho", 6.90);
        spoleto.addSide("Pão Italiano", 7.90);
        spoleto.addDrink("Coca-Cola lata 350ml", 8.90);
        spoleto.addDrink("Fanta Guaraná lata 350ml", 8.90);
        spoleto.addDrink("Fanta Laranja lata 350ml", 8.90);
        spoleto.addDrink("Sprite lata 350ml", 8.90);
        spoleto.addDrink("Água Mineral 500ml", 5.99);
        spoleto.addDrink("Suco de Laranja", 6.90);
        spoleto.addDrink("Suco de Uva", 6.90);
        spoleto.addDessert("Tiramisu", 14.90);
        spoleto.addDessert("Panna Cotta", 12.90);
        spoleto.addDessert("Gelato", 10.90);
        spoleto.addDessert("Cannoli", 8.90);
        spoleto.addDessert("Torta de Limão", 9.90);
        return spoleto;
    }

    private Restaurant createManiaDeChurrasco() {
        Restaurant maniaDeChurrasco = new Restaurant("Mania de Churrasco", "Av. dos Autonomistas, 1828 - Vila Yara, Osasco - SP, 06020-010");
        maniaDeChurrasco.addSandwich("Picanha", 49.90);
        maniaDeChurrasco.addSandwich("Maminha", 44.90);
        maniaDeChurrasco.addSandwich("Linguiça", 29.90);
        maniaDeChurrasco.addSandwich("Costela", 39.90);
        maniaDeChurrasco.addSandwich("Fraldinha", 42.90);
        maniaDeChurrasco.addSide("Arroz Branco", 5.90);
        maniaDeChurrasco.addSide("Vinagrete", 4.90);
        maniaDeChurrasco.addSide("Salada", 6.90);
        maniaDeChurrasco.addSide("Feijão Tropeiro", 7.90);
        maniaDeChurrasco.addSide("Farofa", 5.90);
        maniaDeChurrasco.addDrink("Coca-Cola lata 350ml", 8.90);
        maniaDeChurrasco.addDrink("Fanta Guaraná lata 350ml", 8.90);
        maniaDeChurrasco.addDrink("Fanta Laranja lata 350ml", 8.90);
        maniaDeChurrasco.addDrink("Sprite lata 350ml", 8.90);
        maniaDeChurrasco.addDrink("Água Mineral 500ml", 5.99);
        maniaDeChurrasco.addDrink("Suco de Laranja", 6.90);
        maniaDeChurrasco.addDrink("Suco de Uva", 6.90);
        maniaDeChurrasco.addDessert("Pudim", 7.90);
        maniaDeChurrasco.addDessert("Mousse de Maracujá", 6.90);
        maniaDeChurrasco.addDessert("Sorvete de Creme", 5.90);
        maniaDeChurrasco.addDessert("Doce de Leite", 4.90);
        maniaDeChurrasco.addDessert("Quindim", 4.90);
        return maniaDeChurrasco;
    }
}
