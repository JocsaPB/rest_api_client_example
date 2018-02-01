package client.rest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import client.rest.api.model.PersistentEntity;
import client.rest.api.service.DefaultService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public abstract class DefaultController<E extends PersistentEntity, S extends DefaultService<E>> {

	@Autowired
	private S service;
	
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Entidade obtida com sucesso"),
					@ApiResponse(code = 204, message = "Não foi encontrado nenhum registro para o id informado")
				}
			)
	@ApiOperation(value = "Retorna uma entidade com base no controller chamado")
	@GetMapping(value = "/{id}")
	public ResponseEntity<E> getOne(@PathVariable Integer id) {
		E e = (E) service.get(id);
		
		if( e == null ) {
			return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Lista de entidade retornada com sucesso"),
					@ApiResponse(code = 204, message = "Nenhum dado encontrado para a tabela da entidade informada")
				}
			)
	@ApiOperation(value = "Retorna uma lista de um tipo de entidade com base no controller chamado")
	@GetMapping
	public ResponseEntity<List<E>> getAll(){
		
		List<E> e = service.getAll();
		
		if( e == null || e.size() == 0 ) {
			return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Lista de entidade retornada com sucesso"),
					@ApiResponse(code = 204, message = "Nenhum dado encontrado para a tabela da entidade informada")
				}
			)
	@ApiOperation(value = "Retorna uma lista de um tipo de entidade com base no controller chamado e paginado pelo número da página e número de registros. Ex: nome-controller/pagination?page=0?size=5")
	@RequestMapping(value = "/pagination", method = RequestMethod.GET)
	public ResponseEntity<Page<E>> getAllWithPagination(Pageable pageable){
		
		Page<E> e = service.listAllByPage(pageable);
		
		if( e == null ) {
			return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Requisição realizada com sucesso"),
					@ApiResponse(code = 201, message = "Objeto salvo com sucesso"),
					@ApiResponse(code = 406, message = "Não foi possível salvar objeto passado"),
					@ApiResponse(code = 400, message = "Dados não passados corretamente"),
					@ApiResponse(code = 500, message = "Erro interno.")
				}
			)
	@ApiOperation(value = "Salva o objeto da entidade especificada e retorna no corpo da resposta o objeto salvo")
	@PostMapping(consumes = "application/json")
	public ResponseEntity<E> save(@RequestBody E obj) {
		E e =  (E) service.save(obj);
		
		if( e == null ) {
			return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<>(e, HttpStatus.CREATED);
	}
	
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Objeto atualizado com sucesso"),
					@ApiResponse(code = 406, message = "Não foi possível atualizar objeto passado"),
					@ApiResponse(code = 400, message = "Dados não passados corretamente"),
					@ApiResponse(code = 500, message = "Erro interno.")
				}
			)
	@ApiOperation(value = "Atualiza o objeto da entidade especificada e retorna no corpo da resposta o objeto atualizado")
	@PutMapping(consumes = "application/json")
	public ResponseEntity<E> update(@RequestBody E obj) {
		
		E eOld = (E) service.get(obj.getId());
		
		obj.setDhCriacao(eOld.getDhCriacao());
		
		E e =  (E) service.save(obj);
		
		
		if( e == null ) {
			return new ResponseEntity<>(e, HttpStatus.NOT_ACCEPTABLE);
		}
		
		return new ResponseEntity<>(e, HttpStatus.OK);
	}
	
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Objeto removido com sucesso"),
					@ApiResponse(code = 406, message = "Não foi possível remover objeto passado"),
					@ApiResponse(code = 500, message = "Erro interno.")
				}
			)
	@ApiOperation(value = "Realiza a remoção lógica de um objeto com base no id do objeto passado no corpo da requisição")
	@DeleteMapping(consumes = "application/json")
	public void delete(@RequestBody E obj) {
		
		E entity = (E) service.get(obj.getId());
		
		if(entity != null) {
			service.delete(entity);
		}
	}
	
	@ApiResponses(
			value = {
					@ApiResponse(code = 200, message = "Objeto removido com sucesso"),
					@ApiResponse(code = 406, message = "Não foi possível remover objeto com base no ID informado"),
					@ApiResponse(code = 500, message = "Erro interno.")
				}
			)
	@ApiOperation(value = "Realiza a remoção lógica de um objeto com base no id informado na requisição")
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Integer id) {
		
		E entity = (E) service.get(id);
		
		if(entity != null) {
			service.delete(entity);
		}
	}
	
	
}
