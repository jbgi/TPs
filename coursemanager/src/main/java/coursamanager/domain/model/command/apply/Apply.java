package coursamanager.domain.model.command.apply;

import java.util.HashMap;
import java.util.HashSet;

import coursamanager.domain.model.CategoryDefinition;
import coursamanager.domain.model.RunnerDetails;
import coursamanager.domain.model.command.Status;
import coursamanager.domain.model.event.CourseEvent;
import coursamanager.domain.model.event.CourseEventCases;

public class Apply implements CourseEventCases<CourseState> {

    private final CourseState state;

    private Apply(final CourseState state) {
        this.state = state;
    }

    public CourseState event(final CourseEvent event) {
        return event.match(this);
    }

    public static Apply onCourseState(final CourseState state) {
        return new Apply(state);
    }

    @Override
    public CourseState courseCreated(final String nomCourse) {
        state.status = Status.Preparation;
        return state;
    }

    @Override
    public CourseState courseRenamed(final String nomCourse) {
        return state;
    }

    @Override
    public CourseState categoryDefined(final String categoryName, final CategoryDefinition description) {
        // TODO
        throw new UnsupportedOperationException("No implemented yet");
    }

    @Override
    public CourseState categoryRemoved(final String categoryName) {
        // TODO
        throw new UnsupportedOperationException("No implemented yet");
    }

    @Override
    public CourseState runnerEnlisted(final int numeroDossard, final RunnerDetails runnerDetails) {
        state.enlistedRunners.add(numeroDossard);
        return state;
    }

    @Override
    public CourseState runnerUnlisted(final int numeroDossard) {
        // TODO
        throw new UnsupportedOperationException("No implemented yet");
    }

    public static CourseState initialState() {
        return CourseState.courseState(Status.New, new HashSet<>(), new HashMap<>());
    }

}
