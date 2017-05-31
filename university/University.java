package university;

import java.util.ArrayList;

/**
 * Name:William Gong
 * Section:0303
 * ID:113510311
 * Directory ID:wgong1
 * Honor Code:I pledge on my honor that i have not give or received any
 * unauthorized assistance on this assignment
 * 
 * The University contains courses available to the students. It can add and
 * drop students and courses No two courses can be the same or student names. It
 * uses the object course to store the information of students and the courses
 * the students are enrolled in. Several methods are created in order to find,
 * count, add, and remove courses and students.
 * 
 * @author William Gong
 *
 */
public class University {
	private ArrayList<Course> courses;

	public University() {
		courses = new ArrayList<Course>();
	}

	/**
	 * This method adds a new course to the university and it checks if a course
	 * with the same department and id already exist Then it returns the 
	 * University object
	 * 
	 * @param department
	 * @param number
	 * @param numSeats
	 * @return
	 */
	public University addCourse(String department, int number, int numSeats) {
		Course check = new Course(department, number, numSeats);

		// I can use contain in this case because
		// there is an equals method in the
		// course class
		// checking to see if the arraylist
		// course already contain the course
		if (courses.contains(check)) {
			return this;
		}

		else {
			courses.add(check); // adds the course to the arraylist if it
			return this;
		}

	}

	/**
	 * Remove a course with the same department name, and id number if the
	 * course is successfully removed, then the method returns true, else it
	 * returns false
	 * 
	 * @param department
	 * @param number
	 * @return
	 */
	public boolean cancelCourse(String department, int number) {
		Course temp = new Course(department, number, 0);
		// check if the course contains the department and if it does, remove it
		if (courses.contains(temp)) {
			courses.remove(temp);
			return true;
		}
		return false;
	}

	/**
	 * returns the number of of courses in the university
	 * 
	 * @return
	 */
	public int numCourses() {
		return courses.size();
	}

	/**
	 * It adds a student with the name "name", into the course with the same
	 * department name and id number It checks if the department and id exists
	 * then check if the name was already registered It checks if the student
	 * has registered for 5 or more courses. It checks if adding another student
	 * will go over the limit for the number of available seats it returns true
	 * if the student is successfully added to the course, false if otherwise
	 *
	 * @param department
	 * @param number
	 * @param name
	 * @return
	 */
	public boolean add(String department, int number, String name) {
		Course temp = new Course(department, number, 0);
		if (checkNumStudCourses(name) < 5 && courses.contains(temp))
			// check if the student is taking 5 or more check if the
			// arraylist contains the course
		{
			for (int index = 0; index <= courses.size() - 1; index++) {
				if (courses.get(index).equals(temp)) // finds the course for the
														// student to be enroll
														// in
				{
					if (!(courses.get(index).containStudent(name)) && 
                    // add the student if it doesnt contain the student name
							(courses.get(index).getStudent() < courses.get(
									index).getSeats())) {
						courses.get(index).addStudent(name);
						return true;
					}
				}
			}

		}

		return false;
	}

	/**
	 * finds the number of course with the given name is registered in. returns
	 * the number of courses, it is a helper method 
	 * 
	 * @param name
	 * @return
	 */
	private int checkNumStudCourses(String name) {
		int count = 0;
		for (int index = 0; index <= courses.size() - 1; index++) 
			// the loop goes through every class to find the student name
		{
			if (courses.get(index).containStudent(name)) 
// increment by 1 for each time the student name if found in a class
			{
				count++;
			}
		}
		return count;
	}

	/**
	 * Finds the number of students in a specific course
	 * 
	 * @param department
	 * @param number
	 * @return
	 */
	public int numStudentsInCourse(String department, int number) {
		Course temp = new Course(department, number, 0);
		if (courses.contains(temp)) {
			for (int index = 0; index <= courses.size() - 1; index++)
	// finds the course and return the number of students
			{
				if (courses.get(index).equals(temp)) {
					return courses.get(index).getStudent();
				}
			}
		}
		return -1;
	}

	/**
	 * it checks if the specific student is registered in the given course
	 * returns true if the student is registered in the course, else it returns
	 * false
	 * 
	 * @param department
	 * @param number
	 * @param name
	 * @return
	 */
	public boolean isRegisteredForCourse(String department, int number,
			String name) {
		Course temp = new Course(department, number, 0);

		if (courses.contains(temp)) {
			for (int index = 0; index <= courses.size() - 1; index++) {
				if (courses.get(index).containStudent(name)
						&& courses.get(index).equals(temp)) // check the array
															// and finds
				// the specific student for the class
				{
					return true;

				}
			}
		}
		return false;
	}

	/**
	 * same with the method checkNumStudCourses
	 * 
	 * @param
	 * @return
	 */
	public int numCoursesRegisteredFor(String name) {
		return checkNumStudCourses(name);
	}

	/**
	 * It drops the given student for the course it returns true if the student
	 * is successfully dropped
	 * 
	 * @param department
	 * @param number
	 * @param name
	 * @return
	 */
	public boolean drop(String department, int number, String name) {
		Course temp = new Course(department, number, 0);
		if (courses.contains(temp)) {
			for (int index = 0; index <= courses.size() - 1; index++) 
				// finds the course then remove the student in the parameter
			{
				if (courses.get(index).equals(temp)) {
					return courses.get(index).removeStudent(name);
					// uses the course's remove method
				}
			}

		}
		return false;

	}

	/**
	 * This method drops all the courses of the given student is currently
	 * taking. It first checks if the student its taking any of the courses 
	 * offered in the University, then it uses the drops method from the
	 * University to remove the student from the courses 1 by 1 until the
	 * student is no longer registered in any courses
	 * 
	 * @param name
	 * @return
	 */
	public boolean cancelRegistration(String name) {
		boolean check = false;//return true or false

	// finds the studens then uses the drop
	// method to remove the student's name from every course
	for (int index = 0; index <= courses.size() - 1; index++) {
			if (courses.get(index).containStudent(name)){

			
				this.drop(courses.get(index).getDepartment(), courses
						.get(index).getID(), name);
				check = true;
			}
		}
		return check;
	}

}
