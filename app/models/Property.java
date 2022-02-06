package models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
//import io.ebean.Model;

@ApiModel
public class Property {
	@ApiModelProperty
	public String name;
	
	@ApiModelProperty
	public String address;
	/*public String number;
	public Integer id
	public Integer postalCode;
	public String city;
	public String country;
	public String description;
	public String coordinates*/
	
	
	public Property(String name, String address/*, String number, Integer postalCode, String city, String country, String description, String coordinates*/) {
		this.name = name;
		this.address = address;
		//this number = number;
		//this.postalCode = postalCode;
		//this.city = city;
		//this.country = country;
		//this.description = description;
		//this.coordinates = coordinates;
	}
}