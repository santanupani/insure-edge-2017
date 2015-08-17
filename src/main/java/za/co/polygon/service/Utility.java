package za.co.polygon.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Component;

import za.co.polygon.domain.Quotation;

@Component
public class Utility{
	
	private List<Quotation> expiredQuotations = new ArrayList<Quotation>();

	public int getExpiredDays(LocalDate localDate) {

		LocalDate quoteLocalDate = localDate; 
		LocalDate currentDate = LocalDate.now();

		Period betweenDates = Period.between(quoteLocalDate, currentDate);

		int diffInDays = betweenDates.getDays();
		return diffInDays;

	}

	public List<Quotation> scheduleExpiredQuotations(List<Quotation> allQuotations){
		LocalDate frmQuotationCreateDate = null;
		Calendar calendar = Calendar.getInstance();
		
		while(calendar.get(Calendar.HOUR) != 11 && calendar.get(Calendar.MINUTE) != 23){ 
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}

		for(Quotation quotation: allQuotations){
			frmQuotationCreateDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(quotation.getCreatedDate()));
			int days = getExpiredDays(frmQuotationCreateDate);
			if(days == 0 && quotation.getQuotationRequest().getStatus() != "EXPIRED"){
				quotation.getQuotationRequest().setStatus("EXPIRED");	
				getExpiredQuotations().add(quotation);
			}
		}
		return getExpiredQuotations();
		
	}

	public List<Quotation> getExpiredQuotations() {
		return expiredQuotations;
	}

	public void setExpiredQuotations(List<Quotation> expiredQuotations) {
		this.expiredQuotations = expiredQuotations;
	}

}

