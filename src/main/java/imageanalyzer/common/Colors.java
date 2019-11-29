package imageanalyzer.common;

import java.awt.Color;

public class Colors {
	public static ColorRange colorrange(int from, int to) {
		return new ColorRange(from, to);
	}

	public static int color(int red) {
		return new Color(red, red, red).getRGB();
	}

	public static int color(int r, int g, int b) {
		return new Color(r, g, b).getRGB();
	}
}
