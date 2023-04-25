package com.marcusso.firebasedemo;

public class FirebaseException extends Exception {

    private String error_message;

    public FirebaseException(String error_message) {

        this.setError_message(error_message);
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    @Override
    public String toString() {
        return this.getError_message();
    }

}
