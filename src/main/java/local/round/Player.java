package local.round;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private int points;
	private List<Choice> choices;
	private boolean abandoned;
	private Strat strategy;

	public Player(String name) {
		this.name = name;
		this.points = 0;
		this.choices = new ArrayList<Choice>();
		this.abandoned = false;
	}

	public boolean isAbandoned() {
		return abandoned;
	}

	public void setAbandoned(boolean abandoned) {
		this.abandoned = abandoned;
	}

	public Player() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Choice getChoice(int numRencontre) {
		return choices.get(numRencontre - 1);
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public void addChoice(Choice choice) {
		this.choices.add(choice);
	}

	public Strat getStrategy() {
		return strategy;
	}

	public void setStrategy(Strat strategy) {
		this.strategy = strategy;
	}

}
