package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import java.util.function.Function;

/**
 * Adapts a delegate to a binary delegate. Adapting is performed by ignoring the
 * second parameter passed to the adapted delegate.
 *
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @param <R> the adapter result type
 * @author rferranti
 */
public class IgnoreSecond<T1, T2, R> implements BinaryDelegate<R, T1, T2> {

    private final Function<T1, R> adapted;

    public IgnoreSecond(Function<T1, R> adaptee) {
        dbc.precondition(adaptee != null, "cannot ignore second parameter of a null delegate");
        this.adapted = adaptee;
    }

    @Override
    public R perform(T1 first, T2 second) {
        return adapted.apply(first);
    }
}
