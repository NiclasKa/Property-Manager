package models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.converter.ModelConverters;
import play.Application;

import play.data.validation.Constraints;
import io.ebean.Model;
import io.ebean.Finder;
import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
@ApiModel
public class Property extends Model {
	
	@Id
	public Integer id;
	@Constraints.Required
	public String name;
	public String address;
	public String number;
	public Integer postalCode;
	public String city;
	public String country;
	public String description;
	public String coordinates;
	
	
	public static Finder<Integer, Property> find = new Finder<>(Property.class);
}