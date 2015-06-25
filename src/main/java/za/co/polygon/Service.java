package za.co.polygon;

import static za.co.polygon.mapper.Mapper.toBrokerQueryModel;
import static za.co.polygon.mapper.Mapper.toProductQueryModel;
import static za.co.polygon.mapper.Mapper.toQuestionnaireQueryModel;
import static za.co.polygon.mapper.Mapper.toUserQueryModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.domain.QuotationRequestQuestionnaires;
import static za.co.polygon.mapper.Mapper.toQuotationRequestQueryModel;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationRequestCommandModel;
import za.co.polygon.model.QuotationRequestCommandModel.Questionnaires;
import za.co.polygon.model.QuotationRequestQueryModel;
import za.co.polygon.model.UserQueryModel;
import za.co.polygon.repository.BrokerRepository;
import za.co.polygon.repository.ProductRepository;
import za.co.polygon.repository.QuestionnaireRepository;
import za.co.polygon.repository.QuotationRequestQuestionnaireRepository;
import za.co.polygon.repository.QuotationRequestRepository;
import za.co.polygon.repository.UserRepository;

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
        return toQuestionnaireQueryModel(questionnaires);
    }

    @RequestMapping(value = "api/brokers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BrokerQueryModel> findAllBrokers() {
        log.info("find all brokers");
        List<Broker> brokers = brokerRepository.findAll();
        log.info("found all products - size:{}", brokers.size());
        return toBrokerQueryModel(brokers);
    }

    @Transactional
    @RequestMapping(value = "api/quotation-requests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)

    public void createQuotationRequest(@RequestBody QuotationRequestCommandModel quotationRequest) {
        Broker broker = brokerRepository.findOne(quotationRequest.getBrokerId());
        Product product = productRepository.findOne(quotationRequest.getProductId());
        QuotationRequest quotationRequestModel = new QuotationRequest();
        quotationRequestModel.setApplicantEmail(quotationRequest.getApplicantEmail());
        quotationRequestModel.setApplicantName(quotationRequest.getApplicantName());
        quotationRequestModel.setBroker(broker);
        quotationRequestModel.setProduct(product);
        quotationRequestModel.setCreateDate(new Date());
        quotationRequestModel.setStatus("APPLIED");
        quotationRequestModel.setReference(UUID.randomUUID().toString());
        quotationRequestModel = quotationRequestRepository.save(quotationRequestModel);
        for (Questionnaires questionnaires : quotationRequest.getQuestionnaires()) {
            QuotationRequestQuestionnaires quotationRequestQuestionnaires = new QuotationRequestQuestionnaires();
            quotationRequestQuestionnaires.setQuestion(questionnaires.getQuestion());
            quotationRequestQuestionnaires.setAnswer(questionnaires.getAnswer());
            quotationRequestQuestionnaires.setQuotationRequest(quotationRequestModel);
            quotationRequestQuestionnaireRepository.save(quotationRequestQuestionnaires);
        }

        quotationRequestModel = quotationRequestRepository.findOne(quotationRequestModel.getId());
        log.info("Reference of the Quotation_Requests:" + quotationRequestModel.getReference());
        
        log.info("Size of the Questionnaires of Quotation_Requests:" + quotationRequestModel.getQuotationRequestQuestionnaire().size());
        log.info("No of Questions of Quotation_Requests:" + quotationRequestQuestionnaireRepository.count());
    }
    
    

    @RequestMapping(value = "api/quotation-requests/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuotationRequestQueryModel getQuotationRequest(@PathVariable("reference") String reference) {
        log.info("find all the questions and answers inserted for a product");        
    	QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
         log.info("find all the questions and answers inserted for a product using the referrence");
    	return toQuotationRequestQueryModel(quotationRequest);
    }
}
