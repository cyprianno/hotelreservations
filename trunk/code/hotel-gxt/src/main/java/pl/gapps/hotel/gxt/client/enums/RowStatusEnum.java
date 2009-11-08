/**
 * 
 */
package pl.gapps.hotel.gxt.client.enums;

/**
 * @author cyprian
 *
 */
public enum RowStatusEnum {
	OK(0,"ok","black"), NEW(1,"Nowy","green"), REMOVED(2,"Usunięty","red");
	
	Integer value;
	String label;
	String color;
	RowStatusEnum(Integer value, String label, String color) {
		this.value = value;
		this.label = label;
		this.color = color;
	}

	public Integer getValue() {
		return value;
	}
	public String getLabel() {
		return label;
	}

	public String getColor() {
		return color;
	}
	
}
