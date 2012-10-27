package ru.sigil.eeda.utils;

public class EAdditiveEntity {

	private String name;
	private int number;
	private boolean favorite;
	private int danger;
	private int ID;
	public static int DANGER_BANNED = 2;
	public static int DANGER_WARNING = 1;
	public static int DANGER_SAFE = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return AdditivesBaseAdapter.getTextByID(this.ID);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
		if (favorite) {
			AdditivesBaseAdapter.addFavorite(getID());
		} else {
			AdditivesBaseAdapter.removeFavorite(getID());
		}
	}

	public int getDanger() {
		return danger;
	}

	public void setDanger(int danger) {
		this.danger = danger;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
