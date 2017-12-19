/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalDBConnector {

    Connection conn = null;

    //this is db url connect
    private final String DB_URL = "jdbc:derby:localDB";
    private final String USER = "root";
    private final String PASSWORD = "";

    public LocalDBConnector() throws SQLException {

        //conn= DriverManager.getConnection(DB_URL, USER, PASSWORD);
        try {

            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Opened default database successfully");

        } catch (Exception ex) {
            try {

                conn = DriverManager.getConnection("jdbc:derby:localDB;create=true");
                //System.out.println("Opened new database successfully");
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE test(serial INT,e_from VARCHAR(300),rcv_date VARCHAR(100),subject VARCHAR(500), message TEXT");
                System.out.println("DB createD successfully");

            } catch (SQLException exx) {
                exx.printStackTrace();
            }
        }

    }

    public Statement getStatement() throws SQLException {
        return conn.createStatement();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {

        return conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.CLOSE_CURSORS_AT_COMMIT);

    }

}
