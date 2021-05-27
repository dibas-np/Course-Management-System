package coursemanagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Dibas Sigdel
 */
public class Course {
    private int courseID;
    private String courseName;
    private ArrayList<String> courseLists;

    /**
     * creates new instance of course
     */
    public Course() {
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseID() {
        return courseID;
    }

    public ArrayList<String> getCourseLists() {
        return courseLists;
    }

    public void setCourseLists(ArrayList<String> courseLists) {
        this.courseLists = courseLists;
    }

    /**
     * add course
     */
    public void addCourse() {
        try {
            System.out.print("Enter course ID: ");
            Scanner sc = new Scanner(System.in);
            int id = Integer.parseInt(sc.nextLine());

            if(isInDatabase(id)==false) {
                System.out.print("Enter course Name: ");
                String course = sc.nextLine();
                insertCourseIntoDB(id, course);
            }else{
                System.out.println();
                System.out.println("Course ID already exists!!");
            }
        }catch(InputMismatchException e){
            System.out.println("Error! Please enter integer type value!!");
            System.out.println();
        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     * add course to database
     * @param id course id
     * @param course course name
     */
    private void insertCourseIntoDB(int id, String course) {
        String sql = "INSERT into course (course_id, course_name) values (?,?)";
        try {
            PreparedStatement pst = Database.getConnection().prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, course);
            pst.executeUpdate();
            pst.close();
            System.out.println();
            System.out.println("Course added successfully!!!");
            System.out.println();
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     * checks if the course is in database or not
     * @param id Course ID
     * @return boolean
     */
    public boolean isInDatabase(int id) {
        String sql = "select course_id,course_name from course where course_id = ?";
        try {
            PreparedStatement pst = Database.getConnection().prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                initializeAccount(rs);
                return true;
            }
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
        return false;
    }

    /**
     * Initialize all fields for course
     * @param rs Result set from query
     */
    private void initializeAccount(ResultSet rs) {

        try {
            this.courseID = rs.getInt(1);
            this.courseName = rs.getString(2);
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
    }
    /**
     * delete course
     */
    public void deleteCourse() {
        String sql = "select course_id, course_name from course";
        try {
            PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            courseLists = new ArrayList<String>();
            while (rs.next()) {
                courseLists.add("Course ID: "+rs.getString(1) + " - " +"Course Name: "+ rs.getString(2));
            }

        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
        Iterator itr = courseLists.iterator();//getting the Iterator
        while (itr.hasNext()) {//check if iterator has the elements
            System.out.println(itr.next());//printing the element and move to next

        }
        System.out.println();
        System.out.println("--------------------------------------");
        try {
            System.out.print("Enter the ID of course you want to delete: ");
            Scanner sc = new Scanner(System.in);
            int id = Integer.parseInt(sc.nextLine());
            if(isInDatabase(id)==true) {
                deleteCourseFromDB(id);
            }else{
                System.out.println("Course ID doesn't exist!!");
            }
        }catch (InputMismatchException ex){
            System.out.println("Error!! Enter integer type value!!");
            System.out.println();
        }
    }

    /**
     * delete course from database
     * @param id course id to be deleted
     */
    private void deleteCourseFromDB(int id){
        String sql = "delete from course where course_id=?";
        try {
            PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Course deleted successfully!!");
            System.out.println();
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
    }

    /**
     * display courses
     */
    public void displayCourses() {
        String sql = "select course_id,course_name from course where status=?";
        try {
            PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
            pstmt.setString(1,"True");
            ResultSet rs = pstmt.executeQuery();

            courseLists = new ArrayList<String>();
            while (rs.next()) {
                courseLists.add("Course ID: "+rs.getString(1) + " - Course Name: " + rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
        System.out.println("--------------------------------------");
        Iterator itr = courseLists.iterator();//getting the Iterator
        while (itr.hasNext()) {//check if iterator has the elements
            System.out.println(itr.next());//printing the element and move to next
        }
        System.out.println("--------------------------------------");
        System.out.println();
    }

    /**
     * display modules
     * @param courseID course id
     * @param level course level
     */
    public void displayModules(int courseID,int level) {
        String sql = "select module_id,module_name from module where course_id=? and level=?";
        try {
            PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
            pstmt.setInt(1, courseID);
            pstmt.setInt(2, level);
            ResultSet rs = pstmt.executeQuery();

            courseLists = new ArrayList<String>();
            while (rs.next()) {
                courseLists.add(rs.getString(1) + " - " + rs.getString(2));
            }
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
        Iterator itr = courseLists.iterator();//getting the Iterator
        while (itr.hasNext()) {//check if iterator has the elements
            System.out.println(itr.next());//printing the element and move to next
        }
    }
}