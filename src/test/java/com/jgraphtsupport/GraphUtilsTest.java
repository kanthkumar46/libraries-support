package com.jgraphtsupport;

import javaslang.collection.Array;
import org.jgrapht.UndirectedGraph;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by KanthKumar on 3/3/17.
 */
public class GraphUtilsTest extends TestSetup {

    @Test
    public void convertToGraphEmptyEdgeListTest() {
        UndirectedGraph<Vertex, Edge> graph = GraphUtils.convertToGraph(Array.empty());
        Assert.assertTrue(graph.edgeSet().isEmpty());
    }

    @Test
    public void convertToGraphTest() {
        UndirectedGraph<Vertex, Edge> graph = GraphUtils.convertToGraph(Array.of("1 2", "2 3", "3 4"));
        Assert.assertEquals(3, graph.edgeSet().size());
        Assert.assertEquals(4, graph.vertexSet().size());
    }

    @Test
    public void getMaxVertexIdTest() {
        UndirectedGraph<Vertex, Edge> graph = GraphUtils.convertToGraph(Array.of("1 2", "2 3", "3 4", "2 5"));
        Optional<Integer> maxId = GraphUtils.getMaxVertexId(graph);
        Assert.assertTrue(maxId.isPresent());
        Assert.assertEquals(5, maxId.orElse(-1).intValue());
    }
}
