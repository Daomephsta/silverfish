package daomephsta.silverfish.codegen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

public abstract class SilverfishAnnotationProcessor extends AbstractProcessor
{
    protected static final Pattern TEMPLATE_DUMMY =
        Pattern.compile("\\/\\*!!(\\w+)\\*\\/.+\\/\\*!!\\*\\/");

    private final boolean repeatable;

    protected SilverfishAnnotationProcessor(boolean repeatable)
    {
        this.repeatable = repeatable;
    }

    @Override
    public final boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
    {
        for (TypeElement annotationType : annotations)
        {
            for (Element annotated : roundEnv.getElementsAnnotatedWith(annotationType))
            {
                var packageElement = (PackageElement) annotated;
                getAnnotationMirrors(annotated, annotationType).forEach(annotation ->
                    generate(packageElement.getQualifiedName(),
                        annotation, new AnnotationAttributes(annotation)));
            }
        }
        return true;
    }

    protected abstract void generate(Name packageName, AnnotationMirror annotation,
        AnnotationAttributes attributes);

    protected void generateFromTemplate(Map<String, String> replacements,
        String fileName, String templateId)
    {
        try
        {
            JavaFileObject generatedFile = processingEnv.getFiler().createSourceFile(
                fileName);
            try (BufferedReader template = getTemplateReader(
                    templateId);
                 Writer generated = generatedFile.openWriter())
            {
                String line = null;
                while ((line = template.readLine()) != null)
                {
                    generated.write(TEMPLATE_DUMMY.matcher(line)
                        .replaceAll(result -> replacements.get(result.group(1))));
                    generated.write('\n');
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private BufferedReader getTemplateReader(String template)
    {
        return new BufferedReader(new InputStreamReader(getClass()
            .getResourceAsStream("/daomephsta/silverfish/codegen/template/" + template)));
    }

    private Stream<? extends AnnotationMirror> getAnnotationMirrors(Element annotated, TypeElement annotationType)
    {
        Stream<? extends AnnotationMirror> mirrors = annotated.getAnnotationMirrors().stream()
            .filter(mirror -> mirror.getAnnotationType().asElement().equals(annotationType));
        if (repeatable)
            mirrors = mirrors.flatMap(this::unwrapRepeatable);
        return mirrors;
    }

    private Stream<? extends AnnotationMirror> unwrapRepeatable(AnnotationMirror annotation)
    {
        var elementValues = annotation.getElementValues();
        for (ExecutableElement attribute : elementValues.keySet())
        {
            if (attribute.getSimpleName().contentEquals("value"))
                return unwrapAnnotationListAttribute(elementValues.get(attribute));
        }
        throw new IllegalStateException(annotation + " has no value attribute");
    }

    private Stream<? extends AnnotationMirror> unwrapAnnotationListAttribute(AnnotationValue value)
    {
        return ((List<?>) value.getValue()).stream().map(r ->
        {
            if (r instanceof AnnotationValue rav)
                return (AnnotationMirror) rav.getValue();
            return (AnnotationMirror) r;
        });
    }
}
