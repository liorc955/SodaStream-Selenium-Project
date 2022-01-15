package objects;

public class PackItem extends Item {
	
	private String colorOfPack;
	private String typeOfPack;
	
	
	public PackItem(String titleOfItem, String priceOfItem, String colorOfPack, String typeOfPack) {
		super(titleOfItem, priceOfItem);
		this.setColorOfPack(colorOfPack);
		this.setTypeOfPack(typeOfPack);
		super.itemDeatils.put("colorOfPack", colorOfPack);
		super.itemDeatils.put("typeOfPack", typeOfPack);
	}


	public String getColorOfPack() {
		return colorOfPack;
	}


	public void setColorOfPack(String colorOfPack) {
		this.colorOfPack = colorOfPack;
	}


	public String getTypeOfPack() {
		return typeOfPack;
	}


	public void setTypeOfPack(String typeOfPack) {
		this.typeOfPack = typeOfPack;
	}

}
