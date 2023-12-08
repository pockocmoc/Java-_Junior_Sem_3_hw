package _Java_junior_seminar_3_homework.task_one;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// Разработайте класс Student с полями String name, int age, 
// transient double GPA (средний балл). Обеспечьте поддержку сериализации для этого класса. 
// Создайте объект класса Student и инициализируйте его данными. Сериализуйте этот объект в файл.
// Десериализуйте объект обратно в программу из файла. Выведите все поля объекта, включая GPA, и обсудите, 
// почему значение GPA не было сохранено/восстановлено.
// Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).

public class Program {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		Student student = new Student("Marat", 40, 5.0);

		try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/studentdata.bin");
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
			objectOutputStream.writeObject(student);
			System.out.println("Объект Student сериализован.");
		}

		try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/studentdata.bin");
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
			student = (Student) objectInputStream.readObject();
			System.out.println("Объект Student десериализован.");
		}

		System.out.println("Имя: " + student.getName());
		System.out.println("Возраст: " + student.getAge());
		System.out.println("Средняя оценка (должена быть null, так как transient): " + student.getGPA());

	}

}
