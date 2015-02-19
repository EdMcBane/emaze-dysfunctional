package net.emaze.dysfunctional.multiplexing;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import net.emaze.dysfunctional.contracts.dbc;
import java.util.function.Supplier;
import net.emaze.dysfunctional.iterations.ReadOnlyIterator;
import net.emaze.dysfunctional.options.Box;
import net.emaze.dysfunctional.options.Maybe;
import net.emaze.dysfunctional.tuples.Pair;

/**
 * shortest
 *
 * @param <C>
 * @param <T>
 * @author rferranti
 */
public class UnchainIterator<C extends Collection<T>, T> extends ReadOnlyIterator<C> {

    private final Supplier<Maybe<Integer>> channelsSizesProvider;
    private final Iterator<T> iterator;
    private final Supplier<C> channelProvider;
    private final Box<Pair<Integer, C>> prefetched = Box.empty();

    public UnchainIterator(Supplier<Maybe<Integer>> channelsSizesProvider, Iterator<T> iterator, Supplier<C> channelProvider) {
        dbc.precondition(channelsSizesProvider != null, "channelsSizes cannot be null");
        dbc.precondition(iterator != null, "iterator cannot be null");
        dbc.precondition(channelProvider != null, "channelProvider cannot be null");
        this.channelsSizesProvider = channelsSizesProvider;
        this.iterator = iterator;
        this.channelProvider = channelProvider;
    }

    @Override
    public boolean hasNext() {
        if (!prefetched.hasContent()) {
            prefetched.setContent(fetch(iterator, channelsSizesProvider));
        }
        return prefetched.getContent().second().size() == prefetched.getContent().first();
    }

    @Override
    public C next() {
        if (prefetched.hasContent()) {
            if (prefetched.getContent().second().size() != prefetched.getContent().first()) {
                throw new NoSuchElementException("iterator is not squared");
            }
            return prefetched.unload().value().second();
        }
        return fetch(iterator, channelsSizesProvider).second();
    }

    private Pair<Integer, C> fetch(Iterator<T> iter, Supplier<Maybe<Integer>> sizeProvider) {
        final Maybe<Integer> maybeChannelSize = sizeProvider.get();
        dbc.state(maybeChannelSize.hasValue(), "unexpected channel size request (provider returned Nothing)");
        final int channelSize = maybeChannelSize.value();
        final C result = channelProvider.get();
        for (int counter = 0; counter != channelSize && iter.hasNext(); ++counter) {
            result.add(iter.next());
        }
        return Pair.of(channelSize, result);
    }
}