package controllers;

import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;

import models.Property;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.http.javadsl.model.HttpRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;

/**
 * This controller contains CRUD methods for Properties.
 */
 @Api(value = "/properties", produces = "application/json")
public class PropertyController extends Controller {
	
	@ApiOperation(
		consumes = "application/json",
		value = "Get all properties",
		httpMethod = "GET",
		response = Property.class
	)
    public Result index() {
    	JsonNode result = null;
    	result = Json.newObject();
    	result = Json.parse("{\"name\":\"property\"}");
        return ok(result);
    }
	
	@ApiOperation(
		consumes = "application/json",
		value = "Create property",
		httpMethod = "POST",
		response = Property.class
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "body", value = "Property", required = true, dataType = "models.Property", paramType = "body")
	})
	
	public Result add() {
		JsonNode k = request().body().asJson();
		return ok(k);
	}
	public Result findById(Integer id) {
		return ok(views.html.index.render());
	}
	public Result edit(Integer id) {
		return ok(views.html.index.render());
	}
	public Result delete(Integer id) {
		return ok(views.html.index.render());
	}
}