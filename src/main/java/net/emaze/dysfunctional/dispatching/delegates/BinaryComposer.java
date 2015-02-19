package net.emaze.dysfunctional.dispatching.delegates;

import java.util.function.BiFunction;
import java.util.function.Function;
import net.emaze.dysfunctional.contracts.dbc;

/**
 * Composes an unary delegate with a binary delegate.
 *
 * @param <T1> the first binary parameter type
 * @param <T2> the second binary parameter type
 * @param <T3> unary parameter type and binary return type
 * @param <R> unary return type
 */
public class BinaryComposer<T1, T2, T3, R> implements BiFunction<T1, T2, R> {

    private final BiFunction<T1, T2, T3> binary;
    private final Function<T3, R> unary;

    /**
     * @param unary the unary delegate to be composed
     * @param binary the binary delegate to be composed
     */
    public BinaryComposer(Function<T3, R> unary, BiFunction<T1, T2, T3> binary) {
        dbc.precondition(unary != null, "cannot compose a null unary delegate");
        dbc.precondition(binary != null, "cannot compose a null binary delegate");
        this.unary = unary;
        this.binary = binary;
    }

    @Override
    public R apply(T1 first, T2 second) {
        return unary.apply(binary.apply(first, second));
    }
}