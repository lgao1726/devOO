package tsp;

import java.util.Collection;
import java.util.Iterator;

public class TSP1 extends TemplateTSP {

	@Override
	protected int bound(Float sommetCourant, Collection<Float> nonVus) {
		return 0;
	}

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, Collection<Integer> nonVus, Graphe g) {
		return new IteratorSeq(nonVus, sommetCrt, g);
	}

}
