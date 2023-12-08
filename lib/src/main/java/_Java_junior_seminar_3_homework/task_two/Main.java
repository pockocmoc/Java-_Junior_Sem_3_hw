package _Java_junior_seminar_3_homework.task_two;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static _Java_junior_seminar_3_homework.task_two.StudentApp.*;

public class Main {

	public static void main(String[] args) {
		List<Student> student;
		File f = new File(FILE_JSON);
		if (f.exists() && !f.isDirectory())
			student = loadStudentFromFile(FILE_JSON);
		else
			student = prepareTasks();
		StudentApp.saveStudentToFile(FILE_JSON, student);
		StudentApp.saveStudentToFile(FILE_BIN, student);
		StudentApp.saveStudentToFile(FILE_XML, student);

		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Выберите действие:");
			System.out.println("1. Посмотреть список студентов");
			System.out.println("2. Добавить добавить нового студента");
			System.out.println("3. Удалить студента");
			System.out.println("4. Выйти");

			String choice = scanner.nextLine();

			switch (choice) {
			case "1":
				displayStudent(FILE_JSON);
				break;
			case "2":
				StudentApp.addNewStudent(scanner, student);
				if (scanner.nextLine().isEmpty()) {
					break;
				}
				break;

			case "3":
				System.out.println("Введите имя студента для удаления:");
				String deleteNameStudent = scanner.nextLine();

				List<Student> studentList = loadStudentFromFile(FILE_JSON);

				boolean isDeleted = false;
				for (Student students : studentList) {
					if (students.getName().equals(deleteNameStudent)) {
						StudentApp.deleteStudent(FILE_JSON, students.getName());
						StudentApp.deleteStudent(FILE_BIN, students.getName());
						StudentApp.deleteStudent(FILE_XML, students.getName());
						isDeleted = true;
						System.out.println(deleteNameStudent + " удален.");
						break;
					}
				}

				if (!isDeleted) {
					System.out.println("Студент с таким именем не найден.");
				}

				break;
			case "4":
				scanner.close();
				System.exit(0);
			default:
				System.out.println("Некорректный выбор. Попробуйте снова.");
				break;
			}

		}

	}

	static List<Student> prepareTasks() {
		ArrayList<Student> list = new ArrayList<>();
		list.add(new Student("Marat", 40, 5.0));
		list.add(new Student("Egor", 20, 4.3));
		list.add(new Student("Curt", 30, 3.6));
		return list;
	}

}
