package br.com.fourcamp.ff.enums;

public enum MainMessagesEnum {
    START_APP("Iniciando aplicação FoodFlix"),
    CANCEL_MESSAGE("Sem problemas, peça a hora que quiser!"),
    WELCOME_MESSAGE("Bem-vindo ao FoodFLix!"),
    CPF_PROMPT("Informe seu CPF:"),
    PASSWORD_PROMPT("Informe sua senha:"),
    USER_FOUND("Usuário encontrado: "),
    INVALID_PASSWORD_MESSAGE("Senha inválida. Tente novamente."),
    FORGOT_PASSWORD_PROMPT("Esqueceu sua senha? (s/n)"),
    SECURITY_QUESTION_PROMPT("Responda sua pergunta de segurança para redefinir sua senha."),
    NEW_PASSWORD_PROMPT("Informe uma nova senha:"),
    PASSWORD_UPDATED("Senha atualizada com sucesso."),
    INVALID_SECURITY_ANSWER("Resposta de segurança inválida."),
    USER_NOT_FOUND("Usuário não encontrado"),
    REGISTER_PROMPT("Usuário não encontrado. Por favor, faça seu cadastro."),
    NAME_PROMPT("Informe seu nome:"),
    PHONE_PROMPT("Informe seu telefone (com DDD):"),
    STREET_PROMPT("Informe o nome da sua rua:"),
    RESIDENCE_TYPE_PROMPT("Você mora em: 1. Casa 2. Apartamento"),
    BUILDING_NAME_PROMPT("Informe o nome do edifício:"),
    INVALID_BUILDING_NAME("Nome do edifício inválido. Por favor, insira um nome válido."),
    APARTMENT_NUMBER_PROMPT("Informe o número do apartamento:"),
    HOUSE_NUMBER_PROMPT("Informe o número da sua casa:"),
    SECURITY_QUESTION_CHOICE_PROMPT("Escolha uma pergunta de segurança:"),
    SECURITY_QUESTION_OPTION_1("1. Qual é o nome do seu primeiro animal de estimação?"),
    SECURITY_QUESTION_OPTION_2("2. Qual é o nome da cidade onde você nasceu?"),
    SECURITY_QUESTION_OPTION_3("3. Qual é o seu filme favorito?"),
    INVALID_CHOICE("Escolha inválida. Por favor, selecione uma opção entre 1 e 3."),
    REGISTRATION_SUCCESS("Cadastro realizado com sucesso!"),
    SELECT_RESTAURANT_PROMPT("Selecione um restaurante:"),
    CONTINUE_OPTION("0. Continuar"),
    ALL_RESTAURANTS_SELECTED("Você já selecionou todos os restaurantes disponíveis."),
    ASK_MORE_RESTAURANTS("Deseja pedir em mais algum outro restaurante? (s/n)"),
    MENU_PROMPT("Menu do "),
    CANCEL_ORDER_PROMPT("Deseja cancelar seu pedido do "),
    SELECT_MENU_ITEMS_PROMPT("Selecione os itens que deseja pedir (número), ou 0 para voltar:"),
    INVALID_MENU_ITEM("Por favor, selecione itens válidos do menu."),
    FINISH_ORDER_PROMPT("Deseja encerrar seu pedido do "),
    INVALID_MENU_ITEM_NUMBER("Número do item inválido: "),
    INVALID_INPUT("Entrada inválida: "),
    INVALID_CATEGORY("Categoria inválida."),
    RETURN_OPTION("0. Voltar"),
    INVALID_RESPONSE_MESSAGE("Escolha inválida. Por favor, insira um número válido."),
    ORDER_SUCCESS("Pedido realizado com sucesso! Detalhes do pedido:"),
    ORDER_DETAILS("Pedido "),
    ITEMS_VALUE("Valor dos itens: R$ "),
    DELIVERY_FEE("Frete: R$ "),
    TOTAL_VALUE("Total: R$ "),
    PAYMENT_METHOD_PROMPT("Selecione a forma de pagamento: 1-Débito 2-Crédito 3-PIX"),
    PAYMENT_DEBIT("Forma de pagamento: Débito. Pague ao retirar o produto."),
    PAYMENT_CREDIT("Forma de pagamento: Crédito. Pague ao retirar o produto."),
    PAYMENT_PIX("Forma de pagamento: PIX"),
    PIX_CODE("Use o seguinte código PIX para pagar: "),
    INVALID_PAYMENT_CHOICE("Escolha inválida. Por favor, selecione uma opção entre 1 e 3."),
    DELIVERY_TIME("Seu pedido do restaurante "),
    MENU_OPTIONS_1("1. Sanduíches"),
    MENU_OPTIONS_2("2. Acompanhamentos"),
    MENU_OPTIONS_3("3. Bebidas"),
    MENU_OPTIONS_4("4. Sobremesas"),
    MENU_OPTIONS_SUSHI_1("1. Sushi"),
    MENU_OPTIONS_SUSHI_2("2. Sashimi"),
    MENU_OPTIONS_SUSHI_3("3. Bebidas"),
    MENU_OPTIONS_SUSHI_4("4. Sobremesas"),
    MENU_OPTIONS_PIZZA_1("1. Pizzas"),
    MENU_OPTIONS_PIZZA_2("2. Acompanhamentos"),
    MENU_OPTIONS_PIZZA_3("3. Bebidas"),
    MENU_OPTIONS_PIZZA_4("4. Sobremesas"),
    MENU_OPTIONS_PASTA_1("1. Massas"),
    MENU_OPTIONS_PASTA_2("2. Acompanhamentos"),
    MENU_OPTIONS_PASTA_3("3. Bebidas"),
    MENU_OPTIONS_PASTA_4("4. Sobremesas"),
    MENU_OPTIONS_BBQ_1("1. Cortes de Carne"),
    MENU_OPTIONS_BBQ_2("2. Acompanhamentos"),
    MENU_OPTIONS_BBQ_3("3. Bebidas"),
    MENU_OPTIONS_BBQ_4("4. Sobremesas");

    private final String message;

    MainMessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
