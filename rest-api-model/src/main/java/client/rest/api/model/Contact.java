package client.rest.api.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import client.rest.api.enums.TipoContatoEnum;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "CONTATO")
@AttributeOverride(name = "id", column = @Column(name = "IDCONTATO", unique = true, nullable = false))
@Where(clause = "statusRegistro = 1")
public class Contact extends PersistentEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7753378771253314917L;

	@ApiModelProperty(notes = "Tipo do contato deve ser informado para os tipos: 0 - Telefone Residencial; 1 - Telefone Celular; 2 - Email")
	@Column(name = "TIPOCONTATO", nullable = false)
	private Integer tipoContato;
	
	@ApiModelProperty(notes = "Valor do contato, seja o telefone ou o email do cliente")
	@Column(name = "CONTATO", nullable = false, length = 40)
	private String contato;
	
	@ApiModelProperty(notes = "ID do cliente, propriedade não pode ser null")
	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "IDCLIENTE", nullable = false)
	private Client client;
	
	public TipoContatoEnum getTipoContato() {
		if(this.tipoContato != null) {
			return TipoContatoEnum.valueOf(this.tipoContato);
		}
		return null;
	}

	public void setTipoContato(Integer tipoContato) {
		this.tipoContato = tipoContato;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Client getCliente() {
		return client;
	}

	public void setCliente(Client client) {
		this.client = client;
	}
	
}
