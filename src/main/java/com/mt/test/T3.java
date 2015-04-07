package com.mt.test;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class T3 {
	public static void main(String[] args) {
		try {
			// Document doc =
			// Jsoup.connect("http://dotamax.com/player/detail/136384759/").get();
			File file = new File("D:\\dotamax.com_player_detail_136496469.html");
			Document doc = Jsoup.parse(file, "utf-8", "http://dotamax.com");
			Element user =doc.select("div.new-box").first();
			String ee =user.select("span[style]").get(0).text();
			System.out.println(ee.substring(0, ee.indexOf(" ")));
		
			Element el11 = doc.select("div.flat-grey-box").get(1);
			Element el2 = el11.select("tbody.table-player-detail").first();
			for(int i=0;i<5;i++){
				Element el1 =el2.select("tr").get(i);
				Element elLev = el1.select("td").get(5);
				Element elScore = el1.select("td").get(4);
				Element elName = el1.select("td").first();
				Element elNumber = el1.select("td").get(1);
				Element elDetail = elName.select("a[href]").first();
				Element elPic = elDetail.select("img").first();
				System.out.println(elPic.attr("src"));
				System.out.println(elDetail.attr("href"));
				System.out.println(elName.text());
				System.out.println(elNumber.text());
				System.out.println(elLev.text());
				System.out.println(elScore.text());
				System.out.println(el1.select("td").get(3).text());
				System.out.println(el1.select("td").get(2).text());
			}
			
		
			
	
		/*		Element elLev = el1.select("td").get(5);
			Element elScore = el1.select("td").get(4);
			Element el2 = el1.select("tbody.table-player-detail").first();
			Element elName = el2.select("td").first();
			Element elNumber = el2.select("td").get(1);
			Element elDetail = elName.select("a[href]").first();
			Element elPic = elDetail.select("img").first();
			System.out.println(elPic.attr("src"));
			System.out.println(elDetail.attr("href"));
			System.out.println(elName.text());
			System.out.println(elNumber.text());
			System.out.println(elLev.text());
			System.out.println(elScore.text());
			System.out.println("===");*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
