package br.edu.gamaesouza.intranet.utils;

public enum AreaEnum {
	TI("Informática/ TI/ Internet"),
	TELECOMUNICACAO("Telecomunicações"),
	TECNICOS("Técnicos - Manutenção, Instalação e Reparos"),
	TURISMO("Turismo e Hotelaria"),
	VENDAS("Vendas"),
	TELEMARKETING("Telemarketing / Atendimento ao Cliente"),
	MARKETING("Marketing");
   
	private String name;

    private AreaEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
