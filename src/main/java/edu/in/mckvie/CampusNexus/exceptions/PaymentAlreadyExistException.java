package edu.in.mckvie.CampusNexus.exceptions;

import lombok.Data;

@Data
public class PaymentAlreadyExistException extends RuntimeException{
    int semId;

    public PaymentAlreadyExistException(int semId) {
        super(String.format("%d sem payment is already exist",semId));
        this.semId = semId;
    }
}
