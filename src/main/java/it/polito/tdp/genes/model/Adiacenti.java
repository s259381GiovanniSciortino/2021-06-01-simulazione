package it.polito.tdp.genes.model;

public class Adiacenti implements Comparable<Adiacenti>{
	Genes geneVicino;
	Double peso;
	public Adiacenti(Genes geneVicino, Double peso) {
		super();
		this.geneVicino = geneVicino;
		this.peso = peso;
	}
	public Genes getGeneVicino() {
		return geneVicino;
	}
	public void setGeneVicino(Genes geneVicino) {
		this.geneVicino = geneVicino;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Adiacenti other) {
		
		return -(this.getPeso().compareTo(other.getPeso()));
	}
	@Override
	public String toString() {
		return geneVicino+" | "+peso;
	}
	
}
