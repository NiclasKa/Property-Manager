package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;

import models.Property;

import java.util.List;
import java.util.ArrayList;
import play.db.ebean.Transactional;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.http.javadsl.model.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;

import play.libs.ws.*;
import play.libs.ws.WSBodyReadables.*;
import java.util.concurrent.CompletionStage;

/**
 * This controller contains CRUD methods for Properties.
 */
@Api(value = "/properties", produces = "application/json")
public class PropertyController extends Controller {
	@Inject WSClient ws;
	
	@Inject
    FormFactory formFactory;
	
	@Inject
	ObjectMapper mapper = new ObjectMapper();
	
	/*
	 * Encountered a problem with Ebean and Swagger.
	 * The following hack will remove _ebean_intercept from the request, so it can be validated properly.
	 */
	public JsonNode parseBody(JsonNode body) {
        ObjectNode object = body.deepCopy();
        object.remove("_ebean_intercept");
        JsonNode parsedBody = mapper.valueToTree(object); 
        return parsedBody;
	}
	
	@ApiOperation(
		consumes = "application/json",
		value = "Get all properties",
		httpMethod = "GET"
	)
    public Result properties() {
		try {
			List<Property> properties = Property.find.all();
	    	String result = mapper.writeValueAsString(properties); 
	        return ok(result);
		} catch (Exception e) {
			return badRequest(e.getMessage());
		}
    }
	
	/*
	 * Operation to get properties by country, sorted by City.
	 */
	@ApiOperation(
			consumes = "application/json",
			value = "Get all properties",
			httpMethod = "GET"
		)
	    public Result findByCountry(String country) {
			try {
				//JsonNode body = request.body().asJson();
				List<Property> properties = Property.find.query().where()
				        .ilike("country", country)
				        .orderBy("city asc")
				        .setFirstRow(0)
				        .setMaxRows(500)
				        .findPagedList()
				        .getList();
		    	String result = mapper.writeValueAsString(properties); 
		        return ok(result);
			} catch (Exception e) {
				return badRequest(e.getMessage());
			}
	    }
	
	@ApiOperation(
			consumes = "application/json",
			value = "Get property by id",
			httpMethod = "GET",
			response = Property.class
	)
    public Result findById(Integer id) {
		try {
			Property property = Property.find.byId(id);
	        if (property == null) {
	            return notFound("Not found!");
	        }
	    	String result = mapper.writeValueAsString(property); 
	        return ok(result);
		} catch (Exception e) {
			return badRequest(e.getMessage());
		}
    }
    
	@ApiOperation(
		consumes = "application/json",
		value = "Create property",
		httpMethod = "POST"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "body", value = "Property", required = true, dataType = "models.Property", paramType = "body")
	})
	public Result add() {
		try {
	        JsonNode parsedBody = parseBody(request().body().asJson());
			Form<Property> propertyForm = formFactory.form(Property.class).bind(parsedBody);
			if (propertyForm.hasErrors()) {
				return badRequest("Bad Request!");
			}
			Property property = propertyForm.get();
			String jsonString = mapper.writeValueAsString(property);
			
			// Get coordinates using GeoApify
			String url = "https://api.geoapify.com/v1/geocode/search?street=" + property.address + "&city=" + property.city + "&country=" + property.country + "&format=json&apiKey=e6e1ae81208d417180cf919148672041";
			JsonNode response = ws.url(url).get().toCompletableFuture().get().asJson();
			String coordinates = "[" + response.findValue("lon") + "," + response.findValue("lat") + "]";
			property.coordinates = coordinates;
			
			property.save();
			return ok("Succesful!");
		} catch (Exception e) {
			return badRequest(e.getMessage());
		}
	}
	
	@ApiOperation(
			consumes = "application/json",
			value = "Edit property",
			httpMethod = "PUT"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "body", value = "Property", required = true, dataType = "models.Property", paramType = "body")
	})
	public Result edit(Integer id) {
		try {
			Property property = Property.find.byId(id);
	        if (property == null) {
	            return notFound("Not found!");
	        }
	        JsonNode parsedBody = parseBody(request().body().asJson());
			Form<Property> propertyForm = formFactory.form(Property.class).bind(parsedBody);
			if (propertyForm.hasErrors()) {
				return badRequest("Bad Request!");
			}
			Property updatedProperty = propertyForm.get();
			property.name = updatedProperty.name;
			property.address = updatedProperty.address;
			property.postalCode = updatedProperty.postalCode;
			property.number = updatedProperty.number;
			property.city = updatedProperty.city;
			property.country = updatedProperty.country;
			property.description = updatedProperty.description;
			
			// Get coordinates using GeoApify
			String url = "https://api.geoapify.com/v1/geocode/search?street=" + property.address + "&city=" + property.city + "&country=" + property.country + "&format=json&apiKey=e6e1ae81208d417180cf919148672041";
			JsonNode response = ws.url(url).get().toCompletableFuture().get().asJson();
			String coordinates = "[" + response.findValue("lon") + "," + response.findValue("lat") + "]";
			property.coordinates = coordinates;
			
			property.update();
			return ok("Succesful!");
		} catch (Exception e) {
			return badRequest(e.getMessage());
		}
	}
	
	@ApiOperation(
			consumes = "application/json",
			value = "Edit property",
			httpMethod = "DELETE"
	)
	public Result delete(Integer id) {
		try {
			Property property = Property.find.byId(id);
	        if(property == null) {
	            return notFound("Not found!");
	        }
	        Boolean deleted = property.delete();
	        return ok(deleted.toString());
		} catch (Exception e) {
			return badRequest(e.getMessage());
		}
	}
}