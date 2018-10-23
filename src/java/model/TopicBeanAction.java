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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fmg
 */
public class TopicBeanAction {

    BaseDao bs = new BaseDao();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public boolean addTopic(String username, String title, String content, String city, String topicDate) {
        boolean flag = false;
        conn = bs.getConnection();
        if (conn != null) {
            try {
                String sql = "insert into topic(username,title,content,city,topicDate)values(?,?,?,?,?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, title);
                pstmt.setString(3, content);
                pstmt.setString(4, city);
                pstmt.setString(5, topicDate);
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

    public ArrayList getTopicByPage(int pageSize, int currentPage, String city) {
        ArrayList al = new ArrayList();
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select * from topic where city =" + "'" + city + "'" + " limit " + pageSize * (currentPage - 1) + "," + pageSize;
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    TopicBean tb = new TopicBean();
                    tb.setId(rs.getString(1));
                    tb.setUsername(rs.getString(2));
                    tb.setTitle(rs.getString(3));
                    tb.setTopicDate(rs.getString(4));
                    tb.setContent(rs.getString(5));
                    tb.setReplycount(rs.getString(6));
                    al.add(tb);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return al;
    }

    public int getRowCount() {
        int rowCount = 0;
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select count(*) from topic";
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    rowCount = rs.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return rowCount;
    }

    public TopicBean getTopicById(String Id) {
        TopicBean tb = new TopicBean();
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select * from topic where Id=" + Id;
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    tb.setId(rs.getString(1));
                    tb.setTitle(rs.getString(2));
                    tb.setUsername(rs.getString(3));
                    tb.setTopicDate(rs.getString(6));
                    tb.setReplycount(rs.getString(7));
                    tb.setContent(rs.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return tb;
    }

    public ArrayList getHotReplyByPage(int pageSize, int currentPage) {
        ArrayList al = new ArrayList();
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select * from topic ORDER BY replyCount DESC limit " + pageSize * (currentPage - 1) + "," + pageSize;
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    TopicBean tb = new TopicBean();
                    tb.setId(rs.getString(1));
                    tb.setUsername(rs.getString(2));
                    tb.setTitle(rs.getString(3));
                    tb.setTopicDate(rs.getString(4));
                    tb.setContent(rs.getString(5));
                    tb.setReplycount(rs.getString(6));
                    al.add(tb);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return al;
    }

    public ArrayList getTopicByUsername(String username) {
        ArrayList al = new ArrayList();
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select * from topic where username=" + "'" + username + "'";
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    TopicBean tb = new TopicBean();
                    tb.setId(rs.getString(1));
                    tb.setUsername(rs.getString(2));
                    tb.setTitle(rs.getString(3));
                    tb.setTopicDate(rs.getString(4));
                    tb.setContent(rs.getString(5));
                    tb.setReplycount(rs.getString(6));
                    al.add(tb);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return al;
    }

    public boolean deleteTopic(String id) {
        boolean flag = false;
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "delete from topic where Id =" + id;
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
