package daomephsta.silverfish.codegen;

import java.util.Map;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

@SupportedSourceVersion(SourceVersion.RELEASE_17)
@SupportedAnnotationTypes("daomephsta.silverfish.codegen.RegistryObjectToString.Container")
public class RegistryObjectToStringProcessor extends SilverfishAnnotationProcessor
{
    public RegistryObjectToStringProcessor()
    {
        super(true);
    }

    @Override
    protected void generate(Name packageName, AnnotationMirror annotation,
        Map<String, ? extends AnnotationValue> attributes)
    {
        var target = (TypeElement) processingEnv.getTypeUtils().asElement(
            (TypeMirror) attributes.get("target").getValue());
        var registry = (String) attributes.get("registry").getValue();
        Map<String, String> replacements = Map.of(
            "package", packageName.toString(),
            "target", target.getQualifiedName().toString(),
            "name", target.getSimpleName() + "ToString",
            "registry", registry);
        generateFromTemplate(replacements,
            packageName + "." + target.getSimpleName() + "ToString",
            "DefaultedRegistryObjectToStringTemplate.java");
    }
}
