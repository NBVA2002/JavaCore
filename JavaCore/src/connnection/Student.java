package connnection;

public class Student {
	private int id;
	private String name;
	private String gender;
	private String country;
	private int age;

	public Student(String name, String gender, String country, int age) {
		super();
		this.name = name;
		this.gender = gender;
		this.country = country;
		this.age = age;
	}
	
	public Student(int id, String name, String gender, String country, int age) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.country = country;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", gender=" + gender + ", country=" + country + ", age=" + age
				+ "]";
	}

}
