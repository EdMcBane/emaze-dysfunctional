package net.emaze.dysfunctional.consumers;

import java.util.Iterator;
import junit.framework.Assert;
import net.emaze.dysfunctional.Iterations;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.testing.O;
import org.junit.Test;

public class MaybeLastElementTest {

    @Test(expected = IllegalArgumentException.class)
    public void callingWithNullIteratorYieldsException() {
        new MaybeLastElement<O>().apply(null);
    }

    @Test
    public void callingWithEmptyIteratorYieldsNothing() {
        final Iterator<O> iterator = Iterations.iterator();
        final Maybe<O> got = new MaybeLastElement<O>().apply(iterator);
        Assert.assertEquals(Maybe.<O>nothing(), got);
    }
    @Test
    public void callingWithNonEmptyIteratorYieldsLastElement() {
        final Iterator<O> iterator = Iterations.iterator(O.ONE, O.ANOTHER);
        final Maybe<O> got = new MaybeLastElement<O>().apply(iterator);
        Assert.assertEquals(Maybe.just(O.ANOTHER), got);
    }
}