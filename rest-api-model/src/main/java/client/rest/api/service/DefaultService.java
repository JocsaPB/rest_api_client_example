package client.rest.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import client.rest.api.model.PersistentEntity;

public interface DefaultService<E extends PersistentEntity> {
	
	E get(Integer id);
	
	List<E> getAll();
	
	Page<E> listAllByPage(Pageable pageable);
	
	E save(E t);
	
	E update(E t);
	
	void delete(E t);
	
	void delete(Integer id);
	
}
