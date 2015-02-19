package net.emaze.dysfunctional.dispatching.delegates;

import net.emaze.dysfunctional.dispatching.delegates.Composer;
import net.emaze.dysfunctional.dispatching.delegates.Identity;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class ComposerTest {

    @Test
    public void canComposeTwoDelegates() {
        Composer<Integer, Integer, Integer> c = new Composer<Integer, Integer, Integer>(new Identity<Integer>(), new Identity<Integer>());
        Assert.assertEquals(1, (int)c.apply(1));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void composingWithNullFormerDelegateYieldsException() {
        new Composer<Integer, Integer, Integer>(null, new Identity<Integer>());
    }

    @Test(expected=IllegalArgumentException.class)
    public void composingWithNullLatterDelegateYieldsException() {
        new Composer<Integer, Integer, Integer>(new Identity<Integer>(), null);
    }
}
