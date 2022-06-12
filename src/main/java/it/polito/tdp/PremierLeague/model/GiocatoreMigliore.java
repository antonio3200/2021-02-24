package it.polito.tdp.PremierLeague.model;

public class GiocatoreMigliore {

	Player p;
	double peso;
	public GiocatoreMigliore(Player p, double peso) {
		super();
		this.p = p;
		this.peso = peso;
	}
	public Player getP() {
		return p;
	}
	public void setP(Player p) {
		this.p = p;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return "Il giocatore migliore è "+p.getName()+" con delta di "+this.peso;
	}
	
}
