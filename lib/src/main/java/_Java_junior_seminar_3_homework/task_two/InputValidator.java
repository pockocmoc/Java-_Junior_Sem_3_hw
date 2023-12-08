package _Java_junior_seminar_3_homework.task_two;

public class InputValidator {
	public boolean isValidName(String name) {
		if (name.isEmpty()) {
			return false;
		}

		for (char c : name.toCharArray()) {
			if (!Character.isLetter(c)) {
				return false;
			}
		}

		return true;
	}

	public boolean isValidAge(int age) {
		return age >= 18 && age <= 100;
	}

	public boolean isValidAverageGrade(double averageGrade) {
		return averageGrade >= 1 && averageGrade <= 5;
	}

}
