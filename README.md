# FoodFlix - Projeto de Aplicativo de Delivery em Java

📌 Projeto desenvolvido por Felipe Rocha, projeto FourCamp.

O objetivo deste projeto é criar um aplicativo de delivery chamado FoodFlix. Através de um menu interativo, os usuários podem cadastrar-se, fazer login, selecionar restaurantes, escolher itens do menu e realizar pedidos.

## **📍 IMPORTANTE 📍**
Todo o conteúdo do projeto está localizado na pasta do projeto FoodFlix 3.0:
Além disso, possuímos um arquivo **.JAR** que pode ser executado no **CMD** ou **terminal** do seu sistema operacional. Todo o nosso projeto está localizado na pasta **src/br/com/fourcamp/**:

## 📝👨‍💻 Como foi criado:
Criamos um processo em Java que realiza as seguintes operações:

### **🛠️ Primeiro Passo:**
Criamos a estrutura do projeto utilizando Maven para gerenciamento de dependências e organização do código.

### **🛠️ Segundo Passo:**
Desenvolvemos as classes necessárias para representar os usuários, restaurantes, pedidos, além de serviços para manipular esses dados e utilitários para validação.

### **🛠️ Terceiro Passo:**
Implementamos a classe `Main` que contém o menu interativo para que os usuários possam navegar pelas opções disponíveis.

### **📚 Estrutura do Projeto:**

<div align="center">
<img src="https://github.com/user-attachments/assets/45a2b7c9-6e39-4a5e-b9c5-752f0458360b" height="600" />
</div>

### 💻 Tecnologias usadas: 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" width="50" height="50" />    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/maven/maven-original-wordmark.svg" width="50" height="50" />

## 📖 Funcionalidades

- **Cadastro de Usuários:**
  - CPF: O CPF deve ser válido e formatado como "000.000.000-00".
  - Telefone: O telefone deve ser válido e formatado como "(00)00000-0000".
  - Senha: A senha deve ter pelo menos 8 caracteres, incluindo uma letra maiúscula e um caractere especial (@#$%^&+=).
  - Nome: O nome não pode ser vazio.
  - Endereço: O endereço deve ser válido, incluindo tipo de residência e detalhes adicionais.

- **Login de Usuários:**
  - O usuário deve fornecer um CPF e senha válidos.
  - Se a senha estiver incorreta, o usuário pode optar por redefinir a senha respondendo à pergunta de segurança.

- **Seleção de Restaurantes:**
  - O usuário pode selecionar múltiplos restaurantes para realizar o pedido.
  - O usuário pode cancelar o pedido de um restaurante específico durante o processo de seleção de itens do menu.

- **Seleção de Itens do Menu:**
  - O usuário pode selecionar itens de diferentes categorias (Sanduíches, Acompanhamentos, Bebidas, Sobremesas) dos menus dos restaurantes selecionados.

- **Cálculo de Preços:**
  - O preço total dos itens é calculado com base nos preços dos itens selecionados do menu.
  - A taxa de entrega é calculada com base na quantidade de restaurantes selecionados e pode variar entre um valor mínimo e máximo.

- **Resumo do Pedido:**
  - Um resumo detalhado do pedido é exibido ao usuário, incluindo os itens selecionados, o endereço de entrega, o preço total, a taxa de entrega e o preço final.

- **Formas de Pagamento:**
  - Pix: Um código Pix é gerado para o usuário realizar o pagamento.
  - Débito/Crédito: O usuário é informado que deve pagar o pedido ao retirar o produto.

- **Validação de Dados:**
  - Todas as entradas do usuário são validadas para garantir que estejam no formato correto e que não sejam nulas ou vazias.
  - Mensagens de erro apropriadas são exibidas para o usuário quando uma validação falha.

## 🤝 Agradecimentos:
Agradecimentos à equipe FourCamp por proporcionar essa experiência de aprendizado e desenvolvimento.

## 💡 Como Executar:
Para executar o projeto, utilize o Maven para compilar e rodar a aplicação. Navegue até o diretório do projeto e execute os seguintes comandos:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="br.com.fourcamp.ff.controller.Main"
