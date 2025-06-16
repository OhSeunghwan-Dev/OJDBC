package like.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import comment.dto.CommentDTO;

public class LikeDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result = 0;
	
	public LikeDAO() {
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

	public boolean createLike(String id, int bid) throws SQLException {
		
		try {
			
			String sql = "insert into b_like (bid, likeuser) values(?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bid);		
			pstmt.setString(2, id);				
			
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("좋아요 완료!");
				conn.commit();
			}
			else {
				System.out.println("좋아요 실패!");
				conn.rollback();
			}
		} catch (SQLException e) {
			System.out.println("오류 발생 : createPost() 메서드 확인");
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
		return true;
	}

	public boolean findLike(String id, int bid) throws SQLException{
		
		try {
			
			String sql = "select * from b_like where bid = ? and likeuser = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println("오류 발생 : getAllPost() 메서드 확인");
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
		}
		
		return false;
	}

	public int countLike(int bid) throws SQLException {
		
		int count = 0;
		
		try {
			
			String sql = "select * from b_like where bid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				count++;
			}
			
		} catch (SQLException e) {
			System.out.println("오류 발생 : getAllPost() 메서드 확인");
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
		}
		
		
		return count;
	}

}
