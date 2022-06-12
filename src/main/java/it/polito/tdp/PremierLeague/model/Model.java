package it.polito.tdp.PremierLeague.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private PremierLeagueDAO dao;
	private Map<Integer,Player> idMap;
	private SimpleDirectedWeightedGraph<Player,DefaultWeightedEdge> grafo;
	private List<Player> vertici;
	
	public Model() {
		dao= new PremierLeagueDAO();
		idMap= new HashMap<Integer,Player>();
		this.dao.listAllPlayers(idMap);
	}
	
	public List<Match> getMatches(){
		return this.dao.listAllMatches();
	}
	
	public void creaGrafo(Match m) {
		this.grafo= new SimpleDirectedWeightedGraph(DefaultWeightedEdge.class);
		//Aggiungo i vertici
		this.vertici=this.dao.getPlayerPartita(m.getMatchID(), idMap);
		Graphs.addAllVertices(this.grafo, this.vertici);
		//Aggiungo gli archi
		for(Arco a : this.dao.getArco(m.getMatchID(), idMap)) {
			if(a.getPeso()>=0) {
				if(this.grafo.containsVertex(a.getP1()) && this.grafo.containsVertex(a.getP2())) {
					Graphs.addEdgeWithVertices(this.grafo, a.getP1(), a.getP2(), a.getPeso());
				}
			}
			else {
				if(this.grafo.containsVertex(a.getP1()) && this.grafo.containsVertex(a.getP2())) {
					Graphs.addEdgeWithVertices(this.grafo, a.getP2(), a.getP1(), -a.getPeso());
				}
			}
		}
		System.out.format("Grafo creato con %d vertici e %d archi",this.grafo.vertexSet().size(),this.grafo.edgeSet().size());
	}
	
	
	public int numeroVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int numeroArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public GiocatoreMigliore getGiocatoreMigliore(){
		List<GiocatoreMigliore> giocatori= new LinkedList<GiocatoreMigliore>();
		double max=0.0;
		for(Player p : this.vertici) {
			double pesoEntrante=0.0;
			for(DefaultWeightedEdge e : this.grafo.incomingEdgesOf(p)) {
				pesoEntrante+=this.grafo.getEdgeWeight(e);
			}
			double pesoUscente=0.0;
			for(DefaultWeightedEdge e : this.grafo.outgoingEdgesOf(p)) {
				pesoUscente += this.grafo.getEdgeWeight(e);
			}
			double delta=pesoUscente-pesoEntrante;
			GiocatoreMigliore g = new GiocatoreMigliore(p,delta);
			giocatori.add(g);
		}
		for(GiocatoreMigliore gm : giocatori) {
			if(gm.getPeso()>max) {
				max=gm.getPeso();
			}
		}
		for(GiocatoreMigliore gmi: giocatori) {
			if(gmi.getPeso()==max) {
				return gmi;
			}
		}
		return null;
	}
}
