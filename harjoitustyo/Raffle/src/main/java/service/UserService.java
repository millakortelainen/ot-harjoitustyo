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

    /**
     * Lisää uuden käyttäjän ohjelmalle.
     *
     * @param user lisättävä käyttäjä
     * @throws SQLException käyttää dao:a tietokannan muokkaamiseen
     */
    public void addNewUser(User user) throws SQLException {
        userDao.create(user);
    }

    /**
     * Tarkastaa onko haluttu käyttäjänimi vapaana.
     *
     * @param usernameList käyttäjänimet merkkijono listana
     * @param username käyttäjänimi
     * @return onko käyttäjänimi vapaana
     */
    public boolean usernameIsAvailable(List<String> usernameList, String username) {
        if (usernameList.isEmpty()) {
            return true;
        } else if (!usernameList.contains(username)) {
            return true;
        }
        return false;
    }

    /**
     * Palauttaa seuraavan vapaan id-numeron käyttäjälle.
     *
     * @param list lista olemassaolevista käyttäjistä
     * @return palauttaa uuden id-numeron
     */
    public int nextId(List<User> list) {
        if (list.isEmpty()) {
            return 2;
        }
        return list.size() + 1;
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

}
