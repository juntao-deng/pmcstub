package net.juniper.monitor.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import net.juniper.jmp.tracer.db.driver.DerbyTracerDriver;

public class C {
	static{
		try {
			Class.forName(DerbyTracerDriver.class.getName());
			init();
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		Connection conn = getConnection();
		Statement st = null;
		PreparedStatement pst = null;
		try{
			st = conn.createStatement();
			st.execute("insert into m_test values('" + UUID.randomUUID().toString() + "', 'a')");
			ResultSet rs = st.executeQuery("select count(*) from m_test");
			while(rs.next()){
				
			}
			pst = conn.prepareStatement("select count(*) from m_test");
			pst.execute();
			rs = pst.getResultSet();
			while(rs.next()){
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
		}
	}
	
	private static void init() {
		Connection conn = getConnection();
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("create table m_test (id varchar(100) primary key, name varchar(200))");
		} 
		catch (SQLException e) {
		}
		finally{
			if(st != null){
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:derby:/var/tmp/monitor/test;create=true");
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
