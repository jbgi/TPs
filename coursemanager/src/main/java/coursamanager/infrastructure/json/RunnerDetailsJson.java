
package coursamanager.infrastructure.json;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParsingException;

import coursamanager.domain.model.Genre;
import coursamanager.domain.model.RunnerDetails;

public class RunnerDetailsJson implements JsonConverter<RunnerDetails> {

    private static final JsonConverter<Genre> genreJson = new GenreJson();

    @Override
    public JsonGenerator writeObject(final Optional<String> optionalName, final RunnerDetails t, final JsonGenerator jg) {
        JsonConverter.startComplexObject(optionalName, jg);

        jg.write("nom", t.nom())
                .write("birthDate", t.birthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));

        genreJson.writeObject(Optional.of("genre"), t.genre(), jg);

        return jg.writeEnd();
    }

    @Override
    public RunnerDetails readObject(final JsonParser jp) throws JsonParsingException {
        jp.next();
        jp.next();
        jp.next();
        final String nom = jp.getString();
        jp.next();
        jp.next();
        final LocalDate birthDate = LocalDate.parse(jp.getString(), DateTimeFormatter.ISO_LOCAL_DATE);
        jp.next();
        final Genre genre = genreJson.readObject(jp);
        jp.next();
        return RunnerDetails.runnerDetails(nom, birthDate, genre);
    }

}
