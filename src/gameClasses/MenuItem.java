package gameClasses;

import java.util.ArrayList;

import java.util.List;

@SuppressWarnings("unused")
public class MenuItem {

	public MenuItem(int id, String text, boolean isSelected) {
		this.id = id;
		this.text = text;
		this.isSelected = isSelected;
	}

	private int id;
	private String text;
	private boolean isSelected;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean getIsSelected() {
		return this.isSelected;
	}

	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}