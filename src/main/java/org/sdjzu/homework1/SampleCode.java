package org.sdjzu.homework1;

import org.sdjzu.util.DBConnection;
import java.sql.*;
public class SampleCode {
    public static  void main(String[] args) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "{call getuserNameAndAgeById(?,?,?)}";
            cs = conn.prepareCall(sql);
            cs.setInt(1, 3);
            cs.registerOutParameter(2, Types.VARCHAR);
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            String userName = cs.getString(2);
            int userAge = cs.getInt("userAge");
            System.out.println(userName + "-" + userAge);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, cs, conn);
        }

        //手动提交事务
//        Connection conn = null;
//        PreparedStatement ps1 = null;
//        PreparedStatement ps2 = null;
//        ResultSet rs = null;
//        try{
//            conn = DBConnection.getConnection();
//            conn.setAutoCommit(false);
//            String sql1="insert into user(id,name,password,sex,age,birthday)values(3,'李晨','123','男',15,'2008-08-08')";
//            String sql2="insert into student(stuid,name,sex,age,birthday)values(3,'李晨','男',15,'2008-08-08')";
//            ps1 = conn.prepareStatement(sql1);
//            ps1.executeUpdate();
//            ps2  = conn.prepareStatement(sql2);
//            ps2.executeUpdate();
//            conn.commit();
//        }catch(SQLException e){
//            e.printStackTrace();
//            conn.rollback();
//        }finally {
//            conn.setAutoCommit(true);
//            DBConnection.close(null, ps1, null);
//            DBConnection.close(rs, ps2, conn);
//        }

        //存储点
//        Connection conn = null;
//        Savepoint point = null;
//        try{
//            conn = DBConnection.getConnection();
//            conn.setAutoCommit(false);
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate("insert into student(sno,sex) values('2021', '男')");
//
//            point = conn.setSavepoint();
//
//            stmt.executeUpdate("insert into student(sno,sex) values('2022', '女')");
//            conn.commit();
//
//        }catch (SQLException e){
//            e.printStackTrace();
//            if(conn != null) {
//                try {
//                    if (point == null) {
//                        conn.rollback();
//                    }else{
//                        conn.rollback(point);
//                        conn.releaseSavepoint(point);
//                    }
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                    throw new RuntimeException("运行错误",e1);
//                }
//            }
//        }finally {
//            DBConnection.close(null, null, conn);
//        }

    }//main


}//类





















