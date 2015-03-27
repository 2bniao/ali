package pachong.job;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;

import pachong.redis.PachongRedis;
import pachong.vo.Dota;

public class ReadJob {

	@Autowired
	private PachongRedis pachongRedis;
	public void doJob() throws IOException {
		System.out.println("job====");
		List<String> userList = new ArrayList<String>();
		userList.add("136384759");  //lijian
		userList.add("136496469");  //niao
		userList.add("220314893");
		userList.add("127194220");
		for (String userid : userList) {
			File file = new File("/usr/local/ali/"+"dotamax.com_player_detail_" + userid+ ".html");
			Document doc = Jsoup.parse(file, "utf-8", "http://dotamax.com");
			Element el11 = doc.select("div.flat-grey-box").get(1);
			Element el2 = el11.select("tbody.table-player-detail").first();
			Map<String, List<Dota>> map = new HashMap<String, List<Dota>>();
			List<Dota> list = new ArrayList<Dota>();
			Element user =doc.select("div.new-box").first();
			String ee =user.select("span[style]").get(0).text();
			String accountName = ee.substring(0, ee.indexOf(" "));
			for (int i = 0; i < 5; i++) {
				Dota dota = new Dota();
				Element el1 = el2.select("tr").get(i);
				Element elLev = el1.select("td").get(5);
				Element elScore = el1.select("td").get(4);
				Element elName = el1.select("td").first();
				Element elNumber = el1.select("td").get(1);
				Element elDetail = elName.select("a[href]").first();
				Element elPic = elDetail.select("img").first();
				
				dota.setResult(el1.select("td").get(3).text());
				dota.setPlayDate(el1.select("td").get(2).text());
				dota.setUserid(userid);
				dota.setPic(elPic.attr("src"));
				dota.setHero(elName.text());
				dota.setNumber(elNumber.text());
				dota.setLevel(elLev.text());
				dota.setScore(elScore.text());
				dota.setAccountName(accountName);
				dota.setDetail("http://www.dota2.com.cn" + elDetail.attr("href"));
				list.add(dota);
			}
			map.put(userid, list);
			pachongRedis.saveDota(map);
		}
	}
	
	
	
}
