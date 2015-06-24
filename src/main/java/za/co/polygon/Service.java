package za.co.polygon;

import static za.co.polygon.mapper.Mapper.toBrokerQueryModel;
import static za.co.polygon.mapper.Mapper.toProductQueryModel;
import static za.co.polygon.mapper.Mapper.toQuestionnaireQueryModel;
import static za.co.polygon.mapper.Mapper.toUserQueryModel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.mapper.Mapper;
import static za.co.polygon.mapper.Mapper.*;
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

    @RequestMapping(value = "api/quotation-request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createQuotationRequest(@RequestBody QuotationRequestCommandModel quotationRequest) {
        Product product = productRepository.findOne(quotationRequest.getProductId());
        Broker broker = brokerRepository.findOne(quotationRequest.getBrokerId());
        log.info("Application name::" + quotationRequest.getApplicantName());
        log.info("Application email::" + quotationRequest.getApplicantEmail());
        log.info("Broker ID::" + quotationRequest.getBrokerId());
        List<Questionnaires> questions = quotationRequest.getQuestionnaires();
        for (Questionnaires questionnaires : questions) {
            log.info("Question::" + questionnaires.getQuestion());
            log.info("Answer::" + questionnaires.getAnswer());

        }
        QuotationRequest request = Mapper.toQuotationRequest(quotationRequest);
        request.setApplicantName(quotationRequest.getApplicantName());
        request.setApplicantEmail(quotationRequest.getApplicantEmail());
        quotationRequestRepository.save(request);
    }

    public void getQuotationRequests() {
        quotationRequestRepository.findAll();
    }

    @RequestMapping(value = "api/viewQuote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuotationRequestQueryModel> findAllQuoteInfo() {
        log.info("find all QuoteInfo");
        List<QuotationRequest> quotationRequests = quotationRequestRepository.findAll();
        log.info("found all products - size:{}", quotationRequests.size());
        return toQuotationRequestQueryModel(quotationRequests);
    }

}
