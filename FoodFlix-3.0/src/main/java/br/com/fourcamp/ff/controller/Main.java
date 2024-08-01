package br.com.fourcamp.ff.controller;

import br.com.fourcamp.ff.enums.MainMessagesEnum;
import br.com.fourcamp.ff.models.Order;
import br.com.fourcamp.ff.models.Restaurant;
import br.com.fourcamp.ff.models.User;
import br.com.fourcamp.ff.usecase.OrderService;
import br.com.fourcamp.ff.usecase.RestaurantService;
import br.com.fourcamp.ff.usecase.UserService;
import br.com.fourcamp.ff.exceptions.InvalidInputException;
import br.com.fourcamp.ff.exceptions.UserNotFoundException;
import br.com.fourcamp.ff.utils.ValidationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final double MIN_DELIVERY_FEE = 8.00;
    private static final double MAX_DELIVERY_FEE = 10.00;
    private static final double EXTRA_RESTAURANT_MIN_FEE = 5.00;
    private static final double EXTRA_RESTAURANT_MAX_FEE = 10.00;

    public static void main(String[] args) {
        System.out.println(MainMessagesEnum.START_APP.getMessage());

        RestaurantService restaurantService = new RestaurantService();
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        Scanner scanner = new Scanner(System.in);

        System.out.println(MainMessagesEnum.WELCOME_MESSAGE.getMessage());

        User user = getUserDetails(scanner, userService);

        processOrder(scanner, restaurantService, orderService, user);

        List<Order> allOrders = orderService.getOrders();
        for (Order order : allOrders) {
            logger.info(order.toString());
        }
    }

    private static void processOrder(Scanner scanner, RestaurantService restaurantService, OrderService orderService, User user) {
        while (true) {
            List<Restaurant> selectedRestaurants = selectRestaurants(scanner, restaurantService);

            if (selectedRestaurants.isEmpty()) {
                System.out.println(MainMessagesEnum.CANCEL_MESSAGE.getMessage());
                break;
            }

            double totalPrice = 0;
            Map<String, List<String>> restaurantOrders = new HashMap<>();
            boolean multipleRestaurants = selectedRestaurants.size() > 1;

            for (Restaurant selectedRestaurant : selectedRestaurants) {
                boolean itemsAdded = selectMenuItems(scanner, selectedRestaurant, restaurantOrders);

                if (itemsAdded) {
                    totalPrice += calculateTotalPrice(selectedRestaurant, restaurantOrders.get(selectedRestaurant.getName()));
                }
            }

            if (restaurantOrders.isEmpty()) {
                System.out.println(MainMessagesEnum.CANCEL_MESSAGE.getMessage());
                break;
            }

            double deliveryFee = calculateDeliveryFee(multipleRestaurants, restaurantOrders.size());
            double finalTotalPrice = totalPrice + deliveryFee;

            orderService.addOrder(new Order(user, restaurantOrders, totalPrice, deliveryFee, finalTotalPrice));

            printOrderSummary(user, restaurantOrders, totalPrice, deliveryFee, finalTotalPrice);
            selectPaymentMethod(scanner);
            printDeliveryTimes(restaurantOrders);
            break;
        }
    }

    private static User getUserDetails(Scanner scanner, UserService userService) {
        while (true) {
            try {
                System.out.println(MainMessagesEnum.CPF_PROMPT.getMessage());
                String cpf = scanner.nextLine().trim();
                cpf = ValidationUtils.formatCPF(cpf);
                ValidationUtils.validateCPF(cpf);
                User user;
                try {
                    user = userService.getUserByCPF(cpf);
                    return loginUser(scanner, userService, cpf, user);
                } catch (UserNotFoundException e) {
                    logger.warn(MainMessagesEnum.USER_NOT_FOUND.getMessage() + ": {}", cpf);
                    return registerNewUser(scanner, userService, cpf);
                }
            } catch (InvalidInputException e) {
                logger.error("Erro na validação do CPF: {}", e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }

    private static User loginUser(Scanner scanner, UserService userService, String cpf, User user) {
        while (true) {
            System.out.println(MainMessagesEnum.PASSWORD_PROMPT.getMessage());
            String password = scanner.nextLine().trim();
            if (userService.validateUserPassword(cpf, password)) {
                System.out.println(MainMessagesEnum.USER_FOUND.getMessage() + user.getName());
                return user;
            } else {
                System.out.println(MainMessagesEnum.INVALID_PASSWORD_MESSAGE.getMessage());
                System.out.println(MainMessagesEnum.FORGOT_PASSWORD_PROMPT.getMessage());
                String forgotPassword = scanner.nextLine().trim().toLowerCase();
                if (forgotPassword.equals("s")) {
                    resetPassword(scanner, userService, cpf);
                    return null;
                }
            }
        }
    }

    private static void resetPassword(Scanner scanner, UserService userService, String cpf) {
        try {
            String securityQuestion = userService.getUserSecurityQuestion(cpf);
            System.out.println(MainMessagesEnum.SECURITY_QUESTION_PROMPT.getMessage());
            System.out.println(securityQuestion);
            String answer = scanner.nextLine().trim();
            if (userService.validateUserSecurityAnswer(cpf, answer)) {
                System.out.println(MainMessagesEnum.NEW_PASSWORD_PROMPT.getMessage());
                String newPassword = scanner.nextLine().trim();
                userService.updateUserPassword(cpf, newPassword);
                System.out.println(MainMessagesEnum.PASSWORD_UPDATED.getMessage());
            } else {
                System.out.println(MainMessagesEnum.INVALID_SECURITY_ANSWER.getMessage());
            }
        } catch (UserNotFoundException e) {
            logger.warn(MainMessagesEnum.USER_NOT_FOUND.getMessage() + ": {}", cpf);
            System.out.println(MainMessagesEnum.USER_NOT_FOUND.getMessage());
        }
    }

    private static User registerNewUser(Scanner scanner, UserService userService, String cpf) {
        System.out.println(MainMessagesEnum.REGISTER_PROMPT.getMessage());

        String name = getValidatedInput(scanner, MainMessagesEnum.NAME_PROMPT.getMessage(), ValidationUtils::validateName);
        String phone = getValidatedInput(scanner, MainMessagesEnum.PHONE_PROMPT.getMessage(), ValidationUtils::validatePhone, ValidationUtils::formatPhone);
        String password = getValidatedInput(scanner, MainMessagesEnum.PASSWORD_PROMPT.getMessage(), ValidationUtils::validatePassword);
        String street = getValidatedInput(scanner, MainMessagesEnum.STREET_PROMPT.getMessage(), ValidationUtils::validateStreet);
        String address = getAddress(scanner, street);

        String[] securityQuestionAndAnswer = getSecurityQuestionAndAnswer(scanner);
        String securityQuestion = securityQuestionAndAnswer[0];
        String securityAnswer = securityQuestionAndAnswer[1];

        User user = new User(name, phone, cpf, password, address, securityQuestion, securityAnswer);
        userService.addUser(user);
        logger.info("Novo usuário cadastrado com CPF: {}", cpf);
        System.out.println(MainMessagesEnum.REGISTRATION_SUCCESS.getMessage());
        return user;
    }

    private static String getValidatedInput(Scanner scanner, String prompt, InputValidator validator) {
        return getValidatedInput(scanner, prompt, validator, null);
    }

    private static String getValidatedInput(Scanner scanner, String prompt, InputValidator validator, InputFormatter formatter) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (formatter != null) {
                input = formatter.format(input);
            }
            try {
                validator.validate(input);
                return input;
            } catch (InvalidInputException e) {
                logger.error("Erro na validação: {}", e.getMessage());
                System.out.println(e.getMessage());
            }
        }
    }

    private static String getAddress(Scanner scanner, String street) {
        String residenceType = getValidatedInput(scanner, MainMessagesEnum.RESIDENCE_TYPE_PROMPT.getMessage(), input -> ValidationUtils.validateChoice(input, 1, 2));
        String address = street;

        if (residenceType.equals("2")) {
            String buildingName = getValidatedInput(scanner, MainMessagesEnum.BUILDING_NAME_PROMPT.getMessage(), input -> {
                if (input.isEmpty()) {
                    throw new InvalidInputException(MainMessagesEnum.INVALID_BUILDING_NAME.getMessage());
                }
            });
            String apartmentNumber = getValidatedInput(scanner, MainMessagesEnum.APARTMENT_NUMBER_PROMPT.getMessage(), ValidationUtils::validateInteger);
            address += ", " + buildingName + " - Ap. " + apartmentNumber;
        } else {
            String houseNumber = getValidatedInput(scanner, MainMessagesEnum.HOUSE_NUMBER_PROMPT.getMessage(), ValidationUtils::validateInteger);
            address += ", " + houseNumber;
        }

        return address;
    }

    private static String[] getSecurityQuestionAndAnswer(Scanner scanner) {
        while (true) {
            System.out.println(MainMessagesEnum.SECURITY_QUESTION_CHOICE_PROMPT.getMessage());
            System.out.println(MainMessagesEnum.SECURITY_QUESTION_OPTION_1.getMessage());
            System.out.println(MainMessagesEnum.SECURITY_QUESTION_OPTION_2.getMessage());
            System.out.println(MainMessagesEnum.SECURITY_QUESTION_OPTION_3.getMessage());
            String questionChoice = scanner.nextLine().trim();
            String securityQuestion;
            switch (questionChoice) {
                case "1":
                    securityQuestion = MainMessagesEnum.SECURITY_QUESTION_OPTION_1.getMessage();
                    break;
                case "2":
                    securityQuestion = MainMessagesEnum.SECURITY_QUESTION_OPTION_2.getMessage();
                    break;
                case "3":
                    securityQuestion = MainMessagesEnum.SECURITY_QUESTION_OPTION_3.getMessage();
                    break;
                default:
                    System.out.println(MainMessagesEnum.INVALID_CHOICE.getMessage());
                    continue;
            }
            System.out.println(securityQuestion);
            String securityAnswer = scanner.nextLine().trim();
            return new String[]{securityQuestion, securityAnswer};
        }
    }

    private static List<Restaurant> selectRestaurants(Scanner scanner, RestaurantService restaurantService) {
        List<Restaurant> selectedRestaurants = new ArrayList<>();
        boolean moreRestaurants = true;
        while (moreRestaurants) {
            List<Restaurant> availableRestaurants = new ArrayList<>(restaurantService.getRestaurants());
            availableRestaurants.removeAll(selectedRestaurants);

            if (availableRestaurants.isEmpty()) {
                break;
            }

            System.out.println(MainMessagesEnum.SELECT_RESTAURANT_PROMPT.getMessage());
            for (int i = 0; i < availableRestaurants.size(); i++) {
                Restaurant restaurant = availableRestaurants.get(i);
                System.out.println((i + 1) + ". " + restaurant.getName() + " - " + restaurant.getAddress());
            }
            System.out.println(MainMessagesEnum.CONTINUE_OPTION.getMessage());

            int restaurantChoice = getValidatedChoice(scanner, availableRestaurants.size());

            if (restaurantChoice == 0) {
                break;
            }

            selectedRestaurants.add(availableRestaurants.get(restaurantChoice - 1));
            moreRestaurants = askMoreRestaurants(scanner, selectedRestaurants, restaurantService);
        }
        return selectedRestaurants;
    }

    private static boolean askMoreRestaurants(Scanner scanner, List<Restaurant> selectedRestaurants, RestaurantService restaurantService) {
        if (selectedRestaurants.size() == restaurantService.getRestaurants().size()) {
            System.out.println(MainMessagesEnum.ALL_RESTAURANTS_SELECTED.getMessage());
            return false;
        }

        System.out.println(MainMessagesEnum.ASK_MORE_RESTAURANTS.getMessage());
        String response = getValidatedYesNoChoice(scanner);
        return response.equals("s");
    }

    private static boolean selectMenuItems(Scanner scanner, Restaurant selectedRestaurant, Map<String, List<String>> restaurantOrders) {
        while (true) {
            String restaurantName = selectedRestaurant.getName();
            System.out.println(MainMessagesEnum.MENU_PROMPT.getMessage() + restaurantName + ":");

            displayMenuOptions(restaurantName);

            int categoryChoice = getValidatedChoice(scanner, 4);

            if (categoryChoice == 0) {
                System.out.println(MainMessagesEnum.CANCEL_ORDER_PROMPT.getMessage() + restaurantName + "? (s/n)");
                String cancelResponse = getValidatedYesNoChoice(scanner);
                if (cancelResponse.equals("s")) {
                    restaurantOrders.remove(selectedRestaurant.getName());
                    return false;
                } else {
                    continue;
                }
            }

            Map<String, Double> menu = getMenuByCategory(selectedRestaurant, categoryChoice);

            if (menu != null) {
                while (true) {
                    System.out.println(MainMessagesEnum.SELECT_MENU_ITEMS_PROMPT.getMessage());
                    List<String> menuItems = List.copyOf(menu.keySet());
                    for (int i = 0; i < menuItems.size(); i++) {
                        String item = menuItems.get(i);
                        double price = menu.get(item);
                        System.out.println((i + 1) + ". " + item + ": R$ " + price);
                    }

                    String itemNumbersInput = scanner.nextLine().trim();
                    if (itemNumbersInput.equals("0")) {
                        break;
                    }

                    boolean validItems = addMenuItems(scanner, restaurantOrders, selectedRestaurant, menuItems, itemNumbersInput);

                    if (!validItems) {
                        System.out.println(MainMessagesEnum.INVALID_MENU_ITEM.getMessage());
                    } else {
                        System.out.println(MainMessagesEnum.FINISH_ORDER_PROMPT.getMessage() + restaurantName + "? (s/n)");
                        String response = getValidatedYesNoChoice(scanner);
                        if (response.equals("s")) {
                            return true;
                        } else {
                            break;
                        }
                    }
                }
            }
        }
    }

    private static boolean addMenuItems(Scanner scanner, Map<String, List<String>> restaurantOrders, Restaurant selectedRestaurant, List<String> menuItems, String itemNumbersInput) {
        String[] itemNumbers = itemNumbersInput.split("[, ]+");
        boolean validItems = true;
        List<String> itemsList = restaurantOrders.computeIfAbsent(selectedRestaurant.getName(), k -> new ArrayList<>());

        for (String itemNumber : itemNumbers) {
            int index;
            try {
                index = Integer.parseInt(itemNumber.trim()) - 1;
                if (index < 0 || index >= menuItems.size()) {
                    throw new InvalidInputException(MainMessagesEnum.INVALID_MENU_ITEM_NUMBER.getMessage() + itemNumber);
                }
                String itemName = menuItems.get(index);
                itemsList.add(itemName);
            } catch (NumberFormatException | InvalidInputException e) {
                System.out.println(MainMessagesEnum.INVALID_INPUT.getMessage() + itemNumber);
                validItems = false;
                break;
            }
        }
        return validItems;
    }

    private static void displayMenuOptions(String restaurantName) {
        switch (restaurantName) {
            case "McDonald's":
            case "Burger King":
                System.out.println(MainMessagesEnum.MENU_OPTIONS_1.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_2.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_3.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_4.getMessage());
                break;
            case "Hino Sushi":
                System.out.println(MainMessagesEnum.MENU_OPTIONS_SUSHI_1.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_SUSHI_2.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_SUSHI_3.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_SUSHI_4.getMessage());
                break;
            case "Pizza Hut":
                System.out.println(MainMessagesEnum.MENU_OPTIONS_PIZZA_1.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_PIZZA_2.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_PIZZA_3.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_PIZZA_4.getMessage());
                break;
            case "Spoleto":
                System.out.println(MainMessagesEnum.MENU_OPTIONS_PASTA_1.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_PASTA_2.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_PASTA_3.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_PASTA_4.getMessage());
                break;
            case "Mania de Churrasco":
                System.out.println(MainMessagesEnum.MENU_OPTIONS_BBQ_1.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_BBQ_2.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_BBQ_3.getMessage());
                System.out.println(MainMessagesEnum.MENU_OPTIONS_BBQ_4.getMessage());
                break;
            default:
                System.out.println(MainMessagesEnum.INVALID_CATEGORY.getMessage());
        }
        System.out.println(MainMessagesEnum.RETURN_OPTION.getMessage());
    }

    private static Map<String, Double> getMenuByCategory(Restaurant selectedRestaurant, int categoryChoice) {
        return switch (categoryChoice) {
            case 1 -> selectedRestaurant.getSandwiches();
            case 2 -> selectedRestaurant.getSides();
            case 3 -> selectedRestaurant.getDrinks();
            case 4 -> selectedRestaurant.getDesserts();
            default -> null;
        };
    }

    private static int getValidatedChoice(Scanner scanner, int max) {
        int choice;
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
                ValidationUtils.validateChoice(String.valueOf(choice), 0, max);
                break;
            } catch (NumberFormatException | InvalidInputException e) {
                System.out.println(MainMessagesEnum.INVALID_RESPONSE_MESSAGE.getMessage());
            }
        }
        return choice;
    }

    private static String getValidatedYesNoChoice(Scanner scanner) {
        while (true) {
            String response = scanner.nextLine().trim().toLowerCase();
            try {
                ValidationUtils.validateYesNoChoice(response);
                return response;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static double calculateTotalPrice(Restaurant selectedRestaurant, List<String> orderDetails) {
        double totalPrice = 0;
        Map<String, Double> menu = null;

        for (String itemName : orderDetails) {
            if (selectedRestaurant.getSandwiches().containsKey(itemName)) {
                menu = selectedRestaurant.getSandwiches();
            } else if (selectedRestaurant.getSides().containsKey(itemName)) {
                menu = selectedRestaurant.getSides();
            } else if (selectedRestaurant.getDrinks().containsKey(itemName)) {
                menu = selectedRestaurant.getDrinks();
            } else if (selectedRestaurant.getDesserts().containsKey(itemName)) {
                menu = selectedRestaurant.getDesserts();
            }
            if (menu != null) {
                totalPrice += menu.get(itemName);
            }
        }

        return totalPrice;
    }

    private static double calculateDeliveryFee(boolean multipleRestaurants, int numberOfRestaurants) {
        Random random = new Random();
        double baseFee = MIN_DELIVERY_FEE + random.nextDouble() * (MAX_DELIVERY_FEE - MIN_DELIVERY_FEE);
        if (multipleRestaurants) {
            for (int i = 1; i < numberOfRestaurants; i++) {
                baseFee += EXTRA_RESTAURANT_MIN_FEE + random.nextDouble() * (EXTRA_RESTAURANT_MAX_FEE - EXTRA_RESTAURANT_MIN_FEE);
            }
        }
        return baseFee;
    }

    private static void printOrderSummary(User user, Map<String, List<String>> restaurantOrders, double totalPrice, double deliveryFee, double finalTotalPrice) {
        if (restaurantOrders.isEmpty()) {
            System.out.println(MainMessagesEnum.CANCEL_MESSAGE.getMessage());
            return;
        }

        System.out.println(MainMessagesEnum.ORDER_SUCCESS.getMessage());
        for (Map.Entry<String, List<String>> entry : restaurantOrders.entrySet()) {
            System.out.println(MainMessagesEnum.ORDER_DETAILS.getMessage() + entry.getKey() + ": " + String.join(", ", entry.getValue()) + " para " + user.getAddress() + ".");
        }
        System.out.println(MainMessagesEnum.ITEMS_VALUE.getMessage() + String.format("%.2f", totalPrice));
        System.out.println(MainMessagesEnum.DELIVERY_FEE.getMessage() + String.format("%.2f", deliveryFee));
        System.out.println(MainMessagesEnum.TOTAL_VALUE.getMessage() + String.format("%.2f", finalTotalPrice));
    }

    private static void selectPaymentMethod(Scanner scanner) {
        System.out.println(MainMessagesEnum.PAYMENT_METHOD_PROMPT.getMessage());
        String paymentMethod;
        while (true) {
            paymentMethod = scanner.nextLine().trim();
            try {
                ValidationUtils.validateChoice(paymentMethod, 1, 3);
                break;
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        switch (paymentMethod) {
            case "1":
                System.out.println(MainMessagesEnum.PAYMENT_DEBIT.getMessage());
                break;
            case "2":
                System.out.println(MainMessagesEnum.PAYMENT_CREDIT.getMessage());
                break;
            case "3":
                generatePixCode();
                break;
            default:
                System.out.println(MainMessagesEnum.INVALID_PAYMENT_CHOICE.getMessage());
        }
    }

    private static void generatePixCode() {
        Random random = new Random();
        String pixCode = random.ints(20, 0, 10)
                .mapToObj(String::valueOf)
                .reduce("", String::concat);
        System.out.println(MainMessagesEnum.PAYMENT_PIX.getMessage());
        System.out.println(MainMessagesEnum.PIX_CODE.getMessage() + pixCode);
    }

    private static void printDeliveryTimes(Map<String, List<String>> restaurantOrders) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        for (Map.Entry<String, List<String>> entry : restaurantOrders.entrySet()) {
            int minTime = 30 + new Random().nextInt(11);
            int maxTime = minTime + 15;
            LocalTime deliveryMinTime = currentTime.plusMinutes(minTime);
            LocalTime deliveryMaxTime = currentTime.plusMinutes(maxTime);

            System.out.println(MainMessagesEnum.DELIVERY_TIME.getMessage() + entry.getKey() + " chegará entre " + formatter.format(deliveryMinTime) + " e " + formatter.format(deliveryMaxTime) + ".");
        }
    }

    @FunctionalInterface
    interface InputValidator {
        void validate(String input) throws InvalidInputException;
    }

    @FunctionalInterface
    interface InputFormatter {
        String format(String input);
    }
}
