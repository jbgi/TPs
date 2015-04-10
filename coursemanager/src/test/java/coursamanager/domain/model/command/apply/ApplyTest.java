package coursamanager.domain.model.command.apply;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import coursamanager.domain.model.command.Status;
import coursamanager.domain.model.event.CourseEvent;

public class ApplyTest {

    @Test
    public void testCourseCreated() {
        // given
        final CourseState initialState = Apply.initialState();
        final CourseEvent creationEvent = CourseEvent.courseCreated("Ma Course");

        // when
        final CourseState newState = Apply.onCourseState(initialState).event(creationEvent);

        // then
        assertEquals(newState.status, Status.Preparation);
    }

    @Test
    public void testCourseRenamed() {
        // TODO
        fail("Not yet implemented");
    }

    @Test
    public void testCategoryDefined() {
        // TODO
        fail("Not yet implemented");
    }

    @Test
    public void testCategoryRemoved() {
        // TODO
        fail("Not yet implemented");
    }

    @Test
    public void testRunnerEnlisted() {
        // TODO
        fail("Not yet implemented");
    }

    @Test
    public void testRunnerUnlisted() {
        // TODO
        fail("Not yet implemented");
    }

}
