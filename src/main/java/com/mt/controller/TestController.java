package com.mt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mt.vo.Result;
import com.mt.vo.StudentVO;

@Controller
@RequestMapping("/Test")
public class TestController  extends BaseController {
	@RequestMapping(value = "/test")
	@ResponseBody
	public Result test(ModelMap model, @RequestBody StudentVO studentVO) {
		
		return this.result(0, "", ""); 
	}
}
