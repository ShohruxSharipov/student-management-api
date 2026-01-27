package org.example.demoapplication.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@JsonPropertyOrder({"status","message","timeStamp"})
public class ErrorResponse {
    private final int status;
    private final String message;
    @JsonFormat(pattern = "dd-MMM-yyyy HH:mm:ss")
    private final LocalDateTime timeStamp;

    ErrorResponse(int status, String message){
        this.message = message;
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }

    public int getStatus(){return status;}
    public String getMessage(){return message;}
    public LocalDateTime getTimeStamp(){return timeStamp;}
}
