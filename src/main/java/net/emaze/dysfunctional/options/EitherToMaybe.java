package net.emaze.dysfunctional.options;

import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Function;

/**
 * Transforms an either to a maybe containing just() right type or nothing().
 *
 * @author rferranti
 * @param <LT> the left type parameter
 * @param <RT> the right type parameter
 */
public class EitherToMaybe<LT, RT> implements Function<Either<LT, RT>, Maybe<RT>> {

    @Override
    public Maybe<RT> apply(Either<LT, RT> either) {
        dbc.precondition(either != null, "cannot transform a null either to a maybe");
        return either.maybe();
    }
}
