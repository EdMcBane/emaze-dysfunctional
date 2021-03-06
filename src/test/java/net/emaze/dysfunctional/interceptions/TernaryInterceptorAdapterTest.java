package net.emaze.dysfunctional.interceptions;

import java.util.ArrayList;
import java.util.List;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.tuples.Triple;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class TernaryInterceptorAdapterTest {

    @Test(expected = IllegalArgumentException.class)
    public void creatingAdapterWithNullAdapteeYieldsException() {
        new TernaryInterceptorAdapter<Object, Object, Object, Object>(null, new FirstParam<Object, Object, Object>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingAdapterWithNullInnerDelegateYieldsException() {
        new TernaryInterceptorAdapter<Object, Object, Object, Object>(new TernaryNoopInterceptor<Object, Object, Object>(), null);
    }

    @Test
    public void beforeAndAfterAreCalled() {
        final BucketFillingTernaryInterceptor<String, String, String> interceptor = new BucketFillingTernaryInterceptor<String, String, String>();
        final TriFunction<String, String, String, String> nestedDelegate = new FirstParam<String, String, String>();
        final TriFunction<String, String, String, String> function = new TernaryInterceptorAdapter<String, String, String, String>(interceptor, nestedDelegate);
        function.apply("useless_param", "useless_param", "useless_param");
        Assert.assertTrue(interceptor.getBeforeBucket().size() == 1 && interceptor.getAfterBucket().size() == 1);
    }

    @Test
    public void beforeAndAfterAreCalledInCaseOfException() {
        final BucketFillingTernaryInterceptor<String, String, String> interceptor = new BucketFillingTernaryInterceptor<String, String, String>();
        final TriFunction<String, String, String, String> function = new TernaryInterceptorAdapter<String, String, String, String>(interceptor, new TriFunction<String, String, String, String>() {

            @Override
            public String apply(String first, String second, String third) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });
        try {
            function.apply("useless_param", "useless_param", "useless_param");
            Assert.fail("function is supposed to throw UnsupportedOperationException");
        } catch (UnsupportedOperationException ex) {
            Assert.assertTrue(interceptor.getBeforeBucket().size() == 1 && interceptor.getAfterBucket().size() == 1);
        }
    }

    public static class FirstParam<T1, T2, T3> implements TriFunction<T1, T2, T3, T1> {

        @Override
        public T1 apply(T1 first, T2 second, T3 third) {
            return first;
        }
    }

    public static class TernaryNoopInterceptor<T1, T2, T3> implements TernaryInterceptor<T1, T2, T3> {

        @Override
        public void before(T1 first, T2 second, T3 third) {
        }

        @Override
        public void after(T1 first, T2 second, T3 third) {
        }
    }

    public static class BucketFillingTernaryInterceptor<T1, T2, T3> implements TernaryInterceptor<T1, T2, T3> {

        private final List<Triple<T1, T2, T3>> beforeBucket = new ArrayList<Triple<T1, T2, T3>>();
        private final List<Triple<T1, T2, T3>> afterBucket = new ArrayList<Triple<T1, T2, T3>>();

        @Override
        public void before(T1 first, T2 second, T3 third) {
            beforeBucket.add(Triple.of(first, second, third));
        }

        @Override
        public void after(T1 first, T2 second, T3 third) {
            afterBucket.add(Triple.of(first, second, third));
        }

        public List<Triple<T1, T2, T3>> getAfterBucket() {
            return afterBucket;
        }

        public List<Triple<T1, T2, T3>> getBeforeBucket() {
            return beforeBucket;
        }
    }
}
