package net.emaze.dysfunctional.filtering;

import java.util.function.Predicate;

/**
 * A stateful predicate yielding false until the first time the predicate
 * matches, true when and after the first time the predicate doesn't match.
 *
 * @param <T> the predicate parameter type
 * @author rferranti
 */
public class DropWhile<T> implements Predicate<T> {

    private boolean takeElement;
    private final Predicate<T> dropWhile;

    public DropWhile(Predicate<T> dropWhile) {
        this.dropWhile = dropWhile;
    }

    @Override
    public boolean test(T element) {
        if (takeElement == false) {
            // first time and until predicate is true
            takeElement = !dropWhile.test(element);
        }
        return takeElement;
    }
}
