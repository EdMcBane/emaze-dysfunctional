package net.emaze.dysfunctional.logic;

import java.util.Arrays;
import net.emaze.dysfunctional.contracts.dbc;

/**
 *
 * @author rferranti
 */
public abstract class Logic {

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T> the element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T> CompositePredicate<T> and(Predicate<T>... predicates) {
        dbc.precondition(predicates != null, "cannot compose a null array of predicates");
        final CompositePredicate<T> pred = new AllMatchingPredicate<T>();
        pred.setFunctors(Arrays.asList(predicates));
        return pred;
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2> CompositeBinaryPredicate<T1, T2> and(BinaryPredicate<T1, T2>... predicates) {
        dbc.precondition(predicates != null, "cannot compose a null array of predicates");
        final CompositeBinaryPredicate<T1, T2> pred = new AllMatchingBinaryPredicate<T1, T2>();
        pred.setFunctors(Arrays.asList(predicates));
        return pred;
    }

    /**
     * Creates a composite AND predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2, T3> CompositeTernaryPredicate<T1, T2, T3> and(TernaryPredicate<T1, T2, T3>... predicates) {
        dbc.precondition(predicates != null, "cannot compose a null array of predicates");
        final CompositeTernaryPredicate<T1, T2, T3> pred = new AllMatchingTernaryPredicate<T1, T2, T3>();
        pred.setFunctors(Arrays.asList(predicates));
        return pred;
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T> the element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T> CompositePredicate<T> or(Predicate<T>... predicates) {
        dbc.precondition(predicates != null, "cannot compose a null array of predicates");
        final CompositePredicate<T> pred = new FirstMatchingPredicate<T>();
        pred.setFunctors(Arrays.asList(predicates));
        return pred;
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the former element type parameter
     * @param <T2> the latter element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2> CompositeBinaryPredicate<T1, T2> or(BinaryPredicate<T1, T2>... predicates) {
        dbc.precondition(predicates != null, "cannot compose a null array of predicates");
        final CompositeBinaryPredicate<T1, T2> pred = new FirstMatchingBinaryPredicate<T1, T2>();
        pred.setFunctors(Arrays.asList(predicates));
        return pred;
    }

    /**
     * Creates a composite OR predicate from the given predicates.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @param predicates the predicates to be composed
     * @return the composite predicate
     */
    public static <T1, T2, T3> CompositeTernaryPredicate<T1, T2, T3> or(TernaryPredicate<T1, T2, T3>... predicates) {
        dbc.precondition(predicates != null, "cannot compose a null array of predicates");
        final CompositeTernaryPredicate<T1, T2, T3> pred = new FirstMatchingTernaryPredicate<T1, T2, T3>();
        pred.setFunctors(Arrays.asList(predicates));
        return pred;
    }

    /**
     * Negates the given predicate.
     * @param <T> the element type parameter
     * @param predicate the predicate to be negated
     * @return the negated predicate
     */
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        return new Negator<T>(predicate);
    }

    /**
     * Negates the given predicate.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param predicate the predicate to be negated
     * @return the negated predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> not(BinaryPredicate<T1, T2> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        return new BinaryNegator<T1, T2>(predicate);
    }

    /**
     * Negates the given predicate.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T2> the third element type parameter
     * @param predicate the predicate to be negated
     * @return the negated predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> not(TernaryPredicate<T1, T2, T3> predicate) {
        dbc.precondition(predicate != null, "cannot negate a null predicate");
        return new TernaryNegator<T1, T2, T3>(predicate);
    }

    /**
     * Creates a predicate always yielding true.
     * @param <T> the element type parameter
     * @return the predicate
     */
    public static <T> Predicate<T> always() {
        return new Always<T>();
    }

    /**
     * Creates a predicate always yielding true.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @return the predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> always2() {
        return new BinaryAlways<T1, T2>();
    }

    /**
     * Creates a predicate always yielding true.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @return the predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> always3() {
        return new TernaryAlways<T1, T2, T3>();
    }

    /**
     * Creates a predicate always yielding false.
     * @param <T> the element type parameter
     * @return the predicate
     */
    public static <T> Predicate<T> never() {
        return new Never<T>();
    }

    /**
     * Creates a predicate always yielding false.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @return the predicate
     */
    public static <T1, T2> BinaryPredicate<T1, T2> never2() {
        return new BinaryNever<T1, T2>();
    }

    /**
     * Creates a predicate always yielding false.
     * @param <T1> the first element type parameter
     * @param <T2> the second element type parameter
     * @param <T3> the third element type parameter
     * @return the predicate
     */
    public static <T1, T2, T3> TernaryPredicate<T1, T2, T3> never3() {
        return new TernaryNever<T1, T2, T3>();
    }
}
