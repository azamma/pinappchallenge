package com.pinapp.challenge.dtos;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) que representa el resultado del KPI de clientes.
 */
@Getter
@Setter
public class ClientKpiDTO {

    /**
     * El promedio de edad de los clientes.
     */
    private double promedioEdad;

    /**
     * La desviación estándar de las edades de los clientes.
     */
    private double desviacionEstandar;
}
