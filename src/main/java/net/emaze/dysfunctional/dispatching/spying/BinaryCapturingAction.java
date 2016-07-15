package net.emaze.dysfunctional.dispatching.spying;

import net.emaze.dysfunctional.contracts.dbc;
import net.emaze.dysfunctional.dispatching.actions.BinaryAction;
import net.emaze.dysfunctional.options.Box;

/**
 * Proxies a binary action capturing parameters.
 *
 * @author rferranti
 * @param <T1> the first parameter type
 * @param <T2> the second parameter type
 */
public class BinaryCapturingAction<T1, T2> implements BinaryAction<T1, T2> {

    private final BinaryAction<T1, T2> nested;
    private final Box<T1> param1;
    private final Box<T2> param2;

    public BinaryCapturingAction(BinaryAction<T1, T2> nested, Box<T1> param1, Box<T2> param2) {
        dbc.precondition(nested != null, "cannot capture from a null action");
        dbc.precondition(param1 != null, "cannot capture with a null param1 box");
        dbc.precondition(param2 != null, "cannot capture from a null param2 box");
        this.nested = nested;
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public void perform(T1 former, T2 latter) {
        param1.setContent(former);
        param2.setContent(latter);
        nested.perform(former, latter);
    }
}