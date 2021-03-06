package net.emaze.dysfunctional.sequences;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

class DefaultSequence<T> implements Sequence<T> {

    private final Stream<T> stream;

    DefaultSequence(Stream<T> stream) {
        this.stream = stream;
    }

    @Override
    public Sequence<T> filter(Predicate<? super T> predicate) {
        return Sequence.from(stream.filter(predicate));
    }

    @Override
    public <R> Sequence<R> map(Function<? super T, ? extends R> mapper) {
        return Sequence.from(stream.map(mapper));
    }

    @Override
    public IntStream mapToInt(ToIntFunction<? super T> mapper) {
        return stream.mapToInt(mapper);
    }

    @Override
    public LongStream mapToLong(ToLongFunction<? super T> mapper) {
        return stream.mapToLong(mapper);
    }

    @Override
    public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
        return stream.mapToDouble(mapper);
    }

    @Override
    public <R> Sequence<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
        return Sequence.from(stream.flatMap(mapper));
    }

    @Override
    public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
        return stream.flatMapToInt(mapper);
    }

    @Override
    public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
        return stream.flatMapToLong(mapper);
    }

    @Override
    public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
        return stream.flatMapToDouble(mapper);
    }

    @Override
    public Sequence<T> distinct() {
        return Sequence.from(stream.distinct());
    }

    @Override
    public Sequence<T> sorted() {
        return Sequence.from(stream.sorted());
    }

    @Override
    public Sequence<T> sorted(Comparator<? super T> comparator) {
        return Sequence.from(stream.sorted(comparator));
    }

    @Override
    public Sequence<T> peek(Consumer<? super T> action) {
        return Sequence.from(stream.peek(action));
    }

    @Override
    public Sequence<T> limit(long maxSize) {
        return Sequence.from(stream.limit(maxSize));
    }

    @Override
    public Sequence<T> skip(long n) {
        return Sequence.from(stream.skip(n));
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        stream.forEach(action);
    }

    @Override
    public void forEachOrdered(Consumer<? super T> action) {
        stream.forEachOrdered(action);
    }

    @Override
    public Object[] toArray() {
        return stream.toArray();
    }

    @Override
    public <A> A[] toArray(IntFunction<A[]> generator) {
        return stream.toArray(generator);
    }

    @Override
    public T reduce(T identity, BinaryOperator<T> accumulator) {
        return stream.reduce(identity, accumulator);
    }

    @Override
    public Optional<T> reduce(BinaryOperator<T> accumulator) {
        return stream.reduce(accumulator);
    }

    @Override
    public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
        return stream.reduce(identity, accumulator, combiner);
    }

    @Override
    public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
        return stream.collect(supplier, accumulator, combiner);
    }

    @Override
    public <R, A> R collect(Collector<? super T, A, R> collector) {
        return stream.collect(collector);
    }

    @Override
    public Optional<T> min(Comparator<? super T> comparator) {
        return stream.min(comparator);
    }

    @Override
    public Optional<T> max(Comparator<? super T> comparator) {
        return stream.max(comparator);
    }

    @Override
    public long count() {
        return stream.count();
    }

    @Override
    public boolean anyMatch(Predicate<? super T> predicate) {
        return stream.anyMatch(predicate);
    }

    @Override
    public boolean allMatch(Predicate<? super T> predicate) {
        return stream.allMatch(predicate);
    }

    @Override
    public boolean noneMatch(Predicate<? super T> predicate) {
        return stream.noneMatch(predicate);
    }

    @Override
    public Optional<T> findFirst() {
        return stream.findFirst();
    }

    @Override
    public Optional<T> findAny() {
        return stream.findAny();
    }

    @Override
    public Iterator<T> iterator() {
        return stream.iterator();
    }

    @Override
    public Spliterator<T> spliterator() {
        return stream.spliterator();
    }

    @Override
    public boolean isParallel() {
        return false;
    }

    @Override
    public Sequence<T> onClose(Runnable closeHandler) {
        return Sequence.from(stream.onClose(closeHandler));
    }

    @Override
    public void close() {
        stream.close();
    }

    @Override
    public Sequence<T> sequential() {
        return Sequence.from(stream.sequential());
    }

    @Override
    public Sequence<T> parallel() {
        return Sequence.from(stream.parallel());
    }

    @Override
    public Sequence<T> unordered() {
        return Sequence.from(stream.unordered());
    }
}
