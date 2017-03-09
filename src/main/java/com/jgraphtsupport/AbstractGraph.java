package com.jgraphtsupport;

import javaslang.Tuple;
import javaslang.collection.HashMap;
import javaslang.collection.HashSet;
import org.jgrapht.DirectedGraph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.AsUndirectedGraph;

public abstract class AbstractGraph<T extends AbstractVertex, U extends AbstractEdge<T>> {
	public abstract DirectedGraph<T, U> getDefaultGraph();

	public boolean addVertex(T newVertex) {
		return getDefaultGraph().addVertex(newVertex);
	}

	public boolean addEdge(U newEdge) {
		return GraphUtils.addEdgeWithVertices(getDefaultGraph(), newEdge);
	}

	public UndirectedGraph<T, U> getAsUndirectedGraph() {
		return new AsUndirectedGraph<>(getDefaultGraph());
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