package br.com.fourcamp.ff.models;

public class User {
    private final String name;
    private final String phone;
    private final String cpf;
    private String password;
    private final String address;
    private final String securityQuestion;
    private final String securityAnswer;

    public User(String name, String phone, String cpf, String password, String address, String securityQuestion, String securityAnswer) {
        this.name = name;
        this.phone = phone;
        this.cpf = cpf;
        this.password = password;
        this.address = address;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
