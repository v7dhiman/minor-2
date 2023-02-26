package com.example.minor.exceptions;

public class TxnServiceException extends Exception
{
    public TxnServiceException(String msg)
    {
        super(msg);
    }
}
