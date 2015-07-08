package za.co.polygon;

import static za.co.polygon.mapper.Mapper.*;

import java.util.ArrayList;
import java.util.List;
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
import za.co.polygon.domain.Answer;
import za.co.polygon.domain.Premium;
import za.co.polygon.domain.User;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.PremiumCommandModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationRequestCommandModel;
import za.co.polygon.model.QuotationRequestQueryModel;
import za.co.polygon.model.UserCommandModel;
import za.co.polygon.model.UserQueryModel;
import za.co.polygon.repository.BrokerRepository;
import za.co.polygon.repository.PremiumRepository;
import za.co.polygon.repository.ProductRepository;
import za.co.polygon.repository.QuestionnaireRepository;
import za.co.polygon.repository.QuotationRequestQuestionnaireRepository;
import za.co.polygon.repository.QuotationRequestRepository;
import za.co.polygon.repository.UserRepository;
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
    private PremiumRepository premiumRepository;

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
    @RequestMapping(value = "api/quotation-requests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="text/html")
    public String createQuotationRequest(@RequestBody QuotationRequestCommandModel quotationRequestCommandModel) {        
      Broker broker = brokerRepository.findOne(quotationRequestCommandModel.getBrokerId());
      
      Product product = productRepository.findOne(quotationRequestCommandModel.getProductId());
      
      QuotationRequest quotationRequest = toQuotationRequest(quotationRequestCommandModel, broker, product);
      quotationRequest = quotationRequestRepository.save(quotationRequest);
      
      List<Answer> quotationRequestQuestionnaires = fromQuotationRequestCommandModel(quotationRequestCommandModel, quotationRequest);
      quotationRequestQuestionnaireRepository.save(quotationRequestQuestionnaires);

      notificationService.sendNotificationForNewQuotationRequest(quotationRequest, broker);

      log.info("Quotation Request Created. reference : {} " , quotationRequest.getReference());
      return quotationRequest.getReference();
    }


    @RequestMapping(value = "api/quotation-requests/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuotationRequestQueryModel getQuotationRequest(@PathVariable("reference") String reference) {
        log.info("find all the questions and answers inserted for a product");
        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
        log.info("find all the questions and answers inserted for a product using the reference");
        return toQuotationRequestQueryModel(quotationRequest);
    }
    
    @RequestMapping(value = "api/createuser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="text/html")
    public void fromcreateUser(@RequestBody UserCommandModel userCommandModel) {
        User user = fromUserCommandModel(userCommandModel);
        log.info("request received to create guest : " + user);
        userRepository.save(user);
    }
    
    
    
    @RequestMapping(value = "api/createPolicy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces="text/html")
    public void fromcreatePolicy(@RequestBody PremiumCommandModel premiumCommandModel) {
        Premium premium = toPremiumRequest(premiumCommandModel);       
        premium = premiumRepository.save(premium);
        log.info("saved all the premium values premium table");
    }

}