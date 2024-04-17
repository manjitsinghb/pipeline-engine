package com.pipeline.engine.core;

import com.spring.api.Consumer;
import com.spring.api.Publisher;
import com.spring.api.Transformer;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.List;

public class Pipeline<U, V> {

    public void buildAndStartPipeline(Consumer<U> consumer, Transformer<U, V> transformer, List<Publisher<V>> publisherList, String name, int parallelism) {
        Flux<U> flux = Flux.generate(consumer::consumeMessage, (state, sink) -> {
            sink.next(state);
            return state;
        });
        Flux<V> transformedFlux = flux.map(transformer::transform).publishOn(Schedulers.newParallel(name+"-pool", parallelism));
        for (Publisher<V> publisher : publisherList) {
            transformedFlux.subscribe(publisher::publish);
        }
    }
}
