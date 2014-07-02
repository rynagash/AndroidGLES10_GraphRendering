package com.example.exception;

public class InitException extends Exception {
    public InitException() {
        super("please call init()");
    }
}
