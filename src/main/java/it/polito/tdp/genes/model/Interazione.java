package it.polito.tdp.genes.model;

public class Interazione {
	String geneID1;
	String geneID2;
	Double peso;
	public Interazione(String geneID1, String geneID2, Double peso) {
		super();
		this.geneID1 = geneID1;
		this.geneID2 = geneID2;
		this.peso = peso;
	}
	public String getGeneID1() {
		return geneID1;
	}
	public void setGeneID1(String geneID1) {
		this.geneID1 = geneID1;
	}
	public String getGeneID2() {
		return geneID2;
	}
	public void setGeneID2(String geneID2) {
		this.geneID2 = geneID2;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	

}
