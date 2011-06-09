package br.edu.gamaesouza.intranet.utils;

public enum PaisEnum {
	BRASIL("Brasil"),
	BRUNEI("Brunei"),
	BULGARIA("Bulg�ria"),
	CANADA("Canad� ");

	private String name;

    private PaisEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
