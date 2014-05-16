package gameClasses;
import java.util.ArrayList;
import java.util.List;

public class Menu{
	
	public Menu() {
		@SuppressWarnings("unused")
		List<MenuItem> items = new ArrayList<MenuItem>();;
	}
	
	List<MenuItem> items;
	
	@SuppressWarnings("unused")
	private List<MenuItem> getItems() {
		  return items;
	}

	@SuppressWarnings("unused")
	private void setItems(List<MenuItem> items) {
		  this.items = items;
	}
	
	@SuppressWarnings("unused")
	private void addItem(MenuItem item) {
		  this.items.add( item);
		}
	
	public void selectNext() {
		for (int i = 0; i < items.size() - 1; i++) {
			
			if (items.get(i).getId() == items.size() - 1) {
				items.get(i).setIsSelected(false);
				items.get(0).setIsSelected(true);
				}
			
			
			
			if (items.get(i).getIsSelected()) {
				items.get(i).setIsSelected(false);
				items.get(i+1).setIsSelected(true);
			}
		}
	
	}
	
	public void selectPrev() {
		for (int i = 0; i < items.size() - 1; i++) {
			
			if (items.get(i).getId() == 0) {
				items.get(i).setIsSelected(false);
				items.get(items.size() - 1).setIsSelected(true);
				}
			
			
			
			if (items.get(i).getIsSelected()) {
				items.get(i).setIsSelected(false);
				items.get(i-1).setIsSelected(true);
			}

		}
	
	}
	
	@SuppressWarnings("unused")
	private int getSelectedId() {
		int a = 0;
		for (int i = 0; i < items.size() - 1; i++) {
			if (items.get(i).getIsSelected()) {
				a = items.get(i).getId();
			}
		}
		return a;
	}
	
	}