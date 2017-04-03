package edu.jgraphtsupport;

import edu.junitsupport.TestSetup;
import javaslang.collection.Array;
import javaslang.control.Option;
import org.jgrapht.UndirectedGraph;
import org.junit.Assert;
import org.junit.Test;

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
        Option<Integer> maxId = GraphUtils.getMaxVertexId(graph);
        Assert.assertTrue(maxId.isDefined());
        Assert.assertEquals(5, maxId.getOrElse(-1).intValue());
    }

    @Test
    public void getMaxDegreeTest() {
        UndirectedGraph<Vertex, Edge> graph = GraphUtils.convertToGraph(Array.of("1 2", "2 3", "3 4", "2 5"));
        Option<Integer> maxDegree = GraphUtils.getMaxDegree(graph);
        Assert.assertTrue(maxDegree.isDefined());
        Assert.assertEquals(3, maxDegree.get().intValue());
    }

    @Test
    public void getLoopsTest() {
        UndirectedGraph<Vertex, Edge> graph = GraphUtils.convertToGraph(Array.of("1 2", "2 2", "3 4", "3 1", "4 4"));
        Array<Edge> loops = GraphUtils.getLoops(graph);
        Assert.assertEquals(2, loops.size());
    }
}
