package daomephsta.silverfish.origin_tracing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.OptionalInt;

import daomephsta.silverfish.Silverfish;

public class OriginTracingSettings
{
    private static final String PREFIX = "daomephsta.silverfish.origin_tracing.";
    private static final String CLASSES = PREFIX + "classes";
    private static final String TRACE_DEPTH = PREFIX + "trace_depth";
    private static Collection<Class<?>> targetClasses;
    private static OptionalInt traceDepth = OptionalInt.empty();

    public static boolean isEnabled()
    {
        return System.getProperty(CLASSES) != null;
    }

    public static boolean shouldTrace(Object object)
    {
        for (Class<?> clazz : getTargetClasses())
        {
            if (clazz.isInstance(object))
                return true;
        }
        return false;
    }

    private static Collection<Class<?>> getTargetClasses()
    {
        if (targetClasses == null)
        {
            targetClasses = new ArrayList<>();
            for (String clazz : System.getProperty(CLASSES).split(","))
            {
                try
                {
                    targetClasses.add(Class.forName(clazz, false, OriginTracingSettings.class.getClassLoader()));
                }
                catch (ClassNotFoundException e)
                {
                    Silverfish.LOGGER.error("Unknown class in daomephsta.origin_tracing.classes", e);
                }
            }
        }
        return targetClasses;
    }

    public static OptionalInt getTraceDepth()
    {
        if (traceDepth == null)
        {
            var depth = Integer.getInteger(TRACE_DEPTH);
            traceDepth = depth != null ? OptionalInt.of(depth) : OptionalInt.empty();
        }
        return traceDepth;
    }
}
