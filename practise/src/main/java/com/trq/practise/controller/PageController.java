package com.trq.practise.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class PageController {
	
	//测试用例
	@RequestMapping("/index")
	@ResponseBody
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("hello", "HelloWorld");
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("firstPage")
	public String firstPage(){
		return "firstPage";
	}
	//意义同上
	/*@RequestMapping("firstPage")
	public String firstPage(HttpServletRequest request){
		return "firstPage";
	}*/
	
	@RequestMapping("FileUpLoad")
	public String FileUpLoad(){
		return "FileUpLoad";
	}
	
}
