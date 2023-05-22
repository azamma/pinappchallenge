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

/**
 * Clase utilitaria para realizar operaciones relacionadas con clientes.
 */
public class ClientUtils {


    /**
     * Convierte un objeto ClientDTO a una entidad Client.
     *
     * @param clientDTO El objeto ClientDTO a mapear.
     * @return La entidad Client mapeada.
     */
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

    /**
     * Convierte una lista de entidades Client a un objeto ClientKpiDTO que contiene estadísticas de los clientes.
     *
     * @param clients La lista de entidades Client.
     * @return El objeto ClientKpiDTO con las estadísticas calculadas.
     */
    public static ClientKpiDTO mapToKpiDTO(List<Client> clients) {
        // Crea un nuevo objeto ClientKpiDTO para almacenar las estadísticas
        ClientKpiDTO kpiDTO = new ClientKpiDTO();

        // Calcula el promedio de edad
        double sumEdad = clients.stream()
                .mapToDouble(client -> Double.parseDouble(client.getAge())) // Obtiene la edad de cada cliente como un Double
                .sum(); // Suma todas las edades
        double promedioEdad = sumEdad / clients.size(); // Calcula el promedio dividiendo la suma por la cantidad de clientes
        kpiDTO.setPromedioEdad(promedioEdad); // Establece el promedio de edad en el objeto ClientKpiDTO

        // Calcula la desviación estándar de las edades
        double sumEdadCuadrados = clients.stream()
                .mapToDouble(client -> Math.pow(Double.parseDouble(client.getAge()) - promedioEdad, 2)) // Calcula el cuadrado de la diferencia entre la edad y el promedio
                .sum(); // Suma todos los cuadrados
        double desviacionEstandar = Math.sqrt(sumEdadCuadrados / clients.size()); // Calcula la raíz cuadrada de la suma dividida por la cantidad de clientes
        kpiDTO.setDesviacionEstandar(desviacionEstandar); // Establece la desviación estándar en el objeto ClientKpiDTO

        return kpiDTO; // Devuelve el objeto ClientKpiDTO con las estadísticas calculadas
    }


    /**
     * Convierte una lista de entidades Client a una lista de objetos ClientListDTO.
     *
     * @param clients La lista de entidades Client.
     * @return La lista de objetos ClientListDTO.
     */
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


    /**
     * Verifica si la edad proporcionada en un objeto ClientDTO coincide con la fecha de cumpleaños.
     *
     * @param clientDTO El objeto ClientDTO que contiene la edad y la fecha de cumpleaños.
     * @return true si la edad coincide con la fecha de cumpleaños, false de lo contrario.
     */
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

