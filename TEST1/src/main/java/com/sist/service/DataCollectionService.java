package com.sist.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.lang.invoke.MethodHandles.Lookup.ClassOption;
import java.util.StringTokenizer;

/*
<div id="discoverList">
                
                    <div class="discoverCard" data-discover-index="0">
                        <a class="discoverCard-a" href="/artwork/A0172-0114/" data-code="A0172-0114">
                            <div class="discoverCard-imageWrap"
                                 style="padding-bottom: 128.0%; background-color: #98888a;"
                            >
                                <img class="discoverCard-image" src="https://og-data.s3.amazonaws.com/media/artworks/w_fixed/A0172/A0172-0114.jpg"/>
                                <div class="card-imageMask"></div>
                            </div>
                            <div class="discoverCard-textBox">
                                <h3 class="discoverCard-title">공간질료(matter of space) no.101</h3>
                                <div class="discoverCard-artist">단이상</div>
                                <div class="discoverCard-info">
                                    120x94cm
                                    (50호)
                                    
                                </div>
                                
                                    <div class="discoverCard-tagBox blue">
                                        <div class="discoverCard-tag"></div>
                                        <span>렌탈/전시중</span>
                                    </div>
                                
                            </div>
                        </a>
                        <div class="card-collectionBox " data-code="A0172-0114"></div>
                    </div>
 */

public class DataCollectionService {

	public static void main(String[] args) {
		DataCollectionService ds = new DataCollectionService();
		ds.pictureGetData();
	}
	
	public void pictureGetData() {
		
		try {
			for(int i = 0; i < 50; i++) {
				Document doc = Jsoup.connect("https://www.opengallery.co.kr/discover/?p= " + i+ " &f_ts=&f_ss=&f_os=&f_ps=&f_ra=false&f_pa=false&r_ex=0&").get();
				Elements link = doc.select("div#discoverList a.discoverCard-a");
				
				for(int j = 0; j < link.size(); j++) {
					
					System.out.println(j);
					String s = "https://www.opengallery.co.kr" + link.get(j).attr("href");
					
					Document doc2 = Jsoup.connect(s).get();
					
					Element pic = doc2.selectFirst("div.artwork-detail-viewinroom-view img.artwork-detail-viewinroom-view-img");
					String poster = "";
					System.out.println(pic.attr("src"));
					
					Element title = doc2.selectFirst("h2.artwork-detail-info-title");
					System.out.println(title.text());
					Element name = doc2.selectFirst("a.artwork-detail-info-artist-link");
					System.out.println(name.text());
					
					
					
				}
			}
			
		} catch (Exception e) {}
		
	}
	
	
} 


/*
import com.sist.dao.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataCollectionService {
   public void picData() {
      try {
         for(int i=0;i<=769;i++) {
            Document doc=Jsoup.connect("https://www.opengallery.co.kr/discover/?p="+i+"&f_ts=&f_ss=&f_os=&f_ps=&f_ra=false&f_pa=false&r_ex=0&").get();
            Elements link=doc.select("div.discoverCard a.discoverCard-a");
           
            for(int j=0;j<link.size();j++) {
               String s="https://www.opengallery.co.kr"+link.get(j).attr("href");
//               System.out.println(s);
               Document doc2=Jsoup.connect(s).get();
               Element pic=doc2.selectFirst("div.og-container div.artwork-detail-viewinroom-view img.artwork-detail-viewinroom-view-img");
               System.out.println(pic.attr("src"));
            }
            
         }
      } catch(Exception ex) {}
   }
   public static void main(String[] args) {
	   DataCollectionService pc=new DataCollectionService();
      pc.picData();
   }

}
*/
