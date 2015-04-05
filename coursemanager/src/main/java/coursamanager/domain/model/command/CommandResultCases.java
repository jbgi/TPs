package coursamanager.domain.model.command;

import java.util.List;

import com.github.sviperll.adt4j.GenerateValueClassForVisitor;
import com.github.sviperll.meta.Visitor;

import coursamanager.domain.model.event.CourseEvent;

@Visitor(resultVariableName = "R")
@GenerateValueClassForVisitor(isPublic = true, acceptMethodName = "match", className = "CommandResult")
public interface CommandResultCases<R> {

    R error(CommandError error);

    R events(List<CourseEvent> events);

}
