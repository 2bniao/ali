package com.mt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mt.redis.PachongRedis;
import com.mt.vo.Dota;


@Controller
@RequestMapping("/pachong")
public class PachongController extends BaseController {
	@Autowired
	private PachongRedis pachongRedis;

	@RequestMapping(value = "/list")
	@ResponseBody
	public JSONArray list() {
		System.out.println("list---controller-");
		List<String> userList = new ArrayList<String>();
		JSONArray array = new JSONArray();
	
		userList.add("136384759"); // lijian
		userList.add("136496469"); // niao
		userList.add("220314893");
		userList.add("127194220");
		Map<String,List<Dota>> map = pachongRedis.getDotaInfo(userList);
		List<Dota> list = map.get("136384759");
		list.addAll(map.get("136496469"));
		list.addAll(map.get("220314893"));	list.addAll(map.get("127194220"));
		for(Dota dota:list){
			JSONObject member = new JSONObject();
			member.accumulate("accountName", dota.getAccountName());
			member.accumulate("hero", dota.getHero());
			member.accumulate("number",dota.getNumber() );
			member.accumulate("level",dota.getLevel() );
			member.accumulate("score", dota.getScore());
			member.accumulate("playDate", dota.getPlayDate());
			member.accumulate("result", dota.getResult());
			array.add(member);
		}
		//
		//
		//Map<String,List<Dota>> map = pachongRedis.getDotaInfo(userList);
		//return this.result(0, map, "dota info");
        return array;
	}

}
