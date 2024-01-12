package com.scaler.practicebookmyshow.DTOs;

import com.scaler.practicebookmyshow.models.TicketStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDTO {
    private Long ticketId ;
    private ResponseStatus responseStatus ;
    private double amount ;
}
