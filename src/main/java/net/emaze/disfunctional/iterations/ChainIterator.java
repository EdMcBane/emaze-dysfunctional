package net.emaze.disfunctional.iterations;

import java.util.Arrays;
import net.emaze.disfunctional.delegates.Predicate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import net.emaze.disfunctional.Maybe;

/**
 *
 * @author rferranti
 */
public class ChainIterator<E> implements Iterator<E> {

    private final List<Iterator<E>> iterators;

    public ChainIterator(Iterator<E>... iterators) {
        this.iterators = new ArrayList<Iterator<E>>(iterators.length);
        this.iterators.addAll(Arrays.asList(iterators));
    }

    public boolean hasNext() {
        if (iterators.isEmpty()) {
            return false;
        }
        return Iterations.any(iterators,new Predicate<Iterator<E>>() {
            public boolean call(Iterator<E> iterator) {
                return iterator.hasNext();
            }
        });
    }


    private Maybe<Iterator<E>> currentElement(){
        final Iterator<Iterator<E>> iteratorOfIterators = iterators.iterator();
        while (iteratorOfIterators.hasNext()) {
            final Iterator<E> iterator = iteratorOfIterators.next();
            if (!iterator.hasNext()) {
                iteratorOfIterators.remove();
                continue;
            }
            return Maybe.just(iterator);
        }
        return Maybe.nothing();
    }

    public E next() {
        final Maybe<Iterator<E>> maybeElement = currentElement();
        if(maybeElement.hasValue()){
            return maybeElement.value().next();
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        final Maybe<Iterator<E>> maybeElement = currentElement();
        if(maybeElement.hasValue()){
            maybeElement.value().remove();
        }
    }
}