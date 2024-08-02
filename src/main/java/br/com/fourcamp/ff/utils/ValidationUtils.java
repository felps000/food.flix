package br.com.fourcamp.ff.utils;

import br.com.fourcamp.ff.exceptions.InvalidInputException;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern PHONE_PATTERN = Pattern.compile("\\(\\d{2}\\)\\d{4,5}-\\d{4}");
    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");

    private ValidationUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static void validateCPF(String cpf) throws InvalidInputException {
        if (!CPF_PATTERN.matcher(cpf).matches()) {
            throw new InvalidInputException("CPF inválido. Use o formato 000.000.000-00.");
        }
    }

    public static void validatePhone(String phone) throws InvalidInputException {
        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidInputException("Telefone inválido. Use o formato (00)00000-0000.");
        }
    }

    public static void validatePassword(String password) throws InvalidInputException {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new InvalidInputException("A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula e um caractere especial (@#$%^&+=).");
        }
    }

    public static void validateName(String name) throws InvalidInputException {
        if (name.isEmpty()) {
            throw new InvalidInputException("Nome inválido. Por favor, insira um nome válido.");
        }
    }

    public static void validateStreet(String street) throws InvalidInputException {
        if (street.isEmpty()) {
            throw new InvalidInputException("Nome da rua inválido. Por favor, insira um nome válido.");
        }
    }

    public static void validateChoice(String choice, int min, int max) throws InvalidInputException {
        try {
            int intChoice = Integer.parseInt(choice);
            if (intChoice < min || intChoice > max) {
                throw new InvalidInputException("Escolha inválida. Por favor, selecione uma opção entre " + min + " e " + max + ".");
            }
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Escolha inválida. Por favor, insira um número válido.");
        }
    }

    public static void validateYesNoChoice(String choice) throws InvalidInputException {
        if (!choice.equals("s") && !choice.equals("n")) {
            throw new InvalidInputException("Escolha inválida. Por favor, responda com 's' ou 'n'.");
        }
    }

    public static void validateInteger(String number) throws InvalidInputException {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Número inválido. Por favor, insira um número válido.");
        }
    }

    public static String formatCPF(String cpf) {
        if (cpf.length() == 11) {
            return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
        }
        return cpf;
    }

    public static String formatPhone(String phone) {
        if (phone.length() == 11) {
            return "(" + phone.substring(0, 2) + ")" + phone.substring(2, 7) + "-" + phone.substring(7);
        }
        return phone;
    }
}
