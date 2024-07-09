package com.example.druzhininaj200.repository;

import com.example.druzhininaj200.dto.ClientsDTO;
import com.example.druzhininaj200.entity.ClientsEntity;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Singleton
public class ClientRepository {
    @PersistenceContext
    private EntityManager em;
    //public Optional<ClientsEntity> findClientById(Long id){
    //    Objects.requireNonNull(id, "Идентификатор не может быть null");
     //   return Optional.of(em.find(ClientsEntity.class, id));
   // }

   // public Stream<ClientsEntity> findAll() {
    //    return new HashSet(em.createNativeQuery("SELECT* FROM clients",
    //                    ClientsEntity.class).
    //            getResultList()).stream();
   // }
    public ClientsEntity findClientById(Long id){
        Objects.requireNonNull(id, "Идентификатор не может быть null");
        ClientsEntity clients = em.find(ClientsEntity.class, id);
        return clients;
    }

    public Stream<ClientsEntity> findAll() {
        return new HashSet(em.createNativeQuery("SELECT* FROM clients",
                        ClientsEntity.class).
                getResultList()).stream();
    }
    public void create(ClientsEntity entity) {
        em.persist(entity);
        em.flush();
    }
    public void update(ClientsEntity clients) {
        em.merge(clients);
        em.flush();
    }
    public void remove(ClientsEntity clients) {
        em.remove(clients);
        em.flush();
    }
}
