package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Database;
import domain.User;

public class UserDao implements Dao<User, String> {

    private Database database;

    /**
     * Konstruktori
     *
     * @param database käytössä oleva tietokanta
     */
    public UserDao(Database database) {
        this.database = database;
    }

    /**
     * Konstruktori luo uuden tietokantayhteyden
     *
     * @throws SQLException
     */
    public UserDao() throws SQLException {
        this.database = new Database();
    }

    /**
     * Luo uuden tietokanta rivin käyttäjästä
     *
     * @param user käyttäjä joka luodaan
     * @throws SQLException
     */
    @Override
    public void create(User user) throws SQLException {
        PreparedStatement stmt = database.getConnection().prepareStatement("INSERT INTO user"
                + " (username, isModerator)"
                + " VALUES (?, ?)");
        stmt.setString(1, user.getUsername());
        stmt.setBoolean(2, user.isModerator());
        stmt.executeUpdate();
        stmt.close();
        database.getConnection().close();
    }

    /**
     * Lukee käyttäjän tietokannasta
     *
     * @param key luettavan käyttäjän indeksi
     * @return indeksissä oleva käyttäjä
     * @throws SQLException
     */
    @Override
    public User read(String key) throws SQLException {
        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM user WHERE username = ?");
        stmt.setString(1, key);
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            return null;
        }

        User u = new User(rs.getString("username"),
                rs.getBoolean("isModerator"));

        stmt.close();
        rs.close();
        database.getConnection().close();

        return u;
    }

    /**
     * Listaa kaikki käyttäjät.
     *
     * @return kaikki tietokannan käyttäjät
     * @throws SQLException
     */
    @Override
    public List<User> list() throws SQLException {

        PreparedStatement stmt = database.getConnection().prepareStatement("SELECT * FROM User");
        ResultSet rs = stmt.executeQuery();

        List<User> users = new ArrayList<>();

        while (rs.next()) {
            users.add(this.read(rs.getString("username")));
        }

        stmt.close();
        rs.close();
        database.getConnection().close();
        return users;
    }

}
