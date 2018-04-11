package spider.web.crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleSpiderLeg extends SpiderLeg {

	private static final String PART_NEXT_PAGE_LINK = "https://www.google.ro/search?q=";
	
	private List<String> googlePagesLinks;
	private List<String> googleOptions;
	
	{
		googlePagesLinks = new LinkedList<String>();
		googleOptions = new LinkedList<String>();
	}
	
	@Override
	public boolean crawl(String url) {
		try {
			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
			htmlDocument = connection.get();
			statusCode = connection.response().statusCode();
			
			if(statusCode == 200) {
				System.out.println("\n**Visiting** Received web page at " + url);
			} 
			
			if(!connection.response().contentType().contains("text/html")) {
				System.out.println("\n**Failure** Retreived something else than html");
				return false;
			}
			
			distributeLinks();
			return true;
			
		} catch (IOException e) {
			System.out.println("\n Error: " + e.getMessage());
			return false;
		}
	}
	
	private void distributeLinks() {
		Elements elementLinks = htmlDocument.select("a[href]");
		String stringLink;
		
		for(Element link : elementLinks) {
			stringLink = link.absUrl("href");
			
			if(stringLink.contains(PART_NEXT_PAGE_LINK) && stringLink.contains("&start=")) {
				googlePagesLinks.add(stringLink);
			
			} else if(stringLink.contains("google")) {
				googleOptions.add(stringLink);
				
			} else {
				getLinks().add(stringLink);
			}
		}
	}
	
	public List<String> getGooglePagesLinks() {
		return googlePagesLinks;
	}
	
	public List<String> getGoogleOptions() {
		return googleOptions;
	}
	
	public void resetLinkLists() {
		links = new LinkedList<String>();
		googlePagesLinks = new LinkedList<String>();
		googleOptions = new LinkedList<String>();
	}
}
