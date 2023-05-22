package com.pinapp.challenge.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(nullable = false)
    private Date modified;

    @Column(name = "isactive", nullable = false)
    private boolean isActive;

    /**
     * Setea el estado de activación del client.
     * @param b El estado a setear.
     */
    public void setIsActive(boolean b) {
        isActive = b;
    }

}