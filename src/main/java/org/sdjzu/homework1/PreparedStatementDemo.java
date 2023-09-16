package org.sdjzu.homework1;


import org.sdjzu.util.DBConnection;

import java.sql.*;

public class PreparedStatementDemo {
    public static void main(String[] args) {
        insertStudent("202100000156","男");
        insertStudent("202100000157","男");
        updateStudent("202100000156","女");
        deleteStudent("202100000157");
        selectStudent("202100000156");

    }
    public static void insertStudent(String sno,String sex) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO student(sno, sex) VALUES (?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            ps.setString(2,sex);
            int rowsInsert = ps.executeUpdate();
            if (rowsInsert > 0) {
                System.out.println("插入成功");
            } else {
                System.out.println("插入失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
    }

    public static void updateStudent(String sno,String sex){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE student SET sex = ? WHERE sno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sex);
            ps.setString(2, sno);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("学号为"+sno+"的学生性别修改成功");
            } else {
                System.out.println("未找到该学生");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
    }
    public static void deleteStudent(String sno){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "DELETE FROM student WHERE sno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("学号为"+sno+"的学生删除成功");
            } else {
                System.out.println("未找到该学生");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
    }

    public static void selectStudent(String sno) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM student WHERE sno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, sno);
            rs = ps.executeQuery();
            while (rs.next()) {
                String sno2 = rs.getString("sno");
                String sex2 = rs.getString("sex");
                System.out.println("学号:"+sno2+" 性别:"+sex2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(rs, ps, conn);
        }
    }
}
