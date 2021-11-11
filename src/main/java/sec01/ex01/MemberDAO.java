package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import sec02.ex01.MemberVO;

public class MemberDAO {
	
	Context ctx;
	Context envContext;
	DataSource dataFactory;
	Connection con;
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	public MemberDAO() {
		try {
			ctx = new InitialContext();
			envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();

		try {
			con = dataFactory.getConnection();
			
			String query = "select * from t_member order by joinDate desc";
			
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				MemberVO memberVO = new MemberVO(id, pw, name, email, joinDate);
				membersList.add(memberVO);
				
			}

			rs.close();
			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return membersList;
	}
	
	public void addMember(MemberVO memberVO) {
		try {
			con = dataFactory.getConnection();
			
			String id = memberVO.getId();
			String pw = memberVO.getPw();
			String name = memberVO.getName();
			String email = memberVO.getEmail();
			
			String query = "insert into t_member(id, pw, name, email)" + " values(?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);

			pstmt.executeUpdate();

			pstmt.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
