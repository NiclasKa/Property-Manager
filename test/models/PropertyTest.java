package models;

import play.Application;
import play.test.WithApplication;
import play.test.Helpers;
import static play.test.Helpers.*;
import play.inject.guice.GuiceApplicationBuilder;
import static play.mvc.Http.Status.OK;
import static org.junit.Assert.*;
import play.mvc.Http;
import play.mvc.Result;

import org.junit.Test;


public class PropertyTest extends WithApplication{
	
	@Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }
	
	/* Test properties GET method.
	 * Test is passed, if request does not fail.
	 */
    @Test
    public void testProperties() {
        Http.RequestBuilder request = Helpers.fakeRequest()
                .method(GET)
                .uri("/properties");

        Result result = route(app, request);
        assertEquals(OK, result.status());
    }

}
