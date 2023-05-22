package com.pinapp.challenge.daos;

import com.pinapp.challenge.entities.Client;

import java.util.List;

public interface ClientDao {

    /**
     * Guarda un client en la base de datos.
     * @param client El client a guardar.
     * @return El client guardado.
     */
    Client save(Client client);

    /**
     * Busca un cliente por nombre y apellido en la base de datos.
     *
     * @param firstName El nombre del cliente a buscar.
     * @param lastName El apellido del cliente a buscar.
     * @return El cliente encontrado o null si no se encontró ningún cliente con el nombre y apellido proporcionados.
     */
    Client findByFullName(String firstName, String lastName);


    /**
     * Obtiene todos los clients de la base de datos.
     * @return Una lista con todos los clients.
     */
    List<Client> findAll();

    /**
     * Busca un client por su ID.
     * @param id El ID del client a buscar.
     * @return El client encontrado, o null si no se encuentra.
     */
    Client findById(Long id);

    /**
     * Busca un client por su nombre de client.
     * @param username El nombre de client a buscar.
     * @return El client encontrado, o null si no se encuentra.
     */
    Client findByUsername(String username);

    /**
     * Actualiza los datos de un client en la base de datos.
     * @param client El client a actualizar.
     * @return El client actualizado.
     */
    Client update(Client client);

    /**
     * Elimina un client de la base de datos.
     * @param client El client a eliminar.
     */
    void delete(Client client);

}
