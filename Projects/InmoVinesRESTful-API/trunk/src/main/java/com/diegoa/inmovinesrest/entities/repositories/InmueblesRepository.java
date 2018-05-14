package com.diegoa.inmovinesrest.entities.repositories;

import com.diegoa.inmovinesrest.entities.inmuebles.Inmuebles;
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
public class InmueblesRepository implements JpaRepository<Inmuebles,Long> {

    @Autowired
    EntityManagerFactory emFactory;

    @Override
    public List<Inmuebles> findAll() {
        return emFactory.createEntityManager().createQuery("select i from Inmuebles i").getResultList();
    }

    @Override
    public List<Inmuebles> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Inmuebles> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Inmuebles> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Inmuebles inmuebles) {

    }

    @Override
    public void deleteAll(Iterable<? extends Inmuebles> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Inmuebles> S save(S s) {
        return null;
    }

    @Override
    public <S extends Inmuebles> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Inmuebles> findById(Long aLong) {
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
    public <S extends Inmuebles> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Inmuebles> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Inmuebles getOne(Long aLong) {
        return null;
    }

    @Override
    public <S extends Inmuebles> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Inmuebles> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Inmuebles> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Inmuebles> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Inmuebles> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Inmuebles> boolean exists(Example<S> example) {
        return false;
    }
}
