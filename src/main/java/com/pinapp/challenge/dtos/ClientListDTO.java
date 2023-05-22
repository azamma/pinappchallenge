package com.pinapp.challenge.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) que representa los datos de un cliente en la lista de clientes.
 */
@Getter
@Setter
public class ClientListDTO {

    /**
     * El nombre del cliente.
     */
    private String firstName;

    /**
     * El apellido del cliente.
     */
    private String lastName;

    /**
     * La edad del cliente.
     */
    private String age;

    /**
     * La fecha de nacimiento del cliente en formato yyyy-mm-dd.
     */
    private String birthday;

    /**
     * La fecha probable de muerte del cliente en formato yyyy-mm-dd.
     */
    private String fechaProbableMuerte;
}
