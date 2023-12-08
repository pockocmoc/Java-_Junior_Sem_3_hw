package _Java_junior_seminar_3_homework.task_one;

import java.io.Serializable;

public class Student implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	private transient double GPA;

	public Student(String name, int age, double gPA) {
		super();
		this.name = name;
		this.age = age;
		GPA = gPA;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getGPA() {
		return GPA;
	}

	public void setGPA(double gPA) {
		GPA = gPA;
	}

}
