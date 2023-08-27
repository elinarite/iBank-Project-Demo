package ru.k2.ibank.model.mapper;

import org.springframework.stereotype.Component;
import ru.k2.ibank.model.dto.CreditReqByIdDto;
import ru.k2.ibank.model.entity.CreditReq;

@Component
public class CreditReqMapper {

    /**
     * Method returning basic info about relationship Credit Offer and Credit Requirement
     * @param creditReq object
     * @return object with ID, credit_offer_id, credit_offer_name, credit_req_id and credit_req_name
     */
    public CreditReqByIdDto toCreditReq(CreditReq creditReq){

        CreditReqByIdDto creditReqByIdDto = new CreditReqByIdDto();

        creditReqByIdDto.setId(creditReq.getId());
        creditReqByIdDto.setCreditOfferId(creditReq.getCreditOffer().getId());
        creditReqByIdDto.setCreditName(creditReq.getCreditOffer().getCreditName());
        creditReqByIdDto.setCreditReqId(creditReq.getCreditReqDetails().getId());
        creditReqByIdDto.setCreditReqName(creditReq.getCreditReqDetails().getCreditReqName());

        return creditReqByIdDto;
    }

}
