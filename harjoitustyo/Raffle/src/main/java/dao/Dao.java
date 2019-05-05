/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author kortemil
 */
import java.sql.*;
import java.util.*;

public interface Dao<T, K> {

    void create(T object) throws SQLException;

    T read(K key) throws SQLException;

    List<T> list() throws SQLException;
}
