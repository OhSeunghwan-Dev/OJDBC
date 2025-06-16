package board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.dto.PostDTO;

public class BoardDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	int result = 0;
	
	public BoardDAO() {
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

	public ArrayList<PostDTO> getAllPost() throws SQLException {
		
		ArrayList<PostDTO> postList = new ArrayList<>();
		
		try {
			
			String sql = "select * from board order by bdate desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PostDTO postDTO = new PostDTO();
				postDTO.setBid(rs.getInt("bid"));
				postDTO.setBtitle(rs.getString("btitle"));
				postDTO.setBcontent(rs.getString("bcontent"));
				postDTO.setBauthor(rs.getString("bauthor"));
				postDTO.setBdate(rs.getDate("bdate"));
				
				postList.add(postDTO);
			}
			
		} catch (SQLException e) {
			System.out.println("오류 발생 : getAllPost() 메서드 확인");
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
		}
		
		return postList;
	}

	public void createPost(PostDTO postDTO) throws SQLException {
		
		try {
			
			String sql = "insert into board(bid, btitle, bcontent, bauthor, bdate) values(boardid_seq.nextval, ?, ?, ?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, postDTO.getBtitle());		
			pstmt.setString(2, postDTO.getBcontent());		
			pstmt.setString(3, postDTO.getBauthor());		
			
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

	public ArrayList<PostDTO> getPostsById(String id) throws SQLException {
		
		ArrayList<PostDTO> postList = new ArrayList<>();
		
		try {
			
			String sql = "select * from board where bauthor = ? order by bdate desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);		
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PostDTO postDTO = new PostDTO();
				postDTO.setBid(rs.getInt("bid"));
				postDTO.setBtitle(rs.getString("btitle"));
				postDTO.setBcontent(rs.getString("bcontent"));
				postDTO.setBauthor(rs.getString("bauthor"));
				postDTO.setBdate(rs.getDate("bdate"));
				
				postList.add(postDTO);
			}
			
		} catch (SQLException e) {
			System.out.println("오류 발생 : getPostsById() 메서드 확인");
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
		}
		
		return postList;
	}

	public void deletePost(PostDTO postDTO) throws SQLException {
		
		try {
			
			String sql = "delete from board where bid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postDTO.getBid());		
			
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("삭제 완료!");
				conn.commit();
			}
			else {
				System.out.println("삭제 실패");
				conn.rollback();
			}
		} catch (SQLException e) {
			System.out.println("오류 발생 : deletePost() 메서드 확인");
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}

	public void updatePost(PostDTO postDTO) throws SQLException {
	
		try {
			
			String sql = "update board set btitle = ?, bcontent = ? where bid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, postDTO.getBtitle());
			pstmt.setString(2, postDTO.getBcontent());
			pstmt.setInt(3, postDTO.getBid());
			
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println("수정 완료!");
				conn.commit();
			}
			else {
				System.out.println("수정 실패");
				conn.rollback();
			}
		} catch (SQLException e) {
			System.out.println("오류 발생 : updatePost() 메서드 확인");
			e.printStackTrace();
		} finally {
			pstmt.close();
		}
	}
	
}
