package br.com.fourcamp.ff.usecase;

import br.com.fourcamp.ff.exceptions.UserNotFoundException;
import br.com.fourcamp.ff.models.User;
import org.junit.Before;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() throws IOException {
        // Limpar o arquivo JSON antes de cada teste
        try (FileWriter writer = new FileWriter("src/main/resources/users.json")) {
            writer.write("{}");
        }
        userService = new UserService();
    }

    @Test
    public void testAddUserAndGetUserByCPF() {
        User user = new User("Test User", "(11)99999-9999", "123.456.789-00", "Password@123", "Test Street, 123", "Test Question", "Test Answer");
        userService.addUser(user);

        try {
            User retrievedUser = userService.getUserByCPF("123.456.789-00");
            assertEquals("Test User", retrievedUser.getName());
            assertEquals("(11)99999-9999", retrievedUser.getPhone());
            assertEquals("123.456.789-00", retrievedUser.getCpf());
            assertEquals("Password@123", retrievedUser.getPassword());
            assertEquals("Test Street, 123", retrievedUser.getAddress());
            assertEquals("Test Question", retrievedUser.getSecurityQuestion());
            assertEquals("Test Answer", retrievedUser.getSecurityAnswer());
        } catch (UserNotFoundException e) {
            fail("UserNotFoundException was thrown");
        }
    }

    @Test
    public void testGetUserByCPF_UserNotFound() {
        try {
            userService.getUserByCPF("000.000.000-00");
            fail("Expected UserNotFoundException to be thrown");
        } catch (UserNotFoundException e) {
            // Expected exception
        }
    }

    @Test
    public void testValidateUserPassword() {
        User user = new User("Test User", "(11)99999-9999", "123.456.789-00", "Password@123", "Test Street, 123", "Test Question", "Test Answer");
        userService.addUser(user);

        assertTrue(userService.validateUserPassword("123.456.789-00", "Password@123"));
        assertFalse(userService.validateUserPassword("123.456.789-00", "WrongPassword"));
    }

    @Test
    public void testValidateUserSecurityAnswer() {
        User user = new User("Test User", "(11)99999-9999", "123.456.789-00", "Password@123", "Test Street, 123", "Test Question", "Test Answer");
        userService.addUser(user);

        assertTrue(userService.validateUserSecurityAnswer("123.456.789-00", "Test Answer"));
        assertFalse(userService.validateUserSecurityAnswer("123.456.789-00", "Wrong Answer"));
    }

    @Test
    public void testUpdateUserPassword() {
        User user = new User("Test User", "(11)99999-9999", "123.456.789-00", "Password@123", "Test Street, 123", "Test Question", "Test Answer");
        userService.addUser(user);

        userService.updateUserPassword("123.456.789-00", "NewPassword@123");
        try {
            User updatedUser = userService.getUserByCPF("123.456.789-00");
            assertEquals("NewPassword@123", updatedUser.getPassword());
        } catch (UserNotFoundException e) {
            fail("UserNotFoundException was thrown");
        }
    }

    @Test
    public void testLoadUsersFromFile() {
        User user = new User("Test User", "(11)99999-9999", "123.456.789-00", "Password@123", "Test Street, 123", "Test Question", "Test Answer");
        userService.addUser(user);

        UserService newUserService = new UserService();
        try {
            User loadedUser = newUserService.getUserByCPF("123.456.789-00");
            assertEquals("Test User", loadedUser.getName());
        } catch (UserNotFoundException e) {
            fail("UserNotFoundException was thrown");
        }
    }
}
