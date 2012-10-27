package ru.sigil.eeda.hundredslevel;

public class NumbersLevel1Entity {
	private int LeftImage;
	private String Text;
	private int maxNum;
	private int minNum;

	public int getLeftImage() {
		return LeftImage;
	}

	public void setLeftImage(int leftImage) {
		LeftImage = leftImage;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public int getMinNum() {
		return minNum;
	}

	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}
}
