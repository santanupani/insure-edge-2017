package za.co.polygon.domain;

import java.io.Serializable;

public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String to;
    private String subject;
    private String message;
    
    public Notification(String to, String subject, String message){
    	this.to = to;
    	this.subject = subject;
    	this.message = message;
    }
    
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

    
}
