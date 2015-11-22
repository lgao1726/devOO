package tsp;

import java.util.Collection;
import java.util.Iterator;

public class TSP1 extends TemplateTSP {

	@Override
<<<<<<< HEAD
	protected int bound(Float sommetCourant, Collection<Float> nonVus) {
=======
	protected int bound(Integer sommetCourant, Collection<Integer> nonVus) {
>>>>>>> branch 'development' of https://github.com/lgao1726/devOO.git
		return 0;
	}

	@Override
	protected Iterator<Integer> iterator(Integer sommetCrt, Collection<Integer> nonVus, Graphe g) {
		return new IteratorSeq(nonVus, sommetCrt, g);
	}

}
