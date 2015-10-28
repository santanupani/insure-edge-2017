package za.co.polygon.mapper;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import za.co.polygon.domain.Answer;
import za.co.polygon.domain.AnswerValue;
import za.co.polygon.domain.BankAccount;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.ClaimAnswerValue;
import za.co.polygon.domain.ClaimQuestionnaire;
import za.co.polygon.domain.ClaimType;
import za.co.polygon.domain.Client;
import za.co.polygon.domain.Contact;
import za.co.polygon.domain.IndemnityOption;
import za.co.polygon.domain.Policy;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.domain.SubAgent;
import za.co.polygon.domain.Underwriter;
import za.co.polygon.domain.User;
import za.co.polygon.model.BankAccountQueryModel;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.ClaimQuestionnaireQuery;
import za.co.polygon.model.ClaimTypeQueryModel;
import za.co.polygon.model.ClientCommandModel;
import za.co.polygon.model.ClientQueryModel;
import za.co.polygon.model.ContactQueryModel;
import za.co.polygon.model.IndemnityOptionQueryModel;
import za.co.polygon.model.PolicyCreationCommandModel;
import za.co.polygon.model.PolicyQueryModel;
import za.co.polygon.model.PolicyRequestCommandModel;
import za.co.polygon.model.PolicyRequestQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationCommandModel;
import za.co.polygon.model.QuotationOptionQueryModel;
import za.co.polygon.model.QuotationQueryModel;
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
        for (QuotationCommandModel.Options options : quotationCommandModel.getOptions()) {
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

    public static PolicyRequestQueryModel toPolicyRequestQueryModel(PolicyRequest policyRequest) {

        PolicyRequestQueryModel policyRequestQueryModel = new PolicyRequestQueryModel();

        policyRequestQueryModel.setQuotation(toQuotationQueryModel(policyRequest.getQuotation()));
        policyRequestQueryModel.setQuotationOption(toQuotationOptionQueryModel(policyRequest.getQuotationOption()));
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

    public static List<PolicyRequestQueryModel> toPolicyRequestQueryModel(List<PolicyRequest> fromList) {
        List<PolicyRequestQueryModel> policyRequestList = new ArrayList<PolicyRequestQueryModel>();

        for (PolicyRequest policyRequest : fromList) {
            policyRequestList.add(toPolicyRequestQueryModel(policyRequest));
        }
        return policyRequestList;
    }

    public static SelectedQuotationQueryModel toSelectedQuotationQueryModel(Quotation quotation, QuotationOption quotationOption) {
        SelectedQuotationQueryModel selectedQuotationQueryModel = new SelectedQuotationQueryModel();
        selectedQuotationQueryModel.setQuotation(toQuotationQueryModel(quotation));
        selectedQuotationQueryModel.setSelectedQuotation(toQuotationOptionQueryModel(quotationOption));

        return selectedQuotationQueryModel;
    }

    public static Contact toContactCommandModel(ClientCommandModel clientCommandModel) {
        Contact contact = new Contact();

        contact.setCode(clientCommandModel.getContact().getCode());
        contact.setContactPerson(clientCommandModel.getContact().getContactPerson());
        contact.setEmail(clientCommandModel.getContact().getEmail());
        contact.setFaxNumber(clientCommandModel.getContact().getFaxNumber());
        contact.setStreet(clientCommandModel.getContact().getStreet());
        contact.setSuburb(clientCommandModel.getContact().getSuburb());
        contact.setWorkTelNumber(clientCommandModel.getContact().getWorkTelNumber());

        return contact;
    }

    public static BankAccount toBankAccountCommandModel(ClientCommandModel clientCommandModel) {
        BankAccount bankAccount = new BankAccount();

        bankAccount.setAccountName(clientCommandModel.getBankAccount().getAccountName());
        bankAccount.setAccountNumber(clientCommandModel.getBankAccount().getAccountNumber());
        bankAccount.setBankName(clientCommandModel.getBankAccount().getBankName());
        bankAccount.setBranchCode(clientCommandModel.getBankAccount().getBranch());
        bankAccount.setAccountType(clientCommandModel.getBankAccount().getAccountType());

        return bankAccount;
    }

    public static Client toClientDetailCommandModel(ClientCommandModel clientCommandModel, Contact contact, BankAccount bankAccount) {
        Client client = new Client();

        client.setContacts(contact);
        client.setBankAccount(bankAccount);
        client.setClientName(clientCommandModel.getClientName());
        client.setIncomeTaxNumber(clientCommandModel.getIncomeTaxNumber());
        client.setDesignation(clientCommandModel.getDesignation());
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
        bankAccountQueryModel.setAccountType(from.getAccountType());
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
        result.setDesignation(client.getDesignation());
        result.setRegNumber(client.getRegNumber());
        result.setVatNumber(client.getVatNumber());
        result.setBankAccounts(toBankAccountQueryModel(client.getBankAccount()));
        result.setContact(toContactQueryModel(client.getContact()));

        return result;
    }

    public static List<ClientQueryModel> toClientQueryModel(List<Client> fromList) {
        List<ClientQueryModel> clientList = new ArrayList<ClientQueryModel>();

        for (Client client : fromList) {
            clientList.add(toClientQueryModel(client));
        }
        return clientList;
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
        policyQueryModel.setReference(policy.getReference());
        policyQueryModel.setCollectByDebitOrder(policy.isCollectByDebitOrder());
        policyQueryModel.setBrokerFee(Double.toString(policy.getBrokerFee()));
        policyQueryModel.setDevice(policy.getDevice());
        policyQueryModel.setCollectByDebitOrder(policy.isCollectByDebitOrder());
        policyQueryModel.setExcludeSasria(policy.isExclude_sasria());
        policyQueryModel.setPolicyInceptionDate(new SimpleDateFormat("dd/MM/yyyy").format(policy.getPolicyInceptionDate()));
        policyQueryModel.setInceptionDate(new SimpleDateFormat("dd/MM/yyyy").format(policy.getInceptionDate()));
        policyQueryModel.setRenewalDate(new SimpleDateFormat("dd/MM/yyyy").format(policy.getRenewalDate()));
        policyQueryModel.setAnniversaryDate(new SimpleDateFormat("dd/MM/yyyy").format(policy.getAnniversaryDate()));
        policyQueryModel.setNotes(policy.getNotes());
        policyQueryModel.setProductName(policy.getProductName());
        policyQueryModel.setStatus(policy.getStatus());
        policyQueryModel.setReInstatement(policy.getReInstatement());
        policyQueryModel.setUnderwriterFee(Double.toString(policy.getUnderwriterFee()));
        policyQueryModel.setUnderwritingYear(policy.getUnderwriting_year());
        policyQueryModel.setFrequency(policy.getFrequency());
        policyQueryModel.setSasriaFrequency(policy.getSasriaFrequency());
        policyQueryModel.setBrokerCommission(policy.getBrokerCommission());
        policyQueryModel.setConveyances(policy.getConvenyances());
        policyQueryModel.setExcessStructure(policy.getExcessSturcture());
        policyQueryModel.setGeographicalDuration(policy.getGeographicalDuration());
        policyQueryModel.setMaximumSumInsured(policy.getMaximumSumInsured());
        policyQueryModel.setPremium(policy.getPremoium());
        policyQueryModel.setSasriaPremium(policy.getSasriaPremium());
        policyQueryModel.setScheduleAttaching(policy.getScheduleAttaching());
        policyQueryModel.setSpecialCondition(policy.getSpecialCondition());
        policyQueryModel.setSubjectMatter(policy.getSubjectMatter());
        policyQueryModel.setTypeOfCover(policy.getTypeOfCover());
        policyQueryModel.setSumInsured(policy.getSumInsured());
        policyQueryModel.setUACommission(policy.getUACommission());
        policyQueryModel.setSubAgent(toSubAgentQueryModel(policy.getSubAgent()));
        policyQueryModel.setClient(toClientQueryModel(policy.getClient()));
        policyQueryModel.setUnderwriter(toUnderwriterQueryModel(policy.getUnderwriter()));

        for (IndemnityOption indemnityOption : policy.getIndemnityOptions()) {
            policyQueryModel.getIndemnityOption().add(toIndemnityOptionQueryModel(indemnityOption));
        }

        return policyQueryModel;
    }

    public static IndemnityOptionQueryModel toIndemnityOptionQueryModel(IndemnityOption indemnityOption) {
        IndemnityOptionQueryModel indemnityOptionQueryModel = new IndemnityOptionQueryModel();

        indemnityOptionQueryModel.setId(indemnityOption.getId());
        indemnityOptionQueryModel.setIndemnityItemOption(indemnityOption.getIndemnityItemOption());
        indemnityOptionQueryModel.setIndemnityValue(indemnityOption.getIndemnityValue());
        indemnityOptionQueryModel.setPremium(Double.valueOf(indemnityOption.getPremium()));
        indemnityOptionQueryModel.setSumInsured(Double.valueOf(indemnityOption.getSumInsured()));

        return indemnityOptionQueryModel;
    }

    public static List<IndemnityOptionQueryModel> toIndemnityOptionListQueryModel(Policy policy) {
        List<IndemnityOptionQueryModel> indemnityOptionQueryModel = new ArrayList<IndemnityOptionQueryModel>();

        for (IndemnityOption indemnityOption : policy.getIndemnityOptions()) {
            indemnityOptionQueryModel.add(toIndemnityOptionQueryModel(indemnityOption));
        }
        return indemnityOptionQueryModel;
    }

    public static List<PolicyQueryModel> toPolicyQueryModel(List<Policy> fromPolicies) {
        List<PolicyQueryModel> policiesQueryResult = new ArrayList<PolicyQueryModel>();
        for (Policy policy : fromPolicies) {
            policiesQueryResult.add(toPolicyQueryModel(policy));
        }
        return policiesQueryResult;
    }

    public static BankAccount fromBankAccountCommandModel(PolicyCreationCommandModel policyCreationCommandModel) {
        BankAccount bankAccountResult = new BankAccount();

        bankAccountResult.setAccountName(policyCreationCommandModel.getClient().getBankAccount().getAccountName());
        bankAccountResult.setAccountNumber(policyCreationCommandModel.getClient().getBankAccount().getAccountNumber());
        bankAccountResult.setBranchCode(policyCreationCommandModel.getClient().getBankAccount().getBranch());
        bankAccountResult.setBankName(policyCreationCommandModel.getClient().getBankAccount().getBankName());
        bankAccountResult.setAccountType(policyCreationCommandModel.getClient().getBankAccount().getAccountType());

        return bankAccountResult;
    }

    public static Contact fromContactCommandModel(PolicyCreationCommandModel policyCreationCommandModel) {
        Contact contactResult = new Contact();

        contactResult.setCode(policyCreationCommandModel.getClient().getContact().getCode());
        contactResult.setContactPerson(policyCreationCommandModel.getClient().getContact().getContactPerson());
        contactResult.setEmail(policyCreationCommandModel.getClient().getContact().getEmail());
        contactResult.setFaxNumber(policyCreationCommandModel.getClient().getContact().getFaxNumber());
        contactResult.setStreet(policyCreationCommandModel.getClient().getContact().getStreet());
        contactResult.setSuburb(policyCreationCommandModel.getClient().getContact().getSuburb());
        contactResult.setWorkTelNumber(policyCreationCommandModel.getClient().getContact().getWorkTelNumber());

        return contactResult;
    }

    public static Client fromClientCommandModel(PolicyCreationCommandModel policyCreationCommandModel, Contact contact, BankAccount bankAccount) {
        Client clientResult = new Client();

        clientResult.setBankAccount(bankAccount);
        clientResult.setContact(contact);
        clientResult.setClientName(policyCreationCommandModel.getClient().getClientName());
        clientResult.setIncomeTaxNumber(policyCreationCommandModel.getClient().getIncomeTaxNumber());
        clientResult.setDesignation(policyCreationCommandModel.getClient().getDesignation());
        clientResult.setRegNumber(policyCreationCommandModel.getClient().getRegNumber());
        clientResult.setVatNumber(policyCreationCommandModel.getClient().getVatNumber());

        return clientResult;
    }

    public static Policy fromPolicyCreationCommandModel(PolicyCreationCommandModel policyCreationCommandModel, Client client, SubAgent subAgent, Underwriter underwriter, Contact contact, BankAccount bankAccount,int lastPolicyNumber) throws ParseException {
        Policy policyResult = new Policy();

        Calendar calendar = Calendar.getInstance();

        policyResult.setClient(client);
        policyResult.setSubAgent(subAgent);
        policyResult.setUnderwriter(underwriter);
        policyResult.setPolicyInceptionDate(new SimpleDateFormat("dd-MM-yyyy").parse(policyCreationCommandModel.getPolicyInceptionDate()));
        
        calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(policyCreationCommandModel.getPolicyInceptionDate()));
        calendar.add(Calendar.YEAR, 1);
        Date inceptionDate = calendar.getTime();
        policyResult.setInceptionDate(inceptionDate);
        
        calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(policyCreationCommandModel.getPolicyInceptionDate()));
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.MONTH, -1);
        Date renewalDate = calendar.getTime();
        policyResult.setRenewalDate(renewalDate);
        
        calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("MM-dd-yyy").parse(policyCreationCommandModel.getPolicyInceptionDate()));
        calendar.add(Calendar.YEAR, 1);
        Date anniversaryDate = calendar.getTime();
        policyResult.setAnniversaryDate(anniversaryDate);
        
        policyResult.setUnderwriting_year(policyCreationCommandModel.getUnderwritingYear());        
        policyResult.setProductName(policyCreationCommandModel.getProductName());
        policyResult.setBrokerFee(Double.parseDouble(policyCreationCommandModel.getBrokerFee()));
        policyResult.setDevice(policyCreationCommandModel.getDevice());
        policyResult.setExclude_sasria(policyCreationCommandModel.isExcludeSasria());
        policyResult.setCollectByDebitOrder(policyCreationCommandModel.isCollectByDebitOrder());
        LocalDateTime now = LocalDateTime.now();
        policyResult.setReference(now.getYear()+"-"+now.getMonthValue()+""+lastPolicyNumber);
        policyResult.setStatus(policyCreationCommandModel.getStatus());
        policyResult.setSasriaFrequency(policyCreationCommandModel.getSasriaFrequency());
        policyResult.setNotes(policyCreationCommandModel.getNotes());
        policyResult.setReInstatement(policyCreationCommandModel.getReInstatement());
        policyResult.setFrequency(policyCreationCommandModel.getFrequency());
        policyResult.setUnderwriterFee(policyCreationCommandModel.getUnderwriterFee());
        policyResult.setBrokerCommission(policyCreationCommandModel.getBrokerCommission());
        policyResult.setConvenyances(policyCreationCommandModel.getConveyances());
        policyResult.setExcessSturcture(policyCreationCommandModel.getExcessStructure());
        policyResult.setGeographicalDuration(policyCreationCommandModel.getGeographicalDuration());
        policyResult.setMaximumSumInsured(policyCreationCommandModel.getMaximumSumInsured());
        policyResult.setPremoium(policyCreationCommandModel.getPremium());
        policyResult.setSasriaPremium(policyCreationCommandModel.getSasriaPremium());
        policyResult.setScheduleAttaching(policyCreationCommandModel.getScheduleAttaching());
        policyResult.setSpecialCondition(policyCreationCommandModel.getSpecialCondition());
        policyResult.setSubjectMatter(policyCreationCommandModel.getSubjectMatter());
        policyResult.setTypeOfCover(policyCreationCommandModel.getTypeOfCover());
        policyResult.setSumInsured(policyCreationCommandModel.getSumInsured());
        policyResult.setUACommission(policyCreationCommandModel.getUACommission());

        List<IndemnityOption> indemnityOptionsList = new ArrayList<IndemnityOption>();
        for (PolicyCreationCommandModel.IndemnityOption options : policyCreationCommandModel.getIndemnityOption()) {

            IndemnityOption indemnityOption = new IndemnityOption();
            indemnityOption.setIndemnityItemOption(options.getIndemnityItemOption());
            indemnityOption.setIndemnityValue(options.getIndemnityValue());
            indemnityOption.setPremium(options.getPremium());
            indemnityOption.setSumInsured(options.getSumInsured());
            indemnityOption.setPolicy(policyResult);
            indemnityOptionsList.add(indemnityOption);

        }
        policyResult.setIndemnityOptions(indemnityOptionsList);
        return policyResult;
    }

    public static List<SubAgentQueryModel> toSubAgentQueryModel(List<SubAgent> subAgents) {
        List<SubAgentQueryModel> subAgentList = new ArrayList<SubAgentQueryModel>();
        for (SubAgent agent : subAgents) {
            subAgentList.add(toSubAgentQueryModel(agent));
        }
        return subAgentList;
    }

    public static Client toClientCommandModel(ClientQueryModel clientQueryModel, Client client) {

        client.setClientName(clientQueryModel.getClientName());
        client.setIncomeTaxNumber(clientQueryModel.getIncomeTaxNumber());
        client.setDesignation(clientQueryModel.getDesignation());
        client.setRegNumber(clientQueryModel.getRegNumber());
        client.setVatNumber(clientQueryModel.getVatNumber());
        BankAccount bankAccount = client.getBankAccount();
        bankAccount.setAccountName(clientQueryModel.getBankAccounts().getAccountName());
        bankAccount.setAccountNumber(clientQueryModel.getBankAccounts().getAccountNumber());
        bankAccount.setBankName(clientQueryModel.getBankAccounts().getBankName());
        bankAccount.setBranchCode(clientQueryModel.getBankAccounts().getBranch());
        bankAccount.setAccountType(clientQueryModel.getBankAccounts().getAccountType());

        Contact contact = client.getContact();
        contact.setCode(clientQueryModel.getContact().getCode());
        contact.setContactPerson(clientQueryModel.getContact().getContactPerson());
        contact.setEmail(clientQueryModel.getContact().getEmail());
        contact.setFaxNumber(clientQueryModel.getContact().getFaxNumber());
        contact.setStreet(clientQueryModel.getContact().getStreet());
        contact.setSuburb(clientQueryModel.getContact().getSuburb());
        contact.setWorkTelNumber(clientQueryModel.getContact().getWorkTelNumber());

        Client updatedClient = client;

        return updatedClient;
    }
    
    public static Policy toPolicyUpdateCommandModel(PolicyQueryModel policyQueryModel, Policy policy){
    	policy.setScheduleAttaching(policyQueryModel.getScheduleAttaching());
    	policy.setExcessSturcture(policyQueryModel.getExcessStructure());
    	policy.setConvenyances(policyQueryModel.getConveyances());
    	policy.setGeographicalDuration(policyQueryModel.getGeographicalDuration());
    	policy.setNotes(policyQueryModel.getNotes());
    	policy.setExclude_sasria(new Boolean(policyQueryModel.getSasriaFrequency()));
    	List<IndemnityOption> indemnityOptions = new ArrayList<IndemnityOption>();
    	int i=0;
    	for(IndemnityOption indemnityOption: policy.getIndemnityOptions()){
    		indemnityOption.setIndemnityItemOption(policyQueryModel.getIndemnityOption().get(i).getIndemnityItemOption());
    		indemnityOption.setIndemnityValue(policyQueryModel.getIndemnityOption().get(i).getIndemnityValue());
    		indemnityOption.setPremium(policyQueryModel.getIndemnityOption().get(i).getPremium());
    		indemnityOption.setSumInsured(policyQueryModel.getIndemnityOption().get(i).getSumInsured());
    		indemnityOptions.add(indemnityOption);
    		
    		i++;    		
    	}
    	policy.setIndemnityOptions(indemnityOptions);
    	
    	Policy updatedPolicy = policy;
    	
    	return updatedPolicy;
    }

    public static ClaimTypeQueryModel toClaimTypeQueryModel(ClaimType from) {
        ClaimTypeQueryModel claimTypeQueryModel = new ClaimTypeQueryModel();
        claimTypeQueryModel.setId(from.getId());
        claimTypeQueryModel.setClaimType(from.getClaimType());

        return claimTypeQueryModel;
    }

    public static List<ClaimTypeQueryModel> toClaimTypeQueryModel(List<ClaimType> fromList) {
        List<ClaimTypeQueryModel> claimTypeQueryModels = new ArrayList<ClaimTypeQueryModel>();
        for (ClaimType claimType : fromList) {
            claimTypeQueryModels.add(toClaimTypeQueryModel(claimType));
        }
        return claimTypeQueryModels;
    }

    public static ClaimQuestionnaireQuery toClaimQuestionnaireQueryModel(ClaimQuestionnaire from) {
        ClaimQuestionnaireQuery claimQuestionnaireQuery = new ClaimQuestionnaireQuery();
        claimQuestionnaireQuery.setId(from.getId());
        claimQuestionnaireQuery.setQuestion(from.getQuestion());
        claimQuestionnaireQuery.setSequenceNumber(from.getSequenceNumber());
        claimQuestionnaireQuery.setClaimAnswerType(from.getClaimAnswerType().getClaimAnswerType());
        claimQuestionnaireQuery.setIsRequired(from.getIsRequired());

        for (ClaimAnswerValue claimAnswerValue : from.getClaimAnswerValue()) {
            claimQuestionnaireQuery.getClaimAnswerValues().add(claimAnswerValue.getClaimAnswerValue());
        }

        return claimQuestionnaireQuery;
    }
    
        public static List<ClaimQuestionnaireQuery> toClaimQuestionnaireQueryModel(List<ClaimQuestionnaire> fromList) {
        List<ClaimQuestionnaireQuery> claimQuestionnaireQuerys = new ArrayList<ClaimQuestionnaireQuery>();
        for (ClaimQuestionnaire claimQuestionnaire : fromList) {
            claimQuestionnaireQuerys.add(toClaimQuestionnaireQueryModel(claimQuestionnaire));
        }
        return claimQuestionnaireQuerys;
    }
}
