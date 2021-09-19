package alesh.mohamed.urlshortener.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("shortenedurls")
public class URL {
	private String url;
	@Id
	private String hash;
	private boolean isSafe;
	private String dateCreated;

	public URL() {
		
	}
	public URL(String url, String hash, boolean isSafe, String dateCreated) {
		super();
		this.url = url;
		this.hash = hash;
		this.isSafe = isSafe;
		this.dateCreated = dateCreated;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public boolean isSafe() {
		return isSafe;
	}

	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Override
	public String toString() {
		return "URL [url=" + url + ", hash=" + hash + ", isSafe=" + isSafe + ", dateCreated=" + dateCreated + "]";
	}
}
