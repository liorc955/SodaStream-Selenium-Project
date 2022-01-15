package objects;


import java.util.Hashtable;

public class Item {
	
	private String titleOfItem;
	private String priceOfItem;
	protected Hashtable<String, String> itemDeatils;
	
	public Item(String titleOfItem, String priceOfItem) {
		this.itemDeatils = new Hashtable<String, String>();
		this.setTitleOfItem(titleOfItem);
		this.setPriceOfItem(priceOfItem);
		this.itemDeatils.put("titleOfItem", titleOfItem);
		this.itemDeatils.put("priceOfItem", priceOfItem);
	}
	
	public Hashtable<String, String> getItemDetails() {
		return itemDeatils;
	}

	public String getTitleOfItem() {
		return titleOfItem;
	}

	public void setTitleOfItem(String titleOfItem) {
		this.titleOfItem = titleOfItem;
	}

	public String getPriceOfItem() {
		return priceOfItem;
	}

	public void setPriceOfItem(String priceOfItem) {
		this.priceOfItem = priceOfItem;
	}
	
	
}
