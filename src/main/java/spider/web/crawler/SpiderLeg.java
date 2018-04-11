package spider.web.crawler;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;

public abstract class SpiderLeg {

	protected static final String USER_AGENT = 
			"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
	protected List<String> links;

	protected Document htmlDocument;
	protected int statusCode;
	
	{
		links = new LinkedList<String>();
	}
	
//	public boolean crawl(String url) {
//		try {
//			Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
//			htmlDocument = connection.get();
//			
//			if(connection.response().statusCode() == 200) {
//				System.out.println("\n**Visiting** Received web page at " + url);
//			} 
//			
//			if(!connection.response().contentType().contains("text/html")) {
//				System.out.println("\n**Failure** Retreived something else than html");
//				return false;
//			}
//			
//			Elements linksOnPage = htmlDocument.select("a[href]");
//			System.out.println("\nFound (" + linksOnPage.size() +") links.");
//			for(Element link : linksOnPage) {
//				links.add(link.absUrl("href"));
//			}
//			return true;
//			
//		} catch (IOException e) {
//			System.out.println("\n Error: " + e.getMessage());
//			return false;
//		}
//	}
	
	public boolean searchForWord(String word) {
		
		if(htmlDocument == null) {
			System.out.println("ERROR! Call crawl() before performing analysis on the document");
			return false;
		}
		System.out.println("Searching for the word " + word + "...");
		String bodyText = htmlDocument.body().text();
		return bodyText.contains(word);
	}
	
	abstract boolean crawl(String url);
		
	public List<String> getLinks() {
		return links;
	}
	
	public int getStatusCode() {
		return statusCode;
	}
	
	public Document getHtmlDocument( ) {
		return htmlDocument;
	}
	
	public void setHtmlDocument(Document htmlDocument) {
		this.htmlDocument = htmlDocument;
	}
}
