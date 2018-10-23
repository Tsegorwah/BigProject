/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fmg
 */
public class BaseDao {
        private static Connection conn;
    public static Connection getConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection)
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/web?useUnicode=true&characterEncoding=UTF8", "root", "662213");
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    public void closeConn(Connection conn,PreparedStatement pstmt,ResultSet rs)
	{
		try {
			if(rs!=null)
			{
				rs.close();
			}
			if(pstmt!=null)
			{
				pstmt.close();
			}
			if(conn!=null)
			{
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public boolean update(String sql,List<Object> lp)
	{
		boolean isFlag = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = getConnection();
		if(conn!=null)
		{
			try {
				pstmt = conn.prepareStatement(sql);
				if(lp.size()>0)
				{
					for(int i = 0;i < lp.size();i++)
					{
						pstmt.setObject(i+1,lp.get(i));
					}
				}
				int num = pstmt.executeUpdate();
				if(num > 0 )
				{
					isFlag = true;
				}
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}finally {
				closeConn(conn,pstmt,rs);
			}
		}
		return isFlag;
	}


	public <T>List<T> query(String sql,List<Object> lp,Class clazz)
	{
		List<T> list=new ArrayList<T>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		conn=getConnection();
		if(conn!=null)
		{
			try {
				pstmt=conn.prepareStatement(sql);
				if(lp.size()>0)
				{
					for(int i=0;i<lp.size();i++)
					{
						pstmt.setObject(i+1,lp.get(i));
					}
				}
				rs=pstmt.executeQuery();
				while(rs.next())
				{
					/*
					 * ������ĵ�һ�д洢������
					 */
					Object obj=clazz.newInstance();
					ResultSetMetaData rm=rs.getMetaData();//rm����洢�˽�������е�����
					int num=rm.getColumnCount();
					for(int i=1;i<=num;i++)
					{
						String name=rm.getColumnName(i);//�õ�����
						Field f=clazz.getDeclaredField(name);
						f.setAccessible(true);
						Object o=rs.getObject(i);
						f.set(obj,o);
					}
					list.add((T) obj);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				closeConn(conn, pstmt, rs);
			}
		}
		return list;
	}
	
}

