package com.pipeline.engine.core.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Definition {
    private String name;
    private String consumer;
    private String transformer;
    private List<String> publishers;
    private int parallelism;
}
