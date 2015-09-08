package za.co.polygon.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import za.co.polygon.domain.Notification;

public class MailRepositoryIT {
	
	private String host,user,pass;
	private int port;
	
	public MailRepositoryIT(){}
	
	@Autowired
	MailRepositoryIT(@Value("${polygon.mail.hostname}")String host,
    		@Value("${polygon.mail.port}")int port,
    		@Value("${polygon.mail.username}")String user,
    		@Value("${polygon.mail.password}")String pass){
		this.setHost(host);
		this.setPass(pass);
		this.setUser(user);
		this.setPort(port);
	}

    @Test
    
    public void testSend() {
        Notification notification = new Notification("thabo.thulare@reverside.co.za", "Polygon Notification Test", "This is a test notification");
        try {
            MailRepository mailRepository = new MailRepository(getHost(),getPort(),getUser(),getPass());
            mailRepository.send(notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
    
    
}
