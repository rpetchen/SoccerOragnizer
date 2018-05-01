package com.treehouse.enums;

public enum HeightRange {
	SHORT(35, 40), MEDIUM(41, 46), TALL(47, 50);

	private final int minHeight;
	private final int maxHeight;

	HeightRange(int min, int max) {
		minHeight = min;
		maxHeight = max;

	}

	public int getMinHeight() {
		return minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}
}
