package controllers;

import play.mvc.*;

import models.Property;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiImplicitParam;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
 @Api(value = "Publication Queries", produces = "application/json")
public class PropertyController extends Controller {
	
    public Result index() {
        return ok(views.html.index.render());
    }
	@ApiOperation(
		consumes = "application/json",
		value = "Example",
		httpMethod = "POST"
	)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "name", value = "value", required = true, dataType = "models.Property", paramType = "body")
	})
	public Result add() {
		return ok(views.html.index.render());
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