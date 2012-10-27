package ru.sigil.eeda.dangerouslevel;

public class DangerousEntity {
	private String Name;
	private int danger;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getDanger() {
		return danger;
	}

	public void setDanger(int danger) {
		this.danger = danger;
	}

}
