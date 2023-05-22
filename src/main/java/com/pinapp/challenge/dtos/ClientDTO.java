/**
 * Data Transfer Object (DTO) que representa la información de un Cliente.
 */

package com.pinapp.challenge.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class ClientDTO {

    /**
     * Nombre del client
     */
    @NotEmpty(message = "Falta el campo firstName") // Validación: El campo no debe estar vacío
    @NotNull(message = "El campo name no puede ser nulo") // Validación: El campo no puede ser nulo
    private String firstName;

    /**
     * Correo electrónico del client
     */
    @NotEmpty(message = "Falta el campo lastName") // Validación: El campo no debe estar vacío
    private String lastName;

    /**
     * Edad del client
     */
    @NotEmpty(message = "Falta el campo age") // Validación: El campo no debe estar vacío
    private String age;

    /**
     * Fecha de nacimiento del cliente en formato YYYY-MM-DD.
     */
    @NotEmpty(message = "Falta el campo age") // Validación: El campo no debe estar vacío
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "El formato de fecha de nacimiento debe ser YYYY-MM-DD")
    private String birthday;



}
