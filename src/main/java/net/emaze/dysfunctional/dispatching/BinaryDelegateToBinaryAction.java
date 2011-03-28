package net.emaze.dysfunctional.dispatching;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.dispatching.delegates.BinaryDelegate;

/**
 *
 * @param <T1>
 * @param <T2> 
 * @author rferranti
 */
public class BinaryDelegateToBinaryAction<T1, T2> implements BinaryAction<T1, T2> {

    private final BinaryDelegate<?, T1, T2> adapted;

    public BinaryDelegateToBinaryAction(BinaryDelegate<?, T1, T2> adapted) {
        dbc.precondition(adapted != null, "cannot adapt a null delegate");
        this.adapted = adapted;
    }

    @Override
    public void perform(T1 first, T2 second) {
        adapted.perform(first, second);
    }
}
