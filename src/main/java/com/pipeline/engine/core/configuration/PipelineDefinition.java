package com.pipeline.engine.core.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PipelineDefinition {
    private List<Definition> definitions;
}
