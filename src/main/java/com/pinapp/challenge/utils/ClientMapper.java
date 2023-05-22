package com.pinapp.challenge.utils;

import com.pinapp.challenge.dtos.ClientDTO;
import com.pinapp.challenge.dtos.ClientKpiDTO;
import com.pinapp.challenge.dtos.ClientListDTO;
import com.pinapp.challenge.entities.Client;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientMapper {

    public static Client mapToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        client.setName(clientDTO.getFirstName());
        client.setLastname(clientDTO.getLastName());
        client.setAge(clientDTO.getAge());
        client.setBirthDate(Date.valueOf(clientDTO.getBirthday()));
        client.setModified(new Timestamp(System.currentTimeMillis()));
        client.setActive(true);
        return client;
    }

    public static ClientKpiDTO mapToKpiDTO(List<Client> clients) {
        ClientKpiDTO kpiDTO = new ClientKpiDTO();

        // Calcula el promedio de edad
        double sumEdad = clients.stream()
                .mapToDouble(client -> Double.parseDouble(client.getAge()))
                .sum();
        double promedioEdad = sumEdad / clients.size();
        kpiDTO.setPromedioEdad(promedioEdad);

        // Calcula la desviación estándar de las edades
        double sumEdadCuadrados = clients.stream()
                .mapToDouble(client -> Math.pow(Double.parseDouble(client.getAge()) - promedioEdad, 2))
                .sum();
        double desviacionEstandar = Math.sqrt(sumEdadCuadrados / clients.size());
        kpiDTO.setDesviacionEstandar(desviacionEstandar);

        return kpiDTO;
    }

    public static List<ClientListDTO> mapToListDTO(List<Client> clients) {
        List<ClientListDTO> listDTOs = new ArrayList<>();
        for (Client client : clients) {
            ClientListDTO listDTO = new ClientListDTO();
            listDTO.setFirstName(client.getName());
            listDTO.setLastName(client.getLastname());
            listDTO.setAge(client.getAge());
            listDTO.setBirthday(client.getBirthDate().toString());

            // Calcula la fecha probable de muerte asumiendo una esperanza de vida de 80 años
            LocalDate birthday = LocalDate.parse(client.getBirthDate().toString());
            LocalDate probableMuerte = birthday.plusYears(80);
            listDTO.setFechaProbableMuerte(probableMuerte.toString());

            listDTOs.add(listDTO);
        }
        return listDTOs;
    }

}

