package service;

import dao.UserDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import domain.User;

public class UserService {

    private UserDao userDao;

    /**
     * Konstruktori.
     *
     * @param userDao käyttäjän dao
     */
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addNewUser(String username) throws SQLException {
        userDao.create(new User(username, false));
    }

    /**
     * Tarkastaa onko haluttu käyttäjänimi vapaana.
     *
     * @param username käyttäjänimi
     * @return onko käyttäjänimi vapaana
     */
    public boolean usernameIsAvailable(String username) throws SQLException {
        List<String> usernames = this.usernames(userDao.list());

        if (usernames.isEmpty()) {
            return true;
        } else if (usernames.contains(username)) {
            return false;
        }
        return true;
    }

    /**
     * Metodi palauttaa käyttäjien käyttäjänimet merkkijonolistana
     *
     * @param users lista olemassa olevista käyttäjistä
     * @return käyttäjänimet merkkijonot
     */
    public List<String> usernames(List<User> users) {
        List<String> usernames = new ArrayList<>();
        for (User u : users) {
            usernames.add(u.getUsername());
        }
        return usernames;
    }

    /**
     * Tarkistaa onko käyttäjänimi olemassa tietokannassa
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean usernameExists(String username) throws SQLException {
        List<String> usernames = this.usernames(userDao.list());
        return usernames.contains(username);
    }

    /**
     * Tarkistaa onko käyttäjänimi admin käyttäjän.
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean userIsAdmin(String username) throws SQLException {
        return userDao.read(username).isModerator();
    }

}
