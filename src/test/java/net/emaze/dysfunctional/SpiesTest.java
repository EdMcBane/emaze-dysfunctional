package net.emaze.dysfunctional;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.emaze.dysfunctional.dispatching.actions.BinaryNoop;
import net.emaze.dysfunctional.dispatching.actions.Noop;
import net.emaze.dysfunctional.dispatching.actions.Slacker;
import net.emaze.dysfunctional.dispatching.actions.TriConsumer;
import net.emaze.dysfunctional.dispatching.actions.TernaryNoop;
import net.emaze.dysfunctional.dispatching.delegates.ConstantSupplier;
import net.emaze.dysfunctional.dispatching.delegates.FirstParam;
import net.emaze.dysfunctional.dispatching.delegates.FirstParamOfThree;
import net.emaze.dysfunctional.dispatching.delegates.TriFunction;
import net.emaze.dysfunctional.dispatching.logic.Always;
import net.emaze.dysfunctional.dispatching.logic.BinaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TernaryAlways;
import net.emaze.dysfunctional.dispatching.logic.TriPredicate;
import net.emaze.dysfunctional.dispatching.logic.Yes;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.testing.O;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author rferranti
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    SpiesTest.Spy.class,
    SpiesTest.Monitor.class
})
public class SpiesTest {

    public static class Spy {

        @Test
        public void canSpyAProvider() {
            final Box<O> result = new Box<O>();
            final Supplier<O> spied = Spies.spy(new ConstantSupplier<O>(O.ONE), result);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyADelegate() {
            final Box<O> result = new Box<O>();
            final Box<O> param = new Box<O>();
            final Function<O, O> spied = Spies.spy(Function.identity(), result, param);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyResultOfDelegate() {
            final Box<O> result = new Box<O>();
            final Function<O, O> spied = Spies.spyRes(Function.identity(), result);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyFirstOfDelegate() {
            final Box<O> param = new Box<O>();
            final Function<O, O> spied = Spies.spy1st(Function.identity(), param);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyBinaryDelegate() {
            final Box<O> result = new Box<O>();
            final Box<O> param1 = new Box<O>();
            final Box<O> param2 = new Box<O>();
            final BiFunction<O, O, O> spied = Spies.spy(new FirstParam<O, O>(), result, param1, param2);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyResultOfBinaryDelegate() {
            final Box<O> result = new Box<O>();
            final BiFunction<O, O, O> spied = Spies.spyRes(new FirstParam<O, O>(), result);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyFirstParamOfBinaryDelegate() {
            final Box<O> param1 = new Box<O>();
            final BiFunction<O, O, O> spied = Spies.spy1st(new FirstParam<O, O>(), param1);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpySecondParamOfBinaryDelegate() {
            final Box<O> param2 = new Box<O>();
            final BiFunction<O, O, O> spied = Spies.spy2nd(new FirstParam<O, O>(), param2);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyTernaryDelegate() {
            final Box<O> result = new Box<O>();
            final Box<O> param1 = new Box<O>();
            final Box<O> param2 = new Box<O>();
            final Box<O> param3 = new Box<O>();
            final TriFunction<O, O, O, O> spied = Spies.spy(new FirstParamOfThree<O, O, O>(), result, param1, param2, param3);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyResultOfTernaryDelegate() {
            final Box<O> result = new Box<O>();
            final TriFunction<O, O, O, O> spied = Spies.spyRes(new FirstParamOfThree<O, O, O>(), result);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyFirstParamOfTernaryDelegate() {
            final Box<O> param1 = new Box<O>();
            final TriFunction<O, O, O, O> spied = Spies.spy1st(new FirstParamOfThree<O, O, O>(), param1);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpySecondParamOfTernaryDelegate() {
            final Box<O> param2 = new Box<O>();
            final TriFunction<O, O, O, O> spied = Spies.spy2nd(new FirstParamOfThree<O, O, O>(), param2);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyThirdParamOfTernaryDelegate() {
            final Box<O> param3 = new Box<O>();
            final TriFunction<O, O, O, O> spied = Spies.spy3rd(new FirstParamOfThree<O, O, O>(), param3);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyAProposition() {
            final Box<Boolean> result = new Box<Boolean>();
            final BooleanSupplier spied = Spies.spy(new Yes(), result);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyAPredicate() {
            final Box<Boolean> result = new Box<Boolean>();
            final Box<O> param = new Box<O>();
            final Predicate<O> spied = Spies.spy(new Always<O>(), result, param);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyResultOfPredicate() {
            final Box<Boolean> result = new Box<Boolean>();
            final Predicate<O> spied = Spies.spyRes(new Always<O>(), result);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyFirstParamOfPredicate() {
            final Box<O> param = new Box<O>();
            final Predicate<O> spied = Spies.spy1st(new Always<O>(), param);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyBinaryPredicate() {
            final Box<Boolean> result = new Box<Boolean>();
            final Box<O> param1 = new Box<O>();
            final Box<O> param2 = new Box<O>();
            final BiPredicate<O, O> spied = Spies.spy(new BinaryAlways<O, O>(), result, param1, param2);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyResultOfBinaryPredicate() {
            final Box<Boolean> result = new Box<Boolean>();
            final BiPredicate<O, O> spied = Spies.spyRes(new BinaryAlways<O, O>(), result);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyFirstParamOfBinaryPredicate() {
            final Box<O> param1 = new Box<O>();
            final BiPredicate<O, O> spied = Spies.spy1st(new BinaryAlways<O, O>(), param1);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpySecondParamOfBinaryPredicate() {
            final Box<O> param2 = new Box<O>();
            final BiPredicate<O, O> spied = Spies.spy2nd(new BinaryAlways<O, O>(), param2);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyTernaryPredicate() {
            final Box<Boolean> result = new Box<Boolean>();
            final Box<O> param1 = new Box<O>();
            final Box<O> param2 = new Box<O>();
            final Box<O> param3 = new Box<O>();
            final TriPredicate<O, O, O> spied = Spies.spy(new TernaryAlways<O, O, O>(), result, param1, param2, param3);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyResultOfTernaryPredicate() {
            final Box<Boolean> result = new Box<Boolean>();
            final TriPredicate<O, O, O> spied = Spies.spyRes(new TernaryAlways<O, O, O>(), result);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyFirstParamOfTernaryPredicate() {
            final Box<O> param1 = new Box<O>();
            final TriPredicate<O, O, O> spied = Spies.spy1st(new TernaryAlways<O, O, O>(), param1);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpySecondParamOfTernaryPredicate() {
            final Box<O> param2 = new Box<O>();
            final TriPredicate<O, O, O> spied = Spies.spy2nd(new TernaryAlways<O, O, O>(), param2);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyThirdParamOfTernaryPredicate() {
            final Box<O> param3 = new Box<O>();
            final TriPredicate<O, O, O> spied = Spies.spy3rd(new TernaryAlways<O, O, O>(), param3);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyAnAction() {
            final Box<O> param = new Box<O>();
            final Consumer<O> spied = Spies.spy(new Noop<O>(), param);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyParamOfAnAction() {
            final Box<O> param = new Box<O>();
            final Consumer<O> spied = Spies.spy1st(new Noop<O>(), param);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyBinaryAction() {
            final Box<O> param1 = new Box<O>();
            final Box<O> param2 = new Box<O>();
            final BiConsumer<O, O> spied = Spies.spy(new BinaryNoop<O, O>(), param1, param2);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyFirstParmaOfBinaryAction() {
            final Box<O> param1 = new Box<O>();
            final BiConsumer<O, O> spied = Spies.spy1st(new BinaryNoop<O, O>(), param1);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpySecondParmaOfBinaryAction() {
            final Box<O> param2 = new Box<O>();
            final BiConsumer<O, O> spied = Spies.spy2nd(new BinaryNoop<O, O>(), param2);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyTernaryAction() {
            final Box<O> param1 = new Box<O>();
            final Box<O> param2 = new Box<O>();
            final Box<O> param3 = new Box<O>();
            final TriConsumer<O, O, O> spied = Spies.spy(new TernaryNoop<O, O, O>(), param1, param2, param3);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyFirstParamOfTernaryAction() {
            final Box<O> param1 = new Box<O>();
            final TriConsumer<O, O, O> spied = Spies.spy1st(new TernaryNoop<O, O, O>(), param1);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpySecondParamOfTernaryAction() {
            final Box<O> param2 = new Box<O>();
            final TriConsumer<O, O, O> spied = Spies.spy2nd(new TernaryNoop<O, O, O>(), param2);
            Assert.assertNotNull(spied);
        }

        @Test
        public void canSpyThirdParamOfTernaryAction() {
            final Box<O> param3 = new Box<O>();
            final TriConsumer<O, O, O> spied = Spies.spy3rd(new TernaryNoop<O, O, O>(), param3);
            Assert.assertNotNull(spied);
        }

        @Test
        public void facadeIsNotFinal() {
            new Spies() {
            };
        }
    }

    public static class Monitor {

        private AtomicLong accumulator = new AtomicLong();

        @Test
        public void canMonitorARunnable() {
            final Runnable monitor = Spies.monitor(new Slacker(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorAProvider() {
            final Supplier<O> monitor = Spies.monitor(new ConstantSupplier<O>(O.ONE), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorAProposition() {
            final BooleanSupplier monitor = Spies.monitor(new Yes(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorAnAction() {
            final Consumer<O> monitor = Spies.monitor(new Noop<O>(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorADelegate() {
            final Function<O, O> monitor = Spies.monitor(Function.identity(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorAPredicate() {
            final Predicate<O> monitor = Spies.monitor(new Always<O>(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorABinaryAction() {
            final BiConsumer<O, O> monitor = Spies.monitor(new BinaryNoop<O, O>(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorABinaryDelegate() {
            final BiFunction<O, O, O> monitor = Spies.monitor(new FirstParam<O, O>(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorABinaryPredicate() {
            final BiPredicate<O, O> monitor = Spies.monitor(new BinaryAlways<O, O>(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorATernaryAction() {
            final TriConsumer<O, O, O> monitor = Spies.monitor(new TernaryNoop<O, O, O>(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorATernaryDelegate() {
            final TriFunction<O, O, O, O> monitor = Spies.monitor(new FirstParamOfThree<O, O, O>(), accumulator);
            Assert.assertNotNull(monitor);
        }

        @Test
        public void canMonitorATernaryPredicate() {
            final TriPredicate<O, O, O> monitor = Spies.monitor(new TernaryAlways<O, O, O>(), accumulator);
            Assert.assertNotNull(monitor);
        }
    }
}
