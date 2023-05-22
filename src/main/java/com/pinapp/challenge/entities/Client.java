package com.pinapp.challenge.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Entidad que representa un cliente.
 */
@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Client {

    /**
     * Identificador del cliente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Nombre del cliente.
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Apellido del cliente.
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Edad del cliente.
     */
    @Column(nullable = false)
    private String age;

    /**
     * Fecha de nacimiento del cliente.
     */
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    /**
     * Fecha de modificación del cliente.
     */
    @Column(nullable = false)
    private Date modified;

    /**
     * Indica si el cliente está activo o no.
     */
    @Column(name = "isactive", nullable = false)
    private boolean isActive;

    /**
     * Setea el estado de activación del cliente.
     * @param b El estado a setear.
     */
    public void setIsActive(boolean b) {
        isActive = b;
    }

}
