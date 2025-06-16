package member.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import board.dto.PostDTO;
import member.dto.MemberDTO;
import member.service.MemberService;

public class MemberController {
	
	MemberService memberService = new MemberService();
	
	public void signin(MemberDTO memberDTO) throws SQLException {
		memberService.signin(memberDTO);
	}

	public MemberDTO login(MemberDTO memberDTO) throws SQLException {
		return memberService.getMember(memberDTO);
	}

	public MemberDTO signout(MemberDTO session) throws SQLException {
		memberService.signout(session);
		return null;
	}

	public ArrayList<MemberDTO> getAllMember() throws SQLException {
		return memberService.getAllMember();
	}

	public MemberDTO updateInfo(MemberDTO session) throws SQLException {
		return memberService.updateMember(session);
	}

	public ArrayList<MemberDTO> getLikeMemberList(PostDTO postDTO) throws SQLException{
		return memberService.getLikeMemberList(postDTO.getBid());
	}
	
	

}
