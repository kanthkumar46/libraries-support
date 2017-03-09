package com.jgraphtsupport;

import javaslang.Function1;
import javaslang.collection.Array;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.SimpleGraph;

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
        final UndirectedGraph<Vertex, Edge> graph = new SimpleGraph<>(Edge.class);

        Function1<Edge, Boolean> addEdgeToGraph = edge -> addEdgeWithVertices(graph, edge);

        Array.ofAll(edges).map(edge -> edge.split(" "))
                .map(edge -> ImmutableEdge.of(edge[0], edge[1]))
                .forEach(addEdgeToGraph::apply);

        return graph;
    }

    public static <T extends AbstractVertex, U extends AbstractEdge<T>> Optional<Integer> getMaxVertexId(Graph<T, U> graph) {
        return graph.vertexSet().stream()
                .max(Comparator.comparingInt(AbstractVertex::getVertexId))
                .map(AbstractVertex::getVertexId);
    }

    /**
     * This utility method make sures that adding an edge to graph does not fail
     * if the vertices are not in the graph
     *
     * @param graph
     * @param edge
     * @param <T> Vertex
     * @param <U> Edge
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
