package br.com.fourcamp.ff.usecase;

import br.com.fourcamp.ff.exceptions.UserNotFoundException;
import br.com.fourcamp.ff.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String, User> usersByCPF;
    private static final String USERS_FILE_PATH = "src/main/resources/users.json";
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService() {
        usersByCPF = loadUsersFromFile();
    }

    public User getUserByCPF(String cpf) throws UserNotFoundException {
        User user = usersByCPF.get(cpf);
        if (user == null) {
            throw new UserNotFoundException("Usuário não encontrado.");
        }
        return user;
    }

    public boolean validateUserPassword(String cpf, String password) {
        User user = usersByCPF.get(cpf);
        return user != null && user.getPassword().equals(password);
    }

    public String getUserSecurityQuestion(String cpf) throws UserNotFoundException {
        User user = getUserByCPF(cpf);
        return user.getSecurityQuestion();
    }

    public boolean validateUserSecurityAnswer(String cpf, String answer) {
        User user = usersByCPF.get(cpf);
        return user != null && user.getSecurityAnswer().equals(answer);
    }

    public void updateUserPassword(String cpf, String newPassword) {
        User user = usersByCPF.get(cpf);
        if (user != null) {
            user.setPassword(newPassword);
            saveUsersToFile();
        }
    }

    public void addUser(User user) {
        usersByCPF.put(user.getCpf(), user);
        saveUsersToFile();
    }

    private Map<String, User> loadUsersFromFile() {
        try (FileReader reader = new FileReader(USERS_FILE_PATH)) {
            Type type = new TypeToken<Map<String, User>>() {}.getType();
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            logger.error("Erro ao carregar usuários do arquivo: {}", e.getMessage());
            return new HashMap<>();
        }
    }

    private void saveUsersToFile() {
        try (FileWriter writer = new FileWriter(USERS_FILE_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(usersByCPF, writer);
        } catch (IOException e) {
            logger.error("Erro ao salvar usuários no arquivo: {}", e.getMessage());
        }
    }
}
