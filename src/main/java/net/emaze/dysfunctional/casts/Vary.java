package net.emaze.dysfunctional.casts;

import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Casts a value.
 *
 * Usually composed with other functors to make the simulate covaraiant /
 * controvariant types. E.g:
 * <code>
 *   final Delegate<Integer, Integer> source = new Identity<Integer>();
 *   final Delegate<Number, Integer> got = Applications.compose(new Vary<Number, Integer>(), source);
 * </code>
 *
 * @author rferranti
 * @param <R> the result type
 * @param <T> the source type
 */
public class Vary<R, T> implements Delegate<R, T> {

    @Override
    public R perform(T value) {
        return (R) value;
    }
}
