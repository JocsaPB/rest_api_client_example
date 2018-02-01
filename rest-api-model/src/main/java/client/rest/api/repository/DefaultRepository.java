package client.rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import client.rest.api.model.PersistentEntity;

@NoRepositoryBean
public interface DefaultRepository<E extends PersistentEntity> extends JpaRepository<E, Integer>, PagingAndSortingRepository<E, Integer>{
	
	@Modifying
	@Query("update #{#entityName} set statusRegistro = 0, dhAtualizacao = current_timestamp where id = ?1")
	void delete(Long id);

	@Modifying
	@Query("update #{#entityName} e set e.statusRegistro = 0, e.dhAtualizacao = current_timestamp where e = ?1")
	void delete(E entidade);
}
