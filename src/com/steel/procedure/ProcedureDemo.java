package com.steel.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.junit.Test;

import com.steel.entry.User;
import com.steel.util.DBConnection;

public class ProcedureDemo {

	Statement stmt = null;
    ResultSet rs = null;
    Connection conn = null;
    CallableStatement cstmt = null;
    //调用存储过过程往数据库中插入数据
	//@Test
	public void addUserByProcedure(){
		conn = DBConnection.getConnection();
		CallableStatement proc = null;
		try {
			proc = conn.prepareCall("{call scott.adduser(?,?,?,?)}");
			proc.setString(1, "w123");
			proc.setString(2, "houddm");
			proc.setString(3, "wxl654");
			proc.setString(4, "shanghai");
			proc.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.close(rs,proc,stmt, conn);
	}

	//@Test
	public void findUserNameByProcedure(){
		conn = DBConnection.getConnection();
		try {
			cstmt = conn.prepareCall("{call scott.getuser(?,?)}");
			cstmt.setString(1, "w123");
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.execute();
			String username = cstmt.getString(2);
			System.out.println(username.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBConnection.close(rs, cstmt, stmt, conn);
	}
	@Test
	public void listUserByProcedure(){
		conn = DBConnection.getConnection();
		List<User> list = new ArrayList<User>();
		User user = null;
		try {
			cstmt = conn.prepareCall("{call scott.userlist(?)}");
			cstmt.registerOutParameter(1, OracleTypes.CURSOR);
			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(1);
			while(rs.next()){
				user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				user.setAddress(rs.getString("address"));
				list.add(user);
			}
			for(User u :list){
				System.out.println(u.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
