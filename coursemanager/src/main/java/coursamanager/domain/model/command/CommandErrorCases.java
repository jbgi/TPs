package coursamanager.domain.model.command;

import com.github.sviperll.adt4j.GenerateValueClassForVisitor;
import com.github.sviperll.meta.Visitor;

@Visitor(resultVariableName = "R")
@GenerateValueClassForVisitor(isPublic = true, acceptMethodName = "match", className = "CommandError")
public interface CommandErrorCases<R> {

    R invalidCourseStateForCommand();

    R courseNameTooShort();

    R invalidMaxPartipants();

}
