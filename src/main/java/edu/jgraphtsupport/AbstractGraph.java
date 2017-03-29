package edu.jgraphtsupport;

import javaslang.Tuple;
import javaslang.collection.HashMap;
import javaslang.collection.HashSet;
import org.immutables.value.Value;
import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.UndirectedMaskSubgraph;

import java.util.function.Predicate;

public abstract class AbstractGraph<T extends AbstractVertex, U extends AbstractEdge<T>> {
	public abstract DirectedGraph<T, U> getDefaultGraph();

	@Value.Derived
	public UndirectedGraph<T, U> getAsUndirectedGraph() {
		return new AsUndirectedGraph<>(getDefaultGraph());
	}

	@Value.Lazy
	public UndirectedGraph<T, U> getAsUndirectedGraphWithoutLoops() {
		Predicate<U> loop = edge -> edge.getSourceVertex().equals(edge.getTargetVertex()); //edge that is a loop
		return new UndirectedMaskSubgraph<>(getAsUndirectedGraph(), v -> false, loop);
	}

	public int getSize() {
		return getDefaultGraph().vertexSet().size();
	}

	public boolean addVertex(T newVertex) {
		return getDefaultGraph().addVertex(newVertex);
	}

	public boolean addEdge(U newEdge) {
		return GraphUtils.addEdgeWithVertices(getDefaultGraph(), newEdge);
	}

	@Override
	public String toString() {
		java.util.Map<AbstractVertex, java.util.Set<AbstractVertex>> sourceTargets = new java.util.HashMap<>();

		getDefaultGraph().edgeSet().forEach(edge -> sourceTargets
				.computeIfAbsent(edge.getSourceVertex(), vertex -> new java.util.HashSet<>())
				.add(edge.getTargetVertex()));

		return HashMap.ofAll(sourceTargets)
				.flatMap(tuple -> HashSet.ofAll(tuple._2).map(vertex -> Tuple.of(tuple._1, vertex)))
				.map(VertexTuple -> VertexTuple._1.getVertexId()+"------>"+VertexTuple._2.getVertexId())
				.mkString("\n");
	}
}