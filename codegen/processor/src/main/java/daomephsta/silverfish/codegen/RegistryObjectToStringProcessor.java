package daomephsta.silverfish.codegen;

import java.util.Map;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;

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
        AnnotationAttributes attributes)
    {
        var target = (TypeElement) processingEnv.getTypeUtils()
            .asElement(attributes.get("target"));
        String registry = attributes.get("registry");
        Map<String, String> replacements = Map.of(
            "package", packageName.toString(),
            "target", target.getQualifiedName().toString(),
            "name", target.getSimpleName() + "ToString",
            "registry", registry,
            "annotations", attributes.<Boolean>get("overwrite")
                ? "@org.spongepowered.asm.mixin.Overwrite(remap = false)"
                : "@java.lang.Override");
        generateFromTemplate(replacements,
            packageName + "." + target.getSimpleName() + "ToString",
            "RegistryObjectToStringTemplate.java");
    }
}
