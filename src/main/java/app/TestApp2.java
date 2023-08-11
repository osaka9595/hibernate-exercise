package app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.activation.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mysql.cj.protocol.Resultset;

import web.member.dao.MemberDao;
import web.member.entity.Member;

public class TestApp2 {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		
////		try (
////			Connection conn = dataSource.getConnection();
////			PreparedStatement pstmt = conn.prepareStatement("select * from MEMBER where ID =?");
//		){
//			pstmt.setInt(1, 2);
//			try (ResultSet rs = pstmt.executeQuery()){
//				rs.next();
//				System.out.println(rs.getString("NICKNAME"));
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
  }
}
