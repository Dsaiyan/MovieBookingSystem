package com.scaler.practicebookmyshow.controllers;

import com.scaler.practicebookmyshow.DTOs.BookMovieRequestDTO;
import com.scaler.practicebookmyshow.DTOs.BookMovieResponseDTO;
import com.scaler.practicebookmyshow.DTOs.ResponseStatus;
import com.scaler.practicebookmyshow.models.Ticket;
import com.scaler.practicebookmyshow.services.TicketBookingService;
import org.springframework.stereotype.Controller;



@Controller
public class TicketController {
    private TicketBookingService ticketBookingService ;

    public BookMovieResponseDTO bookTicket(BookMovieRequestDTO bookMovieRequestDTO) throws Exception {



        BookMovieResponseDTO bookMovieResponseDTO = new BookMovieResponseDTO() ;
        try{
            Ticket ticket = ticketBookingService.bookMovie( bookMovieRequestDTO.getUserId(),
                    bookMovieRequestDTO.getShowId(),
                    bookMovieRequestDTO.getShowSeatsId() ) ;

            bookMovieResponseDTO.setTicketId(ticket.getId());
            bookMovieResponseDTO.setResponseStatus(ResponseStatus.IN_PROGRESS);
            bookMovieResponseDTO.setAmount(ticket.getAmount());

        }catch(RuntimeException runtimeException){

            bookMovieResponseDTO.setResponseStatus(ResponseStatus.Failed);
        }
        return bookMovieResponseDTO ;
    }
}
