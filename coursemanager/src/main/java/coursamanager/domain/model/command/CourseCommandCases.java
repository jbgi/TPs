package coursamanager.domain.model.command;

import com.github.sviperll.adt4j.GenerateValueClassForVisitor;
import com.github.sviperll.meta.Visitor;

import coursamanager.domain.model.CategoryDefinition;
import coursamanager.domain.model.RunnerDetails;

@Visitor(resultVariableName = "R")
@GenerateValueClassForVisitor(isPublic = true, acceptMethodName = "match", className = "CourseCommand")
public interface CourseCommandCases<R> {

    R createCourse(String nomCourse);

    R renameCourse(String nomCourse);

    R setMaxParticipants(int maxParticipants);

    R defineCategory(String categoryName, CategoryDefinition categoryDefinition);

    R removeCategory(String categoryName);

    R enlistRunner(RunnerDetails runnerDetails);

    R unlistRunner(int numroDossard);

}
