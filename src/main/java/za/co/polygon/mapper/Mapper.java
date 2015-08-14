package za.co.polygon.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import za.co.polygon.domain.Answer;
import za.co.polygon.domain.AnswerValue;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.domain.User;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.PolicyRequestCommandModel;
import za.co.polygon.model.PolicyRequestQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationCommandModel;
import za.co.polygon.model.QuotationCommandModel.Options;
import za.co.polygon.model.QuotationOptionQueryModel;
import za.co.polygon.model.QuotationQueryModel;
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

    public static QuotationRequest toQuotationRequest(QuotationRequestCommandModel quotationRequestCommandModel, Broker broker, Product product) {
        QuotationRequest quotationRequest = new QuotationRequest();
        quotationRequest.setApplicantName(quotationRequestCommandModel.getApplicantName());
        quotationRequest.setApplicantEmail(quotationRequestCommandModel.getApplicantEmail());
        quotationRequest.setCompanyName(quotationRequestCommandModel.getCompanyName());
        quotationRequest.setReference(UUID.randomUUID().toString());
        quotationRequest.setStatus("APPLIED");
        quotationRequest.setCreateDate(new Date());
        quotationRequest.setProduct(product);
        quotationRequest.setBroker(broker);
        List<Answer> answerList = new ArrayList<Answer>();

        for (Questionnaires questionnaires : quotationRequestCommandModel.getQuestionnaires()) {
            Answer answer = new Answer();
            answer.setQuestion(questionnaires.getQuestion());
            answer.setAnswer(questionnaires.getAnswer());
            answer.setQuotationRequest(quotationRequest);
            answerList.add(answer);

        }
        quotationRequest.setAnswers(answerList);

        return quotationRequest;
    }

    public static QuotationRequestQueryModel toQuotationRequestQueryModel(QuotationRequest quotationRequest) {

        QuotationRequestQueryModel result = new QuotationRequestQueryModel();

        result.setReference(quotationRequest.getReference());
        result.setStatus(quotationRequest.getStatus());
        result.setCreateDate(new SimpleDateFormat("dd/MM/YYYY").format(quotationRequest.getCreateDate()));
        result.setApplicantName(quotationRequest.getApplicantName());
        result.setApplicantEmail(quotationRequest.getApplicantEmail());
        result.setCompanyName(quotationRequest.getCompanyName());

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

        for (Answer answer : quotationRequest.getAnswers()) {
            QuotationRequestQueryModel.Questionnaire q = new QuotationRequestQueryModel.Questionnaire();
            q.setQuestion(answer.getQuestion());
            q.setAnswer(answer.getAnswer());
            result.getQuestionnaire().add(q);
        }
        return result;
    }

    public static List<Answer> fromQuotationRequestCommandModel(QuotationRequestCommandModel quotationRequestQueryModel, QuotationRequest quotationRequest) {
        List<Answer> result = new ArrayList<Answer>();
        for (QuotationRequestCommandModel.Questionnaires questionnaire : quotationRequestQueryModel.getQuestionnaires()) {
            Answer answer = new Answer();
            answer.setQuestion(questionnaire.getQuestion());
            answer.setAnswer(questionnaire.getAnswer());
            answer.setQuotationRequest(quotationRequest);
            result.add(answer);
        }
        return result;
    }

    public static Quotation fromQuotationRequestCommandModel(QuotationCommandModel quotationCommandModel, QuotationRequest quotationRequest) {
        Quotation quotation = new Quotation();
        quotation.setCreatedDate(new Date());
        quotation.setQuotationRequest(quotationRequest);

        List<QuotationOption> quotationOptionList = new ArrayList<QuotationOption>();
        for (Options options : quotationCommandModel.getOptions()) {
            QuotationOption quotationOption = new QuotationOption();
            quotationOption.setCommodity(options.getCommodity());
            quotationOption.setCover(options.getCover());
            quotationOption.setExcess(options.getExcess());
            quotationOption.setLimit(options.getLimit());
            quotationOption.setLocation(options.getLocation());
            quotationOption.setPremium(options.getPremium());
            quotationOption.setQuotation(quotation);
            quotationOptionList.add(quotationOption);
        }
        quotation.setQuotationOptions(quotationOptionList);
        return quotation;
    }

    public static QuotationQueryModel toQuotationQueryModel(Quotation quotation) {

        QuotationQueryModel result = new QuotationQueryModel();
        result.setQuotationId(quotation.getId());
        result.setQuotationRequest(toQuotationRequestQueryModel(quotation.getQuotationRequest()));

        for (QuotationOption quotationOption : quotation.getQuotationOptions()) {
            QuotationQueryModel.Option option = new QuotationQueryModel.Option();
            option.setQuotationOptionId(quotationOption.getId());
            option.setCommodity(quotationOption.getCommodity());
            option.setCover(quotationOption.getCover());
            option.setExcess(quotationOption.getExcess());
            option.setLimit(quotationOption.getLimit());
            option.setLocation(quotationOption.getLocation());
            option.setPremium(quotationOption.getPremium());
            result.getOption().add(option);
        }
        return result;
    }
    
    
     public static List<QuotationQueryModel> toQuotationQueryModel(List<Quotation> fromList) {
        List<QuotationQueryModel> quotationQueryModel = new ArrayList<QuotationQueryModel>();
        for (Quotation quotation : fromList) {
            quotationQueryModel.add(toQuotationQueryModel(quotation));

        }
        return quotationQueryModel;
    }


    public static PolicyRequest toPolicyRequest(PolicyRequestCommandModel policyRequestCommandModel, Quotation quotation, QuotationOption quotationOption) {
        PolicyRequest policyRequest = new PolicyRequest();

        
        policyRequest.setCompanyRegNumber(policyRequestCommandModel.getCompanyRegNumber());
        policyRequest.setVatRegNumber(policyRequestCommandModel.getVatRegNumber());
        policyRequest.setTelephoneNumber(policyRequestCommandModel.getTelephoneNumber());
        policyRequest.setFaxNumber(policyRequestCommandModel.getFaxNumber());
        policyRequest.setStreetAddress(policyRequestCommandModel.getStreetAddress());
        policyRequest.setSuburb(policyRequestCommandModel.getSuburb());
        policyRequest.setPostalCode(policyRequestCommandModel.getPostalCode());
        policyRequest.setDesignation(policyRequestCommandModel.getDesignation());
        policyRequest.setBuisnessDesc(policyRequestCommandModel.getBuisnessDesc());
        policyRequest.setAccountHolder(policyRequestCommandModel.getAccountHolder());
        policyRequest.setAccountName(policyRequestCommandModel.getAccountName());
        policyRequest.setBankName(policyRequestCommandModel.getBankName());
        policyRequest.setAccountNumber(policyRequestCommandModel.getAccountNumber());
        policyRequest.setBranchCode(policyRequestCommandModel.getBranchCode());
        policyRequest.setAccType(policyRequestCommandModel.getAccType());
        policyRequest.setDebitOrderDate(policyRequestCommandModel.getDebitOrderDate());
        policyRequest.setStatus("APPLIED");
        policyRequest.setQuotation(quotation);
        policyRequest.setQuotationOption(quotationOption);

        return policyRequest;
    }
    
    public static QuotationOptionQueryModel toQuotationOptionQueryModel(QuotationOption quotationOption){
    	QuotationOptionQueryModel quotationOptionQueryModel = new QuotationOptionQueryModel();
    	quotationOptionQueryModel.setQuotationOptionId(quotationOption.getId());
    	quotationOptionQueryModel.setCommodity(quotationOption.getCommodity());
    	quotationOptionQueryModel.setCover(quotationOption.getCover());
    	quotationOptionQueryModel.setExcess(quotationOption.getExcess());
    	quotationOptionQueryModel.setLimit(quotationOption.getLimit());
    	quotationOptionQueryModel.setLocation(quotationOption.getLocation());
    	quotationOptionQueryModel.setPremium(quotationOption.getPremium());
    	
    	return quotationOptionQueryModel;
    }
    
    public static PolicyRequestQueryModel toPolicyRequestQueryModel(PolicyRequest policyRequest,Quotation quotation,QuotationOption quotationOption){
    	
    	PolicyRequestQueryModel policyRequestQueryModel = new PolicyRequestQueryModel();
    	
    	policyRequestQueryModel.setQuotation(toQuotationQueryModel(quotation));
    	policyRequestQueryModel.setQuotationOption(toQuotationOptionQueryModel(quotationOption));
    	policyRequestQueryModel.setCompanyRegNumber(policyRequest.getCompanyRegNumber());
    	policyRequestQueryModel.setVatRegNumber(policyRequest.getVatRegNumber());
    	policyRequestQueryModel.setTelephoneNumber(policyRequest.getTelephoneNumber());
    	policyRequestQueryModel.setFaxNumber(policyRequest.getFaxNumber());
    	policyRequestQueryModel.setStreetAddress(policyRequest.getStreetAddress());
    	policyRequestQueryModel.setSuburb(policyRequest.getSuburb());
    	policyRequestQueryModel.setPostalCode(policyRequest.getPostalCode());
    	policyRequestQueryModel.setDesignation(policyRequest.getDesignation());
    	policyRequestQueryModel.setBuisnessDesc(policyRequest.getBuisnessDesc());
    	policyRequestQueryModel.setAccountHolder(policyRequest.getAccountHolder());
    	policyRequestQueryModel.setAccountName(policyRequest.getAccountName());
    	policyRequestQueryModel.setBankName(policyRequest.getBankName());
    	policyRequestQueryModel.setAccountNumber(policyRequest.getAccountNumber());
    	policyRequestQueryModel.setBranchCode(policyRequest.getBranchCode());
    	policyRequestQueryModel.setAccType(policyRequest.getAccType());
    	policyRequestQueryModel.setDebitOrderDate(policyRequest.getDebitOrderDate());
    	policyRequestQueryModel.setBankStatement(policyRequest.getBankStatement());
        policyRequestQueryModel.setStatus(policyRequest.getStatus());
    	
    	return policyRequestQueryModel;
    	
    }

}
