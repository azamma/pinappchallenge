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
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthDate() {
        return birthday;
    }

    public void setBirthDate(Date birthDate) {
        this.birthday = birthDate;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}