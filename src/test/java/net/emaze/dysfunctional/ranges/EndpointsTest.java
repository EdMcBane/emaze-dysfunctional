package net.emaze.dysfunctional.ranges;

import org.junit.Assert;
import org.junit.Test;
import static net.emaze.dysfunctional.ranges.RangeMother.*;

/**
 *
 * @author rferranti
 */
public class EndpointsTest {

    @Test
    public void nonIncludedBoundsAreStringifiedAsParens() {
        Assert.assertEquals("(0-1)", Endpoints.IncludeNone.stringify(r(0, 1)));
    }

    @Test
    public void includedBoundsAreStringifiedAsSqureBracket() {
        Assert.assertEquals("[0-1]", Endpoints.IncludeBoth.stringify(r(0, 1)));
    }

    @Test
    public void includingBothIncludesLeftEndpoint() {
        Assert.assertTrue(Endpoints.IncludeBoth.includeLeftEndpoint());
    }

    @Test
    public void includingBothIncludesRightEndpoint() {
        Assert.assertTrue(Endpoints.IncludeBoth.includeRightEndpoint());
    }

    @Test
    public void excludingBothDoesntIncludRightEndpoint() {
        Assert.assertFalse(Endpoints.IncludeNone.includeRightEndpoint());
    }

    @Test
    public void excludingBothDoesntIncludLeftEndpoint() {
        Assert.assertFalse(Endpoints.IncludeNone.includeLeftEndpoint());
    }
}