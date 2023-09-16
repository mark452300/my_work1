package org.sdjzu.homework1;

import org.sdjzu.util.DBConnection;

import java.sql.*;

public class StatementDemo {
        public static void main(String[] args) {
            insertStudent("202100000156","男");
            insertStudent("202100000157","男");
            updateStudent("202100000156","女");
            deleteStudent("202100000157");
            selectStudent("202100000156");

        }
        public static void insertStudent(String sno,String sex) {
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            try{
                conn = DBConnection.getConnection();
                String sql = "insert into student(sno,sex) values('" + sno + "', '" + sex + "')";
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("插入成功");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                DBConnection.close(rs,st,conn);
            }
        }

        public static void updateStudent(String sno,String sex){
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            try{
                conn = DBConnection.getConnection();
                String sql = "update student set sex='"+sex+"' where sno='"+sno+"'";
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("学号为"+sno+"的学生性别修改成功");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                DBConnection.close(rs,st,conn);
            }
        }
        public static void deleteStudent(String sno){
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            try{
                conn = DBConnection.getConnection();
                String sql = "delete from student where sno='"+sno+"' ";
                st = conn.createStatement();
                st.executeUpdate(sql);
                System.out.println("学号为"+sno+"的学生删除成功");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                DBConnection.close(rs,st,conn);
            }
        }

        public static void selectStudent(String sno){
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            try{
                conn = DBConnection.getConnection();
                String sql = "select * from student where sno='"+sno+"' ";
                st = conn.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    String sno2 = rs.getString("sno");
                    String sex2 = rs.getString("sex");
                    System.out.println("学号:"+sno2+"性别:"+sex2);
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                DBConnection.close(rs,st,conn);
            }
        }

}
