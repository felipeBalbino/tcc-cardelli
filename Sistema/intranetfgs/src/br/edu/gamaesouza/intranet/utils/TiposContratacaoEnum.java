package br.edu.gamaesouza.intranet.utils;

public enum TiposContratacaoEnum {
	CLT("CLT"),
	PJ("PJ"),
	CLT_FLEX("Clt-Flex"),
	COOPERADO("Cooperado"),
	ESTAGIO("Estágio"),
	OUTRO("Outros");
   
	private String name;

    private TiposContratacaoEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
