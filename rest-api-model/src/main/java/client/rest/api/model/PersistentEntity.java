package client.rest.api.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import client.rest.api.enums.StatusRegistroEnum;
import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public abstract class PersistentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2874270447663013500L;

	@ApiModelProperty(notes = "ID da entidade gerada automaticamente")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	// Status do registro atualizado automaticamente de acordo com a ação ao recurso
	@JsonIgnore
	@Column(name = "STATUSREGISTRO", nullable = false)
	private Integer statusRegistro;
	
	// Data de criação gerada automaticamente
	@JsonIgnore
	@Column(name = "DHCRIACAO", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dhCriacao;
	
	// Data de atualização gerada automaticamente
	@JsonIgnore
	@Column(name = "DHATUALIZACAO", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dhAtualizacao;
	
	@PrePersist
	protected void onPersiste() {
		this.dhCriacao = Date.from(Instant.now());
		this.dhAtualizacao = Date.from(Instant.now());
		this.statusRegistro = StatusRegistroEnum.ATIVO.getCodigo();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.dhAtualizacao = Date.from(Instant.now());
		this.statusRegistro = StatusRegistroEnum.ATIVO.getCodigo();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatusRegistro() {
		return statusRegistro;
	}

	public void setStatusRegistro(Integer statusRegistro) {
		this.statusRegistro = statusRegistro;
	}

	public Date getDhCriacao() {
		return dhCriacao;
	}

	public void setDhCriacao(Date dhCriacao) {
		this.dhCriacao = dhCriacao;
	}

	public Date getDhAtualizacao() {
		return dhAtualizacao;
	}

	public void setDhAtualizacao(Date dhAtualizacao) {
		this.dhAtualizacao = dhAtualizacao;
	}
	
}
