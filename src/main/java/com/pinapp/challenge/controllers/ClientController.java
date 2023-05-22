/**
 * Controlador REST para clients.
 */

package com.pinapp.challenge.controllers;

import com.pinapp.challenge.dtos.ClientDTO;
import com.pinapp.challenge.dtos.ClientKpiDTO;
import com.pinapp.challenge.dtos.ClientListDTO;
import com.pinapp.challenge.entities.Client;
import com.pinapp.challenge.services.ClientService;
import com.pinapp.challenge.utils.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Validated
public class ClientController {

    @Autowired
    ClientService clientService;

    /**
     * Maneja la solicitud para crear un nuevo client.
     *
     * @param clientDTO DTO que contiene los campos del request.
     * @return ResponseEntity con un objeto ClientResponseDTO que representa el client creado.
     */
    @PostMapping("/creacliente")
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        Client clientCreated = clientService.saveClient(clientDTO);
        return ResponseEntity.ok(clientCreated);
    }

    /**
     * Maneja la solicitud para obtener el promedio de edad y la desviación estándar de las edades de los clientes.
     *
     * @return ResponseEntity con un objeto ClientKpiDTO que contiene el promedio de edad y la desviación estándar.
     */
    @GetMapping("/kpideclientes")
    public ResponseEntity<ClientKpiDTO> getClientKpi() {
        List<Client> clients = clientService.getAllClients();
        ClientKpiDTO kpiDTO = ClientMapper.mapToKpiDTO(clients);
        return ResponseEntity.ok(kpiDTO);
    }

    /**
     * Maneja la solicitud para obtener la lista de clientes con sus datos y la fecha probable de muerte de cada uno.
     *
     * @return ResponseEntity con una lista de objetos ClientListDTO que representan los clientes y su fecha probable de muerte.
     */
    @GetMapping("/listclientes")
    public ResponseEntity<List<ClientListDTO>> getClientList() {
        List<Client> clients = clientService.getAllClients();
        List<ClientListDTO> listDTO = ClientMapper.mapToListDTO(clients);
        return ResponseEntity.ok(listDTO);
    }
}
