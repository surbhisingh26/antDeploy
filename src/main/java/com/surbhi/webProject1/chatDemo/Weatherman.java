package com.surbhi.webProject1.chatDemo;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.util.URL;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;

public class Weatherman implements Runnable{
	 
    private final List<URL> zipCodes;
    private final String YAHOO_WEATHER = "http://weather.yahooapis.com/forecastrss?p=";
 
    public Weatherman(Integer... zips) {
    	System.out.println("Weatherman");
        zipCodes = new ArrayList<URL>(zips.length);
        for (Integer zip : zips) {
            try {
                zipCodes.add(new URL(YAHOO_WEATHER + zip));
            } catch (Exception e) {
                // dont add it if it sucks
            }
        }
    }
 
   public void run() {
	   System.out.println("Weatherman RUN");
       int i = 0;
       while (i >= 0) {
           int j = i % zipCodes.size();
           SyndFeedInput input = new SyndFeedInput();
           try {
        	   MessageSender messageSender = new MessageSender();
               SyndFeed feed = input.build(new InputStreamReader(null,zipCodes.get(j).getFile()));
               SyndEntry entry = (SyndEntry) feed.getEntries().get(0);
               messageSender.send(entryToHtml(entry));
               Thread.sleep(30000L);
           } catch (Exception e) {
               // just eat it, eat it
           }
           i++;
       }
   }
 
    private String entryToHtml(SyndEntry entry){
    	System.out.println("Weatherman entryToHtml");
        StringBuilder html = new StringBuilder("<h2>");
        html.append(entry.getTitle());
        html.append("</h2>");
        html.append(entry.getDescription().getValue());
        return html.toString();
    }
}
