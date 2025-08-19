package gov.upsc.post.migration.configuration;

import gov.upsc.post.migration.entity.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {
    private static final Logger logger = LoggerFactory.getLogger(CustomerProcessor.class);

    @Override
    public Customer process(Customer item) throws Exception {
        final String methodName = "process() : ";
        logger.info(methodName + "called");
        logger.info(methodName + item);

        return null;
    }
}
