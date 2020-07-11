
package question1;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "car")
@XmlType(propOrder = { "id", "name", "model", "regarea"})
public class car {
	

	private int id;
	private String name;
	private String model;
	private String regarea;
	
	public car() {}
	public car(int id,String name, String model,String regarea) {
		super();
		this.id =id;
		this.name=name;
		this.model =model;
		this.regarea =regarea;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRegarea() {
		return regarea;
	}
	public void setRegarea(String regarea) {
		this.regarea = regarea;
	}
	
}
