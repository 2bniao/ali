package com.mt.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mt.redis.PachongRedis;
import com.mt.redis.ZhuoqiuRedis;
import com.mt.tools.DateFormatUtil;
import com.mt.vo.Result;
import com.mt.vo.ZhuoqiuUserinfo;


@Controller
@RequestMapping("/zhuoqiu")
public class ZhuoqiuController  extends BaseController{
	
	@Autowired
	private ZhuoqiuRedis zhuoqiuRedis;
	
	@RequestMapping(value = "/baoming")
	@ResponseBody
	public Result baoming(HttpServletRequest request) {
		String name= request.getParameter("name").trim();
		String bb= request.getParameter("bb");
		int flag = zhuoqiuRedis.existUserinfo(name);
		if(flag==-1) {
			return this.result(-1, "", "请勿重复报名"); 
		}
/*		if(flag==-2) {
			return this.result(-2, "", "请输入正确的中文名"); 
		}*/
		ZhuoqiuUserinfo zhuoqiu = new ZhuoqiuUserinfo();
		zhuoqiu.setName(name);
		zhuoqiu.setBaomingDate(DateFormatUtil.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
		zhuoqiuRedis.saveUserInfo(zhuoqiu);
		System.out.println("====baoming===");
		return this.result(0, "", "");
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public JSONArray list(HttpServletRequest request) {
		JSONArray array = new JSONArray();
		List<ZhuoqiuUserinfo> list = zhuoqiuRedis.getAllUser();
		
    	Collections.sort(list, new Comparator<ZhuoqiuUserinfo>(){
			@Override
			public int compare(ZhuoqiuUserinfo o1, ZhuoqiuUserinfo o2) {
				 return String.valueOf(o2.getBaomingDate()).compareTo(String.valueOf(o1.getBaomingDate()));  
			}
    	});
		
	    for(ZhuoqiuUserinfo zhuoqiu:list){
	    	array.add(zhuoqiu);
	    }
		return  array;
	}
}
