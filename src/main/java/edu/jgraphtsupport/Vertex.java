package edu.jgraphtsupport;

import edu.immutablessupport.styles.TupleStyle;
import org.apache.commons.lang3.math.NumberUtils;
import org.immutables.value.Value;

/**
 * Created by KanthKumar on 2/27/17.
 */
@Value.Immutable
@TupleStyle
public abstract class Vertex implements AbstractVertex {

    public static Vertex of(String vertexId) {
        return ImmutableVertex.of(NumberUtils.toInt(vertexId));
    }
}
