package daomephsta.silverfish.codegen;

import static java.util.stream.Collectors.toMap;

import java.util.Map;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.util.Elements;

public class AnnotationAttributes
{
    private final Map<String, Object> attributes;

    public AnnotationAttributes(AnnotationMirror annotation, Elements elements)
    {
        this.attributes = elements.getElementValuesWithDefaults(annotation).entrySet().stream()
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
