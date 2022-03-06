package daomephsta.silverfish.codegen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

@Repeatable(RegistryObjectToString.Container.class)
@Target(ElementType.PACKAGE)
public @interface RegistryObjectToString
{
    Class<?> target();
    String registry();

    @Target(ElementType.PACKAGE)
    public @interface Container
    {
        RegistryObjectToString[] value();
    }
}
