package net.emaze.dysfunctional.dispatching.adapting;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;
import net.emaze.dysfunctional.dispatching.delegates.Delegate;

/**
 * Adapts a delegate to a binary delegate. Adapting is performed by ignoring the
 * second parameter passed to the adapted delegate.
 *
 * @param <R> the adapter result type
 * @param <T1> the adapter first parameter type
 * @param <T2> the adapter second parameter type
 * @author rferranti
 */
public class IgnoreSecond<R, T1, T2> implements BinaryDelegate<R, T1, T2> {

    private final Delegate<R, T1> delegate;

    public IgnoreSecond(Delegate<R, T1> delegate) {
        dbc.precondition(delegate != null, "cannot ignore the second parameter of a null delegate");
        this.delegate = delegate;
    }

    @Override
    public R perform(T1 first, T2 second) {
        return delegate.perform(first);
    }
}
