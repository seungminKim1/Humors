package humor.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import humor.Bean.MemberBoardBean;
import humor.Bean.ReplyBean;



public class BoardDAO {
	DataSource ds;
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	
	public BoardDAO() {
		try {
			Context  init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/Humor");
			
		} catch (Exception e) {
			System.out.println("DB연결실패:"+e);
			return;
		}
	}
	public int getListCount() {
		int x = 0;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("select count(*) from memberboard");
			rs = ps.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		}catch (Exception e) {
			System.out.println("getListCount에러:"+e);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return x;
	}
	public List getBoardList (int page,int limit) {
		String board_list_sql="select * from memberboard limit ?,?";
		List<MemberBoardBean> list = new ArrayList<MemberBoardBean>();
		
		int startrow = (page-1)*10;
		int endrow = startrow+limit-1;
		System.out.println("startrow"+startrow);
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(board_list_sql);
			ps.setInt(1, startrow);
			ps.setInt(2, endrow);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MemberBoardBean board = new MemberBoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_id(rs.getString("board_id"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getDate("board_date"));
				board.setBoard_like(rs.getInt("board_like"));
				list.add(board);
			}
			return list;
		}catch (Exception e) {
			System.out.println("getBoardList 에러 " +e);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return null;
	}
	public MemberBoardBean getDetail(int num){
		MemberBoardBean board = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("select * from memberboard where board_num = ?");
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				board = new MemberBoardBean();
				board.setBoard_num(rs.getInt("board_num"));
				board.setBoard_id(rs.getString("board_id"));
				board.setBoard_subject(rs.getString("board_subject"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_file(rs.getString("board_file"));
				board.setBoard_re_ref(rs.getInt("board_re_ref"));
				board.setBoard_re_lev(rs.getInt("board_re_lev"));
				board.setBoard_re_seq(rs.getInt("board_re_seq"));
				board.setBoard_readcount(rs.getInt("board_readcount"));
				board.setBoard_date(rs.getDate("board_date"));
			}
			return board;
		} catch (Exception e) {
			System.out.println("getDetail 에러 " +e);
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return null;
	}
	public boolean boardInsert(MemberBoardBean board) {
		int num = 0;
		String sql = "";
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("select max(board_num) from memberboard");
			rs = ps.executeQuery();
			
			if(rs.next()) 
				num = rs.getInt(1)+1;
			else 
				num = 1;
			
			sql = "insert into memberboard (board_num,board_id,board_subject,";
			sql += "board_content, board_file, board_re_ref,"+
					"board_re_lev,board_re_seq,board_readcount,"+
					"board_date,board_like) values (?,?,?,?,?,?,?,?,?,now(),?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setString(2, board.getBoard_id());
			ps.setString(3, board.getBoard_subject());
			ps.setString(4, board.getBoard_content());
			ps.setString(5, board.getBoard_file());
			ps.setInt(6, num);
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			ps.setInt(9, 0);
			ps.setInt(10, 0);
			
			result = ps.executeUpdate();
			if(result == 0) 
				return false;
			
			return true;
		} catch (Exception e) {
			System.out.println("boardInsert 에러"+e);
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return false;
	}
	
	public int boardReply(MemberBoardBean board) {
		String board_max_sql = "select max(board_num) from memberboard";
		String sql = "";
		int num = 0;
		int result = 0;
		
		int re_ref = board.getBoard_re_ref();
		int re_lev = board.getBoard_re_lev();
		int re_seq = board.getBoard_re_seq();
		
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(board_max_sql);
			rs = ps.executeQuery();
			if(rs.next())
				num = rs.getInt(1)+1;
			else 
				num = 1;
			
			sql = "update memberboard set board_re_seq = board_re_seq+1 where board_re_ref = ? and board_re_seq>?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, re_ref);
			ps.setInt(2, re_seq);
			result = ps.executeUpdate();
			
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;
			
			sql = "insert into memberboard (board_num,board_id,board_subject,board_content,board_file,board_re_ref,board_re_lev,board_re_seq,board_readcount,board_date) "
					+ "values(?,?,?,?,?,?,?,?,?,now())";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setString(2, board.getBoard_id());;
			ps.setString(3, board.getBoard_subject());
			ps.setString(4, board.getBoard_content());
			ps.setString(5, "");
			ps.setInt(6, re_ref);
			ps.setInt(7, re_lev);
			ps.setInt(8, re_seq);
			ps.setInt(9, 0);
			ps.executeUpdate();
			return num;
		} catch (Exception e) {
			System.out.println("boardReply 에러" + e);
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return 0;
	}
	public boolean registReply(ReplyBean rp) {
		String sql = "insert into reply (board_num,reply_id,reply_content) values (?,?,?)";
		int result = 0; 
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rp.getBoard_num());
			ps.setString(2, rp.getReply_id());
			ps.setString(3, rp.getReply_content());
			result = ps.executeUpdate();
			if(result == 0)
				return false;
			return true;
		} catch (Exception e) {
			System.out.println("registReply 에러" + e);
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return false;
	}
	public List replyView(int num) {
		List <ReplyBean> list = new ArrayList<ReplyBean>(); 
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement("select * from reply where board_num = ?");
			ps.setInt(1, num);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReplyBean rp= new ReplyBean();
				rp.setBoard_num(rs.getInt("board_num"));
				rp.setReply_content(rs.getString("reply_content"));
				rp.setReply_id(rs.getString("reply_id"));
				rp.setReply_num(rs.getInt("reply_num"));
				list.add(rp);
			}
			return list;
		}catch (Exception e) {
			System.out.println("registView 에러" + e);
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return null;
	}
	public int likeAdd(int num) {
		String sql = "update memberboard set board_like = board_like + 1 where board_num = "+num;
		int result = 0;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println("likeadd 에러" + e);
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return result;
	}
	public boolean boardModify(MemberBoardBean modifyboard) {
		String sql = "update memberboard set board_subject = ?,board_content = ? where board_num = ?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, modifyboard.getBoard_subject());
			ps.setString(2, modifyboard.getBoard_content());
			ps.setInt(3, modifyboard.getBoard_num());
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("boardModify error"+e);
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return false;
	}
	public boolean boardDelete(int num) {
		String board_delete_sql = "delete from memberboard where board_num =?" ;
		int result = 0;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(board_delete_sql);
			ps.setInt(1, num);
			result = ps.executeUpdate();
			String sql = "delete from reply where board_num = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			int result1 = ps.executeUpdate();
			if(result == 0 )
				return false;
			return true;
		} catch (Exception e) {
			System.out.println("boardDelete error"+e);
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return false;
	}
	public void setReadCountUpdate(int num) {
		String sql = "update memberboard set board_readcount = board_readcount+1 where board_num = "+num;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("setReadCountUpdate err"+e); 
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
	}
	public boolean isBoardWriter(int num,String id) {
		String board_sql = "select * from memberboard where board_num = ?";
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(board_sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				if(id.equals(rs.getString("board_id"))) {
					return true;
				}
			}
		}catch (Exception e) {
			System.out.println("isBoardWriter err"+e);
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(ps!=null) try {ps.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		return false;
	}
}
