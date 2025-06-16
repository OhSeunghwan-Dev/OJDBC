package member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import member.dto.MemberDTO;

public class MemberDAO {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		public MemberDAO() {
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

		public void insertMember(MemberDTO memberDTO) throws SQLException {
			
			try {
				
				String sql = "insert into member(id, pw, gender, address, phoneNumber, email, regdate) values(?, ?, ?, ?, ?, ?, sysdate)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, memberDTO.getId());		
				pstmt.setString(2, memberDTO.getPw());		
				pstmt.setString(3, memberDTO.getGender());		
				pstmt.setString(4, memberDTO.getAddress());		
				pstmt.setString(5, memberDTO.getPhoneNumber());		
				pstmt.setString(6, memberDTO.getAddress());
				result = pstmt.executeUpdate();
				
				if (result > 0) {
					System.out.println("회원등록 완료!");
					conn.commit();
				}
				else {
					System.out.println("회원등록 실패!");
					conn.rollback();
				}
				
			} catch (SQLException e) {
				System.out.println("오류발생 : insertMember() 메서드의 쿼리문을 확인하세요.");
				e.printStackTrace();
			} finally {
				pstmt.close();
			}
		}

		public ArrayList<MemberDTO> getAllMember() throws SQLException {
			
			ArrayList<MemberDTO> memberList = new ArrayList<>();
			
			try {
				
				String sql = "select * from member";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setId(rs.getString("id"));
					memberDTO.setPw(rs.getString("pw"));
					memberDTO.setGender(rs.getString("gender"));
					memberDTO.setAddress(rs.getString("address"));
					memberDTO.setPhoneNumber(rs.getString("phoneNumber"));
					memberDTO.setEmail(rs.getString("email"));
					memberDTO.setRegDate(rs.getDate("regdate"));
					
					memberList.add(memberDTO);
				}
			
			} catch (SQLException e) {
				System.out.println("오류 발생 : getAllMember() 메서드 확인");
				e.printStackTrace();
			} finally {
				rs.close();
				pstmt.close();
			}
			
			return memberList;
		}

		public MemberDTO getMember(MemberDTO memberDTO) throws SQLException {
			
			MemberDTO member = new MemberDTO();
			
			try {
				
				String sql = "select * from member where id = ? and pw = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberDTO.getId());
				pstmt.setString(2, memberDTO.getPw());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
					member.setId(rs.getString("id"));
					member.setPw(rs.getString("pw"));
					member.setGender(rs.getString("gender"));
					member.setAddress(rs.getString("address"));
					member.setPhoneNumber(rs.getString("phoneNumber"));
					member.setEmail(rs.getString("email"));
					member.setRegDate(rs.getDate("regdate"));
					return member;
					
				}
				
			} catch (SQLException e) {
				System.out.println("오류발생 : getMember()메서드 확인하기");
				e.printStackTrace();
			} finally {
				rs.close();
				pstmt.close();
			}
			
			return null;
		}

		public void deleteMember(MemberDTO memberDTO) throws SQLException {
			
			try {
				
				String sql = "delete from member where id = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, memberDTO.getId());
				result = pstmt.executeUpdate();
				
				if (result > 0) {
					System.out.println("회원 탈퇴 완료!");
					conn.commit();
				} else {
					System.out.println("회원 탈퇴 실패");
					conn.rollback();
				}
				
			} catch (SQLException e) {
				System.out.println("오류 발생 : deleteMember() 메서드 확인하기");
				e.printStackTrace();
			} finally {
				pstmt.close();
			}
			
		}

		public boolean isDuplicatedId(String id) throws SQLException {
			
			try {
				
				String sql = "select * from member where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if (rs.next()) return true;
				
			} catch (SQLException e) {
				System.out.println("오류 발생 : isDuplicatedID() 메서드 확인하기");
				e.printStackTrace();
			} finally {
				rs.close();
				pstmt.close();
			}
			
			return false;
		}

		public MemberDTO updateMember(MemberDTO session) throws SQLException {

			try {
				
				String sql = "update member set pw = ?, address = ?, phoneNumber = ?, email = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, session.getPw());
				pstmt.setString(2, session.getAddress());
				pstmt.setString(3, session.getPhoneNumber());
				pstmt.setString(4, session.getEmail());
				pstmt.setString(5, session.getId());
				
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
			return session;
		}

		public ArrayList<MemberDTO> getLikeMembers(int bid) throws SQLException {
			
			ArrayList<MemberDTO> memberList = new ArrayList<>();
			
			try {
				
				String sql = "select m.* from member m join b_like b on m.id = b.likeuser where b.bid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bid);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					MemberDTO memberDTO = new MemberDTO();
					memberDTO.setId(rs.getString("id"));
					memberDTO.setPw(rs.getString("pw"));
					memberDTO.setGender(rs.getString("gender"));
					memberDTO.setAddress(rs.getString("address"));
					memberDTO.setPhoneNumber(rs.getString("phoneNumber"));
					memberDTO.setEmail(rs.getString("email"));
					memberDTO.setRegDate(rs.getDate("regdate"));
					
					memberList.add(memberDTO);
				}
			
			} catch (SQLException e) {
				System.out.println("오류 발생 : getAllMember() 메서드 확인");
				e.printStackTrace();
			} finally {
				rs.close();
				pstmt.close();
			}
			
			return memberList;
		}

}
