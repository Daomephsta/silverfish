package daomephsta.silverfish;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public final class JsonType<T extends JsonElement>
{
    public static final JsonType<JsonObject> OBJECT = new JsonType<>("JsonObject",
        JsonElement::isJsonObject, JsonElement::getAsJsonObject, JsonObject::new);
    public static final JsonType<JsonArray> ARRAY = new JsonType<>("JsonArray",
        JsonElement::isJsonArray, JsonElement::getAsJsonArray, JsonArray::new);
    public static final JsonType<JsonPrimitive> STRING = new JsonType<>("JsonString",
        element -> element.isJsonPrimitive() && element.getAsJsonPrimitive().isString(),
        JsonElement::getAsJsonPrimitive, () -> new JsonPrimitive(""));
    public static final JsonType<JsonPrimitive> NUMBER = new JsonType<>("JsonNumber",
        element -> element.isJsonPrimitive() && element.getAsJsonPrimitive().isNumber(),
        JsonElement::getAsJsonPrimitive, () -> new JsonPrimitive(1));
    public static final JsonType<JsonPrimitive> BOOLEAN = new JsonType<>("JsonBoolean",
        element -> element.isJsonPrimitive() && element.getAsJsonPrimitive().isBoolean(),
        JsonElement::getAsJsonPrimitive, () -> new JsonPrimitive(false));
    private static final JsonType<?>[] TYPES = {OBJECT, ARRAY, STRING, NUMBER, BOOLEAN};

    public final String name;
    private final Predicate<JsonElement> is;
    private final Function<JsonElement, T> cast;
    private final Supplier<T> dummy;

    private JsonType(String name, Predicate<JsonElement> is, Function<JsonElement, T> cast,
        Supplier<T> dummy)
    {
        this.name = name;
        this.is = is;
        this.cast = cast;
        this.dummy = dummy;
    }

    @SuppressWarnings("unchecked")
    public static <T extends JsonElement> JsonType<? extends T> of(T element)
    {
        for (JsonType<?> type : TYPES)
        {
            if (type.is(element))
                return (JsonType<? extends T>) type;
        }
        throw new IllegalArgumentException();
    }

    public boolean is(JsonElement element)
    {
        return is.test(element);
    }

    public T cast(JsonElement element)
    {
        return cast.apply(element);
    }

    protected T dummy()
    {
        return dummy.get();
    }
}
