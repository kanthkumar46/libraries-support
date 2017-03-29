package edu.jgraphtsupport;

import javaslang.Function1;
import javaslang.collection.Array;
import javaslang.control.Option;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.Pseudograph;

import java.util.Comparator;
import java.util.Optional;

public class GraphUtils {

    /**
     * utility method to create graphT Graph object from the list of strings, each string represents the edge
     * with space separated node ids of their endpoints.
     *
     * @param edges specified as described above (eg: {"1 2", "2 3"})
     * @return undirected graph object
     */
    public static UndirectedGraph<Vertex, Edge> convertToGraph(Iterable<String> edges) {
        final UndirectedGraph<Vertex, Edge> graph = new Pseudograph<>(Edge.class);

        Function1<Edge, Boolean> addEdgeToGraph = edge -> addEdgeWithVertices(graph, edge);

        Array.ofAll(edges).map(edge -> edge.split(" "))
                .map(edge -> ImmutableEdge.of(edge[0], edge[1]))
                .forEach(addEdgeToGraph::apply);

        return graph;
    }

    public static <T extends AbstractVertex, U extends AbstractEdge<T>> Option<Integer> getMaxVertexId(Graph<T, U> graph) {
        Optional<Integer> optional = graph.vertexSet().stream()
                .max(Comparator.comparingInt(AbstractVertex::getVertexId))
                .map(AbstractVertex::getVertexId);
        return Option.ofOptional(optional);
    }

    public static <T extends AbstractVertex, U extends AbstractEdge<T>> Option<Integer> getMaxDegree(UndirectedGraph<T, U> graph) {
        Optional<Integer> optional = graph.vertexSet().stream()
                .map(graph::degreeOf)
                .max(Integer::compareTo);
        return Option.ofOptional(optional);
    }

    public static <T extends AbstractVertex, U extends AbstractEdge<T>> Array<U> getLoops(Graph<T, U> graph) {
        return graph.edgeSet().stream()
                .filter(e -> graph.getEdgeSource(e).equals(graph.getEdgeTarget(e)))
                .collect(Array.collector());
    }

    /**
     * This utility method make sures that adding an edge to graph does not fail
     * if the vertices are not in the graph
     *
     * @param graph
     * @param edge
     * @param <T> Vertex type
     * @param <U> Edge type
     * @return true if edge is added succesfully to the graph
     */
    public static <T extends AbstractVertex, U extends AbstractEdge<T>> boolean addEdgeWithVertices(Graph<T, U> graph, U edge){
        T sourceVertex = edge.getSourceVertex();
        T targetVertex = edge.getTargetVertex();
        graph.addVertex(sourceVertex);
        graph.addVertex(targetVertex);
        return graph.addEdge(sourceVertex, targetVertex, edge);
    }
}
