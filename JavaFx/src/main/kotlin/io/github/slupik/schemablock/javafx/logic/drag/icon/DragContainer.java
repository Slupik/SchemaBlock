package io.github.slupik.schemablock.javafx.logic.drag.icon;

import javafx.scene.input.DataFormat;
import javafx.util.Pair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DragContainer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1890998765646621338L;

	static final DataFormat AddNode =
			new DataFormat("application.DragGhostIcon.add");
	
	private final List <Pair<String, Object> > mDataPairs = new ArrayList <Pair<String, Object> > ();
	
	void addData (String key, Object value) {
		mDataPairs.add(new Pair<>(key, value));
	}

	public <T> T getValue (String key) {
		
		for (Pair<String, Object> data: mDataPairs) {
			
			if (data.getKey().equals(key))
				return (T) data.getValue();

		}
		
		return null;
	}
	
	List <Pair<String, Object> > getData () { return mDataPairs; }
}
