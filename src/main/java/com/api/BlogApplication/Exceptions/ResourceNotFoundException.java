package com.api.BlogApplication.Exceptions;

//RuntimeException is unchecked exception
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName,String fieldName, int fieldValue) {
        super(resourceName+" not found with "+fieldName+":"+fieldValue);
    }
}
