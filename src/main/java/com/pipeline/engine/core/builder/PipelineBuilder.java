package com.pipeline.engine.core.builder;


import com.pipeline.engine.core.Pipeline;
import com.spring.api.Consumer;
import com.spring.api.Publisher;
import com.spring.api.Transformer;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

public class PipelineBuilder<U,V> {

    public List<Consumer<U>> consumerList;

    public List<Publisher<V>> publisherList;

    public List<Transformer<U,V>> transformerList;


    public PipelineBuilder<U,V> withConsumers(List<Consumer<U>> consumerList){
        this.consumerList = consumerList;
        return this;
    }

    public PipelineBuilder<U,V> withPublishers(List<Publisher<V>> publisherList){
        this.publisherList = publisherList;
        return this;
    }

    public PipelineBuilder<U,V> withTransformers(List<Transformer<U,V>> transformerList){
        this.transformerList = transformerList;
        return this;
    }

    public Pipeline buildPipeline(){
        if(CollectionUtils.isEmpty(consumerList)){
            throw new IllegalArgumentException("Consumers are required for the pipeline");
        }
        return new Pipeline();
    }


}
