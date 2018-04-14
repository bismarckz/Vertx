package io.vertx.book.message;

import io.vertx.core.AbstractVerticle;

public class HelloMicroservice extends AbstractVerticle {

    @Override
    public void start() {

	//recibe un mensaje de la direcion hhola
	vertx.eventBus().<String>consumer("hello", message -> {
		.put("servidor-por", this.toString());
		//cheuqe quien ha recivido el mesnaje un plyload 
		if(message.body().isEmpty()){
			message.reply(json.put("message","hello"));
		}else{
			message.reply(json.put("message", "hello" + message.body()));
		}
	});
    }

}
