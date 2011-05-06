package net.emaze.dysfunctional.time;

import java.util.concurrent.TimeUnit;
import junit.framework.Assert;
import net.emaze.dysfunctional.tuples.Pair;
import org.junit.Test;

/**
 *
 * @author rferranti
 */
public class SleepAtLeastTest {

    private final WarpingTimeStrategy strategy = new WarpingTimeStrategy(0l);

    @Test
    public void canSleep() {
        final SleepAtLeast sleeper = new SleepAtLeast(strategy);
        sleeper.perform(1l, TimeUnit.DAYS);
    }

    @Test
    public void interruptingDuringSleepForcesResleepUntilDuration() throws InterruptedException {
        final SleepAtLeast sleeper = new SleepAtLeast(strategy);
        sleeper.perform(1l, TimeUnit.DAYS);
        Assert.assertEquals(Pair.of(TimeUnit.DAYS.toMillis(1l), TimeUnit.MILLISECONDS), strategy.currentTime());
    }

    public static class SelfInterruptingTimeStrategy implements TimeStrategy {

        public long now;

        public SelfInterruptingTimeStrategy(long now) {
            this.now = now;
        }

        @Override
        public Pair<Long, TimeUnit> currentTime() {
            return Pair.of(now, TimeUnit.MILLISECONDS);
        }

        @Override
        public void sleep(long duration, TimeUnit unit) {
            now+= unit.convert(duration, TimeUnit.MILLISECONDS);
            throw new SleepInterruptedException(new InterruptedException("forged-exception"));
        }
    }
}