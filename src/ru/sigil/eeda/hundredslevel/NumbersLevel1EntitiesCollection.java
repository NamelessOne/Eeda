package ru.sigil.eeda.hundredslevel;

import java.util.ArrayList;
import java.util.List;


public class NumbersLevel1EntitiesCollection {
	private List<NumbersLevel1Entity> listEntities = new ArrayList<NumbersLevel1Entity>();

	public List<NumbersLevel1Entity> getEntities() {
		return listEntities;
	}

	public void setEntities(List<NumbersLevel1Entity> newMusicEntities) {
		listEntities = newMusicEntities;
	}

	public void add(NumbersLevel1Entity entity) {
		listEntities.add(entity);
	}

	public void addAll(List<NumbersLevel1Entity> entities) {
		listEntities.addAll(entities);
	}

	public void clear() {
		listEntities.clear();
	}
}
