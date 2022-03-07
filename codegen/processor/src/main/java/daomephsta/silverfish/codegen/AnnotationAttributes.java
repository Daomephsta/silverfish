package daomephsta.silverfish.codegen;

import static java.util.stream.Collectors.toMap;

import java.util.Map;

import javax.lang.model.element.AnnotationMirror;

public class AnnotationAttributes
{
    private final Map<String, Object> attributes;

    public AnnotationAttributes(AnnotationMirror annotation)
    {
        this.attributes = annotation.getElementValues().entrySet().stream()
            .collect(toMap(
                e -> e.getKey().getSimpleName().toString(),
                e -> e.getValue().getValue()));
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String string)
    {
        return (T) attributes.get(string);
    }
}
