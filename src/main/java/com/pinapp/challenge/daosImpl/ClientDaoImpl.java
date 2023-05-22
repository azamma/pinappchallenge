/**
 Implementación del Data Access Object (DAO) para la entidad User
 */
package com.pinapp.challenge.daosImpl;

import com.pinapp.challenge.daos.ClientDao;
import com.pinapp.challenge.entities.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ClientDaoImpl implements ClientDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(ClientDaoImpl.class);


    /**
     * Guarda un nuevo client en la base de datos
     * @param user el client a guardar
     * @return el client guardado
     */
    @Override
    public Client save(Client user) {
        logger.info("Guardando client: {}", user);
        entityManager.persist(user);
        logger.info("client guardado exitosamente");
        return user;
    }

    /**
     * Devuelve una lista de todos los clientes en la base de datos.
     *
     * @return una lista de clientes
     */
    @Override
    public List<Client> findAll() {
        TypedQuery<Client> query = entityManager.createQuery("SELECT c FROM Client c", Client.class);
        return query.getResultList();
    }

    /**
     * Busca un client por su ID
     * @param id el ID del client a buscar
     * @return el client encontrado o null si no existe
     */
    @Override
    public Client findById(Long id) {
        return entityManager.find(Client.class, id);
    }

    /**
     * Busca un client por su nombre de client
     * @param username el nombre de client a buscar
     * @return el client encontrado o null si no existe
     */
    @Override
    public Client findByUsername(String username) {
        TypedQuery<Client> query = entityManager.createQuery("SELECT u FROM Client u WHERE u.username = :username", Client.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    /**
     * Busca un client por su token de acceso
     * @param token el token de acceso a buscar
     * @return el client encontrado o null si no existe
     */
    @Override
    public Client findByToken(String token) {
        TypedQuery<Client> query = entityManager.createQuery("SELECT u FROM Client u WHERE u.token = :token", Client.class);
        query.setParameter("token", token);
        return query.getSingleResult();
    }

    /**
     * Busca un client por su dirección de correo electrónico
     * @param email la dirección de correo electrónico a buscar
     * @return el client encontrado o null si no existe
     */
    @Override
    public Client findByFullName(String firstName, String lastName) {
        String jpql = "SELECT u FROM Client u WHERE u.firstName = :firstName AND u.lastName = :lastName";
        TypedQuery<Client> query = entityManager.createQuery(jpql, Client.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        List<Client> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }


    /**
     * Actualiza un client existente en la base de datos
     * @param user el client a actualizar
     * @return el client actualizado
     */
    @Override
    public Client update(Client user) {
        return entityManager.merge(user);
    }

    /**
     * Elimina un client de la base de datos
     * @param user el client a eliminar
     */
    @Override
    public void delete(Client user) {
        entityManager.remove(user);
    }


}