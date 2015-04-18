package coursamanager.infrastructure.json;

import java.util.Optional;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;

import coursamanager.domain.model.AgeInterval;
import coursamanager.domain.model.CategoryDefinition;
import coursamanager.domain.model.Genre;

public class CategoryDefinitionJson implements JsonConverter<CategoryDefinition> {

    private static final JsonConverter<AgeInterval> ageIntervalJson = new AgeIntervalJson();

    private static final JsonConverter<Optional<Genre>> genreJson = new OptionalJson<>(new GenreJson());

    @Override
    public JsonGenerator writeObject(final Optional<String> optionalName, final CategoryDefinition t, final JsonGenerator jg) {
        JsonConverter.startComplexObject(optionalName, jg);
        ageIntervalJson.writeObject(Optional.of("ageInterval"), t.ageInterval(), jg);
        genreJson.writeObject(Optional.of("genre"), t.genre(), jg);
        return jg.writeEnd();
    }

    @Override
    public CategoryDefinition readObject(final JsonParser jp) throws JsonParsingException {
        jp.next();
        jp.next();
        final AgeInterval ageInterval = ageIntervalJson.readObject(jp);
        jp.next();
        // jp.next();
        final Optional<Genre> genre = genreJson.readObject(jp);
        jp.next();
        return CategoryDefinition.category(ageInterval, genre);
    }

}
