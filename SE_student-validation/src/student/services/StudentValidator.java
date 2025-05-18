package student.services;

import student.exceptions.InvalidDepartmentException;
import student.exceptions.UnderAgeException;

public class StudentValidator {

    public static void validate(int age, String department)
            throws UnderAgeException, InvalidDepartmentException {

        if (age < 18) {
            throw new UnderAgeException("Age must be 18 or older.");
        }

        if (!department.equalsIgnoreCase("ICT")) {
            throw new InvalidDepartmentException("Department must be ICT.");
        }
    }
}
