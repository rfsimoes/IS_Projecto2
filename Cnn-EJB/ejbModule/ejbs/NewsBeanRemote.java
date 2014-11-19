package ejbs;

import java.util.List;

import javax.ejb.Remote;

import common.News;

@Remote
public interface NewsBeanRemote {
	public List<News> getNews();
	public List<News> newsSortedByDate(String region);
	public List<News> newsFromAuthor(String author);
	public List<News> newsFromAuthor(String author, String region);
	public List<News> newsMoreRecentThan(String date);
	public List<News> newsMoreRecentThan(String date, String region);
}
