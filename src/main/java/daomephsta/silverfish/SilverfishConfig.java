package daomephsta.silverfish;

import static java.util.stream.Collectors.toSet;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.OptionalInt;
import java.util.Set;

import com.google.common.base.Predicates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import net.fabricmc.loader.api.FabricLoader;

public class SilverfishConfig
{
    private static SilverfishConfig INSTANCE;
    private static final Gson GSON = new GsonBuilder()
        .registerTypeAdapter(SilverfishConfig.class, new Serialiser())
        .setLenient().create();

    public final OriginTracing originTracing;
    public static class OriginTracing
    {
        private final Collection<String> classNames;
        private Collection<Class<?>> classes;
        public final OptionalInt depth;

        private OriginTracing(Collection<String> classNames, OptionalInt depth)
        {
            this.classNames = classNames;
            this.depth = depth;
        }

        public boolean isEnabled()
        {
            return !classNames.isEmpty();
        }

        public boolean shouldTrace(Object object)
        {
            if (classes == null)
            {
                classes = classNames.stream()
                    .map(this::loadClass)
                    .filter(Predicates.notNull())
                    .collect(toSet());
            }
            for (Class<?> clazz : classes)
            {
                if (clazz.isInstance(object))
                    return true;
            }
            return false;
        }

        private Class<?> loadClass(String name)
        {
            try
            {
                return Class.forName(name, false, getClass().getClassLoader());
            }
            catch (ClassNotFoundException e)
            {
                Silverfish.LOGGER.error("Unknown class in array originTracing/classes", e);
                return null;
            }
        }
    }
    public record ImproveToStrings(boolean enabled) {}
    public final ImproveToStrings improveToStrings;

    private SilverfishConfig(OriginTracing originTracing, ImproveToStrings improveToStrings)
    {
        this.originTracing = originTracing;
        this.improveToStrings = improveToStrings;
    }

    public static SilverfishConfig instance()
    {
        if (INSTANCE == null)
        {
            Path config = FabricLoader.getInstance().getConfigDir().resolve("silverfish.json");
            try
            {
                if (!Files.exists(config))
                {
                    Files.createDirectories(config.getParent());
                    Files.copy(SilverfishConfig.class.getResourceAsStream("/default_config.json"), config);
                }
                try (Reader reader = Files.newBufferedReader(config))
                {
                    INSTANCE = GSON.fromJson(reader, SilverfishConfig.class);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    private static class Serialiser implements JsonDeserializer<SilverfishConfig>
    {
        @Override
        public SilverfishConfig deserialize(
            JsonElement root, Type type, JsonDeserializationContext context)
            throws JsonParseException
        {
            var jsonStack = new JsonStack(GSON, root);

            jsonStack.push("originTracing").allow("classes", "depth");
            var depth = jsonStack.maybeInt("depth");
            Set<String> classes = jsonStack.streamAs("classes", String.class)
                .collect(toSet());
            jsonStack.pop();

            jsonStack.push("improveToStrings").allow("enabled");
            boolean improveToStrings = jsonStack.getBoolean("enabled");
            jsonStack.pop();

            if (!jsonStack.getErrors().isEmpty())
            {
                Silverfish.LOGGER.error("Config loading failed, JVM terminated");
                for (String error : jsonStack.getErrors())
                    Silverfish.LOGGER.error("\t" + error);
                System.exit(-1);
            }
            return new SilverfishConfig(
                new OriginTracing(classes, depth),
                new ImproveToStrings(improveToStrings));
        }
    }
}
