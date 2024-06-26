package com.example.app.domain.common.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.dto.Criteria;

public class CommonDao {
	protected Connection conn =null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
	
	protected ConnectionPool connectionPool;
	
	public CommonDao() throws Exception{
		connectionPool = ConnectionPool.getInstance();
		conn = connectionPool.getConnection();
	}
	
	
	//
	public void freeConnection(Connection conn) throws SQLException {
		 conn.close();
	}
	public void freeConnection(Connection conn,PreparedStatement pstmt) throws SQLException {
		pstmt.close(); 
		conn.close();
	}
	public void freeConnection(Connection conn,PreparedStatement pstmt,ResultSet rs) throws SQLException {
		rs.close();
		pstmt.close();
		conn.close();
	}
	public void freeConnection(PreparedStatement pstmt) throws SQLException {
		pstmt.close(); 
	}
	public void freeConnection(PreparedStatement pstmt,ResultSet rs) throws SQLException {
		rs.close();
		pstmt.close(); 
	}

}
