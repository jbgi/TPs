package coursamanager.infrastructure.json;

import java.util.Optional;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParsingException;

import coursamanager.domain.model.Genre;
import coursamanager.domain.model.GenreCases;

public class GenreJson implements JsonConverter<Genre> {

    @Override
    public JsonGenerator writeObject(final Optional<String> optionalName, final Genre genre, final JsonGenerator jg) {
        final String genreCode = genre.match(new GenreCases<String>() {
            @Override
            public String homme() {
                return "h";
            }

            @Override
            public String femme() {
                return "f";
            }
        });

        return JsonConverter.writeAsAttributeValue(optionalName, jg, genreCode);
    }


    @Override
    public Genre readObject(final JsonParser jp) {
        final Genre genre;
        final Event e = jp.next();
        if (e == Event.VALUE_STRING) {
            final String v = jp.getString();

            switch (v) {
                case "h":
                    genre = Genre.homme();
                    break;
                case "f":
                    genre = Genre.femme();
                    break;
                default:
                    throw new JsonParsingException("Expecting genre but found: " + v, jp.getLocation());
            }

        }
        else {
            throw new JsonParsingException("Expecting genre (string value) but found " + e, jp.getLocation());
        }
        return genre;
    }
}
