package za.co.polygon.mapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

import za.co.polygon.domain.AnswerValue;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.domain.User;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationRequestCommandModel;
import za.co.polygon.model.QuotationRequestQueryModel;
import za.co.polygon.model.QuotationRequestQueryModel.Applicant;
import za.co.polygon.model.UserQueryModel;

public class Mapper {

    public static UserQueryModel toUserQueryModel(User from) {
        UserQueryModel user = new UserQueryModel();
        user.setId(from.getId());
        user.setUserName(from.getUserName());
        user.setRole(from.getRole());
        return user;
    }

    public static List<UserQueryModel> toUserQueryModel(List<User> fromList) {
        List<UserQueryModel> userList = new ArrayList<UserQueryModel>();
        for (User user : fromList) {
            userList.add(toUserQueryModel(user));
        }
        return userList;
    }

    public static ProductQueryModel toProductQueryModel(Product from) {
        ProductQueryModel productQueryModel = new ProductQueryModel();
        productQueryModel.setId(from.getId());
        productQueryModel.setName(from.getName());
        productQueryModel.setDescription(from.getDescription());
        productQueryModel.setImage(from.getImage());
        return productQueryModel;
    }

    public static List<ProductQueryModel> toProductQueryModel(List<Product> fromList) {
        List<ProductQueryModel> productQueryModels = new ArrayList<ProductQueryModel>();
        for (Product product : fromList) {
            productQueryModels.add(toProductQueryModel(product));

        }
        return productQueryModels;
    }
    
    public static QuestionnaireQuery toQuestionnaireQueryModel(Questionnaire from){
    	QuestionnaireQuery questionnaireQuery = new QuestionnaireQuery();
    	questionnaireQuery.setId(from.getId());
    	questionnaireQuery.setQuestion(from.getQuestion());
    	questionnaireQuery.setSequenceNumber(from.getSequenceNumber());
    	questionnaireQuery.setAnswerType(from.getAnswertype().getAnswerType());
    	questionnaireQuery.setDependsOn(from.getDependsOn());
    	questionnaireQuery.setOnAnswer(from.getOnAnswer());
    	questionnaireQuery.setIsRequired(from.getIsRequired());
        
    	for(AnswerValue answerValue : from.getAnswerValues()){
    		questionnaireQuery.getAnswerValues().add(answerValue.getAnswerValue());
    	}
    	
    	return questionnaireQuery;
    }
    
    public static List<QuestionnaireQuery> toQuestionnaireQueryModel(List<Questionnaire> fromList){
    	List<QuestionnaireQuery> questionnaireQueries = new ArrayList<QuestionnaireQuery>();
    	for(Questionnaire questionnaire : fromList){
    		questionnaireQueries.add(toQuestionnaireQueryModel(questionnaire));
    	}
    	return questionnaireQueries;
    }
    
    public static BrokerQueryModel toBrokerQueryModel(Broker from) {
        BrokerQueryModel brokerQueryModel = new BrokerQueryModel();
        brokerQueryModel.setId(from.getId());
        brokerQueryModel.setCode(from.getCode());
        brokerQueryModel.setName(from.getName());
        brokerQueryModel.setEmail(from.getEmail());
        return brokerQueryModel;
    }

    public static List<BrokerQueryModel> toBrokerQueryModel(List<Broker> fromList) {
        List<BrokerQueryModel> brokerQueryModels = new ArrayList<BrokerQueryModel>();
        for (Broker broker : fromList) {
            brokerQueryModels.add(toBrokerQueryModel(broker));

        }
        return brokerQueryModels;
    }
    
     public static QuotationRequest toQuotationRequest(QuotationRequestCommandModel from) {
        QuotationRequest quotationRequest = new QuotationRequest();
        quotationRequest.setApplicantName(from.getApplicantName());
        quotationRequest.setApplicantEmail(from.getApplicantEmail());
        
        
        return quotationRequest;
    }
    
    public static QuotationRequestQueryModel toQuotationRequestQueryModel(QuotationRequest from,Broker broker){
           QuotationRequestQueryModel quotationRequestQueryModel = new QuotationRequestQueryModel();
           quotationRequestQueryModel.setReference(from.getReference());
           quotationRequestQueryModel.setStatus(from.getStatus());
           
           quotationRequestQueryModel.getBroker(broker);
           //quotationRequestQueryModel.setDate(new java.sql.Date(new Date().getTime()));
           // System.out.println("Applicant Name" + quotationRequestQueryModel.getApplicant());
//           quotationRequestQueryModel.getApplicant().setApplicantEmail(from.getApplicantEmail());
//           quotationRequestQueryModel.getProduct().setId(from.getId());
//           System.out.println(from.getId() + "Received");
//           quotationRequestQueryModel.getBroker().setId(from.getId());
//           quotationRequestQueryModel.getBroker().setBrokerName(from.getBroker().getName());
//           quotationRequestQueryModel.getBroker().setBrokerEmail(from.getBroker().getEmail());
//            quotationRequestQueryModel.getProduct().setId(from.getId());
//            quotationRequestQueryModel.getProduct().setProductName(from.getProduct().getName());
//            quotationRequestQueryModel.getProduct().setProductDesc(from.getProduct().getDescription());
//            quotationRequestQueryModel.getProduct().setProductImage(from.getProduct().getImage());
            
           
           return quotationRequestQueryModel;
    }
    
    public static List<QuotationRequestQueryModel> toQuotationRequestQueryModel(List<QuotationRequest> fromList){
        List<QuotationRequestQueryModel> quotationRequestQueryModel = new ArrayList<QuotationRequestQueryModel>();
        
        for ( QuotationRequest quotationRequest : fromList){
            quotationRequestQueryModel.add(toQuotationRequestQueryModel(quotationRequest));
        }
        return quotationRequestQueryModel;
    }
    
}
