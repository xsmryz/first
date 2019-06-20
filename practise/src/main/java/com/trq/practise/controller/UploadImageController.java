package com.trq.practise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/v1/app")
public class UploadImageController {

	@RequestMapping(value="/uploadImage",method=RequestMethod.POST)
	@ResponseBody
	public String uploadImage(String mobile,MultipartFile image_ref1){
		System.out.println(mobile);
		System.out.println(image_ref1.getName());
		return "xiaopige";
	}
}
