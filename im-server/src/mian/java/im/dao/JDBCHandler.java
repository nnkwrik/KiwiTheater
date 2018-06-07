package im.dao;


import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class JDBCHandler {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/kiwitheater";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "1234";

    public static Connection getCon() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);

            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ArrayList<Integer> getAllRoom() {
        Connection conn = getCon();
        String sql = "SELECT id FROM room";
        PreparedStatement pstmt = null;
        ArrayList<Integer> roomids = new ArrayList<>();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                roomids.add(rs.getInt("id"));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return roomids;
    }


}
