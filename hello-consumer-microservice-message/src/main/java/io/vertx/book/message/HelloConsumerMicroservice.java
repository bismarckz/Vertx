package io.vertx.book.message;

import io.vertx.core.AbstractVerticle;

public class HelloConsumerMicroservice extends AbstractVerticle {

    @Override
    public void start() {

	EventBus bus = vertx.eventBus();
	Single<JsonObject> obs1 = bus
	.<JsonObject>rxSend("hello", "Son")
	.map(Message::body);
	
	Single<JsonObject> obs2 = bus
	.<JsonObject>rxSend("hello", "Goku")
	.map(Message::body);
    }

	Single	.zip(obs1,obs2,(son, goku))->
	new JsonObject()
	.put("Son", Son.getString("message"))
	.put("Goku", Son.getString("message"));
	)
	.subscribe(
	x->System.out.println(x.encode()),
	Throwable::printStackTrace);
	
}
