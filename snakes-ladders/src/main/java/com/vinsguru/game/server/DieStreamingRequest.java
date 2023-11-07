package com.vinsguru.game.server;

import com.vinsguru.game.Die;
import com.vinsguru.game.GameState;
import com.vinsguru.game.Player;
import io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom;
import io.grpc.stub.StreamObserver;

import java.util.stream.Stream;

public class DieStreamingRequest implements StreamObserver<Die> {

    private Player client;
    private Player server;
    private StreamObserver<GameState> gameStateStreamObserver;

    public DieStreamingRequest(Player client, Player server, StreamObserver<GameState> gameStateStreamObserver) {
        this.client = client;
        this.server = server;
        this.gameStateStreamObserver = gameStateStreamObserver;
    }

    @Override
    public void onNext(Die die) {
        System.out.println("Client on next -------->" + die.getValue());
        this.client = this.getNewPlayerPosition(this.client, die.getValue());
        if(this.client.getPosition() != 100) {
            int serverRollNumber = ThreadLocalRandom.current().nextInt(1, 7);
            System.out.println("Server on next -------->" + serverRollNumber);
            this.server = this.getNewPlayerPosition(this.server, serverRollNumber);
        }

        this.gameStateStreamObserver.onNext(this.getGameState());
        System.out.println("\n");
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        System.out.println("Die request on Completed!");
        this.gameStateStreamObserver.onCompleted();
    }

    private GameState getGameState() {
        return GameState.newBuilder()
                .addPlayer(this.client)
                .addPlayer(this.server)
                .build();
    }

    private Player getNewPlayerPosition(Player player, int dieValue) {
        int position = player.getPosition() + dieValue;
        position = SnakesAndLaddersMap.getPosition(position);
        if(position <= 100) {
            player = player.toBuilder().setPosition(position)
                    .build();
        }
        return player;
    }
}
