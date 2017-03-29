package edu.jgraphtsupport;

import org.immutables.value.Value;

/**
 * Created by KanthKumar on 3/3/17.
 */
public abstract class AbstractEdge<T extends AbstractVertex> {
    public abstract T getSourceVertex();
    public abstract T getTargetVertex();

    @Value.Derived
    public int getSourceVertexId() {
        return getSourceVertex().getVertexId();
    }

    @Value.Derived
    public int getTargetVertexId(){
        return getTargetVertex().getVertexId();
    }
}
