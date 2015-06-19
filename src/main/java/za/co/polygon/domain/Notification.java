package za.co.polygon.domain;

public class Notification {

    private String Message;
    private String to;
    final private String from = "thabothulare68@gmail.com";
    private String subject;
    final private String password = "Ndivhu@tee1";


   
    

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

  

    public String getSubject() {
        return subject;
    }
    
    public String getPassword() {
        return password;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

  

}
