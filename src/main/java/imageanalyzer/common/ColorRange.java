package imageanalyzer.common;

public class ColorRange {
	int from;
	int to;

	public int getFrom() {
		return from;
	}

	public int getTo() {
		return to;
	}

	public ColorRange(int from, int to) {
		super();
		this.from = from;
		this.to = to;
	}

	public final boolean include(int color) {
		return from <= color && color <= to;
	}

}
