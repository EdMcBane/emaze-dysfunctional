package net.emaze.dysfunctional.dispatching.logic;

import net.emaze.dysfunctional.dispatching.logic.NotNull;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class NotNullTest {

    @Test
    public void nullYieldsFalse() {
        Assert.assertFalse(new NotNull<Object>().test(null));
    }

    @Test
    public void nonNullYieldsTrue() {
        Assert.assertTrue(new NotNull<Object>().test(new Object()));
    }
}
