package spider.web.crawler;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Samuel
 * */
public class GoogleSpider extends Spider {

	private static final String URL_GOOGLE_SEARCH = "https://www.google.ro/search?q=";

	private List<String> googleSearchPages;
	private List<String> googleOptionsLinks;
	private GoogleSpiderLeg leg;
	
	{
		googleSearchPages = new LinkedList<String>();
		googleOptionsLinks = new LinkedList<String>();
		leg = new GoogleSpiderLeg();
	}
	
	/**
	 * {@inheritDoc}
	 * */
	public void search(String wordToSearch) {
		leg.crawl(URL_GOOGLE_SEARCH + wordToSearch);
		
		if(leg.getStatusCode() == 200) {
			pagesToVisit.addAll(leg.getLinks());
			googleSearchPages.addAll(leg.getGooglePagesLinks());
			googleOptionsLinks.addAll(leg.getGoogleOptions());
		} else {
			System.out.println("**Fail**");
		}
		
		System.out.println("**Links** " + pagesToVisit);
		System.out.println("**Google pages search** " + googleSearchPages);
		System.out.println("**Google options links** " + googleOptionsLinks);
		
		indexSearchPages();
	}
	
	private void indexSearchPages() {
		for(String link : googleSearchPages.subList(1, googleSearchPages.size() - 1)) {
			leg.resetLinkLists();
			leg.crawl(link);
			pagesToVisit.addAll(leg.getLinks());
			System.out.println("**Link** - " + link);
			System.out.println("**Links** " + leg.getLinks());
		}
	}
}
