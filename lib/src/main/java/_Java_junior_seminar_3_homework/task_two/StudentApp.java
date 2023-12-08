package _Java_junior_seminar_3_homework.task_two;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class StudentApp {
	public static final String FILE_JSON = "src/main/resources/student.json";
	public static final String FILE_BIN = "src/main/resources/student.bin";
	public static final String FILE_XML = "src/main/resources/student.xml";

	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final XmlMapper xmlMapper = new XmlMapper();

	public static void addNewStudent(Scanner scanner, List<Student> student) {

		InputValidator inputValidator = new InputValidator();

		System.out.println("Введите имя студента:");
		String newStudentName = scanner.nextLine();

		while (!inputValidator.isValidName(newStudentName)) {
			System.out.println("Некорректное имя студента. Попробуйте еще раз:");
			newStudentName = scanner.nextLine();
		}

		System.out.println("Введите возраст студента:");
		while (!scanner.hasNextInt()) {
			System.out.println("Пожалуйста, введите целочисленное значение:");
			scanner.next();
		}
		int newStudentAge = scanner.nextInt();

		while (!inputValidator.isValidAge(newStudentAge)) {
			System.out.println("Некорректный возраст студента. Попробуйте еще раз:");
			newStudentAge = scanner.nextInt();
		}

		System.out.println("Введите среднюю оценку студента:");
		while (!scanner.hasNextDouble()) {
			System.out.println("Пожалуйста, введите числовое значение:");
			scanner.next();
		}
		double newStudentGPA = scanner.nextDouble();

		while (!inputValidator.isValidAverageGrade(newStudentGPA)) {
			System.out.println("Некорректная средняя оценка студента. Попробуйте еще раз:");
			newStudentGPA = scanner.nextDouble();
		}

		student.add(new Student(newStudentName, newStudentAge, newStudentGPA));
		saveStudentToFile(FILE_JSON, student);
		saveStudentToFile(FILE_BIN, student);
		saveStudentToFile(FILE_XML, student);
		System.out.println("Новый студент добавлен.");
	}

	public static void saveStudentToFile(String fileName, List<Student> student) {
		try {
			if (fileName.endsWith(".json")) {
				objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
				objectMapper.writeValue(new File(fileName), student);
			} else if (fileName.endsWith(".bin")) {
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
					oos.writeObject(student);
				}
			} else if (fileName.endsWith(".xml")) {
				xmlMapper.writeValue(new File(fileName), student);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Student> loadStudentFromFile(String fileName) {
		List<Student> student = new ArrayList<>();

		File file = new File(fileName);
		if (file.exists()) {
			try {
				if (fileName.endsWith(".json")) {
					student = objectMapper.readValue(file,
							objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
				} else if (fileName.endsWith(".bin")) {
					try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
						student = (List<Student>) ois.readObject();
					}
				} else if (fileName.endsWith(".xml")) {
					student = xmlMapper.readValue(file,
							xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		return student;
	}

	public static void deleteStudent(String fileName, String studentName) {
		List<Student> students = loadStudentFromFile(fileName);

		students.removeIf(student -> student.getName().equals(studentName));

		saveStudentToFile(fileName, students);

	}

	public static void displayStudent(String filaName) {

		List<Student> student = loadStudentFromFile(filaName);
		System.out.println(student);

	}
}
