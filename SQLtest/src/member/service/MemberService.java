package member.service;

import java.sql.SQLException;
import java.util.ArrayList;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberService {

	MemberDAO memberDAO = new MemberDAO();
	
	public void signin(MemberDTO memberDTO) throws SQLException {
		memberDAO.insertMember(memberDTO);
	}

	public boolean isDuplicatedId(String id) throws SQLException {
		return memberDAO.isDuplicatedId(id);
	}

	public MemberDTO getMember(MemberDTO memberDTO) throws SQLException {
		
		if (memberDAO.getMember(memberDTO) != null) {
			return memberDAO.getMember(memberDTO);
		}
		
		return null;
	}

	public void signout(MemberDTO memberDTO) throws SQLException {
		memberDAO.deleteMember(memberDTO);
	}

	public ArrayList<MemberDTO> getAllMember() throws SQLException {
		return memberDAO.getAllMember();
	}

	public MemberDTO updateMember(MemberDTO session) throws SQLException {
		return memberDAO.updateMember(session);
	}

	public ArrayList<MemberDTO> getLikeMemberList(int bid) throws SQLException {
		return memberDAO.getLikeMembers(bid);
	}

}
