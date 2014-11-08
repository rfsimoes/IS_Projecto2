package ejbs;

import java.util.List;

import javax.ejb.Remote;

import common.News;

@Remote
public interface NewsBeanRemote {
	public List<News> newsSortedByDate();
	public List<News> newsFromAuthor(String author);
	public List<News> newsMoreRecentThan(String date);
	public List<News> newsWithHighlightWord(String word);
}
