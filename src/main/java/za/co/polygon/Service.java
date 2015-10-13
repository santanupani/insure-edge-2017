package za.co.polygon;

import static za.co.polygon.mapper.Mapper.fromBankAccountCommandModel;
import static za.co.polygon.mapper.Mapper.fromClientCommandModel;
import static za.co.polygon.mapper.Mapper.fromContactCommandModel;
import static za.co.polygon.mapper.Mapper.fromPolicyCreationCommandModel;
import static za.co.polygon.mapper.Mapper.fromQuotationRequestCommandModel;
import static za.co.polygon.mapper.Mapper.toBrokerQueryModel;
import static za.co.polygon.mapper.Mapper.toClientCommandModel;
import static za.co.polygon.mapper.Mapper.toClientQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyRequest;
import static za.co.polygon.mapper.Mapper.toPolicyRequestQueryModel;
import static za.co.polygon.mapper.Mapper.toProductQueryModel;
import static za.co.polygon.mapper.Mapper.toQuestionnaireQueryModel;
import static za.co.polygon.mapper.Mapper.toQuotationQueryModel;
import static za.co.polygon.mapper.Mapper.toQuotationRequest;
import static za.co.polygon.mapper.Mapper.toQuotationRequestQueryModel;
import static za.co.polygon.mapper.Mapper.toSelectedQuotationQueryModel;
import static za.co.polygon.mapper.Mapper.toSubAgentQueryModel;
import static za.co.polygon.mapper.Mapper.toUserQueryModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

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
import za.co.polygon.domain.Client;
import za.co.polygon.domain.Contact;
import za.co.polygon.domain.Policy;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.domain.SubAgent;
import za.co.polygon.domain.Underwriter;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.ClientQueryModel;
import za.co.polygon.model.PolicyCreationCommandModel;
import za.co.polygon.model.PolicyQueryModel;
import za.co.polygon.model.PolicyRequestCommandModel;
import za.co.polygon.model.PolicyRequestQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationCommandModel;
import za.co.polygon.model.QuotationQueryModel;
import za.co.polygon.model.QuotationRequestCommandModel;
import za.co.polygon.model.QuotationRequestQueryModel;
import za.co.polygon.model.SelectedQuotationQueryModel;
import za.co.polygon.model.SubAgentQueryModel;
import za.co.polygon.model.UserQueryModel;
import za.co.polygon.repository.BankAccountRepository;
import za.co.polygon.repository.BrokerRepository;
import za.co.polygon.repository.ClientRepository;
import za.co.polygon.repository.ContactRepository;
import za.co.polygon.repository.PolicyRepository;
import za.co.polygon.repository.PolicyRequestRepository;
import za.co.polygon.repository.ProductRepository;
import za.co.polygon.repository.QuestionnaireRepository;
import za.co.polygon.repository.QuotationOptionRepository;
import za.co.polygon.repository.QuotationRepository;
import za.co.polygon.repository.QuotationRequestQuestionnaireRepository;
import za.co.polygon.repository.QuotationRequestRepository;
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
	private QuestionnaireRepository questionnaireRepository;

	@Autowired
	private BrokerRepository brokerRepository;

	@Autowired
	private QuotationRequestRepository quotationRequestRepository;

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

	@RequestMapping(value = "api/products/{productId}/questionnaires", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<QuestionnaireQuery> findQuestionnaires(@PathVariable("productId") Long productId) {
		log.info("find questionnaires for product - productId:{}", productId);
		List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
		Product product = productRepository.findOne(productId);
		if (product != null) {
			questionnaires = questionnaireRepository.findByProduct(product);
		}
		log.info("found questionnaires for product - productId:{}, size:{}", productId, questionnaires.size());
		List<QuestionnaireQuery> r  = toQuestionnaireQueryModel(questionnaires);
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

		QuotationRequest quotationRequest = toQuotationRequest(quotationRequestCommandModel, broker, product);
		quotationRequest = quotationRequestRepository.save(quotationRequest);

		List<Answer> quotationRequestQuestionnaires = fromQuotationRequestCommandModel(quotationRequestCommandModel, quotationRequest);
		quotationRequestQuestionnaireRepository.save(quotationRequestQuestionnaires);

		notificationService.sendNotificationForNewQuotationRequest(quotationRequest, broker);

		log.info("Quotation Request Created. reference : {} ", quotationRequest.getReference());
		return quotationRequest.getReference();
	}

	@RequestMapping(value = "api/quotation-requests/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public QuotationRequestQueryModel getQuotationRequest(@PathVariable("reference") String reference) {
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
		byte[] data = reportService.generateQuotationPDF(quotation);
		notificationService.sendNotificationForAcceptQuotationRequest(quotation.getQuotationRequest(), data);
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
			return toSelectedQuotationQueryModel(quotation,quotationOption);

		}
		throw new RuntimeException("Quotation has not been Accepted yet");
	}

	@Transactional
	@RequestMapping(value = "api/policy-requests", method = RequestMethod.POST)
	public void createPolicyRequest(@RequestPart(value = "policyRequest") PolicyRequestCommandModel policyRequestCommandModel, @RequestPart(value = "file") MultipartFile file) throws IOException {

		QuotationRequest quotationRequest = quotationRequestRepository.findByReference(policyRequestCommandModel.getReference());

		Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);

		QuotationOption quotationOption = quotationOptionRepository.findOne(policyRequestCommandModel.getQuotationOptionId());

		PolicyRequest policyRequest = toPolicyRequest(policyRequestCommandModel, quotation, quotationOption);
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
		QuotationOption quotationOption = quotationOptionRepository.findOne(policyRequest.getQuotationOption().getId());
		log.info("QuotationOption object: " + quotationOption.toString() + "\nSelected Option ID: " + quotationOption.getId());
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

	@RequestMapping(value = "api/clients/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ClientQueryModel getClient(@PathVariable("clientId") Long clientId) {

		Client client = clientRepository.findOne(clientId);
		log.info("client details");
		return toClientQueryModel(client);
	}

	@RequestMapping(value = "api/clients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ClientQueryModel> findAllClients(){
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
		Policy policy = policyRepository.save(fromPolicyCreationCommandModel(policyCreationCommandModel, client, subAgent, underwriter, contact, bankAccount));
		documentService.policyScheduleReportPDF(policy);
		return policy.getReference();
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
	
	@RequestMapping(value = "api/policy-schedules/{reference}", method = RequestMethod.GET)
	@Produces("application/pdf")
	public Response generatePolicySchedule(@PathVariable("reference") String reference) throws JRException, IOException{

		Policy policy = policyRepository.findByReference(reference);
		File policySchedulePDF = documentService.policyScheduleReportPDF(policy);
		ResponseBuilder response = Response.ok((Object) policySchedulePDF);
		response.type("application/pdf");
		response.header("Content-Disposition", "attachment; Policy_Schedule_"+policy.getReference()+".pdf");

		return response.build();

	}

}
