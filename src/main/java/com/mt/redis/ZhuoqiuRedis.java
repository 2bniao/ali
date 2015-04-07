package com.mt.redis;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mt.vo.Dota;
import com.mt.vo.ZhuoqiuUserinfo;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
@Service
public class ZhuoqiuRedis {
	@Autowired
	private ShardedJedisPool shardedJedisPool;
	
	private final String ZHUOQIU_USER_INFO="zhuoqiu:user:info";
	private final String ALL_USER_INFO="zhuoqiu:all:user";

    public void saveUserInfo(ZhuoqiuUserinfo zhuoqiu){
    	ShardedJedis redis = null;
		try{
			redis = shardedJedisPool.getResource();
			redis.hset(ZHUOQIU_USER_INFO,zhuoqiu.getName(),new Gson().toJson(zhuoqiu));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(redis);
		}
	}
    
    public int existUserinfo(String name){
     	ShardedJedis redis = null;
    		try{
    			redis = shardedJedisPool.getResource();
    			if(redis.hexists(ZHUOQIU_USER_INFO, name)) return -1;
    			Set<String> sets = redis.smembers(ALL_USER_INFO);
    			if(!sets.contains(String.valueOf(name))) return -2;
     		}catch(Exception e){
    			e.printStackTrace();
    		}finally{
    			shardedJedisPool.returnResource(redis);
    		}
    		return 0;
    }
    
    public List<ZhuoqiuUserinfo> getAllUser(){
    	ShardedJedis redis = null;
    	List<ZhuoqiuUserinfo> list = new ArrayList<ZhuoqiuUserinfo>();
		try{
			redis = shardedJedisPool.getResource();
			Map map = redis.hgetAll(ZHUOQIU_USER_INFO);
			Set set = map.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				System.out.println("key====" + key);
				String value = (String) map.get(key);
				ZhuoqiuUserinfo zhuoqiu = new Gson().fromJson(value,ZhuoqiuUserinfo.class);
				list.add(zhuoqiu);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(redis);
		}
		return list;
    }
}
