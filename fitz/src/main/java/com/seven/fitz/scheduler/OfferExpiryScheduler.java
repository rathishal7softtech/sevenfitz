package com.seven.fitz.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.seven.fitz.entity.Offer;
import com.seven.fitz.repository.OfferRepository;

@Component
public class OfferExpiryScheduler {
	@Autowired
    private OfferRepository offerRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void disableExpiredOffers(){

        List<Offer> offers = offerRepository.findAll();

        for(Offer offer : offers){

            if(offer.getOfferClosedDate().isBefore(LocalDate.now())){
                offer.setExpired(true);
                offerRepository.save(offer);
            }
        }
    }

}
