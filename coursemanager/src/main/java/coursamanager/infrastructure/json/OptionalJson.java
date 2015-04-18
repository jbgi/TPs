package coursamanager.infrastructure.json;

import java.util.Optional;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParsingException;

public class OptionalJson<T> implements JsonConverter<Optional<T>> {

    private final JsonConverter<T> convert;

    public OptionalJson(final JsonConverter<T> converter) {
        this.convert = converter;
    }

    @Override
    public JsonGenerator writeObject(final Optional<String> optionalName, final Optional<T> option, final JsonGenerator jg) {
        return option.map(t -> {
            JsonConverter.startComplexObject(optionalName, jg);
            return convert.writeObject(Optional.of("some"), t, jg).writeEnd();
        }).orElseGet(() -> JsonConverter.writeAsAttributeValue(optionalName, jg, "none"));
    }

    @Override
    public Optional<T> readObject(final JsonParser jp) throws JsonParsingException {
        final Event e = jp.next();
        final Optional<T> result;
        switch (e) {
            case START_OBJECT:
                jp.next();
                result = Optional.of(convert.readObject(jp));
                jp.next();
                break;

            default:
                result = Optional.empty();
                break;
        }
        return result;
    }

}
