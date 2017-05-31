package university;

/**
 * This class is a course class that contain a department, an ID, the maximum
 * number of students, and the list of students registered for the course. There
 * are methods that returns information for the university about the current
 * state of the course. This class is a storage for the university class. It 
 * creates courses and students to be used by the University class.
 * 
 * @author William Gong
 *
 */
public class Course {
	private String department;
	private int id, numSeats, numStudents;
	private String[] studentList;
/**
 * the default constructor that sets everything to its default values
 */
	public Course() {
		department = "";
		id = 0;
		numSeats = 0;
		numStudents = 0;
		studentList = new String[0]; // set the students seats as 0
	}

	/**
	 * The course constructor that takes in a department, an id and the maximum
	 * number students who can register
	 * 
	 * @param department
	 * @param id
	 * @param numSeats
	 */
	public Course(String department, int id, int numSeats) {
		this.department = department;
		this.id = id;
		this.numSeats = numSeats;
		numStudents = 0;
		if (numSeats < 0) // check to see the parameter doesnt go blow 0
			numSeats = 0;
		studentList = new String[numSeats];
	}

	/**
	 * the addStudent method adds a student to the course and checks if a
	 * student with the same name already exist within the course, it it does
	 * then the method does nothing, if it doesnt, it adds the student to the
	 * course
	 * 
	 * @param name
	 */
	public void addStudent(String name) {
		boolean check = false;
		for (int index = 0; index <= numStudents - 1; index++)
			// goes through the array to find if the parameter name already
		// exist in the array
		{
			if (studentList[index].equals(name)
					&& numStudents < studentList.length)
				check = true;
		}
		// if it is a new name, then it adds to the array
		if (!(check) && numStudents < studentList.length) {
			studentList[numStudents] = name;
			numStudents++;

		}

	}

	/**
	 * this method removes a student with the name "name". If the student is not
	 * registered in the course, return false if the student is successfully
	 * removed, returns true
	 * 
	 * @param name
	 * @return
	 */
	public boolean removeStudent(String name) {
		boolean check = false;
		String[] temp = new String[studentList.length];
		int k = 0;
		// check if the student name in the array exists and skips over the copy
		// at the removed student's location
		// so the student will not be in the new array
		for (int index = 0; index <= numStudents - 1; index++) {

			if (studentList[index].equals(name)) {

				index++;
				numStudents--;
				check = true;
			}
			//copy the array
			temp[k] = studentList[index];
			k++;

		}
		studentList=temp;
		return check;
	}

	/**
	 * returns the number of student registered in the course
	 * 
	 * @return
	 */
	public int getStudent() {
		return numStudents;
	}

	/**
	 * returns the course name
	 * 
	 * @return
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * returns the course id number
	 * 
	 * @return
	 */
	public int getID() {
		return id;
	}

	/**
	 * checks the course if it contains the student, returns true or false
	 * if it finds the student
	 * @param student
	 * @return
	 */
	public boolean containStudent(String name) {

		// check if the array contain the student
		// could've used it in other methods but it was created last, so didnt
		// bother to change the other methods
		// System.out.println(numStudents);
		for (int index = 0; index <= numStudents - 1; index++) {
			if (studentList[index].equals(name))
				return true;
		}
		return false;
	}

	/**
	 * return the maximum number of students allowed to enroll in the course
	 * 
	 * @return
	 */
	public int getSeats() {
		return numSeats;
	}

	/**
	 * overrides the object equals method and check if other objects are equal
	 * to this course
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true; //checks null
		if (obj == null)
			return false;
		if (!(obj instanceof Course)) 
			return false;
		Course core = (Course) obj;

		if (this.department.equals(core.department) && this.id == core.id)
			// check if the two departments and ids are equal
			return true;

		return false;

	}

	/**
	 * gives the tostring for this object
	 */
	public String toString() {
		// not really needed, used for testing
		return "The " + department + " with ID " + id + " and " + numSeats
				+ " number of of seats " + numStudents + " students "
				+ studentList[0];

	}

}
