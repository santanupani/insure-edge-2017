package za.co.polygon;

import static za.co.polygon.mapper.Mapper.fromBankAccountCommandModel;
import static za.co.polygon.mapper.Mapper.fromClaimRequestCommandModel;
import static za.co.polygon.mapper.Mapper.fromClientCommandModel;
import static za.co.polygon.mapper.Mapper.fromContactCommandModel;
import static za.co.polygon.mapper.Mapper.fromHistoryCommandModel;
import static za.co.polygon.mapper.Mapper.fromLocationOptionsCommandModel;
import static za.co.polygon.mapper.Mapper.fromPolicyCreationCommandModel;
import static za.co.polygon.mapper.Mapper.fromPolicyRequestTypeCommandModel;
import static za.co.polygon.mapper.Mapper.fromQuotationRequestCommandModel;
import static za.co.polygon.mapper.Mapper.fromQuotationUpdateCommandModel;
import static za.co.polygon.mapper.Mapper.toBrokerQueryModel;
import static za.co.polygon.mapper.Mapper.toClaimQuestionnaireQueryModel;
import static za.co.polygon.mapper.Mapper.toClaimRequest;
import static za.co.polygon.mapper.Mapper.toClaimRequestQueryModel;
import static za.co.polygon.mapper.Mapper.toClaimTypeQueryModel;
import static za.co.polygon.mapper.Mapper.toClientCommandModel;
import static za.co.polygon.mapper.Mapper.toClientQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyRequest;
import static za.co.polygon.mapper.Mapper.toPolicyRequestQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyRequestTypeQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyUpdateCommandModel;
import static za.co.polygon.mapper.Mapper.toProductQueryModel;
import static za.co.polygon.mapper.Mapper.toQuestionnaireQueryModel;
import static za.co.polygon.mapper.Mapper.toQuotationQueryModel;
import static za.co.polygon.mapper.Mapper.toQuotationRequest;
import static za.co.polygon.mapper.Mapper.toQuotationRequestQueryModel;
import static za.co.polygon.mapper.Mapper.toRequestQuestionnaireQueryModel;
import static za.co.polygon.mapper.Mapper.toRequestTypeQueryModel;
import static za.co.polygon.mapper.Mapper.toSelectedQuotationQueryModel;
import static za.co.polygon.mapper.Mapper.toSubAgentQueryModel;
import static za.co.polygon.mapper.Mapper.toUserQueryModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.sf.jasperreports.engine.JRException;
import za.co.polygon.domain.Answer;
import za.co.polygon.domain.BankAccount;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.ClaimAnswer;
import za.co.polygon.domain.ClaimQuestionnaire;
import za.co.polygon.domain.ClaimRequest;
import za.co.polygon.domain.ClaimType;
import za.co.polygon.domain.Client;
import za.co.polygon.domain.Contact;
import za.co.polygon.domain.History;
import za.co.polygon.domain.LocationOption;
import za.co.polygon.domain.Policy;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.PolicyRequestType;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.domain.RequestAnswer;
import za.co.polygon.domain.RequestQuestionnaire;
import za.co.polygon.domain.RequestType;
import za.co.polygon.domain.SubAgent;
import za.co.polygon.domain.Underwriter;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.ClaimQuestionnaireQuery;
import za.co.polygon.model.ClaimRequestCommandModel;
import za.co.polygon.model.ClaimRequestQueryModel;
import za.co.polygon.model.ClaimTypeQueryModel;
import za.co.polygon.model.ClientQueryModel;
import za.co.polygon.model.PolicyCreationCommandModel;
import za.co.polygon.model.PolicyQueryModel;
import za.co.polygon.model.PolicyRequestCommandModel;
import za.co.polygon.model.PolicyRequestQueryModel;
import za.co.polygon.model.PolicyRequestTypeCommandModel;
import za.co.polygon.model.PolicyRequestTypeQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationCommandModel;
import za.co.polygon.model.QuotationQueryModel;
import za.co.polygon.model.QuotationRequestCommandModel;
import za.co.polygon.model.QuotationRequestQueryModel;
import za.co.polygon.model.RequestQuestionnaireQueryModel;
import za.co.polygon.model.RequestTypeQueryModel;
import za.co.polygon.model.SelectedQuotationQueryModel;
import za.co.polygon.model.SubAgentQueryModel;
import za.co.polygon.model.UserQueryModel;
import za.co.polygon.repository.BankAccountRepository;
import za.co.polygon.repository.BrokerRepository;
import za.co.polygon.repository.ClaimQuestionnaireRepository;
import za.co.polygon.repository.ClaimRequestQuestionnaireRepository;
import za.co.polygon.repository.ClaimRequestRepository;
import za.co.polygon.repository.ClaimTypeRepository;
import za.co.polygon.repository.ClientRepository;
import za.co.polygon.repository.ContactRepository;
import za.co.polygon.repository.HistoryRepository;
import za.co.polygon.repository.LocationOptionRepository;
import za.co.polygon.repository.PolicyRepository;
import za.co.polygon.repository.PolicyRequestRepository;
import za.co.polygon.repository.PolicyRequestTypeRepository;
import za.co.polygon.repository.ProductRepository;
import za.co.polygon.repository.QuestionnaireRepository;
import za.co.polygon.repository.QuotationOptionRepository;
import za.co.polygon.repository.QuotationRepository;
import za.co.polygon.repository.QuotationRequestQuestionnaireRepository;
import za.co.polygon.repository.QuotationRequestRepository;
import za.co.polygon.repository.RequestAnswersRepository;
import za.co.polygon.repository.RequestTypeRepository;
import za.co.polygon.repository.SubAgentRepository;
import za.co.polygon.repository.UnderwriterRepository;
import za.co.polygon.repository.UserRepository;
import za.co.polygon.service.DocumentService;
import za.co.polygon.service.NotificationService;

@RestController
public class Service {

	private static final Logger log = LoggerFactory.getLogger(Service.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private LocationOptionRepository locationOptionRepository;

	@Autowired
	private QuestionnaireRepository questionnaireRepository;

	@Autowired
	private BrokerRepository brokerRepository;

	@Autowired
	private QuotationRequestRepository quotationRequestRepository;

	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	private QuotationRequestQuestionnaireRepository quotationRequestQuestionnaireRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private QuotationRepository quotationRepository;

	@Autowired
	private QuotationOptionRepository quotationOptionRepository;

	@Autowired
	private PolicyRequestRepository policyRequestRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PolicyRepository policyRepository;

	@Autowired
	private RequestTypeRepository requestTypeRepository;

	@Autowired
	private PolicyRequestTypeRepository policyRequestTypeRepository;

	@Autowired
	private DocumentService reportService;

	@Autowired
	private UnderwriterRepository underwriterRepository;

	@Autowired
	private SubAgentRepository subAgentRepository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private ClaimTypeRepository claimTypeRepository;

	@Autowired
	private ClaimQuestionnaireRepository claimQuestionnaireRepository;

	@Autowired
	private ClaimRequestRepository claimRequestRepository;

	@Autowired
	private ClaimRequestQuestionnaireRepository claimRequestQuestionnaireRepository;

	@Autowired
	private RequestAnswersRepository requestAnswersRepository;

	@Autowired
	private DocumentService documentService;

	@RequestMapping(value = "api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<UserQueryModel> findAllUsers() {
		log.info("find user");
		List<za.co.polygon.domain.User> users = userRepository.findAll();
		List<UserQueryModel> result = toUserQueryModel(users);
		log.info("found user, size:{}", result.size());
		log.info("this service to get all users");
		return result;
	}

	@RequestMapping(value = "api/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductQueryModel> findAllProducts() {
		log.info("find all products");
		List<Product> products = productRepository.findAll();
		log.info("found all products - size:{}", products.size());
		return toProductQueryModel(products);
	}


	@RequestMapping(value = "api/policy-requests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PolicyRequestQueryModel> getAllPolicyRequests(){
		log.info("Find all Policy Requests");
		List<PolicyRequest> policyRequests = policyRequestRepository.findAll();
		List<PolicyRequestQueryModel> policyRequestQueryModel = toPolicyRequestQueryModel(policyRequests);

		return  policyRequestQueryModel;
	}

	/*The all the policies*/
	@RequestMapping(value = "api/policies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PolicyQueryModel> getPolicies() {
		log.info("Find all policies");
		List<Policy> policy = policyRepository.findAll();
		List<PolicyQueryModel> policies =  toPolicyQueryModel(policy);
		log.info("Number of policies returned: "+policies.size());
		return policies;
	}

	@Transactional
	@RequestMapping(value = "api/policy", method = RequestMethod.POST)
	public String createPolicy(@RequestBody PolicyCreationCommandModel policyCreationCommandModel) throws IOException, ParseException, JRException {

		Underwriter underwriter = underwriterRepository.findOne(policyCreationCommandModel.getUnderwriterId());
		SubAgent subAgent = subAgentRepository.findOne(policyCreationCommandModel.getSubAgentId());
		BankAccount bankAccount = bankAccountRepository.save(fromBankAccountCommandModel(policyCreationCommandModel));
		Contact contact = contactRepository.save(fromContactCommandModel(policyCreationCommandModel));
		Client client = clientRepository.save(fromClientCommandModel(policyCreationCommandModel, contact, bankAccount));
		int lastPolicyNumber = policyRepository.findAll().size();
		Policy policy = policyRepository.save(fromPolicyCreationCommandModel(policyCreationCommandModel, client, subAgent, underwriter, contact, bankAccount,lastPolicyNumber));
		return policy.getReference();
	}

	@Transactional
	@RequestMapping(value = "api/policy-update/{reference}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updatePolicy(@PathVariable("reference") String reference, @RequestBody PolicyQueryModel PolicyQueryModel) throws ParseException {
		log.info("Updating Policy with reference: "+reference);
		Policy policy = policyRepository.findByReference(reference);
		SubAgent subAgent = subAgentRepository.findOne(PolicyQueryModel.getSubAgent().getId());
		log.info("Product Name: "+policy.getProductName());
		policyRepository.save(toPolicyUpdateCommandModel(PolicyQueryModel,policy,subAgent));
	}

	@RequestMapping(value = "api/sub-agents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SubAgentQueryModel> getSubAgents() {
		log.info("Find all sub agents");
		List<SubAgent> subAgent = subAgentRepository.findAll();
		List<SubAgentQueryModel> subAgents =  toSubAgentQueryModel(subAgent);
		return subAgents;
	}



	@Transactional
	@RequestMapping(value = "api/client/{clientId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
	public void updateClient(@PathVariable("clientId") Long clientId, @RequestBody ClientQueryModel clientQueryModel) throws ParseException {

		Client client = clientRepository.findOne(clientId);

		clientRepository.save(toClientCommandModel(clientQueryModel, client));
	}

	@RequestMapping(value = "api/policy-schedules/{reference}", method = RequestMethod.GET,produces="application/pdf")
	public byte[] generatePolicySchedule(@PathVariable("reference") String reference) throws JRException, IOException{
		Policy policy = policyRepository.findByReference(reference);
		return documentService.policyScheduleReportPDF(policy);

	}

	@RequestMapping(value = "api/quotation-request-pdf/{reference}", method = RequestMethod.GET,produces="application/pdf")
	public byte[] viewQuotationPDF(@PathVariable("reference") String reference) throws JRException, IOException{
		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
		Quotation quotation  = quotationRepository.findByQuotationRequest(quotationRequest);
		return documentService.generateQuotationPDF(quotation);

	}

	@RequestMapping(value = "api/products/{productId}/questionnaires", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<QuestionnaireQuery> findQuestionnaires(@PathVariable("productId") Long productId) {
		log.info("find questionnaires for product - productId:{}", productId);
		List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
		Product product = productRepository.findOne(productId);
		if (product != null) {
			questionnaires = questionnaireRepository.findByProduct(product);
		}
		log.info("found questionnaires for product - productId:{}, size:{}", productId, questionnaires.size());
		List<QuestionnaireQuery> r = toQuestionnaireQueryModel(questionnaires);
		log.info("mapping done....");
		return r;
	}

	@RequestMapping(value = "api/brokers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<BrokerQueryModel> findAllBrokers() {
		log.info("find all brokers");
		List<Broker> brokers = brokerRepository.findAll();
		log.info("found all products - size:{}", brokers.size());
		return toBrokerQueryModel(brokers);
	}

	@Transactional
	@RequestMapping(value = "api/quotation-requests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
	public String createQuotationRequest(@RequestBody QuotationRequestCommandModel quotationRequestCommandModel) {
		Broker broker = brokerRepository.findOne(quotationRequestCommandModel.getBrokerId());

		Product product = productRepository.findOne(quotationRequestCommandModel.getProductId());

		QuotationRequest quotationRequest = toQuotationRequest(quotationRequestCommandModel, broker, product,quotationRequestRepository.findAll().size());
		quotationRequest = quotationRequestRepository.save(quotationRequest);
		List<Answer> quotationRequestQuestionnaires = fromQuotationRequestCommandModel(quotationRequestCommandModel, quotationRequest);
		quotationRequestQuestionnaireRepository.save(quotationRequestQuestionnaires);
		List<LocationOption> locationOptions = fromLocationOptionsCommandModel(quotationRequestCommandModel, quotationRequest);
		locationOptionRepository.save(locationOptions);
		if(quotationRequestCommandModel.getHistories() != null){
			List<History> histories = fromHistoryCommandModel(quotationRequestCommandModel, quotationRequest);
			historyRepository.save(histories);
		}
		notificationService.sendNotificationForNewQuotationRequest(quotationRequest, broker);
		log.info("Quotation Request Created. reference : {} ", quotationRequest.getReference());
		return quotationRequest.getReference();
	}

	@RequestMapping(value = "api/quotation-requests/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public QuotationRequestQueryModel getQuotationRequest(@PathVariable("reference") String reference) throws ParseException {
		log.info("find all the questions and answers inserted for a product");
		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
		log.info("find all the questions and answers inserted for a product using the reference");
		return toQuotationRequestQueryModel(quotationRequest);
	}

	@Transactional
	@RequestMapping(value = "api/quotation-requests/{reference}/reject", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
	public void rejectQuotationRequest(@PathVariable("reference") String reference, @RequestBody Map<String, String> reason) {
		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
		quotationRequest.setStatus("REJECTED");
		notificationService.sendNotificationForRejectQuotationRequest(quotationRequest, reason.get("reason"));
		quotationRequestRepository.save(quotationRequest);
		log.info("New status :" + quotationRequest.getStatus());
	}

	@Transactional
	@RequestMapping(value = "api/quotations", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createNewQuotation(@RequestBody QuotationCommandModel quotationCommandModel) throws DocumentException, FileNotFoundException, IOException, JRException {
		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(quotationCommandModel.getReference());
		quotationRequest.setStatus("ACCEPTED");
		Quotation quotation = fromQuotationRequestCommandModel(quotationCommandModel, quotationRequest);
		quotation = quotationRepository.save(quotation);
		log.info("Quotation Command size: " + quotation.getQuotationOptions().size());
		log.info("Quotation Created Successfully !!!");
		
	}
	
	@Transactional
	@RequestMapping(value = "api/quotation-update/{reference}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateQuotation(@PathVariable("reference") String reference, @RequestBody QuotationCommandModel quotationCommandModel) throws ParseException {
		log.info("Updating Quotation with reference: "+reference);
		
		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(quotationCommandModel.getReference());
		Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);
		List<QuotationOption> quotationOptions = fromQuotationUpdateCommandModel(quotationCommandModel,quotation);
		quotationOptionRepository.save(quotationOptions);
		log.info("QuotationOptions updated successfully : "+quotationOptions.size());
		
	}
	
	@RequestMapping(value = "api/quotation-submit/{reference}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void submitNewQuotation(@PathVariable("reference") String reference) throws DocumentException, FileNotFoundException, IOException, JRException {
		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);

		byte[] data = reportService.generateQuotationPDF(quotationRepository.findByQuotationRequest(quotationRequest));
		notificationService.sendNotificationForAcceptQuotationRequest(quotationRequest, data);
		
		log.info("Quotation Created Successfully !!!");
	}

	@RequestMapping(value = "api/quotations/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public QuotationQueryModel getQuotation(@PathVariable("reference") String reference) {

		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);

		if (quotationRequest.getStatus().equals("ACCEPTED")) {

			Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);

			log.info("Number of quotation options for this quotation: " + quotationRepository.count());
			log.info("Quotation request size: " + quotation.getQuotationOptions().size());

			log.info("find all the quotation details inserted for a product using the reference");
			return toQuotationQueryModel(quotation);

		}
		throw new RuntimeException("Quotation has not been Accepted yet");
	}

	@RequestMapping(value = "api/quotations/{reference}/{quotationOptionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SelectedQuotationQueryModel getQuotationSelected(@PathVariable("reference") String reference, @PathVariable("quotationOptionId") String optionId) {

		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
		QuotationOption quotationOption = quotationOptionRepository.findOne(Long.parseLong(optionId));

		if (quotationRequest.getStatus().equals("ACCEPTED")) {

			Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);

			log.info("Number of quotation options for this quotation: " + quotationRepository.count());
			log.info("Quotation request size: " + quotation.getQuotationOptions().size());

			log.info("find all the quotation details inserted for a product using the reference");
			return toSelectedQuotationQueryModel(quotation, quotationOption);

		}
		throw new RuntimeException("Quotation has not been Accepted yet");
	}

	@Transactional
	@RequestMapping(value = "api/policy-requests", method = RequestMethod.POST)
	public void createPolicyRequest(@RequestPart(value = "policyRequest") PolicyRequestCommandModel policyRequestCommandModel, @RequestPart(value = "file") MultipartFile file) throws IOException {

		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(policyRequestCommandModel.getReference());

		Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);

		PolicyRequest policyRequest = toPolicyRequest(policyRequestCommandModel, quotation);
		policyRequest.setBankStatement(file.getBytes());
		policyRequest = policyRequestRepository.save(policyRequest);

		log.info("saved all the values");
		log.info("Policy Request Object :" + policyRequest.toString());

		notificationService.sendNotificationForNewPolicyRequest(policyRequest, file, "polygon.testing@gmail.com", "Polygon Underwritter");
	}

	@RequestMapping(value = "api/policy-requests/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PolicyRequestQueryModel getPolicyRequest(@PathVariable("reference") String reference) {

		QuotationRequest quotationtRequest = quotationRequestRepository.findByReference(reference);
		Quotation quotation = quotationRepository.findByQuotationRequest(quotationtRequest);
		log.info("Quotation object: " + quotation.toString());
		PolicyRequest policyRequest = policyRequestRepository.findByQuotation(quotation);
		log.info("PolicyRequest object: " + policyRequest.toString());
		
		return toPolicyRequestQueryModel(policyRequest);

	}

	@Transactional
	@RequestMapping(value = "api/policy-requests/{reference}/reject", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
	public void rejectPolicyRequest(@PathVariable("reference") String reference, @RequestBody Map<String, String> reason) {
		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
		Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);
		PolicyRequest policyRequest = policyRequestRepository.findByQuotation(quotation);
		policyRequest.setStatus("REJECTED");
		notificationService.sendNotificationForRejectPolicyRequest(policyRequest, reason.get("reason"));
		policyRequestRepository.save(policyRequest);
		log.info("New status :" + policyRequest.getStatus());
		log.info("Policy Request Rejected succes");
	}

	@RequestMapping(value = "api/quotations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<QuotationQueryModel> findAllQuotations() {
		log.info("find all quotations");
		List<Quotation> quotation = quotationRepository.findAll();
		log.info("found all quotations - size:{}", quotation.size());
		return toQuotationQueryModel(quotation);
	}
        
	@RequestMapping(value = "api/quotation-requests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<QuotationRequestQueryModel> findAllQuotationRequest() {
		log.info("find all quotations");
		List<QuotationRequest> quotationRequests = quotationRequestRepository.findAll();
		log.info("found all quotations - size:{}", quotationRequests.size());
		return toQuotationRequestQueryModel(quotationRequests);
	}

	@RequestMapping(value = "api/clients/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientQueryModel getClient(@PathVariable("clientId") Long clientId) {

		Client client = clientRepository.findOne(clientId);
		log.info("client details");
		return toClientQueryModel(client);
	}

	@RequestMapping(value = "api/clients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClientQueryModel> findAllClients() {
		log.info("Find all Clients");
		List<Client> client = clientRepository.findAll();
		log.info("Found all Cleints - size : ", client.size());
		return toClientQueryModel(client);
	}

	/*The get service to return the policy details per specific policy ID*/
	@RequestMapping(value = "api/policy/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PolicyQueryModel getPolicy(@PathVariable("reference") String reference) {
		log.info("Find the details of specific policy");
		Policy policy = policyRepository.findByReference(reference);

		return toPolicyQueryModel(policy);
	}

	@RequestMapping(value = "api/claim-types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClaimTypeQueryModel> findAllClaimTypes() {
		log.info("find all claim Types");
		List<ClaimType> claimType = claimTypeRepository.findAll();
		log.info("Found all claim types");

		return toClaimTypeQueryModel(claimType);
	}


	@RequestMapping(value = "api/claim-types/{claimTypeId}/claim-questionnaires", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClaimQuestionnaireQuery> findClaimQuestionnaires(@PathVariable("claimTypeId") Long claimTypeId) {

		log.info("Find all Claim questionaire for a claim type");
		List<ClaimQuestionnaire> claimQuestionnaire = new ArrayList<ClaimQuestionnaire>();

		ClaimType claimType = claimTypeRepository.findOne(claimTypeId);

		if (claimType != null) {
			claimQuestionnaire = claimQuestionnaireRepository.findByClaimType(claimType);
		}
		log.info("found all the questionnaires for claim type");
		List<ClaimQuestionnaireQuery> r = toClaimQuestionnaireQueryModel(claimQuestionnaire);

		return r;
	}

	@RequestMapping(value = "api/claim-requests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClaimRequestQueryModel> getAllClaimRequests() {
		log.info("Find all claim Requests");
		List<ClaimQuestionnaire> claimQuestionnaires = claimQuestionnaireRepository.findAll();
		List<ClaimRequest> claimRequest = claimRequestRepository.findAll();
		List<ClaimRequestQueryModel> claimRequestQueryModels = toClaimRequestQueryModel(claimRequest, claimQuestionnaires);
		log.info("found all claim requests");
		return claimRequestQueryModels;
	}

	@RequestMapping(value = "api/claim-requests/{claimNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ClaimRequestQueryModel getClaimRequest(@PathVariable("claimNumber") String claimNumber) {
		log.info("find claim");
		List<ClaimQuestionnaire> claimQuestionnaires = claimQuestionnaireRepository.findAll();
		ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
		log.info("found claim by claim number");
		return toClaimRequestQueryModel(claimRequest, claimQuestionnaires);
	}

	@Transactional
	@RequestMapping(value = "api/claim-requests", method = RequestMethod.POST)
	public String createClaimRequest(@RequestPart(value = "claimRequest") ClaimRequestCommandModel claimRequestCommandModel, @RequestPart("file") MultipartFile[]  file) throws IOException {

		Policy policy = policyRepository.findByReference(claimRequestCommandModel.getReference());

		ClaimType claimType = claimTypeRepository.findOne(claimRequestCommandModel.getClaimTypeId());
		List<ClaimQuestionnaire> claimQuestionnaires = claimQuestionnaireRepository.findAll();

		ClaimRequest claimRequest = toClaimRequest(claimRequestCommandModel, policy, claimType, claimQuestionnaires,file);
		claimRequest = claimRequestRepository.save(claimRequest);

		List<ClaimAnswer> claimRequestQuestionnaires = fromClaimRequestCommandModel(claimRequestCommandModel, claimRequest);

		claimRequestQuestionnaireRepository.save(claimRequestQuestionnaires);

		notificationService.sendNotificationForNewClaimRequest(claimRequest, "polygon.testing@gmail.com", "Polygon Claims Department");

		return claimRequest.getClaimNumber();
	}

	@RequestMapping(value = "api/request-types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RequestTypeQueryModel> findAllRequestTypes() {
		log.info("find all request Types");
		List<RequestType> requestType = requestTypeRepository.findAll();
		log.info("Found all request types");

		return toRequestTypeQueryModel(requestType);
	}

	@RequestMapping(value = "api/request-type/{requestTypeId}/request-questionnaire", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RequestQuestionnaireQueryModel> findRequestTypeQuestionnaires(@PathVariable("requestTypeId") Long requestTypeId) {

		log.info("Find all questionaire for a request type");
		RequestType requestType = requestTypeRepository.findOne(requestTypeId);
		List<RequestQuestionnaire> requestQuestionnaire = requestType.getRequestQuestionnaire();

		return toRequestQuestionnaireQueryModel(requestQuestionnaire);
	}


	@Transactional
	@RequestMapping(value = "api/generic-policy-requests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String createGenericPolicyRequest(@RequestBody PolicyRequestTypeCommandModel policyRequestTypeCommandModel) throws ParseException{

		Policy policy = policyRepository.findByReference(policyRequestTypeCommandModel.getPolicyNo());
		RequestType requestType = requestTypeRepository.findOne(policyRequestTypeCommandModel.getRequestTypeId());
		PolicyRequestType policyRequestType = fromPolicyRequestTypeCommandModel(policyRequestTypeCommandModel,policy,requestType);
		PolicyRequestType savePolicyRequestType = policyRequestTypeRepository.save(policyRequestType);

		List<RequestAnswer> requestAnswers = fromPolicyRequestTypeCommandModel(policyRequestTypeCommandModel, policyRequestType);

		requestAnswersRepository.save(requestAnswers);

		notificationService.sendNotificationForNewGenericPolicyRequest(savePolicyRequestType,"polygon.testing@gmail.com");
		log.info("Generic policy request created Successfully !!!");
		return savePolicyRequestType.getReference();
	}

	@RequestMapping(value = "api/generic-policy-request/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PolicyRequestTypeQueryModel getGenericPolicyRequest(@PathVariable("reference") String reference) {
		log.info("Find the details of specific generic policy");
		PolicyRequestType policyRequestType = policyRequestTypeRepository.findByReference(reference);

		return toPolicyRequestTypeQueryModel(policyRequestType);
	}
}
