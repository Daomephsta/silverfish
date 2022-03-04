package daomephsta.silverfish.origin_tracing;

import java.lang.StackWalker.StackFrame;
import java.util.List;

public interface OriginAware
{
    public List<StackFrame> silverfish_getOrigin();
}
