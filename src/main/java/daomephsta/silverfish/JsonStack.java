package daomephsta.silverfish;

import static java.util.stream.Collectors.joining;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.google.common.collect.Sets;
import com.google.common.collect.Streams;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class JsonStack
{
    private record Member(JsonObject json, String name) {}
    private final Gson gson;
    private final Deque<Member> elementPath = new ArrayDeque<>();
    private final List<String> errors = new ArrayList<>();

    public JsonStack(Gson gson, JsonElement root)
    {
        this.gson = gson;
        elementPath.push(new Member(root.getAsJsonObject(), ""));
    }

    public JsonStack push(String key)
    {
        elementPath.push(new Member(child(key, JsonType.OBJECT), key));
        return this;
    }

    public JsonStack pop()
    {
        elementPath.pop();
        return this;
    }

    public JsonStack allow(String... allowed)
    {
        return allow(Set.of(allowed));
    }

    public JsonStack allow(Set<String> allowed)
    {
        Set<String> present = elementPath.peek().json.keySet();
        Set<String> unexpected = Sets.difference(present, allowed);
        if (!unexpected.isEmpty())
        {
            errors.add(joinPath() + " allows children " +
                allowed + ". Did not expect " + unexpected);
        }
        return this;
    }

    public OptionalInt maybeInt(String key)
    {
        Optional<JsonPrimitive> child = maybeChild(key, JsonType.NUMBER);
        return child.isPresent()
            ? OptionalInt.of(child.get().getAsInt())
            : OptionalInt.empty();
    }

    public <T> Stream<T> streamAs(String key, Class<T> elementType)
    {
        return maybeChild(key, JsonType.ARRAY).stream()
            .flatMap(array -> StreamSupport.stream(array.spliterator(), false))
            .map(element -> gson.fromJson(element, elementType));
    }

    private <T extends JsonElement> T child(String key, JsonType<T> expected)
    {
        return maybeChild(key, expected).orElseGet(() ->
        {
            errors.add("Missing " + expected.name + ' ' + joinPath() + '/' + key);
            return expected.dummy();
        });
    }

    public List<String> getErrors()
    {
        return errors;
    }

    private <T extends JsonElement> Optional<T> maybeChild(String key, JsonType<T> expected)
    {
        JsonObject head = elementPath.peek().json;
        if (head.has(key))
        {
            JsonElement element = head.get(key);
            if (expected.is(element))
                return Optional.of(expected.cast(element));
            else
            {
                errors.add(joinPath() + '/' + key + " must be " + expected.name +
                    " not " + JsonType.of(element).name);
            }
        }
        return Optional.empty();
    }

    private String joinPath()
    {
        return Streams.stream(elementPath.descendingIterator())
            .map(Member::name)
            .collect(joining("/"));
    }
}
