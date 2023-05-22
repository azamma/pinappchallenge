package com.pinapp.challenge.utils;

import com.pinapp.challenge.dtos.ClientDTO;
import com.pinapp.challenge.dtos.ClientKpiDTO;
import com.pinapp.challenge.dtos.ClientListDTO;
import com.pinapp.challenge.entities.Client;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class ClientUtils {

    public static Client mapToEntity(ClientDTO clientDTO) {
        Client client = new Client();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedBirthday = dateFormat.format(Date.valueOf(clientDTO.getBirthday()));
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setAge(clientDTO.getAge());
        client.setBirthday(Date.valueOf(formattedBirthday));
        client.setModified(new Timestamp(System.currentTimeMillis()));
        client.setIsActive(true);
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
            listDTO.setFirstName(client.getFirstName());
            listDTO.setLastName(client.getLastName());
            listDTO.setAge(client.getAge());
            listDTO.setBirthday(client.getBirthday().toString());

            // Calcula la fecha probable de muerte asumiendo una esperanza de vida de 80 años
            LocalDate birthday = LocalDate.parse(client.getBirthday().toString());
            LocalDate probableMuerte = birthday.plusYears(80);
            listDTO.setFechaProbableMuerte(probableMuerte.toString());

            listDTOs.add(listDTO);
        }
        return listDTOs;
    }

    public static boolean isAgeMatchingBirthday(ClientDTO clientDTO) {
        if (clientDTO.getAge() == null || clientDTO.getBirthday() == null) {
            return true; // Skip validation if either age or birthday is null
        }

        LocalDate birthday = LocalDate.parse(clientDTO.getBirthday());
        int age = Integer.parseInt(clientDTO.getAge());

        LocalDate currentDate = LocalDate.now();

        int calculatedAge = Period.between(birthday, currentDate).getYears();

        return calculatedAge == age;
    }

}

