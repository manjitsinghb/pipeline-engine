package com.pipeline.engine.core.builder;


import com.pipeline.engine.core.Pipeline;
import com.spring.api.Consumer;
import com.spring.api.Publisher;
import com.spring.api.Transformer;

import java.util.List;

public class PipelineBuilder<U, V> {

    private Consumer<U> consumer;

    private List<Publisher<V>> publisherList;

    private Transformer<U, V> transformer;

    private int parallelism = 1;

    private String pipelineName;

    public PipelineBuilder(String pipelineName) {
        this.pipelineName = pipelineName;
    }


    public PipelineBuilder<U, V> withConsumer(Consumer<U> consumer) {
        this.consumer = consumer;
        return this;
    }

    public PipelineBuilder<U, V> withPublishers(List<Publisher<V>> publisherList) {
        this.publisherList = publisherList;
        return this;
    }

    public PipelineBuilder<U, V> withTransformer(Transformer<U, V> transformer) {
        this.transformer = transformer;
        return this;
    }

    public PipelineBuilder<U, V> withParallelism(int parallelism) {
        this.parallelism = parallelism;
        return this;
    }

    public Pipeline buildPipeline() {
        Pipeline pipeline = new Pipeline();
        pipeline.buildAndStartPipeline(this.consumer, this.transformer, this.publisherList, this.pipelineName, this.parallelism);
        return pipeline;
    }

}
