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
        @Override
        public CDHramFulcom process(CDHramFulcom hramItem) throws Exception {
            LOGGER.info("Processing items => {}", hramItem);

            List<CreditCardReferenceDatum> referenceDataList = new ArrayList<>();
            TemporaryAuthorizationsRequestV1 transactionRequest = new TemporaryAuthorizationsRequestV1();
            CardHolderDetailsRequestV1 cardHolderDetailsRequestV1 = new CardHolderDetailsRequestV1();
            CardHolderRequestDetails requestDetails = new CardHolderRequestDetails();
            List<AccountsDetails> accountsList = new ArrayList<>();

            // Check clientId and set account type if matches
            if (hramItem != null) {
                String clientId = hramItem.getClientId();
                if (clientId != null && clientId.equals("5596")) {
                    requestDetails.setAccountType(CardHolderRequestDetails.AccountTypeEnum.fromValue("CC"));
                }
            }

            // Build AccountsDetails
            AccountsDetails account = new AccountsDetails();
            account.setAccountIdentifier(hramItem.getAccountNumber());
            account.cardOwnershipCode(AccountsDetails.CardOwnershipCodeEnum.PRIMARY);
            accountsList.add(account);

            // Build CreditCardReferenceDatum
            CreditCardReferenceDatum transactionAuthorizationDTO = new CreditCardReferenceDatum();
            transactionAuthorizationDTO.setCreditCardReferenceNumber(hramItem.getAccountNumber());
            transactionAuthorizationDTO.setIncludeAllTransactions(false);
            referenceDataList.add(transactionAuthorizationDTO);

            hramItem.setApiFetchStatus("I");

            // Populate requestDetails
            requestDetails.setAccounts(accountsList);
            cardHolderDetailsRequestV1.setData(requestDetails);
            transactionRequest.setCreditCardReferenceData(referenceDataList);

            LOGGER.info("CSMR Card Holder Request DTO: {}", cardHolderDetailsRequestV1);
            LOGGER.info("TransactionAuth Request DTO: {}", transactionRequest);

            // Save to DB
            hramFulcomRepository.saveAndFlush(hramItem);
            LOGGER.info("Saved HRAM FULCom record to DB with status I, item size: {}", hramItem.size());

            // Call cardholder service if request details are not null
            if (cardHolderDetailsRequestV1 != null) {
                hramCardholderService.getCardHolderDetails(cardHolderDetailsRequestV1);
            } else {
                LOGGER.warn("cardHolderDetailsRequestV1 is null, skipping CardHolder details fetch.");
            }

            return hramItem;
        }

        return null;
    }
}
