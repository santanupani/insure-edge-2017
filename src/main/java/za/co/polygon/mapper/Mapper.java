package za.co.polygon.mapper;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import za.co.polygon.domain.Answer;
import za.co.polygon.domain.AnswerValue;
import za.co.polygon.domain.BankAccount;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.Client;
import za.co.polygon.domain.Contact;
import za.co.polygon.domain.IndemnityOption;
import za.co.polygon.domain.Policy;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.PolicySchedule;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.domain.SubAgent;
import za.co.polygon.domain.Underwriter;
import za.co.polygon.domain.User;
import za.co.polygon.model.BankAccountCommandModel;
import za.co.polygon.model.BankAccountQueryModel;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.ClientCommandModel;
import za.co.polygon.model.ClientQueryModel;
import za.co.polygon.model.ContactCommandModel;
import za.co.polygon.model.ContactQueryModel;
import za.co.polygon.model.IndemnityOptionQueryModel;
import za.co.polygon.model.PolicyQueryModel;
import za.co.polygon.model.PolicyRequestCommandModel;
import za.co.polygon.model.PolicyRequestQueryModel;
import za.co.polygon.model.PolicyScheduleCommandModel;
import za.co.polygon.model.PolicyScheduleCommandModel.IndemOptions;
import za.co.polygon.model.PolicyScheduleQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationCommandModel;
import za.co.polygon.model.QuotationCommandModel.Options;
import za.co.polygon.model.QuotationOptionQueryModel;
import za.co.polygon.model.QuotationQueryModel;
import za.co.polygon.model.QuotationQueryModel.Option;
import za.co.polygon.model.QuotationRequestCommandModel;
import za.co.polygon.model.QuotationRequestCommandModel.Questionnaires;
import za.co.polygon.model.QuotationRequestQueryModel;
import za.co.polygon.model.SelectedQuotationQueryModel;
import za.co.polygon.model.SubAgentQueryModel;
import za.co.polygon.model.UnderwriterQueryModel;
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
        NumberFormat nft = NumberFormat.getInstance(new Locale("en", "ZA"));
        nft.setMaximumFractionDigits(2);
        for (Answer answer : quotationRequest.getAnswers()) {
            QuotationRequestQueryModel.Questionnaire q = new QuotationRequestQueryModel.Questionnaire();
            q.setQuestion(answer.getQuestion());
            if (answer.getQuestion().contains("What is the maximum amount you wish to insure ?")) {
                try {
                    q.setAnswer(nft.format(nft.parse(answer.getAnswer())));
                } catch (ParseException pex) {

                }
            } else {
                q.setAnswer(answer.getAnswer());
            }
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
            quotationOption.setDuration(options.getDuration());
            quotationOption.setLocation(options.getLocation());
            quotationOption.setPremium(options.getPremium());
            quotationOption.setPavements(options.getPavement());
            quotationOption.setStaticLimit(options.getStaticLimit());
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
            option.setDuration(quotationOption.getDuration());
            option.setLimit(quotationOption.getLimit());
            option.setLocation(quotationOption.getLocation());
            option.setPremium(quotationOption.getPremium());
            option.setPavement(quotationOption.getPavements());
            option.setStaticLimit(quotationOption.getStaticLimit());
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

    public static QuotationOptionQueryModel toQuotationOptionQueryModel(QuotationOption quotationOption) {

        QuotationOptionQueryModel quotationOptionQueryModel = new QuotationOptionQueryModel();
        quotationOptionQueryModel.setQuotationOptionId(quotationOption.getId());
        quotationOptionQueryModel.setCommodity(quotationOption.getCommodity());
        quotationOptionQueryModel.setCover(quotationOption.getCover());
        quotationOptionQueryModel.setExcess(quotationOption.getExcess());
        quotationOptionQueryModel.setLimit(quotationOption.getLimit());
        quotationOptionQueryModel.setLocation(quotationOption.getLocation());
        quotationOptionQueryModel.setPremium(quotationOption.getPremium());
        quotationOptionQueryModel.setPavement(quotationOption.getPavements());
        quotationOptionQueryModel.setStaticLimit(quotationOption.getStaticLimit());

        return quotationOptionQueryModel;
    }

    public static PolicyRequestQueryModel toPolicyRequestQueryModel(PolicyRequest policyRequest, Quotation quotation, QuotationOption quotationOption) {

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

    public static SelectedQuotationQueryModel toSelectedQuotationQueryModel(Quotation quotation, QuotationOption quotationOption) {
        SelectedQuotationQueryModel selectedQuotationQueryModel = new SelectedQuotationQueryModel();
        selectedQuotationQueryModel.setQuotation(toQuotationQueryModel(quotation));
        selectedQuotationQueryModel.setSelectedQuotation(toQuotationOptionQueryModel(quotationOption));

        return selectedQuotationQueryModel;
    }

    public static Contact toContactCommandModel(ContactCommandModel contactCommandModel) {
        Contact contact = new Contact();

        contact.setCode(contactCommandModel.getCode());
        contact.setContactPerson(contactCommandModel.getContactPerson());
        contact.setEmail(contactCommandModel.getEmail());
        contact.setFaxNumber(contactCommandModel.getFaxNumber());
        contact.setStreet(contactCommandModel.getStreet());
        contact.setSuburb(contactCommandModel.getSuburb());
        contact.setWorkTelNumber(contactCommandModel.getWorkTelNumber());

        return contact;
    }

    public static BankAccount toBankAccountCommandModel(BankAccountCommandModel bankAccountCommandModel) {
        BankAccount bankAccount = new BankAccount();

        bankAccount.setAccountName(bankAccountCommandModel.getAccountName());
        bankAccount.setAccountNumber(bankAccountCommandModel.getAccountNumber());
        bankAccount.setBankName(bankAccountCommandModel.getBankName());
        bankAccount.setBranchCode(bankAccount.getBranchCode());

        return bankAccount;
    }

    public static Client toClientDetailCommandModel(ClientCommandModel clientCommandModel, Contact contact, BankAccount bankAccount) {
        Client client = new Client();

        client.setContacts(contact);
        client.setBankAccount(bankAccount);
        client.setClientName(clientCommandModel.getClientName());
        client.setIncomeTaxNumber(clientCommandModel.getIncomeTaxNumber());
        client.setRegNumber(clientCommandModel.getRegNumber());
        client.setVatNumber(clientCommandModel.getVatNumber());

        return client;
    }

    public static BankAccountQueryModel toBankAccountQueryModel(BankAccount from) {
        BankAccountQueryModel bankAccountQueryModel = new BankAccountQueryModel();
        bankAccountQueryModel.setId(from.getId());
        bankAccountQueryModel.setAccountName(from.getAccountName());
        bankAccountQueryModel.setAccountNumber(from.getAccountNumber());
        bankAccountQueryModel.setBankName(from.getBankName());
        bankAccountQueryModel.setBranch(from.getBranchCode());
        return bankAccountQueryModel;
    }

    public static ContactQueryModel toContactQueryModel(Contact from) {
        ContactQueryModel contactQueryModel = new ContactQueryModel();
        contactQueryModel.setId(from.getId());
        contactQueryModel.setCode(from.getCode());
        contactQueryModel.setContactPerson(from.getContactPerson());
        contactQueryModel.setEmail(from.getEmail());
        contactQueryModel.setFaxNumber(from.getFaxNumber());
        contactQueryModel.setStreet(from.getStreet());
        contactQueryModel.setSuburb(from.getSuburb());
        contactQueryModel.setWorkTelNumber(from.getWorkTelNumber());
        return contactQueryModel;
    }

    public static ClientQueryModel toClientQueryModel(Client client) {

        ClientQueryModel result = new ClientQueryModel();

        result.setClientId(client.getId());
        result.setClientName(client.getClientName());
        result.setIncomeTaxNumber(client.getIncomeTaxNumber());
        result.setRegNumber(client.getRegNumber());
        result.setVatNumber(client.getVatNumber());
        result.setBankAccounts(toBankAccountQueryModel(client.getBankAccount()));
        result.setContact(toContactQueryModel(client.getContact()));

        return result;
    }


    public static SubAgentQueryModel toSubAgentQueryModel(SubAgent subAgent) {
        SubAgentQueryModel subAgentQueryModel = new SubAgentQueryModel();
        subAgentQueryModel.setId(subAgent.getId());
        subAgentQueryModel.setFirstName(subAgent.getFirstName());
        subAgentQueryModel.setMiddleName(subAgent.getMiddleName());
        subAgentQueryModel.setLastName(subAgent.getLastName());
        subAgentQueryModel.setEmail(subAgent.getEmail());
        subAgentQueryModel.setBroker(toBrokerQueryModel(subAgent.getBroker()));

        return subAgentQueryModel;
    }

    public static UnderwriterQueryModel toUnderwriterQueryModel(Underwriter underwriter) {
        UnderwriterQueryModel underwriterQueryModel = new UnderwriterQueryModel();
        underwriterQueryModel.setId(underwriter.getId());
        underwriterQueryModel.setFirstName(underwriter.getFirstName());
        underwriterQueryModel.setMiddleName(underwriter.getMiddleName());
        underwriterQueryModel.setLastName(underwriter.getLastName());
        underwriterQueryModel.setEmail(underwriter.getEmail());

        return underwriterQueryModel;
    }

    public static PolicyQueryModel toPolicyQueryModel(Policy policy) {
        PolicyQueryModel policyQueryModel = new PolicyQueryModel();

        policyQueryModel.setId(policy.getId());
        policyQueryModel.setPolicyReference(policy.getPolicyReference());
        policyQueryModel.setCollectByDebitOrder(policy.isCollectByDebitOrder());
        policyQueryModel.setBrokerFee(Double.toString(policy.getBrokerFee()));
        policyQueryModel.setDevice(policy.getDevice());
        policyQueryModel.setCollectByDebitOrder(policy.isCollectByDebitOrder());
        policyQueryModel.setExcludeSasria(policy.isExclude_sasria());
        policyQueryModel.setInceptionDate(policy.getInceptionDate().toString());
        policyQueryModel.setNotes(policy.getNotes());
        policyQueryModel.setStatus(policy.getStatus());
        policyQueryModel.setReInstatement(policy.getReInstatement());
        policyQueryModel.setRenewalDate(policy.getRenewalDate().toString());
        policyQueryModel.setRetroActiveDate(policy.getRetroactiveDate().toString());
        policyQueryModel.setUnderwriterFee(Double.toString(policy.getUnderwriterFee()));
        policyQueryModel.setUnderwritingYear(policy.getUnderwriting_year());
        policyQueryModel.setFrequency(policy.getFrequency());
        policyQueryModel.setSasriaFrequency(policy.getSasriaFrequency());
        policyQueryModel.setSubAgent(toSubAgentQueryModel(policy.getSubAgent()));
        policyQueryModel.setClient(toClientQueryModel(policy.getClient()));
        policyQueryModel.setUnderwriter(toUnderwriterQueryModel(policy.getUnderwriter()));
        policyQueryModel.setPolicySchedule(toPolicyScheduleQueryModel(policy.getPolicySchedule()));

        return policyQueryModel;
    }
    
    
    
    public static PolicySchedule toPolicyScheduleCommandModel(PolicyScheduleCommandModel policyScheduleCommandModel) {
        
        PolicySchedule policySchedule = new PolicySchedule();
        
        policySchedule.setId(policyScheduleCommandModel.getId());
        policySchedule.setBrokerCommission(policyScheduleCommandModel.getBrokerCommission());
        policySchedule.setConvenyances(policyScheduleCommandModel.getConveyances());
        policySchedule.setExcessSturcture(policyScheduleCommandModel.getExcessStructure());
        policySchedule.setGeographicalDuration(policyScheduleCommandModel.getGeographicalDuration());
        policySchedule.setMaximumSumInsured(policyScheduleCommandModel.getMaximumSumInsured());
        policySchedule.setPremoium(policyScheduleCommandModel.getPremium());
        policySchedule.setSasriaPremium(policyScheduleCommandModel.getSasriaPremium());
        policySchedule.setScheduleAttaching(policyScheduleCommandModel.getScheduleAttaching());
        policySchedule.setSpecialCondition(policyScheduleCommandModel.getSpecialCondition());
        policySchedule.setSubjectMatter(policyScheduleCommandModel.getSubjectMatter());
        policySchedule.setTypeOfCover(policyScheduleCommandModel.getTypeOfCover());
        policySchedule.setSumInsured(policyScheduleCommandModel.getSumInsured());
        policySchedule.setUACommission(policyScheduleCommandModel.getUACommission());
        
    
        List<IndemnityOption> indemnityOptionsList = new ArrayList<IndemnityOption>();
        for (IndemOptions options : policyScheduleCommandModel.getIndemOptions()) {
            IndemnityOption indemnityOption = new IndemnityOption();
            indemnityOption.setIndemnityItemOption(options.getIndemnityItemOption());
            indemnityOption.setIndemnityValue(options.getIndemnityValue());
            indemnityOption.setPremium(options.getPremium());
            indemnityOption.setSumInsured(options.getSumInsured());
            indemnityOption.setPolicySchedule(policySchedule);
            indemnityOptionsList.add(indemnityOption);
        }
        policySchedule.setIndemnityOptions(indemnityOptionsList);
        
        return policySchedule;
    }

    public static IndemnityOptionQueryModel toIndemnityOptionQueryModel(IndemnityOption indemnityOption) {
        IndemnityOptionQueryModel indemnityOptionQueryModel = new IndemnityOptionQueryModel();

        indemnityOptionQueryModel.setId(indemnityOption.getId());
        indemnityOptionQueryModel.setIndemnityItemOption(indemnityOption.getIndemnityItemOption());
        indemnityOptionQueryModel.setIndemnityValue(indemnityOption.getIndemnityValue());
        indemnityOptionQueryModel.setPremium(indemnityOption.getPremium());
        indemnityOptionQueryModel.setSumInsured(indemnityOption.getSumInsured());

        return indemnityOptionQueryModel;
    }
    
    public static List<IndemnityOptionQueryModel> toIndemnityOptionListQueryModel(PolicySchedule policySchedule) {
        List<IndemnityOptionQueryModel> indemnityOptionQueryModel = new ArrayList<IndemnityOptionQueryModel>();

        for(IndemnityOption indemnityOption: policySchedule.getIndemnityOptions()){
        	indemnityOptionQueryModel.add(toIndemnityOptionQueryModel(indemnityOption));
        }
        return indemnityOptionQueryModel;
    }

    public static PolicyScheduleQueryModel toPolicyScheduleQueryModel(PolicySchedule policySchedule) {
        
        PolicyScheduleQueryModel policyScheduleQueryModel = new PolicyScheduleQueryModel();
        
        policyScheduleQueryModel.setId(policySchedule.getId());
        policyScheduleQueryModel.setBrokerCommission(policySchedule.getBrokerCommission());
        policyScheduleQueryModel.setConveyances(policySchedule.getConvenyances());
        policyScheduleQueryModel.setExcessStructure(policySchedule.getExcessSturcture());
        policyScheduleQueryModel.setGeographicalDuration(policySchedule.getGeographicalDuration());
        policyScheduleQueryModel.setMaximumSumInsured(policySchedule.getMaximumSumInsured());
        policyScheduleQueryModel.setPremium(policySchedule.getPremoium());
        policyScheduleQueryModel.setSasriaPremium(policySchedule.getSasriaPremium());
        policyScheduleQueryModel.setScheduleAttaching(policySchedule.getScheduleAttaching());
        policyScheduleQueryModel.setSpecialCondition(policySchedule.getSpecialCondition());
        policyScheduleQueryModel.setSubjectMatter(policySchedule.getSubjectMatter());
        policyScheduleQueryModel.setTypeOfCover(policySchedule.getTypeOfCover());
        policyScheduleQueryModel.setSumInsured(policySchedule.getSumInsured());
        policyScheduleQueryModel.setUACommission(policySchedule.getUACommission());
        
        for(IndemnityOption indemnityOption: policySchedule.getIndemnityOptions()){
        	policyScheduleQueryModel.getIndemnityOption().add(toIndemnityOptionQueryModel(indemnityOption));
        }
        
        return policyScheduleQueryModel;
    }
    
    public static List<PolicyQueryModel> toPolicyQueryModel(List<Policy> fromPolicies){
    	List<PolicyQueryModel> policiesQueryResult = new ArrayList<PolicyQueryModel>();
    	for(Policy policy:fromPolicies){
    		policiesQueryResult.add(toPolicyQueryModel(policy));
    	}
    	return policiesQueryResult;
    }

}
