package com.pinapp.challenge.services;

import com.pinapp.challenge.daos.ClientDao;
import com.pinapp.challenge.dtos.ClientDTO;
import com.pinapp.challenge.entities.Client;
import com.pinapp.challenge.utils.ClientUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.PersistenceException;
import java.util.Date;
import java.util.List;

/**
 * Servicio para manejar los clientes.
 */
@Service
public class ClientService {

    @Autowired
    private ClientDao clientDao;

    private static final Logger logger = LogManager.getLogger(ClientService.class);

    /**
     * Guarda un cliente en la base de datos.
     *
     * @param clientDTO El DTO del cliente a guardar.
     * @return El cliente guardado.
     * @throws PersistenceException Si el correo del cliente ya está registrado.
     */
    public Client saveClient(ClientDTO clientDTO) throws PersistenceException {

        // Verifica si el correo electrónico del cliente ya existe en la base de datos
        if (clientDao.findByFullName(clientDTO.getFirstName(), clientDTO.getLastName()) != null) {
            logger.info("Cliente ya registrado");
            throw new PersistenceException("El cliente ya está registrado");
        }

        // Crea un nuevo objeto Client a partir del objeto ClientDTO proporcionado
        Client client = ClientUtils.mapToEntity(clientDTO);

        // Guarda el objeto Client en la base de datos
        clientDao.save(client);
        return client;
    }

    /**
     * Actualiza un cliente en la base de datos.
     *
     * @param client El cliente a actualizar.
     * @return El cliente actualizado.
     */
    public Client updateClient(Client client) {
        client.setModified(new Date());
        clientDao.update(client);
        return client;
    }

    /**
     * Obtiene un cliente por su id.
     *
     * @param id El id del cliente a obtener.
     * @return El cliente con el id especificado.
     */
    public Client getClientById(Long id) {
        return clientDao.findById(id);
    }

    /**
     * Elimina un cliente de la base de datos.
     *
     * @param id El id del cliente a eliminar.
     */
    public void deleteClient(Long id) {
        Client client = clientDao.findById(id);
        client.setIsActive(false);
        clientDao.update(client);
    }

    /**
     * Obtiene una lista de todos los clientes en la base de datos.
     *
     * @return una lista de clientes
     */
    public List<Client> getAllClients() {
        return clientDao.findAll();
    }
}
