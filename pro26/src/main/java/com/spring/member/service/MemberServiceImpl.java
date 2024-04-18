package com.spring.member.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.member.dao.MemberDAO;
import com.spring.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	//사원 MemberDAOImpl객체를 주입받아 저장할 참조변수 선언
	private final MemberDAO memberDAO;// = new MemberDAOImpl();
	
	
	//모든 회원 정보 조회 기능 
	@Override
	public List listMembers() throws DataAccessException {
		
		//사원 MemberDAOImpl객체의 selectAllMembers()메소드를 호출해 
		//모든 회원 정보 조회 요청을 명령함!
		//조회된 회원정보들(MemberVO객체들)이 저장된  List배열을 반환 받음 
		List membersList = memberDAO.selectAllMembers();
	
		return membersList;//사장님 MemberControllerImpl객체에 반환 
	}

	//회원 가입 기능
	@Override
	public void addMembers(MemberVO vo) throws DataAccessException{
		
		memberDAO.InsertMember(vo);
		
	}

	//회원 삭제 기능 
	@Override
	public void delMembers(String id) throws DataAccessException {
		
		memberDAO.DeleteMember(id);
	}

	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	@Override
	public MemberVO detailMembers(String id) throws DataAccessException {
		
		return memberDAO.oneMember(id);
	}

	//회원정보 수정 기능 
	@Override
	public void UpdateMember(MemberVO vo) throws DataAccessException {
		
		
		memberDAO.UpdateMember(vo);
	}
	
	
	
}












