package com.jsoup;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jsoup.model.Site;
import com.jsoup.repository.SiteRepository;

@SpringBootApplication
public class JsoupSiteCrawlerApplication implements CommandLineRunner {

	@Autowired
	SiteRepository repository;

	private static final int MAX_DEPTH = 5;
	private HashSet<String> links = new HashSet<>();
	private String URL = "https://www.michelin.in/";
	private int depth = 0;
	private String userAgent = "Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6";

	// meta properties key
	private static final String DESCRIPTION = "description";
	private static final String OGDESCRIPTION = "og:description";
	private static final String KEYWORDS = "keywords";
	private static final String OGTITLE = "og:title";
	private static final String OGTYPE = "og:type";
	private static final String OGURL = "og:url";
	private static final String OGIMAGE = "og:image";
	private static final String MSAPPLICATION_TILEIMAGE = "msapplication-TileImage";
	private static final String MSAPPLICATION_CONFIG = "msapplication-config";
	private static final String MSAPPLICATION_TILECOLOR = "msapplication-TileColor";
	private static final String THEME_COLOR = "theme-color";

	public static void main(String[] args) {
		SpringApplication.run(JsoupSiteCrawlerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		getPageLinks(URL, depth);
	}

	public void getPageLinks(String URL, int depth) {
		if ((!links.contains(URL) && (depth < MAX_DEPTH))) {
			System.out.println(">> Depth: " + depth + " [" + URL + "]");
			try {
				links.add(URL);

				Document document = Jsoup.connect(URL).timeout(10000).userAgent(userAgent).get();
				Elements metaTags = document.getElementsByTag("meta");
				Elements linksOnPage = document.select("a[href]");

				// List<Site> sites = new ArrayList<Site>();
				Site site = new Site();
				for (Element element : metaTags) {

					// System.out.println(element);
					String name = element.attr("name");
					String property = element.attr("property");
					String content = element.attr("content");
					// System.out.println(name+" "+property+" "+content);
					if (name != null && !name.trim().equals("")) {
						if (name.equals(DESCRIPTION)) {
							site.setDescription(content);
						} else if (name.equals(KEYWORDS)) {
							String[] keywords = content.split(",");
							site.setKeyword_tags(Arrays.asList(keywords));
						} else if (name.equals(MSAPPLICATION_TILEIMAGE)) {
							site.addMsapplication_tileimage(content);
						} else if (name.equals(MSAPPLICATION_CONFIG)) {
							site.setMsapplication_config(content);
						} else if (name.equals(MSAPPLICATION_TILECOLOR)) {
							site.addMsapplication_tileimage(content);
						} else if (name.equals(THEME_COLOR)) {
							site.setTheme_color(content);
						}
					} else if (property != null && !property.trim().equals("")) {
						if (property.equals(OGDESCRIPTION)) {
							site.setOgdescription(content);
						} else if (property.equals(OGTITLE)) {
							site.setOgtitle(content);
						} else if (property.equals(OGTYPE)) {
							site.setOgtype(content);
						} else if (property.equals(OGURL)) {
							site.setOgurl(content);
						} else if (property.equals(OGIMAGE)) {
							site.addMsapplication_tileimage(content);
						}
					}
					site.setUrl(URL);
					// sites.add(site);
				}

				System.out.println(site);
				repository.save(site);

				depth++;
				for (Element page : linksOnPage) {
					getPageLinks(page.attr("abs:href"), depth);
				}
			} catch (IOException e) {
				System.err.println("For '" + URL + "': " + e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed For URL:" + URL);
			}
		}
	}

}
