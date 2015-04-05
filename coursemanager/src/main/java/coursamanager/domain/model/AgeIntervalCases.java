package coursamanager.domain.model;

import com.github.sviperll.adt4j.GenerateValueClassForVisitor;
import com.github.sviperll.meta.Visitor;

@Visitor(resultVariableName = "R")
@GenerateValueClassForVisitor(isPublic = true, acceptMethodName = "match", className = "AgeInterval")
public interface AgeIntervalCases<R> {

    R lessThan(int ageMaxExcluded);

    R atLeast(int ageMinIncluded);

    R between(int ageMinIncluded, int ageMaxExcluded);

}
