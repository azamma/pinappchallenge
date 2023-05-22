/**

 ExceptionHandler para el controlador de clients.

 Este controlador maneja las excepciones que se producen en el controlador de clients.
 */
package com.pinapp.challenge.controllers.exceptionsHandlers;

import com.pinapp.challenge.controllers.ClientController;
import com.pinapp.challenge.controllers.responseobjects.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackageClasses = ClientController.class)
public class ClientExceptionHandler {

    /**

     Maneja las excepciones relacionadas con la validación de los datos de entrada en la solicitud.
     @param ex excepción que se produjo.
     @return ResponseEntity con un objeto ErrorResponse que describe los campos con errores de la solicitud.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ErrorResponse errorResponse = new ErrorResponse("Hay uno o más campos con errores -> ".concat(errors.toString()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    /**

     Maneja las excepciones relacionadas con problemas de persistencia de datos.
     @param ex excepción que se produjo.
     @return ResponseEntity con un objeto ErrorResponse que describe el error que se produjo.
     */
    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<ErrorResponse> handlePersistenceExceptions(PersistenceException ex) {
        ErrorResponse errorResponse = new ErrorResponse("Se produjo un error al crear el client -> ".concat(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    /**

     Maneja cualquier excepción inesperada que se produzca durante el procesamiento de la solicitud.
     @param ex excepción que se produjo.
     @return ResponseEntity con un objeto ErrorResponse que describe el error que se produjo.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("Se produjo un error inesperado -> ".concat(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}