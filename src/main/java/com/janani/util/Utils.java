package com.janani.util;

public class Utils {

    // You can add any required services or settings here if needed for your Java app.

    public static String getExceptionMessage(Exception exp) {
        // Return the exception message with inner exception message and source if available.
        StringBuilder exceptionMessage = new StringBuilder(exp.getMessage());
        if (exp.getCause() != null) {
            exceptionMessage.append(" ").append(exp.getCause().getMessage());
        }
        if (exp.getStackTrace() != null && exp.getStackTrace().length > 0) {
            exceptionMessage.append(" ").append(exp.getStackTrace()[0].toString());
        }
        return exceptionMessage.toString();
    }
}
