package edu.in.mckvie.CampusNexus.filter;

import edu.in.mckvie.CampusNexus.exceptions.PaymentAlreadyExistException;
import edu.in.mckvie.CampusNexus.repositories.PaymentRepository;
import edu.in.mckvie.CampusNexus.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateOrderFilter {
    Logger logger= LoggerFactory.getLogger(CreateOrderFilter.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    public String isOrderAlreadyCreated(String uRoll,int semId){
        int id=userRepository.findByUniversityRollNumber(uRoll).get().getId();
        logger.info("userId= {} ",id);
        logger.info("semId= {} ",semId);
        String order_id=this.paymentRepository.findOrderIdByUserIdAndSemId(id,semId);
        logger.info("order= {}",order_id);
        if(order_id == null)
            return null;
        else{
            if(this.paymentRepository.findStatusByOrderId(order_id).equals("created"))
                return order_id;
            else if(this.paymentRepository.findStatusByOrderId(order_id).equals("paid"))
                throw new PaymentAlreadyExistException(semId);
            else
                return null;
        }

    }
}
