package com.scaler.practicebookmyshow.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    //MappedSuperClass is used to tell JPA that this base model class
    // will be in col of each table of entity which extends this class

    @Id     //THis is to show that id is primaryKey for all the entity which extends this class
    @GeneratedValue(strategy = GenerationType.IDENTITY )    //this is to increment the value of id using different Strategy
    private Long id ; //primary key

    private Date createdAt ;

    private Date lastModifiedAt ;
}
