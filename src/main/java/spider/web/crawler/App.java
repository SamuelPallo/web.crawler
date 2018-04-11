package spider.web.crawler;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Spider spider = new GoogleSpider();
        //spider.search("http://arstechnica.com/", "computer");
        spider.search("instalator");
    }
}
