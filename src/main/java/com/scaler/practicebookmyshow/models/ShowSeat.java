package com.scaler.practicebookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel{
    @ManyToOne
    private Show show ;

    @ManyToOne
    private Seat seats ;

    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus ;
}
