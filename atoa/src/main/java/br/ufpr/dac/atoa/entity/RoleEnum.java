package br.ufpr.dac.atoa.entity;

public enum RoleEnum {
	
	GERENTE("ROLE_GERENTE"), FUNCIONARIO("ROLE_FUNCIONARIO");
	
	private String value;
	
	private RoleEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
}
