package client.rest.api.model;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import client.rest.api.util.CustomDateDeserializer;
import io.swagger.annotations.ApiModelProperty;

@Entity(name = "CLIENTE")
@AttributeOverride(name = "id", column = @Column(name = "IDCLIENTE", unique = true, nullable = false))
@Where(clause = "statusRegistro = 1")
public class Client extends PersistentEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -10026303372343647L;

	@ApiModelProperty(notes = "Nome não pode ser null e deve conter no máximo 50 caracteres")
	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "IDCONTATO")
	@OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade={ CascadeType.PERSIST, CascadeType.MERGE })
	private List<Contact> contact;
	
	@ApiModelProperty(notes = "Data de nascimento deve ser informada com o formado dia mês e ano. Ex: 01/01/1900")
	@JsonDeserialize(using = CustomDateDeserializer.class)
	@Column(name = "DTNASCIMENTO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtNascimento;
	
	@ApiModelProperty(notes = "Sexo deve ser informado como um char de 'M' ou 'F'")
	@Column(name = "SEXO", nullable = false)
	private char sexo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Contact> getContato() {
		return contact;
	}

	public void setContato(List<Contact> contact) {
		this.contact = contact;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
		
}
