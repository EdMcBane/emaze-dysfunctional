package net.emaze.dysfunctional.options;

import java.util.Iterator;
import java.util.Optional;
import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;

/**
 * Decorates an Iterator yielding Optional.of(element) for every element in the
 * contained iterator and Optional.empty() beyond the nested iterator bounds
 *
 * @param <E> the iterator element type
 * @author rferranti
 */
public class OptionalIterator<E> extends ReadOnlyIterator<Optional<E>> {

    private final Iterator<E> iterator;

    public OptionalIterator(Iterator<E> iterator) {
        dbc.precondition(iterator != null, "trying to create a OptionalIterator from a null iterator");
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * calling next over the boundary of the contained iterator leads
     * Optional.empty indefinitely "no matter how many times you try, you can't
     * shoot the dog"
     *
     * @return Optional yielding next element if present, Optional.empty() otherwise
     */
    @Override
    public Optional<E> next() {
        if (iterator.hasNext()) {
            return Optional.of(iterator.next());
        }
        return Optional.empty();
    }
}
