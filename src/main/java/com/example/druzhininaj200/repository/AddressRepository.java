package com.example.druzhininaj200.repository;

import com.example.druzhininaj200.entity.AddressesEntity;
import com.example.druzhininaj200.entity.ClientsEntity;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Singleton
public class AddressRepository {
    @PersistenceContext
    private EntityManager em;
    public AddressesEntity findAddressById(Long id){
        if(id==null) Objects.requireNonNull(id, "Идентификатор не может быть null");
        return em.find(AddressesEntity.class, id);
    }
    public Set<AddressesEntity> findAll(){
        return new HashSet<>(em.createNativeQuery("select * from addresses", AddressesEntity.class).getResultList());
    }
    public void create(AddressesEntity entity) {
        em.persist(entity);
        em.flush();
    }
    public void update(AddressesEntity addresses) {
        em.merge(addresses);
        em.flush();
    }
    public void remove(AddressesEntity addresses) {
        em.remove(addresses);
        em.flush();
    }
}