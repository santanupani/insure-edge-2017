package za.co.polygon.service;


public class TestToken {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String date = "2015-10-28";
		
		StringBuffer resultString = new StringBuffer();
		
		String[] dateTokens = date.split("-");
        for(int i=dateTokens.length-1;i>=0;i--){
        	resultString.append(dateTokens[i]+"/");
        }
        
        String buff = resultString.replace(10, 11, new String()).toString();
        System.out.println("Result :"+buff.toString());
		

	}

}
