/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raffle;

/**
 *
 * @author kortemil
 */
import java.sql.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        DatabaseControl dc = new DatabaseControl();
        Connection connection = dc.connectDatabase();
        Statement stmt = dc.getStatement(connection);
        String sqlStmt = "SELECT * FROM projectCategory;";
        ResultSet rs = dc.getResultSet(stmt, sqlStmt);
//
        while (rs.next()) {
            System.out.println(rs.getString("category"));
        }
//

        stmt.close();
        rs.close();

        connection.close();
        System.out.println(stmt.isClosed());
        System.out.println(rs.isClosed());
        System.out.println(connection.isClosed());

    }

}
