package comment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import comment.dto.CommentDTO;

public class CommentDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result = 0;
	
	public CommentDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.161:1521:xe", "testaccount", "testaccount");
		} catch (ClassNotFoundException e) {
			System.out.println("jar 파일을 찾지 못하거나 oracle 드라이버 확인이 필요합니다.");
			e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			System.out.println("데이터베이스와 연결에 실패했습니다.");
			e.printStackTrace();
			System.exit(0);	// 강제 종료
		}
	}

	public void createComment(CommentDTO commentDTO) throws SQLException{
		
		try {
			
			String sql = "insert into b_comment (cid, bid, ccontent, cauthor, cdate) values(commentid_seq.nextval, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, commentDTO.getBid());		
			pstmt.setString(2, commentDTO.getCcontent());		
			pstmt.setString(3, commentDTO.getCauthor());		
			
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("등록 완료!");
				conn.commit();
			}
			else {
				System.out.println("등록 실패!");
				conn.rollback();
			}
		} catch (SQLException e) {
			System.out.println("오류 발생 : createPost() 메서드 확인");
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}

	public ArrayList<CommentDTO> getCommentsByBid(int bid) throws SQLException {
		
		ArrayList<CommentDTO> commentList = new ArrayList<>();
		
		try {
			
			String sql = "select * from b_comment where bid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				CommentDTO commentDTO = new CommentDTO();
				commentDTO.setCid(rs.getInt("cid"));
				commentDTO.setBid(rs.getInt("bid"));
				commentDTO.setCcontent(rs.getString("ccontent"));
				commentDTO.setCauthor(rs.getString("cauthor"));
				commentDTO.setCdate(rs.getDate("cdate"));
				
				commentList.add(commentDTO);
				
				if (commentList.isEmpty())	return null;
			}
			
		} catch (SQLException e) {
			System.out.println("오류 발생 : getAllPost() 메서드 확인");
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
		}
		
		return commentList;
		
	}
	

	
} 
