package coursamanager.domain.model.command;

import static coursamanager.domain.model.command.CommandResult.error;
import static coursamanager.domain.model.command.CommandResult.events;
import static coursamanager.domain.model.command.apply.CourseState.courseState;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.Collections;

import org.junit.Test;

import coursamanager.domain.model.command.apply.CourseState;
import coursamanager.domain.model.event.CourseEvent;

public class DecideTest {

    @Test
    public void createCourse() {
        // given
        final CourseCommand command = CourseCommand.createCourse("Ma course");
        final CourseState etatCourse = courseState(Status.New, Collections.emptySet(), Collections.emptyMap());
        final Instant now = Instant.now();

        // when
        final CommandResult commandResult = Decide.given(etatCourse, now).resultOf(command);

        // then
        assertEquals(events(asList(CourseEvent.courseCreated("Ma course"))), commandResult);

    }

    @Test
    public void createCourse_Already_Created() {
        // given
        final CourseCommand command = CourseCommand.createCourse("Ma course");
        final CourseState etatCourse = courseState(Status.InProgress, Collections.emptySet(), Collections.emptyMap());
        final Instant now = Instant.now();

        // when
        final CommandResult commandResult = Decide.given(etatCourse, now).resultOf(command);

        // then
        assertEquals(error(CommandError.invalidCourseStateForCommand()), commandResult);

    }

    @Test
    public void createCourse_Name_too_short() {
        // given
        final CourseCommand command = CourseCommand.createCourse("Ma");
        final CourseState etatCourse = courseState(Status.New, Collections.emptySet(), Collections.emptyMap());
        final Instant now = Instant.now();

        // when
        final CommandResult commandResult = Decide.given(etatCourse, now).resultOf(command);

        // then
        assertEquals(error(CommandError.courseNameTooShort()), commandResult);

    }

    @Test
    public void renameCourse() {
        // given
        final CourseCommand command = CourseCommand.renameCourse("Ma Course 2");
        final CourseState etatCourse = courseState(Status.Preparation, Collections.emptySet(), Collections.emptyMap());
        final Instant now = Instant.now();

        // when
        final CommandResult commandResult = Decide.given(etatCourse, now).resultOf(command);

        // then
        assertEquals(events(asList(CourseEvent.courseRenamed("Ma Course 2"))), commandResult);
    }

    // TODO : tests pour toutes les autres commandes

}
