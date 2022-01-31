package com.example.capstoneproject.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private Date Timestamp;
    private String message;
    private String details;
    ExceptionResponse(Date timestamp,String message,String details){
        super();
        this.Timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
