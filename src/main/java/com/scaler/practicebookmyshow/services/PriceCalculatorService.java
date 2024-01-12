package com.scaler.practicebookmyshow.services;

import com.scaler.practicebookmyshow.models.Show;
import com.scaler.practicebookmyshow.models.ShowSeat;
import com.scaler.practicebookmyshow.models.ShowSeatType;
import com.scaler.practicebookmyshow.repositories.ShowSeatTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculatorService {
    private ShowSeatTypeRepository showSeatTypeRepository ;
    public double calculateBookingPrice(List<ShowSeat> bookedShowSeats, Show bookedShow) {

        double amount = 0 ;
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.
                                        findAllByShow(bookedShow.getId()) ;

        for( ShowSeat showSeat : bookedShowSeats ){
            for(ShowSeatType showSeatType : showSeatTypes ){
                if( showSeat.getSeats().getSeatType().equals(showSeatType.getSeatType())){
                    amount += showSeatType.getPrice();
                }

            }
        }
        return amount ;
    }
}
