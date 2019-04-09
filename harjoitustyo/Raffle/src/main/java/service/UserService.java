package service;

import dao.UserDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import domain.User;

public class UserService {

    UserDao userDao;

    public UserService() {
        this.userDao = new UserDao();
    }

    public void addNewUser(User user) throws SQLException {
        userDao.create(user);

    }

    public boolean usernameIsAvailable(List<String> usernameList, String username) {
        if (usernameList.isEmpty()) {
            return true;
        } else if (!usernameList.contains(username)) {
            return true;
        }
        return false;
    }

    public int nextId(List<User> list) {
        if (list.isEmpty()) {
            return 1;
        }

        return list.size() + 1;
    }

    public List<String> usernames(List<User> users) {
        List<String> usernames = new ArrayList<>();
        for (User u : users) {
            usernames.add(u.getUsername());
        }
        return usernames;
    }

}
