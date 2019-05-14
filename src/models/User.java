package models;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class User {
    private static HashMap<String, User> users = new HashMap<>();

    private String username, password;
    private int highScore;

    public User(String username, String password) throws UserExistsException {
        if(users.containsKey(username))
            throw new UserExistsException(username);
        users.put(username, this);
        this.highScore = 0;
        this.username = username;
        this.password = password;
    }

    public static User authenticateUser(String username, String password) throws UserNotFoundException, WrongPasswordException {
        User user = users.get(username);
        if(user == null)
            throw new UserNotFoundException(username);
        if(user.password.equals(password))
            return user;
        throw new WrongPasswordException();
    }

    @Override
    public boolean equals(Object object) {
        if(object == this)
            return true;
        return object instanceof User && ((User) object).getUsername().equals(username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    public String getUsername() {
        return username;
    }

    public int getHighScore() {
        return highScore;
    }

    public static class UserNotFoundException extends Exception {
        public UserNotFoundException(String username) {
            super(String.format("Username '%s' not found.", username));
        }
    }

    public static class UserExistsException extends Exception {
        public UserExistsException(String username) {
            super(String.format("Username '%s' exists.", username));
        }
    }

    public static class WrongPasswordException extends Exception {
        public WrongPasswordException() {
            super("Wrong password");
        }
    }
}
