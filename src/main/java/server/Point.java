package server;

public enum Point {
	T(5), D(0), C(3), P(1);
	
	public final int value;

    private Point(int value) {
        this.value = value;
    }

	public int getValue() {
        return value;
    }
}