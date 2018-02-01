package client.rest.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import client.rest.api.model.PersistentEntity;
import client.rest.api.repository.DefaultRepository;
import client.rest.api.service.DefaultService;

public class DefaultServiceImplementation<E extends PersistentEntity, R extends DefaultRepository<E>> implements DefaultService<E>{

	@Autowired
	private R repository;
	
	@Override
	public E get(Integer id) {
		return (E) repository.findOne(id);
	}

	@Override
	public List<E> getAll() {
		return repository.findAll();
	}

	@Override
	public E save(E entidade) {
		return (E) repository.save(entidade);
	}

	@Override
	public E update(E entidade) {
		// TODO Auto-generated method stub
		return (E) repository.save(entidade);
	}

	@Override
	public void delete(E entidade) {
		// TODO Auto-generated method stub
		repository.delete(entidade);
	}
	
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		repository.delete(id);
	}

	@Override
	public Page<E> listAllByPage(Pageable pageable) {
		return repository.findAll(pageable);
	}
	
}
