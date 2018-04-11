package spider.web.crawler;

import java.util.LinkedList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Spider {
	
	private static final int MAX_SEARCH_PAGES = 10;
	protected Set<String> pagesVisited;
	protected List<String> pagesToVisit;
	
	{
		pagesVisited = new HashSet<String>();
		pagesToVisit = new LinkedList<String>();
	}
	
	
	/**
	 * @param String wordToSearch
	 * 
	 * This method is used to search using a concatenated URL and a word
	 * 
	 * @return
	 * */
	abstract void search(String wordToSearch);
//	public void search(String url, String word) {
//		String currentUrl;
//		SpiderLeg leg;
//		boolean succes;
//		
//		while(pagesVisited.size() < MAX_SEARCH_PAGES) {
//			leg = new SpiderLeg();
//			
//			if(pagesToVisit.isEmpty()) {
//				currentUrl = url;
//				pagesVisited.add(url);
//			
//			} else {
//				currentUrl = nextUrl();
//			}
//			
//			leg.crawl(currentUrl);
//			succes = leg.searchForWord(word);
//			
//			if(succes) {
//				System.out.println(String.format("**Success** Word %s found at %s", word, currentUrl));
//				break;
//			}
//			pagesToVisit.addAll(leg.getLinks());
//		}
//	    System.out.println("\n**Done** Visited " + pagesVisited.size() + " web page(s)");
//	}


	/**
	 * Return the next URL to visit (in the order that they were found). Also do a check to make sure
	 * that this method doesn't return a URL that was already visited.
	 * 
	 * @return String nextUrl
	 * */
	protected String nextUrl() {
		String nextUrl = "";
		
		do {
			nextUrl = pagesToVisit.remove(0);
		} while (pagesVisited.contains(nextUrl));
		pagesVisited.add(nextUrl);
		return nextUrl;
	}
	
	
}
