package ejbs;

import java.util.List;

import javax.ejb.Remote;

import common.News;

@Remote
public interface NewsBeanRemote {
	public List<News> getNews(String username, String password);
	public List<News> newsSortedByDate(String region, String username, String password);
	public List<News> newsFromAuthor(String author, String username, String password);
	public List<News> newsFromAuthor(String author, String region, String username, String password);
	public List<News> newsMoreRecentThan(String date, String username, String password);
	public List<News> newsMoreRecentThan(String date, String region, String username, String password);
}
