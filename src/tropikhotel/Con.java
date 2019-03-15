/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tropikhotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author robot
 */
public class Con {
    public Connection conn() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/TropikHotel";
        String user = "root";
        String password = "iloveyou";
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
}
