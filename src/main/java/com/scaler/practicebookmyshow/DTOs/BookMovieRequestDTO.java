package com.scaler.practicebookmyshow.DTOs;

import com.scaler.practicebookmyshow.models.ShowSeat;
import com.scaler.practicebookmyshow.models.Ticket;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookMovieRequestDTO {
    private List<Long> showSeatsId ;
    private Long userId ;
    private Long showId ;
}
