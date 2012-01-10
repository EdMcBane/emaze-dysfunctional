package net.emaze.dysfunctional.interceptions;

/**
 * An inner automorphism.
 * @param <T>
 * @author rferranti
 */
public interface Interceptor<T> {
    void before(T value);
    void after(T value);
}
