package objects;

public class CylinderItem extends Item {
	
	private String decriptionOfCylinder;

	public CylinderItem(String titleOfItem, String priceOfItem, String decriptionOfCylinder) {
		super(titleOfItem, priceOfItem);
		this.setDecriptionOfCylinder(decriptionOfCylinder);
		super.itemDeatils.put("decriptionOfCylinder", decriptionOfCylinder);
	}

	public String getDecriptionOfCylinder() {
		return decriptionOfCylinder;
	}

	public void setDecriptionOfCylinder(String decriptionOfCylinder) {
		this.decriptionOfCylinder = decriptionOfCylinder;
	}

}
