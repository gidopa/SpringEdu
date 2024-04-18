package com.spring.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.spring.member.service.MemberService;
import com.spring.member.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberControllerImpl implements MemberController {
	
	private final MemberService memberService; 
	
	// /member/listMembers.do DB에 저장된 모든 회원 조회 요청 주소를 받았을때 호출 되는 메소드로
	@Override
	public ModelAndView listMembers(HttpServletRequest request, 
									HttpServletResponse response) 
											throws Exception {	
		
		//MemberServiceImpl객체의 listMembers()메소드를 호출하여
		//모든 회원 조회 요청을 명령함!
		//웹브라우저로 응답할 조회한 정보들이 담긴  List배열을 반환 받는다.
		List membersList = memberService.listMembers();
	
	//응답할 뷰 이름 얻기 	
		//요청 URL주소  /member/listMembers.do 에서  .do를 제외한 /listMembers뷰이름얻기
		String viewName = getViewName(request); 
		
	//응답할 값 과 응답할 뷰 이름을  ModelAndView객체 메모리에 바인딩(저장)
		ModelAndView mav = new ModelAndView();
					 //응답할 데이터 저장
					 mav.addObject("membersList", membersList);	
					//뷰 이름 저장 
					 mav.setViewName(viewName);
		
		return mav;//디스팩처 서블릿으로 ModelAndView객체 반환 
		
	}
	
	//회원가입 화면 요청 주소 /member/memberForm.do를 받았을때...
	@Override     
	public String memberForm(HttpServletRequest request, 
							HttpServletResponse response) 
									throws Exception {	
		
		String viewName = getViewName(request);
		
		return viewName;// "/memberForm";
	}
	
	
	// 회원가입 요청 주소 /member/addMember.do를 받았을때....
	@Override
	public String addMember(HttpServletRequest request, 
						  HttpServletResponse response) 
								  throws Exception {	
		
		request.setCharacterEncoding("UTF-8");
		/*
		//요청한 값 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		//VO에 저장
		MemberVO vo = new MemberVO();
				 vo.setId(id);
				 vo.setPwd(pwd);
				 vo.setName(name);
				 vo.setEmail(email);
		*/
		MemberVO membervo = new MemberVO();
		//회원가입 창에서 전송된 회원정보를 bind()메소드를 이용해 
		//request에서 꺼내서 MemberVO의 각변수에 자동저장
		bind(request, membervo);
		
		
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 INSERT명령!
		memberService.addMembers(membervo);		 
			
		//회원가입 후 모든회원을 조회 하는 재요청 주소 작성 
		return "redirect:/member/listMembers.do";

	}
	
	//회원삭제 기능 
	@Override
	public String memberDel(HttpServletRequest request, 
							HttpServletResponse response) 
							throws Exception {
	
		request.setCharacterEncoding("UTF-8");
		
		//요청한 값 얻기
		String id = request.getParameter("id");
		System.out.println("삭제할 회원 아이디 = " + id);
			 
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 DELETE명령!
		memberService.delMembers(id);		 
			
		//회원 삭제후 모든회원을 조회 하는 재요청 주소 작성 
		return "redirect:/member/listMembers.do";
	}
	
	//회원정보  수정을 위해 회원 한명의 정보 조회 기능
	// 수정링크를 누르면 요청 주소   ->  /member/memberDetail.do
	@Override
	public ModelAndView memberDetail(HttpServletRequest request, 
									 HttpServletResponse response) 
											 throws Exception {
		
		request.setCharacterEncoding("UTF-8");
			
		//요청한 값(수정할 회원을 조회 하기 위한 아이디값) 얻기
		String id = request.getParameter("id");
		
		//부장 MemberServiceImpl객체의 메소드 호출시 vo를 전달하여 SELECT명령!
		MemberVO vo = memberService.detailMembers(id);		 
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("membervo",vo);
		mav.setViewName( getViewName(request) ); // /memberDetail
	 
		return mav;
		
		
	}
	
	//수정 요청 /member/UpdateMember.do 주소를 받았을때
	@Override
	public String UpdateMember(HttpServletRequest request, 
							   HttpServletResponse response) throws Exception {

		
		request.setCharacterEncoding("UTF-8");
		/*
		//요청한 수정할 값 얻기
		//요청한 값 얻기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		System.out.println("수정할 회원 아이디 = " + id);
		
		//VO에 저장
		MemberVO vo = new MemberVO();
				 vo.setId(id);
				 vo.setPwd(pwd);
				 vo.setName(name);
				 vo.setEmail(email);
		*/
		MemberVO vo = new MemberVO();
		bind(request, vo);
		
		
		
		//부장 MemberServiceImpl객체의 메소드 호출시 수정할 id를 전달하여 UPDATE명령!
		memberService.UpdateMember(vo);		 
			
		//회원 수정후 모든회원을 조회 하는 재요청 주소 작성 
		return "redirect:/member/listMembers.do";
		
	
	}
	
	
	
	
	
	//request 객체에서 URL 요청명을 가져와 .do를 제외한 요청명을 구하는 메소드 
	private  String getViewName(HttpServletRequest request) throws Exception {
	      
		  String contextPath = request.getContextPath();
	      // /pro21
		  String uri = (String)request.getAttribute("javax.servlet.include.request_uri");
	      
		  if(uri == null || uri.trim().equals("")) {
	       //요청한 주소 전체 얻기 
	         uri = request.getRequestURI();
	       //http://localhost:8090/pro21/test/memberForm.do
	         
	         System.out.println(uri);
	      }
	      int begin = 0;
	      if(!((contextPath==null)||("".equals(contextPath)))){
	         begin = contextPath.length();
	      }
	      int end;
	      if(uri.indexOf(";")!=-1){
	         end=uri.indexOf(";");
	      }else if(uri.indexOf("?")!=-1){
	         end=uri.indexOf("?");
	      }else{
	         end=uri.length();
	      }
	      String fileName=uri.substring(begin,end);
	
	      if(fileName.indexOf(".")!=-1){
	         fileName=fileName.substring(0,fileName.lastIndexOf("."));
	      }
	      if(fileName.lastIndexOf("/")!=-1){
	         fileName=fileName.substring(fileName.lastIndexOf("/"),fileName.length());
	      }
	      return fileName; // .do를 제외한 요청주소를 리턴
	   }






	
}
