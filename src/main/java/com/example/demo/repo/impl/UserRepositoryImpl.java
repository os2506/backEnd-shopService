package com.example.demo.repo.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Utilisateur;
import com.example.demo.repo.UserRepository;

import com.example.demo.entity.Utilisateur;
import com.example.demo.repo.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;



public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int getUserCount() {
        String jpql = "SELECT COUNT(u) FROM Utilisateur u";
        Query query = entityManager.createQuery(jpql);
        Long count = (Long) query.getSingleResult();
        return count.intValue();
    }

    @Override
    public Utilisateur saveUser(Utilisateur user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<Utilisateur> findAll() {
        String jpql = "SELECT u FROM Utilisateur u";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Utilisateur.class, id));
    }

    @Override
    public void delete(Utilisateur entity) {
        entityManager.remove(entity);
    }

	@Override
	public List<Utilisateur> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Utilisateur> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Utilisateur> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Utilisateur> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllInBatch(Iterable<Utilisateur> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Utilisateur> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Utilisateur> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Utilisateur> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Utilisateur> S save(S entity) {
		entityManager.persist(entity);
	    return entity;	
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Utilisateur> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Utilisateur> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public <S extends Utilisateur> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Utilisateur> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Utilisateur> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <S extends Utilisateur, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Utilisateur> findByUsername(String username) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Utilisateur> findByEmail(String email) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}

