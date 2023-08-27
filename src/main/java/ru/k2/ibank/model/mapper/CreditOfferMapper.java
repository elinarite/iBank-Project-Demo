package ru.k2.ibank.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.k2.ibank.model.dto.CreditOfferDto;
import ru.k2.ibank.model.dto.CreditReqDetailDto;
import ru.k2.ibank.model.entity.CreditOffer;
import ru.k2.ibank.model.entity.CreditReq;
import ru.k2.ibank.service.CreditReqService;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreditOfferMapper {

    static final String DAYS = "days";
    static final String INTEREST = "% per year";
    static final String FINE = "% per year for overdue payments";
    static final String CURRENCY = "Loan currency: ";

    private final CreditReqService creditReqService;

    /**
     * Method returning info about Credit Offer with related Credit Requirements
     * @param creditOffer object
     * @return object with all credit offer details including currency and credit requirements
     */
    public CreditOfferDto toCreditReq(CreditOffer creditOffer){

        CreditOfferDto creditOfferDto = new CreditOfferDto();

        creditOfferDto.setId(creditOffer.getId());
        creditOfferDto.setCreditName(creditOffer.getCreditName());
        creditOfferDto.setCreditValidFrom(creditOffer.getCreditValidFrom());
        creditOfferDto.setCreditValidTill(creditOffer.getCreditValidTill());
        creditOfferDto.setCreditInterest(creditOffer.getCreditInterest().toString() + INTEREST);
        creditOfferDto.setCreditFine(creditOffer.getCreditFine().toString() + FINE);
        creditOfferDto.setCreditMinTerm(creditOffer.getCreditMinTerm().toString() + DAYS);
        creditOfferDto.setCreditMaxTerm(creditOffer.getCreditMaxTerm().toString() + DAYS);
        creditOfferDto.setCreditMinAmount(creditOffer.getCreditMinAmount() + " " + creditOffer.getCurrencyData().getCurrencyCode());
        creditOfferDto.setCreditMaxAmount(creditOffer.getCreditMaxAmount() + " " + creditOffer.getCurrencyData().getCurrencyCode());
        creditOfferDto.setCreditRemarks(creditOffer.getCreditRemarks());
        creditOfferDto.setCurrencyData(CURRENCY + creditOffer.getCurrencyData().getCurrencyName());

        List<CreditReq> originDetails = creditOffer.getCreditReqs();
        List<CreditReqDetailDto> simplifiedDetails = new ArrayList<>();

        for (CreditReq details : originDetails) {
            CreditReqDetailDto c = new CreditReqDetailDto();
            c.setCreditReqName(details.getCreditReqDetails().getCreditReqName());
            c.setCreditReqDescription(details.getCreditReqDetails().getCreditReqDescription());
            simplifiedDetails.add(c);
        }

        creditOfferDto.setCreditReqs(simplifiedDetails);

        return creditOfferDto;
    }

    /**
     * Method returning info about Credit Offer with related Credit Requirements
     * @param creditOffers List of CreditOffer
     * @return List of CreditOfferDto
     */
    public List<CreditOfferDto> toCreditReq (List<CreditOffer> creditOffers){

        List<CreditOfferDto> creditOfferDto = new ArrayList<>();

        for (CreditOffer creditOffer: creditOffers){
            creditOfferDto.add(toCreditReq(creditOffer));
        }

        return creditOfferDto;
    }

}
