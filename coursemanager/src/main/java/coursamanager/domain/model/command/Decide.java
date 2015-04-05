package coursamanager.domain.model.command;

import static coursamanager.domain.model.command.CommandResult.error;
import static coursamanager.domain.model.command.CommandResult.events;
import static java.util.Arrays.asList;

import java.time.Instant;

import coursamanager.domain.model.CategoryDefinition;
import coursamanager.domain.model.RunnerDetails;
import coursamanager.domain.model.command.apply.CourseState;
import coursamanager.domain.model.event.CourseEvent;

public class Decide implements CourseCommandCases<CommandResult> {

    private final CourseState etatCourse;
    private final Instant now;

    private Decide(final CourseState etatCourse, final Instant now) {
        this.etatCourse = etatCourse;
        this.now = now;
    }

    public static Decide given(final CourseState etatCourse, final Instant now) {
        return new Decide(etatCourse, now);
    }

    public CommandResult resultOf(final CourseCommand command) {
        return command.match(this);
    }

    @Override
    public CommandResult createCourse(final String nomCourse) {

        final CommandResult result;

        if (!Status.New.equals(etatCourse.status())) {
            // REGLE 1: l'état de la course doit être à New pour pouvoir accepter la commande createCourse
            result = error(CommandError.invalidCourseStateForCommand());

        } else if (nomCourse.length() < 3) {
            // REGLE 2: on ne peut utiliser moins de 3 caractères pour le nom d'un course
            result = error(CommandError.courseNameTooShort());

        } else {
            result = events(asList(CourseEvent.courseCreated(nomCourse)));
        }

        return result;
    }

    @Override
    public CommandResult renameCourse(final String nomCourse) {

        final CommandResult result;

        if (!Status.Preparation.equals(etatCourse.status())) {
            // REGLE 3: l'état de la course doit être à Preparation pour pouvoir accepter la commande renameCourse
            result = error(CommandError.invalidCourseStateForCommand());

        } else if (nomCourse.length() < 3) {
            // REGLE 2: on ne peut utiliser moins de 3 caractères pour le nom d'un course
            result = error(CommandError.courseNameTooShort());

        } else {
            result = events(asList(CourseEvent.courseRenamed(nomCourse)));
        }

        return result;
    }

    @Override
    public CommandResult setMaxParticipants(final int maxParticipants) {

        final CommandResult result;

        // TODO
        // REGLE 3: l'état de la course doit être à Preparation pour pouvoir accepter la commande renameCours4
        // REGLE 5: on ne peut pas utiliser une valeur inférieure au nombre d'inscrit pour maxParticipant.
        throw new UnsupportedOperationException("not implemented yet");

        // return result;
    }

    @Override
    public CommandResult defineCategory(final String categoryName, final CategoryDefinition categoryDefinition) {

        final CommandResult result;

        // TODO
        // REGLES: à définir de façon réaliste...
        throw new UnsupportedOperationException("not implemented yet");

        // return result;
    }

    @Override
    public CommandResult removeCategory(final String categoryName) {

        final CommandResult result;

        // TODO
        // REGLES: à définir de façon réaliste...
        throw new UnsupportedOperationException("not implemented yet");

        // return result;
    }

    @Override
    public CommandResult enlistRunner(final RunnerDetails runnerDetails) {

        final CommandResult result;

        // TODO
        // REGLES: à définir de façon réaliste...
        throw new UnsupportedOperationException("not implemented yet");

        // return result;
    }

    @Override
    public CommandResult unlistRunner(final int numroDossard) {

        final CommandResult result;

        // TODO
        // REGLES: à définir de façon réaliste...
        throw new UnsupportedOperationException("not implemented yet");

        // return result;
    }

}
