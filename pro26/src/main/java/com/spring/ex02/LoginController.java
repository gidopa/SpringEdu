package com.spring.ex02;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LoginController {
	
//	@RequestMapping(value = "/test/loginForm.do", method = RequestMethod.GET)
//	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		ModelAndView mav = new ModelAndView("loginForm");
//		mav.setViewName("loginForm");
//		return mav;
//	}
	
	@RequestMapping(value = {"/test/loginForm.do", "/test/loginForm2.do"}, method = RequestMethod.GET)
	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return "loginForm";
	}
	
	@RequestMapping(value="/test/login.do", method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam String userId, @RequestParam String userName)
	throws Exception{
		request.setCharacterEncoding("UTF-8");
		log.info("userId : {}",userId);
		log.info("userName : {}",userName);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userName", userName);
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(map);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping(value="/test/login2.do", method=RequestMethod.POST)
	public ModelAndView login2(HttpServletRequest request, HttpServletResponse response, 
							@RequestParam(required = true) String userId,
							@RequestParam(required = true) String userName,
							@RequestParam(required = true) String email) throws Exception{
		request.setCharacterEncoding("UTF-8");
		log.info("userId : {}",userId);
		log.info("userName : {}",userName);
		log.info("email : {}", email);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("userName", userName);
		map.put("email", email);
		ModelAndView mav = new ModelAndView();
		mav.addAllObjects(map);
		mav.setViewName("result");
		return mav;
	}
	

	@RequestMapping(value="/test/login3.do", method=RequestMethod.POST)
	public ModelAndView login3(HttpServletRequest request, HttpServletResponse response, 
							   @RequestParam Map<String, String> map) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String userId = map.get("userId");
		String userName = map.get("userName");
		String email = map.get("email");
		ModelAndView mav = new ModelAndView();
		log.info("userId : {}", userId);
		log.info("userName : {}", userName);
		log.info("email : {}", email);
		mav.addAllObjects(map);
		mav.setViewName("result");
		return mav;
	}
	
	//@ModelAttribute는 객체에 필드에 맞춰 parameter 등을 바로 바인딩해주고 ModelAndView에 넣어준다
	@RequestMapping(value="/test/login4.do", method=RequestMethod.POST)
	public ModelAndView login4(HttpServletRequest request, HttpServletResponse response, 
							   @ModelAttribute LoginVO loginVO, @RequestParam String email) throws Exception{
		request.setCharacterEncoding("UTF-8");
		String userId = loginVO.getUserId();
		String userName = loginVO.getUserName();
		ModelAndView mav = new ModelAndView();
		log.info("userId : {}", userId);
		log.info("userName : {}", userName);
		log.info("email : {}", email);
		mav.addObject("email", email);
		mav.setViewName("result");
		return mav;
	}
	
	@RequestMapping(value="/test/login5.do", method=RequestMethod.POST)
	public String login5( Model model, @RequestParam String email) throws Exception{
		model.addAttribute("userId","hong");
		model.addAttribute("userName","홍길동");
		model.addAttribute("email", email);
		return "result";
	}
}
