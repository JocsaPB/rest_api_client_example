package client.rest.api.enums;

public enum TipoContatoEnum {

	TELEFONE_RESIDENCIAL(0, "Telefone Residencial"),
	TELEFONE_CELULAR(1, "Telefone Celular"),
	EMAIL(1, "Email");
	
	private int codigo;
	private String descricao;
		
	private TipoContatoEnum(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoContatoEnum valueOf(int codigo){
		for (TipoContatoEnum tipo : TipoContatoEnum.values()) {
			if (tipo.codigo == codigo){
				return tipo;
			}
		}
		return null;
	}
		
}
