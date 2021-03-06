package net.emaze.dysfunctional.convolutions;

import java.util.Iterator;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * Adapts two iterators yielding their shortest convolution (via a Pair<E1,E2>)
 * @param <E1> the first iterator element type
 * @param <E2> the second iterator element type
 * @author rferranti
 */
public class ZipShortestIterator<E1, E2> implements Iterator<Pair<E1, E2>> {

    private final Iterator<E1> former;
    private final Iterator<E2> latter;

    public ZipShortestIterator(Iterator<E1> former, Iterator<E2> latter) {
        dbc.precondition(former != null, "trying to create a ZipShortestIterator from a null iterator (former)");
        dbc.precondition(latter != null, "trying to create a ZipShortestIterator from a null iterator (latter)");
        this.former = former;
        this.latter = latter;
    }

    @Override
    public boolean hasNext() {
        return former.hasNext() && latter.hasNext();
    }

    @Override
    public Pair<E1, E2> next() {
        return Pair.of(former.next(), latter.next());
    }

    @Override
    public void remove() {
        former.remove();
        latter.remove();
    }
}
