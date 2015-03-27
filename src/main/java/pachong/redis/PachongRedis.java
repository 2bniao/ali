package pachong.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import pachong.vo.Dota;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class PachongRedis {
	@Autowired
	private ShardedJedisPool shardedJedisPool;
	
	private final String DOTA_USER_INFO="dota:user:info";
	
	
	public void saveDota(Map<String,List<Dota>> map){
		ShardedJedis redis = null;
		try{
			redis = shardedJedisPool.getResource();
			Set set = map.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				System.out.println("key===="+key);
				List<Dota> list = map.get(key);
				redis.hset(DOTA_USER_INFO, key, new Gson().toJson(list));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			shardedJedisPool.returnResource(redis);
		}
	}
	
	public Map<String,List<Dota>> getDotaInfo(List<String> list ){
		ShardedJedis redis = null;
		Map<String, List<Dota>> map = new HashMap<String, List<Dota>>();
		try {
			redis = shardedJedisPool.getResource();
			for (String userid : list) {
				String value = redis.hget(DOTA_USER_INFO, userid);
				JSONArray array1 = JSONArray.fromObject(value);
				List<Dota> dotaList = (List<Dota>) JSONArray.toCollection(array1, Dota.class);
				map.put(userid, dotaList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			shardedJedisPool.returnResource(redis);
		}
		return map;
	}
	
}
