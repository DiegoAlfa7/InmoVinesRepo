package com.diegoa.inmovinesrest.entities.repositories;

import com.diegoa.inmovinesrest.entities.clientes.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;


@Repository
public class ClientesRepository implements JpaRepository<Clientes, Long> {

    @Autowired
    EntityManagerFactory emFactory;

    @Override
    public List<Clientes> findAll() {
        return emFactory.createEntityManager().createQuery("SELECT c FROM Clientes c ").getResultList();
    }

    @Override
    public List<Clientes> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Clientes> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Clientes> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

        emFactory.createEntityManager().createQuery("DELETE c FROM Clientes c WHERE c.id = :" + aLong);

    }

    @Override
    public void delete(Clientes clientes) {

    }

    @Override
    public void deleteAll(Iterable<? extends Clientes> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Clientes> S save(S s) {
        return null;
    }

    @Override
    public <S extends Clientes> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Clientes> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Clientes> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Clientes> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Clientes getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Clientes> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Clientes> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Clientes> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Clientes> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Clientes> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Clientes> boolean exists(Example<S> example) {
        return false;
    }
}
