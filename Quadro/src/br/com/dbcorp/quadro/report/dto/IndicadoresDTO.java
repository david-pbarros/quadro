package br.com.dbcorp.quadro.report.dto;

import java.time.LocalDate;

public class IndicadoresDTO {

	private LocalDate data;
	private String indicadores;
	private String volantes;
	private String tpAssembleia;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public String getIndicadores() {
		return indicadores;
	}
	public void setIndicadores(String indicadore1, String indicadore2, String indicadore3) {
		this.indicadores = indicadore1 + " / " + indicadore2 + " / " + indicadore3;
	}
	
	public String getVolantes() {
		return volantes;
	}
	public void setVolantes(String volante1, String volante2) {
		this.volantes = volante1 + " / " + volante2;
	}
	
	public String getTpAssembleia() {
		return tpAssembleia;
	}
	public void setTpAssembleia(String tpAssembleia) {
		this.tpAssembleia = tpAssembleia;
	}
}
