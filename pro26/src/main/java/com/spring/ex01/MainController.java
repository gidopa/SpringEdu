package com.spring.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class MainController {
	
	@RequestMapping(value="/main1.do", method = RequestMethod.GET)
	public ModelAndView main1(HttpServletRequest reqeust, HttpServletResponse response) throws Exception{
		System.out.println("main1 호출");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("msg","main1");
		return mav; 
	}
	
	@RequestMapping("/main2.do")
	public ModelAndView main2(HttpServletRequest reqeust, HttpServletResponse response) throws Exception{
		System.out.println("main2 호출");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("main");
		mav.addObject("msg","main2");
		return mav;
	}
}
