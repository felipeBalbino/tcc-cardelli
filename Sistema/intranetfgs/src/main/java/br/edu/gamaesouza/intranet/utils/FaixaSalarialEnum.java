package br.edu.gamaesouza.intranet.utils;

public enum FaixaSalarialEnum {
	FAIXA1("100 - 200"),
	FAIXA2("200 - 500"),
	FAIXA3("500 - 1000"),
	FAIXA4("1000 - 2000"),
	FAIXA5("2000 - 4000"),
	FAIXA6("4000 - 5000"),
	FAIXA7("5000 - 7000"),
	FAIXA8("7000 - 10000"),
	FAIXA9("10000 - 15000");
   
	private String name;

    private FaixaSalarialEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
