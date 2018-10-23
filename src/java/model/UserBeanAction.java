/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fmg
 */
public class UserBeanAction {

    BaseDao bs = new BaseDao();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public boolean regUser(String username, String password) {
        boolean flag = false;
        String sql = "insert into users(username,password) values(?,?)";
        List<Object> lp = new ArrayList<Object>();
        lp.add(username);
        lp.add(password);
        flag = bs.update(sql, lp);
        return flag;
    }

    public boolean checkUser(String username) {
        boolean flag = true;
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select * from users where username ='" + username + "'";
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    flag = false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return flag;
    }

    public String login(String username, String password) {
        String id = null;
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select*from users where username=? and password=?";
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    id = rs.getString("Id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return id;
    }

    public boolean deleteUser(String id) {
        boolean flag = false;
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "delete from users where id =" + id;
            try {
                pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return flag;
    }
}
