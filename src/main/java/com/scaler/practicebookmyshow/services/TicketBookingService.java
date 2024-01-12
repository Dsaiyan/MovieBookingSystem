package com.scaler.practicebookmyshow.services;

import com.scaler.practicebookmyshow.exceptions.ShowNotFoundException;
import com.scaler.practicebookmyshow.exceptions.ShowSeatNotFoundException;
import com.scaler.practicebookmyshow.exceptions.UserNotFoundException;
import com.scaler.practicebookmyshow.models.*;
import com.scaler.practicebookmyshow.repositories.ShowRepository;
import com.scaler.practicebookmyshow.repositories.ShowSeatRepository;
import com.scaler.practicebookmyshow.repositories.TicketRepository;
import com.scaler.practicebookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
//to create the object of the class at run time we need tell Spring that
// this class are special classes, so WE HAVE some annotation
//@service  @controller     @repository     @component

@Service
public class TicketBookingService {
    private UserRepository userRepository ;
    private ShowRepository showRepository ;
    private ShowSeatRepository showSeatRepository ;
    private PriceCalculatorService priceCalculatorService ;
    private TicketRepository ticketRepository ;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Ticket bookMovie(Long userId, Long showId, List<Long> showSeatIds) throws Exception{
        // flow:
        //-----------Take a Lock---------------
        //1. get user from userId
        //2. get show from showId
        //3. get list of showSeatIds from showSeatId
        //4. check for status for all showSeatIds
        //5. if not available then throws exception
        //6. if all are available change the status to Locked
        //7. change the status in DB as well
        //8. Create ticket object
        //---------release the lock------------

        //1. get user from userId
        Optional<User> user = userRepository.findById(userId) ;

        if( user.isEmpty() ){
            throw new UserNotFoundException("User Id Invalid") ;
        }
        User bookedBy = user.get() ;
        //2. get show from showId
        Optional<Show> show = showRepository.findById(showId) ;

        if( show.isEmpty()){
            throw new ShowNotFoundException( "This show is not available") ;
        }
        Show bookedShow = show.get() ;

        //3. get list of showSeatIds from showSeatId
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds) ;

        //4. check for status for all showSeatIds
        for( ShowSeat showSeat : showSeats ){
            //5. if not available then throws exception
            if( ! showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ){
                throw new ShowSeatNotFoundException(showSeat.getId()+" is not available") ;
            }
        }

        List<ShowSeat> bookedShowSeats = new ArrayList<>() ;
        //6. if all are available change the status to Locked
        for( ShowSeat showSeat : showSeats ){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            //7. change the status in DB as well
            bookedShowSeats.add(showSeatRepository.save(showSeat) );
        }

        //8. Create ticket object
        Ticket ticket = new Ticket() ;
        ticket.setCreatedAt(new Date());
        ticket.setUser(bookedBy);
        ticket.setTicketStatus(TicketStatus.Pending);
        ticket.setPaymentsList(new ArrayList<>());
        ticket.setShowSeatList(bookedShowSeats);
        ticket.setAmount(priceCalculatorService.
                calculateBookingPrice(bookedShowSeats , bookedShow));


        return ticketRepository.save(ticket) ;
    }
}
