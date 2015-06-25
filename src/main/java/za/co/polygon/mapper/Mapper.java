package za.co.polygon.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import za.co.polygon.domain.AnswerValue;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.domain.QuotationRequestQuestionnaires;
import za.co.polygon.domain.User;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationRequestCommandModel;
import za.co.polygon.model.QuotationRequestCommandModel.Questionnaires;
import za.co.polygon.model.QuotationRequestQueryModel;
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

    public static QuestionnaireQuery toQuestionnaireQueryModel(Questionnaire from) {
        QuestionnaireQuery questionnaireQuery = new QuestionnaireQuery();
        questionnaireQuery.setId(from.getId());
        questionnaireQuery.setQuestion(from.getQuestion());
        questionnaireQuery.setSequenceNumber(from.getSequenceNumber());
        questionnaireQuery.setAnswerType(from.getAnswertype().getAnswerType());
        questionnaireQuery.setDependsOn(from.getDependsOn());
        questionnaireQuery.setOnAnswer(from.getOnAnswer());
        questionnaireQuery.setIsRequired(from.getIsRequired());

        for (AnswerValue answerValue : from.getAnswerValues()) {
            questionnaireQuery.getAnswerValues().add(answerValue.getAnswerValue());
        }

        return questionnaireQuery;
    }

    public static List<QuestionnaireQuery> toQuestionnaireQueryModel(List<Questionnaire> fromList) {
        List<QuestionnaireQuery> questionnaireQueries = new ArrayList<QuestionnaireQuery>();
        for (Questionnaire questionnaire : fromList) {
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

    public static QuotationRequest toQuotationRequestModel(QuotationRequestCommandModel from, Broker broker, Product product) {
        QuotationRequest quotationRequest = new QuotationRequest();
        quotationRequest.setId(from.getId());
        quotationRequest.setApplicantName(from.getApplicantName());
        quotationRequest.setApplicantEmail(from.getApplicantEmail());
        quotationRequest.setCreateDate(new Date());
        quotationRequest.setReference(UUID.randomUUID().toString());
        quotationRequest.setStatus("APPLIED");
        quotationRequest.setBroker(broker);
        quotationRequest.setProduct(product);
        for (Questionnaires questionnaires : from.getQuestionnaires()) {
            QuotationRequestQuestionnaires quotationRequestQuestionnaires = new QuotationRequestQuestionnaires();
            quotationRequestQuestionnaires.setQuestion(questionnaires.getQuestion());
            quotationRequestQuestionnaires.setAnswer(questionnaires.getAnswer());
            quotationRequest.getQuotationRequestQuestionnaire().add(quotationRequestQuestionnaires);
        }
        return quotationRequest;
    }

    public static QuotationRequestQueryModel toQuotationRequestQueryModel(QuotationRequest quotationRequest) {

        QuotationRequestQueryModel result = new QuotationRequestQueryModel();

        result.setReference(quotationRequest.getReference());
        result.setStatus(quotationRequest.getStatus());
        result.setCreateDate(quotationRequest.getCreateDate().toString());
        result.setApplicantName(quotationRequest.getApplicantName());
        result.setApplicantEmail(quotationRequest.getApplicantEmail());

        BrokerQueryModel broker = new BrokerQueryModel();
        broker.setId(quotationRequest.getBroker().getId());
        broker.setCode(quotationRequest.getBroker().getCode());
        broker.setName(quotationRequest.getBroker().getName());
        broker.setEmail(quotationRequest.getBroker().getEmail());
        result.setBroker(broker);

        ProductQueryModel product = new ProductQueryModel();
        product.setId(quotationRequest.getProduct().getId());
        product.setDescription(quotationRequest.getProduct().getDescription());
        product.setName(quotationRequest.getProduct().getName());
        product.setImage(quotationRequest.getProduct().getImage());
        result.setProduct(product);

        for (QuotationRequestQuestionnaires quotationRequestQuestionnaires : quotationRequest.getQuotationRequestQuestionnaire()) {
            za.co.polygon.model.QuotationRequestQueryModel.Questionnaire questionnaire = new za.co.polygon.model.QuotationRequestQueryModel.Questionnaire();
            questionnaire.setQuestion(quotationRequestQuestionnaires.getQuestion());
            questionnaire.setAnswer(quotationRequestQuestionnaires.getAnswer());
            result.getQuestionnaire().add(questionnaire);
        }

        return result;
    }
}
