package BankDao;

import java.sql.*;

public class BaseDao {
    protected Connection con;
    protected PreparedStatement pst;
    protected ResultSet rs;

    // 获得数据库连接
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/bank" +
                "?serverTimezone=GMT%2B8" +
                "&useSSL=false" +
                "&allowPublicKeyRetrieval=true" +
                "&useUnicode=true&characterEncoding=UTF-8";
        String user = "root";
        String password = "root123456";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(url, user, password);
        closeAll();
        return con;
    }

    // 关闭对象
    public void closeAll() throws SQLException {
        if(rs != null) {
            rs.close();
        }
        if(pst != null) {
            pst.close();
        }
        if(con != null) {
            con.close();
        }
    }
}
