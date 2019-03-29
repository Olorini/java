package basic.exceptionExamples;

public interface IExceptionExamples {

	double calculate(String expr);

	double resolve(String expr) throws MyCheckedException;
}
