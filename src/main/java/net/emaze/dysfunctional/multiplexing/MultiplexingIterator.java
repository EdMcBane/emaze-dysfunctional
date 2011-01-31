package net.emaze.dysfunctional.multiplexing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import net.emaze.dysfunctional.consumers.Consumers;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.delegates.Delegate;
import net.emaze.dysfunctional.logic.HasNext;
import net.emaze.dysfunctional.iterations.Iterations;

/**
 * shortest 
 * @param <T> 
 * @author rferranti
 */
public class MultiplexingIterator<T> implements Iterator<T> {

    private final Iterable<Iterator<T>> iterators;
    private final Queue<T> prefetched = new LinkedList<T>();

    public MultiplexingIterator(Iterator<Iterator<T>> iterators) {
        dbc.precondition(iterators != null, "trying to create a ChainIterator from a null array of iterators");
        this.iterators = Consumers.all(iterators);
        dbc.precondition(this.iterators.iterator().hasNext(), "trying to create a ChainIterator from an empty iterable");
    }

    @Override
    public boolean hasNext() {
        if (prefetched.isEmpty()) {
            tryPrefetch();
        }
        return !prefetched.isEmpty();
    }

    @Override
    public T next() {
        if (prefetched.isEmpty()) {
            tryPrefetch();
        }
        return prefetched.remove();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Cannot remove from a MultiplexintIterator.");
    }

    private void tryPrefetch() {
        if (!Iterations.every(iterators, new HasNext<Iterator<T>>())) {
            return;
        }
        prefetched.addAll(Consumers.all(Iterations.transform(iterators, new Delegate<T, Iterator<T>>() {

            @Override
            public T perform(Iterator<T> iter) {
                return iter.next();
            }
        })));
    }
}
