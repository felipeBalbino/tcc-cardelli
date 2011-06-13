package br.edu.gamaesouza.intranet.utils;

public enum EnumTipoTelefone {
	RESIDENCIAL("Residencial"),
	COMERCIAL("Comercial"),
	CELULAR("Celular"),
	RECADO("Recado");

	private String name;

    private EnumTipoTelefone(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
