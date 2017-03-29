package edu.jgraphtsupport;


import edu.immutablessupport.styles.TupleStyle;
import org.immutables.value.Value;

/**
 * Created by KanthKumar on 2/27/17.
 */
@Value.Immutable
@TupleStyle
public abstract class Edge extends AbstractEdge<Vertex> {

    public static Edge of(String sourceVertex, String targetVertex) {
        return ImmutableEdge.of(ImmutableVertex.of(sourceVertex), ImmutableVertex.of(targetVertex));
    }

    public static Edge of(int sourceVertex, int targetVertex) {
        return ImmutableEdge.of(ImmutableVertex.of(sourceVertex), ImmutableVertex.of(targetVertex));
    }
}
