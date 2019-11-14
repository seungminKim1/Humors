package humor.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import humor.Bean.MemberBean;

public class MemberDAO {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	DataSource ds;
	public MemberDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource)init.lookup("java:comp/env/jdbc/Humor");
		} catch(Exception e) {
			System.out.println("DB 연결 실패 :"+e);
			return;
		}
	}
	public int isMember(MemberBean member) {
		String sql = "select member_pw from member where member_id=?";
		int result = -1;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getMember_id());
			rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getString("member_pw").equals(member.getMember_pw())) {
					result = 1; // 일치
				}else {
					result = 0; //불일치
				}
			} else {
				result = -1; //아이디 존재하지 않음
			}
		}catch (Exception e) {
			System.out.print("isMember 에러:"+e);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return result;
	}
	
	public boolean joinMember (MemberBean member) {
		String sql = "insert into member values(?,?,?,?)";
		int result = 0;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, member.getMember_id());
			ps.setString(2, member.getMember_pw());
			ps.setString(3, member.getMember_name());
			ps.setInt(4, member.getMember_age());
			result = ps.executeUpdate();
			if(result!=0) {
				return true;
			}
		}catch (Exception e) {
			System.out.print("joinMember 에러:"+e);
		}finally {
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return false;
	}
	public List getMemberList() {
		String sql = "select * from member";
		List memberList = new ArrayList();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setMember_id(rs.getString("member_id"));
				mb.setMember_pw(rs.getString("member_pw"));
				mb.setMember_name(rs.getString("member_name"));
				mb.setMember_age(rs.getInt("member_age"));
				memberList.add(mb);
			}
			return memberList;
		}catch (Exception e) {
			System.out.print("getMemberList 에러:"+e);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return null;
	}
	public MemberBean getDetailMember(String id) {
		String sql = "select * from member where member_id = ?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			rs.next();
			
			MemberBean mb = new MemberBean();
			mb.setMember_id(rs.getString("member_id"));
			mb.setMember_pw(rs.getString("member_pw"));
			mb.setMember_name(rs.getString("member_name"));
			mb.setMember_age(rs.getInt("member_age"));
			return mb;
		}catch (Exception e) {
			System.out.print("getDetailMember 에러:"+e);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return null;
	}
	public boolean deleteMember(String id) {
		String sql = "delete from member where member_id=?";
		int result = 0;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,id);
			result = ps.executeUpdate();
			if(result != 0) {
				return true;
			}
		}catch (Exception e) {
			System.out.println("deleteMember에러"+e);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return false;
	}
}
