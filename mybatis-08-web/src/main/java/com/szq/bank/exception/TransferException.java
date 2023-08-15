package com.szq.bank.exception;

public class TransferException extends Exception{
    public TransferException() {
    }

    public TransferException(String message) {
        super(message);
    }
}
