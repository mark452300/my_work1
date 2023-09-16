package org.sdjzu;


import java.sql.*;

public class jdbc {
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/testdb?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
    private static final String dbuser = "root";
    private static final String dbpassword = "232626212";

    private jdbc() {
    }
    static {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,dbuser,dbpassword) ;
    }
    //关闭连接
    public static void close(ResultSet rs, Statement st, Connection conn){
        try {
            if (rs != null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (st !=null){
                    st.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                try {
                    if (conn != null){
                        conn.close();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }

            }

        }
    }
    //插入数据
    public void add1() throws SQLException{
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = jdbc.getConnection();
            String sql = "insert into user(name,password,sex,birthday)" +
                    "values('zhangsan','123','man','1990-12-31')";
            st = conn.createStatement();
            st.executeUpdate(sql);

        }finally {
            jdbc.close(rs, st, conn);
        }
    }
    //插入数据
    public void add() throws ClassNotFoundException,SQLException{
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/testdb?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
        String dbuser = "root";
        String dbpassword = "232626212";


        Class.forName(driverName);

        Connection conn = DriverManager.getConnection(url,dbuser,dbpassword) ;
        String sql = "insert into user(name,password,sex,birthday)" +
                "values('zhangsan','123','man','1990-12-31')";
        Statement stmt = conn.createStatement();
        int lines = stmt.executeUpdate(sql);
        System.out.println("lines="+lines);

        stmt.close();
        conn.close();
    }
    //列出数据
    public static void listAll() throws ClassNotFoundException,SQLException{
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/testdb?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
        String dbuser = "root";
        String dbpassword = "232626212";

        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url,dbuser,dbpassword) ;
        String sql = "select * from user";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()){
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            String sex = rs.getString("sex");
            Date birthday = rs.getDate("birthday");
            System.out.println("id="+id+";name="+name+";password="+password+";sex="+sex+";birthday="+birthday);
        }
        rs.close();
        stmt.close();
        conn.close();
    }
    //更新数据
    public static void update() throws ClassNotFoundException,SQLException{
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/testdb?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
        String dbuser = "root";
        String dbpassword = "232626212";
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url,dbuser,dbpassword) ;
        String sql = "update user set password='666' where id=1" ;
        Statement stmt = conn.createStatement();
        int lines = stmt.executeUpdate(sql);
        System.out.println("lings="+lines);

        stmt.close();
        conn.close();

    }
    //删除数据
    public static void delete() throws ClassNotFoundException,SQLException{
        String driverName = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/testdb?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
        String dbuser = "root";
        String dbpassword = "232626212";
        Class.forName(driverName);
        Connection conn = DriverManager.getConnection(url,dbuser,dbpassword) ;
        String sql = "delete from user where id=1" ;
        Statement stmt = conn.createStatement();
        int lines = stmt.executeUpdate(sql);
        System.out.println("lings="+lines);

        stmt.close();
        conn.close();
    }
    //更新数据
    public static void update1() throws SQLException,ClassNotFoundException{
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            conn = jdbc.getConnection();
            String sql = "insert into user(name,password,sex,birthday)" +
                    "values(?,?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1,"zhangsan");
            st.setString(2,"123");
            st.setString(3,"man");
            st.setString(4,"1990-12-31");
            st.executeUpdate();

        }finally {
            jdbc.close(rs, st, conn);
        }
//        String driverName = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://localhost:3306/25?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false&allowPublicKeyRetrieval=true";
//        String dbuser = "root";
//        String dbpassword = "123456";
//
//
//        Class.forName(driverName);
//
//        Connection conn = DriverManager.getConnection(url,dbuser,dbpassword) ;
//        String sql = "insert into user(name,password,sex,birthday)" +
//                    "values(?,?,?,?)";
//        PreparedStatement stmt = conn.prepareStatement(sql);
//        stmt.setString(1,"zhangsan");
//        stmt.setString(2,"123");
//        stmt.setString(3,"man");
//        stmt.setString(4,"1990-12-31");
//        stmt.executeUpdate();
//        int lines = stmt.executeUpdate(sql);
//        System.out.println("lines="+lines);
//
//        stmt.close();
//        conn.close();
    }


}
