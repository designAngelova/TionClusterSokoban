package gameClasses;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	public Menu() {
		this.items = new ArrayList<MenuItem>();
	}

	List<MenuItem> items;

	public List<MenuItem> getItems() {
		return this.items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}

	public void addItem(MenuItem item) {
		this.items.add(item);
	}

	public void selectNext() {
		for (int i = 0; i < this.items.size() - 1; i++) {
			if (this.items.get(i).getIsSelected()) {
				if (this.items.get(i).getId() == items.size() - 1) {
					this.items.get(i).setIsSelected(false);
					this.items.get(0).setIsSelected(true);
				} else {
					this.items.get(i).setIsSelected(false);
					this.items.get(i + 1).setIsSelected(true);
					break;
				}

				break;
			}
		}
	}

	public void selectPrev() {
		for (int i = 0; i < items.size() - 1; i++) {
			if (this.items.get(i).getIsSelected()) {
				if (items.get(i).getId() == 0) {
					items.get(i).setIsSelected(false);
					items.get(items.size() - 1).setIsSelected(true);
				} else {
					items.get(i).setIsSelected(false);
					items.get(i - 1).setIsSelected(true);
				}
				
				break;
			}
		}
	}

	public int getSelectedId() {
		int a = 0;
		for (int i = 0; i < items.size() - 1; i++) {
			if (items.get(i).getIsSelected()) {
				a = items.get(i).getId();
				break;
			}
		}

		return a;
	}
}