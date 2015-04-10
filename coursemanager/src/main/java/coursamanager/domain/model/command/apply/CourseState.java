package coursamanager.domain.model.command.apply;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import coursamanager.domain.model.CategoryDefinition;
import coursamanager.domain.model.command.Status;

public class CourseState {

    Status status;
    final SortedSet<Integer> enlistedRunners;
    final Map<String, CategoryDefinition> categories;

    private CourseState(final Status status, final SortedSet<Integer> enlistedRunners,
            final Map<String, CategoryDefinition> categories) {
        this.status = status;
        this.enlistedRunners = enlistedRunners;
        this.categories = categories;
    }

    public Status status() {
        return status;
    }

    public SortedSet<Integer> enlistedRunners() {
        return Collections.unmodifiableSortedSet(enlistedRunners);
    }

    public static CourseState courseState(final Status status, final Collection<Integer> enlistedRunners,
            final Map<String, CategoryDefinition> categories) {
        return new CourseState(status, new TreeSet<>(enlistedRunners), new HashMap<>(categories));
    }

    @Override
    public boolean equals(final Object obj) {
        // TODO
        throw new UnsupportedOperationException("Not implement yet");
    }

    @Override
    public int hashCode() {
        // TODO
        throw new UnsupportedOperationException("Not implement yet");
    }

}
