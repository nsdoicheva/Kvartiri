package source;

public class Message {
	private String title;
	private String content;
	private static final int MAX_lENGTH_OF_MESSAGE = 200;
	private static final int MAX_lENGTH_OF_TITLE = 20;
	
	protected Message(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
    
	
	@Override
	public String toString() {
		return "MESSAGE: TITLE : " + title + "\n" + "CONTENT : " + content + "\n" + "FROM : " ;
	}



	String getTitle() {
		return title;
	}

	
	String getContent() {
		return content;
	}
	
	

}
