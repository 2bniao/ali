package com.mt;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



import org.apache.poi.hssf.record.formula.functions.Row;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.google.gson.Gson;
import com.mt.vo.Dota;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;


public class Test {
	public static void main(String[] args) throws IOException {
	
	 JedisPoolConfig config = new JedisPoolConfig(); 
         config.setMaxActive(20); 
         config.setMaxIdle(5); 
         config.setMaxWait(1000l); 
         config.setTestOnBorrow(false); 
         
	       JedisPool  jedisPool =   new JedisPool(config, "120.25.204.152", 22122, 10000);
	       Jedis jedis =jedisPool.getResource();
	       //jedis.set("gg12", "g1");
	      for(int i=0;i<1000;i++){
	    	  //  System.out.println(jedis.set("vv"+i, i+""));
	    		jedis.del("vv"+i);
	    	  
	    		 //   System.out.println(jedis.get("vv2"));
	       }
	  
	  //  System.out.println("----"+jedis.get("a3"));
	}
}
