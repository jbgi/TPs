package coursamanager.domain.model.command.apply;

import java.util.Collection;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import coursamanager.domain.model.command.Status;

public class CourseState {

    private final Status status;
    private final SortedSet<Integer> enlistedRunners;

    private CourseState(final Status status, final SortedSet<Integer> enlistedRunners) {
        this.status = status;
        this.enlistedRunners = enlistedRunners;
    }

    public Status status() {
        return status;
    }

    public SortedSet<Integer> enlistedRunners() {
        return Collections.unmodifiableSortedSet(enlistedRunners);
    }

    public static CourseState courseState(final Status status, final Collection<Integer> enlistedRunners) {
        return new CourseState(status, new TreeSet<>(enlistedRunners));
    }

}
