package io.vertx.book;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.core.http.HttpHeaders;
import io.vertx.ext.web.*;

public class HelloMicroservice extends AbstractVerticle {

    @Override
    public void start() {
//Ejercicio uno se levanda un servidor Http en el puerto 8080
	/*
		vertx.createHttpServer()
			.requestHandler(req -> req.response()
				.end("Hola"))
			.listen(8080);-
*/

//Segundo Ejercicio
	/*
	Router router = Router.router(vertx);
	router.get("/").handler(rc -> rc.response().end("Hola"));
	router.get("/:name").handler(rc -> rc.response().end("Holas " + rc.pathParam("name")));
	
	vertx.createHttpServer()
			.requestHandler(router::accept)
			.listen(8081);
			
    
*/
//Tercer Ejercicio
	
	Router router = Router.router(vertx);
	router.get("/").handler(this::Hola);
	router.get("/:name").handler(this::Hola);
	vertx.createHttpServer()
		.requestHandler(router::accept)
		.listen(8081);

	}
	
	private void Hola(RoutingContext rc){

		String message ="holas ";
		if(rc.pathParam("name") != null){
			message += " " +rc.pathParam("name");
		}
			JsonObject json = new JsonObject().put("message", message);
			rc.response()
				.putHeader(HttpHeaders.CONTENT_TYPE, "application/json")
				.end(json.encode());
			
	}
		
	
}
