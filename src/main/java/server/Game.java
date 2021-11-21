package server;

public class Game {
	private int pointJ1;
	private int pointJ2;

	public Game() {
		this.setPointJ1(0);
		this.setPointJ2(0);
	}

	public void addPointToPLayer(int joueur, int nbrPoints) {
		if (joueur == 1) {
			this.setPointJ1(this.getPointJ1() + nbrPoints);
		} else {
			this.setPointJ2(this.getPointJ2() + nbrPoints);
		}
	}

	public void addPointToPLayers(int nbrPoints) {
		this.setPointJ1(this.getPointJ1() + nbrPoints);
		this.setPointJ2(this.getPointJ2() + nbrPoints);
	}

	public int getPointJ1() {
		return pointJ1;
	}

	public void setPointJ1(int pointJ1) {
		this.pointJ1 = pointJ1;
	}

	public int getPointJ2() {
		return pointJ2;
	}

	public void setPointJ2(int pointJ2) {
		this.pointJ2 = pointJ2;
	}
}
