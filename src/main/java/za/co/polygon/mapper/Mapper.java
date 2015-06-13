package za.co.polygon.mapper;

import java.util.ArrayList;
import java.util.List;

import za.co.polygon.domain.AnswerValue;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.User;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
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
        brokerQueryModel.setBrokerCode(from.getBrokerCode());
        brokerQueryModel.setBrokerName(from.getBrokerName());
        brokerQueryModel.setBrokerEmail(from.getBrokerEmail());
        return brokerQueryModel;
    }

    public static List<BrokerQueryModel> toBrokerQueryModel(List<Broker> fromList) {
        List<BrokerQueryModel> brokerQueryModels = new ArrayList<BrokerQueryModel>();
        for (Broker broker : fromList) {
            brokerQueryModels.add(toBrokerQueryModel(broker));

        }
        return brokerQueryModels;
    }
    
    

}
