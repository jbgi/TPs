package coursamanager.domain.model.event;

import com.github.sviperll.adt4j.GenerateValueClassForVisitor;
import com.github.sviperll.meta.Visitor;

import coursamanager.domain.model.CategoryDefinition;
import coursamanager.domain.model.RunnerDetails;

@Visitor(resultVariableName = "R")
@GenerateValueClassForVisitor(isPublic = true, acceptMethodName = "match", className = "CourseEvent")
public interface CourseEventCases<R> {

    R courseCreated(String nomCourse);

    R courseRenamed(String nomCourse);

    R categoryDefined(String categoryName, CategoryDefinition description);

    R categoryRemoved(String categoryName);

    R runnerEnlisted(int numeroDossard, RunnerDetails runnerDetails);

    R runnerUnlisted(int numeroDossard);

}
