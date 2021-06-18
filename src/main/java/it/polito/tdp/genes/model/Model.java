package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	Graph<Genes,DefaultWeightedEdge> grafo;
	Map<String, Genes> genesIDMap;
	
	public String doCreaGrafo(){
		GenesDao dao  =new GenesDao();
		List<Genes> vertici = new ArrayList<>(dao.getAllEssentialGenes());
		genesIDMap = new HashMap<>();
		for(Genes g: vertici)
			genesIDMap.put(g.getGeneId(), g);
		
		grafo  = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, vertici);
		List<Interazione> archi = new ArrayList<>(dao.getArchi());
		
		for(Interazione e: archi) {
			Genes gene1 = genesIDMap.get(e.getGeneID1());
			Genes gene2 = genesIDMap.get(e.getGeneID2());
			if(!gene1.getGeneId().equals(gene2.getGeneId())) {
				if(gene1.getChromosome()==gene2.getChromosome()) {
					double peso = 2*e.getPeso();
					Graphs.addEdge(grafo, gene1, gene2, peso);
				}else{
					Graphs.addEdge(grafo, gene1, gene2, e.getPeso());
				}
			}
		}
		
		String result = "";
		if(this.grafo==null) {
			result ="Grafo non creato";
			return result;
		}
		result = "Grafo creato con :\n# "+this.grafo.vertexSet().size()+" VERTICI\n# "+this.grafo.edgeSet().size()+" ARCHI\n";
		return result;
	}
	
	public String doGeniAdiacenti(Genes genePartenza) {
		List<Adiacenti> adiacenti = new ArrayList<>();
		for(Genes vicino: Graphs.neighborListOf(grafo, genePartenza)) {
			double peso = grafo.getEdgeWeight(grafo.getEdge(genePartenza, vicino));
			adiacenti.add(new Adiacenti(vicino,peso));
		}
		Collections.sort(adiacenti);
		String res = "\nGeni adiacenti a "+genePartenza+"\n";
		for(Adiacenti a : adiacenti) {
			res+=a+"\n";
		}
		return res;
	}
	
	public List<Genes> getVertex(){
		List<Genes> res = new ArrayList<>(grafo.vertexSet());
		return res;
	}
}
