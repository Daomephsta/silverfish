package daomephsta.silverfish.origin_tracing;

import java.util.function.Predicate;

public class Before<T> implements Predicate<T>
{
    private final Predicate<T> matcher;
    private boolean targetFound = false;

    public Before(Predicate<T> matcher)
    {
        this.matcher = matcher;
    }

    @Override
    public boolean test(T t)
    {
        if (!targetFound && matcher.test(t))
            targetFound = true;
        return !targetFound;
    }
}