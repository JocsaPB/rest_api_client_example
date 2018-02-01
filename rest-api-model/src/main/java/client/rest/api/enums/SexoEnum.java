package client.rest.api.enums;

public enum SexoEnum {

	MASCULINO('M', "Masculino"),
	FEMININO('F', "Masculino");
	
	private char codigo;
	private String descricao;
		
	private SexoEnum(char codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public char getCodigo() {
		return codigo;
	}
	
	public void setCodigo(char codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static SexoEnum valueOf(char codigo){
		for (SexoEnum tipo : SexoEnum.values()) {
			if (tipo.codigo == codigo){
				return tipo;
			}
		}
		return null;
	}
		
}
