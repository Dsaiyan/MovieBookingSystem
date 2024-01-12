package com.scaler.practicebookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "Shows") //because show is a reserved keyword in MySql
public class Show extends BaseModel{

    @ManyToOne
    private Movie movie ;

    private Date startTime ;

    private Date endTime ;
    @ManyToOne
    private Screen screen ;

    @Enumerated(EnumType.ORDINAL)   // for storing Enum in DB as Mapping Table with number
    @ElementCollection  //Element for enums and collection for list
    private List<Feature> showFeatureList ;

}
