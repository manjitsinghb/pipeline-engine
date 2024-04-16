package com.pipeline.engine.core;

import com.spring.api.Consumer;
import com.spring.api.Publisher;
import com.spring.api.Transformer;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import java.util.List;

public class Pipeline<U, V> {

    private ConnectableFlux<V> connectableFlux;

    public void buildPipeline(Consumer<U> consumer, Transformer<U, V> transformer, List<Publisher<V>> publisherList) {
        Flux<U> flux = Flux.generate(consumer::consumeMessage, (state, sink) -> {
            sink.next(state);
            return state;
        });
        ConnectableFlux<U> connectableFlux = flux.publish();
        ConnectableFlux<V> transformedFlux = connectableFlux.map(transformer::transform).publish();
        for (Publisher<V> publisher : publisherList) {
            transformedFlux.subscribe(publisher::publish);
        }
        this.connectableFlux = transformedFlux;
    }

    public void startPipeline(){
        if(this.connectableFlux == null){
            throw new IllegalArgumentException("Build pipeline before starting the pipeline");
        }
        this.connectableFlux.connect();
    }
}
