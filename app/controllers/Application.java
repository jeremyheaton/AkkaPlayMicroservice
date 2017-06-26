package controllers;

import akka.actor.ActorRef;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.SubscriptionMessage;
import models.Message;
import models.NewSubscription;
import play.libs.Json;
import play.mvc.*;
import scala.compat.java8.FutureConverters;


import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.concurrent.CompletionStage;

import static akka.pattern.Patterns.ask;


public class Application extends Controller {

    private ActorRef apiActor;

    @Inject
    Application(@Named("Api-Actor") ActorRef fizzActorRef){
        apiActor = fizzActorRef;
    }

    public CompletionStage<Result> index() throws IOException {
        JsonNode json = request().body().asJson();
        ObjectMapper mapper = new ObjectMapper();
        NewSubscription sub = mapper.readValue( json.toString(), NewSubscription.class);
        return FutureConverters.toJava(ask(apiActor, sub, 1000))
                .thenApply(response -> ok(response.toString()));
    }

    public CompletionStage<Result> messages() throws IOException {
        JsonNode json = request().body().asJson();
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue( json.toString(), Message.class);
        return FutureConverters.toJava(ask(apiActor, message, 1000))
                .thenApply(response -> ok(Json.toJson(response)));
    }

    public CompletionStage<Result> subscriptions(Long id) throws IOException {
        return FutureConverters.toJava(ask(apiActor, new SubscriptionMessage(id.intValue()), 1000))
                .thenApply(response -> ok(Json.toJson(response)));
    }

    public CompletionStage<Result> editSupscriptionMessage() throws IOException {
        JsonNode json = request().body().asJson();
        ObjectMapper mapper = new ObjectMapper();
        SubscriptionMessage message = mapper.readValue( json.toString(), SubscriptionMessage.class);
        return FutureConverters.toJava(ask(apiActor, message, 1000))
                .thenApply(response -> ok(Json.toJson(response)));
    }
}
