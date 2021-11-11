package Test;

import java.sql.*;

public class test {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/bank" +
                "?serverTimezone=GMT%2B8" +
                "&useSSL=false" +
                "&allowPublicKeyRetrieval=true" +
                "&useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String password = "root123456";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        Statement st = con.createStatement();
        PreparedStatement pst = null; // note 使用prepared封装对象

        String sql = "select * from account";
        pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while(rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            int money = rs.getInt(3);
            System.out.println(id + " " + name + " " + money);
        }

        pst.close();
        con.close();
    }
}
