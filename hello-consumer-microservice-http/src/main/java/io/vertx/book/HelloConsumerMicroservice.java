package io.vertx.book;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.*;
import io.vertx.ext.web.client.*;
import io.vertx.ext.web.codec.BodyCodec;
import rx.Single;

public class HelloConsumerMicroservice extends AbstractVerticle {

private WebClient client;

    @Override
    public void start() {
		
		client = WebClient.create(vertx);
		
		Router router = Router.router(vertx);
		
		router.get("/").handler(this::invokeMyFirstMicroservice);
		 vertx.createHttpServer()
			.requestHandler(router::accept)
			.listen(8082);
		
    }
	/*
	private void invokeMyFirstMicroservice(RoutingContext rc){
		HttpRequest<JsonObject> request = client
			.get(8081, "localhost","/Bismarck")
			.as(BodyCodec.jsonObject());

			
			request.send(ar -> {
				if(ar.failed()){
					rc.fail(ar.cause());
				}else{
					rc.response().end(ar.result().body().encode());
				}
				
			});
		
	}
	*/
	private void invokeMyFirstMicroservice(RoutingContext rc){
		    HttpRequest<JsonObject> request1 = client
			.get(8081, "localhost","/Son")
			.as(BodyCodec.jsonObject());

			
			HttpRequest<JsonObject> request2 = client
			.get(8081, "localhost","/Goku")
			.as(BodyCodec.jsonObject());
			
			Single<JsonObject> s1 = request1.rxSend()
				.map(HttpResponse::body);
			Single<JsonObject> s2 = request2.rxSend()
				.map(HttpResponse::body);	
			Single
				.zip(s1,s2,(Son, Goku) -> {
					return new JsonObject()
						.put("Son", Son.getString("message"))
						.put("Goku", Goku.getString("message"));
					
				})
				.subscribe(
					result -> rc.response().end(result.encodePrettily()),
					error -> {
						error.printStackTrace();
						rc.response()
							.setStatusCode(500).end(error.getMessage());
					}
	);
}

}
