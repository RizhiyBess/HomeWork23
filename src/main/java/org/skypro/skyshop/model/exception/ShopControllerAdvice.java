package org.skypro.skyshop.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException e){
        HttpStatus statusNotFound = HttpStatus.NOT_FOUND;
     ShopError shopError = new ShopError(String.valueOf(statusNotFound.value()), e.getMessage());
     return ResponseEntity.status(statusNotFound).body(shopError);
    }
}
