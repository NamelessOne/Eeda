package ru.sigil.eeda;

import java.util.ArrayList;
import java.util.List;

public class MainListEntityCollection {
	private List<MainListEntity> listEntities = new ArrayList<MainListEntity>();

	public List<MainListEntity> getEntities() {
		return listEntities;
	}

	public void setEntities(List<MainListEntity> newMusicEntities) {
		listEntities = newMusicEntities;
	}

	public void add(MainListEntity entity) {
		listEntities.add(entity);
	}

	public void addAll(List<MainListEntity> entities) {
		listEntities.addAll(entities);
	}

	public void clear() {
		listEntities.clear();
	}
}
