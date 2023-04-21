package edu.in.mckvie.CampusNexus.filter;

import edu.in.mckvie.CampusNexus.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderFilter {
    Logger logger= LoggerFactory.getLogger(CreateOrderFilter.class);
    @Autowired
    UserRepository userRepository;
    public String isOrderAlreadyCreated(String uRoll){
        int id=userRepository.findByUniversityRollNumber(uRoll).get().getId();
        logger.info("id= {} ",id);
        String order_id=userRepository.findOrderIdByUserId(id);
        logger.info("order= {}",order_id);
        return order_id;
    }
}
