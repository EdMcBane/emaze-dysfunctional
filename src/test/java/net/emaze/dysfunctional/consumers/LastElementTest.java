package net.emaze.dysfunctional.consumers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class LastElementTest {

    @Test(expected=IllegalArgumentException.class)
    public void consumingNullIteratorYieldsException() {
        new LastElement<Object>().apply(null);
    }

    @Test
    public void yieldsLastElement(){
        List<Integer> consumable = Arrays.asList(1,2,3);
        LastElement<Integer> consumer = new LastElement<Integer>();
        Integer got = consumer.apply(consumable.iterator());
        Assert.assertEquals(consumable.get(consumable.size() -1 ), got);
    }
    @Test(expected=IllegalArgumentException.class)
    public void consumingEmptyIteratorYieldsException(){
        new LastElement<Object>().apply(Collections.emptyList().iterator());
    }

}