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

public class ReplyDaoImpl {

    BaseDao bs = new BaseDao();
    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmtt = null;
    ResultSet rs = null;

    public boolean addReply(String username, String replyDate, String replyContent, int topicId) {
        boolean flag = false;
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "insert into reply(username,replyDate,replyContent,topicId)values(?,?,?,?)";
            String sqll = "update topic set replyCount=replyCount+1 where Id=" + topicId;
            try {
                pstmt = conn.prepareStatement(sql);
                pstmtt = conn.prepareStatement(sqll);
                pstmt.setString(1, username);
                pstmt.setString(2, replyDate);
                pstmt.setString(3, replyContent);
                pstmt.setInt(4, topicId);
                pstmt.executeUpdate();
                pstmtt.executeUpdate();
                flag = true;
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return flag;
    }

    public ArrayList getReplyContentByPage(int pageSize, int currentPage, int topicId) {
        ArrayList al = new ArrayList();
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select * from reply where topicId = " + topicId + " limit " + pageSize * (currentPage - 1) + "," + pageSize;
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Reply rb = new Reply();
                    rb.setId(rs.getInt(1));
                    rb.setUsername(rs.getString(2));
                    rb.setReplyDate(rs.getString(3));
                    rb.setReplycontent(rs.getString(4));
                    al.add(rb);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return al;
    }

    public int getRowCount(int topicId) {
        int rowCount = 0;
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select count(*) from reply where topicId = "+topicId;
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

    public Reply getReplyById(String id) {
        Reply rb = new Reply();
        conn = bs.getConnection();
        if (conn != null) {
            String sql = "select * from reply where topicId=" + id;
            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    rb.setId(rs.getInt(1));
                    rb.setUsername(rs.getString(2));
                    rb.setReplyDate(rs.getString(3));
                    rb.setReplycontent(rs.getString(4));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                bs.closeConn(conn, pstmt, rs);
            }
        }
        return rb;
    }

}
