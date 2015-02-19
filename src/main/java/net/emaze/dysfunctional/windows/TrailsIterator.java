package net.emaze.dysfunctional.windows;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Maybe;

/**
 * [1,2,3,4], 3, id -> (Nothing, Nothing, Just 1), (Nothing, Just 1, Just 2),
 * (Just 1, Just 2, Just 3), (Just 2, Just 3, Just 4)
 *
 * @param <T>
 * @param <W>
 * @author rferranti
 */
public class TrailsIterator<T, W extends Collection<?>> extends ReadOnlyIterator<W> {

    private final Iterator<T> iter;
    private final Queue<Maybe<T>> trails = new LinkedList<Maybe<T>>();
    private final Function<Queue<Maybe<T>>, W> copy;

    public TrailsIterator(Iterator<T> iter, int trailSize, Function<Queue<Maybe<T>>, W> copy) {
        dbc.precondition(iter != null, "cannot create a TrailsIterator with a null iterator");
        dbc.precondition(trailSize > 0, "cannot create a TrailsIterator with a non positive window size");
        dbc.precondition(copy != null, "cannot create a TrailsIterator with a null copy semantics");
        this.iter = iter;
        this.copy = copy;
        for (int i = 0; i != trailSize; ++i) {
            trails.add(Maybe.<T>nothing());
        }
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public W next() {
        if (!iter.hasNext()) {
            throw new NoSuchElementException("iterator is consumed");
        }
        trails.add(Maybe.just(iter.next()));
        trails.remove();
        return copy.apply(trails);
    }
}
