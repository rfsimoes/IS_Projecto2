package common;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: News
 *
 */
@Entity

public class News implements Serializable {
	
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "newsid")
	private Long id;
	private String region;
	private String title;
	private String url;
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="newsHighlights", joinColumns=@JoinColumn(name="newsid"))
	@Column(name = "highlight")
	private List<String> highlights;
	private Timestamp date;
	
	@ManyToMany
	private List<Author> authors;
	
	@Column(columnDefinition="TEXT")
	private String text;
	private String photoURL;
	private String videoURL;
	
	

	public News() {
		super();
	}   
	
	public News(String region, String title, String url, List<String> highlights, Timestamp date, List<Author> authors, String text, String photoURL, String videoURL){
		this.region = region;
		this.title = title;
		this.url = url;
		this.highlights = highlights;
		this.date = date;
		this.authors = authors;
		this.text = text;
		this.photoURL = photoURL;
		this.videoURL = videoURL;
	}
	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	} 
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}   
	public List<String> getHighlights() {
		return this.highlights;
	}

	public void setHighlights(List<String> highlights) {
		this.highlights = highlights;
	}   
	public Timestamp getDate() {
		return this.date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}   
	public List<Author> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}   
	public String getPhotoURL() {
		return this.photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}   
	public String getVideoURL() {
		return this.videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	
	public String toStringForEmail()
	{
		String email="";
		email = "<p><strong> # "+title+"</strong></p>";
		for(String h : highlights)
		{
			email += "<p>	-	"+h+"</p>";
		}
		
		email += "<p><a href="+url+">Read more.</a></p>";
		email += "<b></b>";
		
		return email;
		
	}
   
}
