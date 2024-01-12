package com.scaler.practicebookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Ticket extends BaseModel{

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus ticketStatus ;

    @ManyToMany     //in case of cancel booking m:m but other case 1:m
    private List<ShowSeat> showSeatList ;
    @ManyToOne
    private User user ;

    private double amount ;
    @OneToMany
    private List<Payments> paymentsList ;
}
