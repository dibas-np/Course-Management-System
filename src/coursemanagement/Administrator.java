package coursemanagement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
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
public class Administrator {

    private String name;
    private String username;
    private String password;
    private ArrayList<String> courseList;
    private PreparedStatement pst;

    /**
     * creates new instance of Administrator
     */
    public Administrator(){}

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName(){
        return name;
    }

    /**
     * Admin log in
     * @return true or false based on log in is successful or not
     */
    public boolean adminLogin(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        System.out.println("---------------------------------------");
        /*
        checking if username exists in the database
         */
        if(isInDatabase(username)==true){
            if(validateAdmin(username,pass)==true){
                return true;
            }
            else{
                System.out.println();
                System.out.println("Incorrect password!");
                System.out.println("---------------------------------------");
            }
        }else{
            System.out.println();
            System.out.println("Username doesn't exist!");
            System.out.println("---------------------------------------");
        }
        return false;
    }

    /**
     * validate username and password
     * @param user username of the admin
     * @param pass password of the admin
     * @return true or false
     */
    private boolean validateAdmin(String user, String pass){
        File file = new File("admin.txt");
        try{
            FileReader fr = new FileReader(file);
            Scanner sc = new Scanner(fr);
            /*
            confirming username and password
             */
            while(sc.hasNext()){
                String username = sc.nextLine();
                String password = sc.nextLine();
                if(username.equals(user)){
                    if(password.equals(pass)){
                        return true;
                    }
                }
            }
        }
        catch(IOException e){
            System.out.println("Error: "+e.getMessage());
        }
        return false;
    }

    /**
     * checks if admin is in database or not
     * @param username admin username
     * @return true or false
     */
    private boolean isInDatabase(String username) {
        String sql = "select admin_name,admin_username, admin_password from admin where admin_username=?";
        try {
            pst = Database.getConnection().prepareStatement(sql);
            pst.setString(1, username);
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
     * Setup all fields of administrator account
     *
     * @param rs Result set from query
     */
    private void initializeAccount(ResultSet rs) {

        try {
            this.name = rs.getString(1);
            this.username = rs.getString(2);
            this.password = rs.getString(3);
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
    }

    /**
     * Cancel a course
     */
    public void cancelCourse() {
        String sql = "select course_id, course_name,status from course";
        try {
            PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            courseList = new ArrayList<String>();
            while (rs.next()) {
                courseList.add(rs.getString(1) + " - " + rs.getString(2));
            }

        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
        /*
        displaying courses
         */
        System.out.println("--------------------------------------");
        Iterator itr = courseList.iterator();//getting the Iterator
        while (itr.hasNext()) {//check if iterator has the elements
            System.out.println(itr.next());//printing the element and move to next
        }
        System.out.println("--------------------------------------");
        System.out.println();
        try {
            System.out.print("Enter the ID of course you want to cancel: ");
            Scanner sc = new Scanner(System.in);
            int id = Integer.parseInt(sc.nextLine());
            Course co = new Course();
            if(co.isInDatabase(id)==true) {
                String sql1 = "select status from course where course_id = ?";
                try {
                    PreparedStatement pstmt = Database.getConnection().prepareStatement(sql1);
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        if (rs.getString(1).equals("False")) {
                            System.out.println("Course is already cancelled!");
                            return;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println();
                    System.out.println("Error: "+ ex.getMessage());
                }
                changeCourseStatusFromDB(id, "False");
            }else{
                System.out.println("Course ID doesn't exists!!");
                System.out.println();
            }
        }catch (InputMismatchException e){
            System.out.println("Error!! Enter Integer type value!!");
        }catch (Exception ex){
            System.out.println("Error: "+ ex.getMessage());
        }

    }

    /**
     * resume a course
     */
    public void resumeCourse() {
        String sql = "select course_id, course_name,status from course";
        try {
            PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            courseList = new ArrayList<String>();
            while (rs.next()) {
                courseList.add(rs.getString(1) + " - " + rs.getString(2));
            }

        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }

        System.out.println();
        System.out.println("--------------------------------------");
        Iterator itr = courseList.iterator();//getting the Iterator
        while (itr.hasNext()) {//check if iterator has the elements
            System.out.println(itr.next());//printing the element and move to next
        }
        System.out.println("--------------------------------------");
        System.out.println();
        try {
            System.out.print("Enter the ID of course you want to resume: ");
            Scanner sc = new Scanner(System.in);
            int id = Integer.parseInt(sc.nextLine());
            Course co = new Course();
            if(co.isInDatabase(id)==true) {
                String sql1 = "select status from course where course_id = ?";
                try {
                    PreparedStatement pstmt = Database.getConnection().prepareStatement(sql1);
                    pstmt.setInt(1, id);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        if (rs.getString(1).equals("True")) {
                            System.out.println("Course is already resumed!");
                            return;
                        }
                    }
                } catch (SQLException ex) {
                    System.out.println();
                    System.out.println("Error: "+ ex.getMessage());
                }
                changeCourseStatusFromDB(id, "True");
            }else{
                System.out.println("Error!! Course ID doesn't exists!!");
            }
        }catch (InputMismatchException e){
            System.out.println("Error! Please enter integer type value!!");
        }

    }

    /**
     * update status of course in database
     * @param id course id
     * @param newStatus course status
     */
    private void changeCourseStatusFromDB(int id,String newStatus){
        String sql = "update course set status=? where course_id=?";
        try {
            PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
            pstmt.setString(1, newStatus);
            pstmt.setInt(2,id);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Success!! Course status changed successfully!!");
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
    }

    /**
     * add module
     */
    public void addModule() {
        try {
            Course co = new Course();
            co.displayCourses();
            System.out.println();
            System.out.print("Enter course ID: ");
            Scanner sc = new Scanner(System.in);
            int courseID = Integer.parseInt(sc.nextLine());
            System.out.print("Enter module Name: ");
            String moduleName = sc.nextLine();
            System.out.print("Enter module ID: ");
            int moduleID = Integer.parseInt(sc.nextLine());
            System.out.print("Enter level of the course: ");
            int level = Integer.parseInt(sc.nextLine());
            System.out.println();
            Module mo = new Module();
            if(mo.isInDatabase(moduleID)==false) {
                insertModuleIntoDB(moduleID, moduleName, courseID, level);
            }else{
                System.out.println("Error!! Module ID already exists!!");
            }
        }
        catch (InputMismatchException e){
            System.out.println("Error! Enter integer type value!!");
        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     * add module to database
     * @param moduleID module ID
     * @param name module name
     * @param courseID course ID
     * @param level course level
     */
    private void insertModuleIntoDB(int moduleID,String name,int courseID,int level) {
        String sql = "INSERT into module (module_id,module_name, course_id,level) values (?,?,?,?)";
        try {
            PreparedStatement pst = Database.getConnection().prepareStatement(sql);
            pst.setInt(1, moduleID);
            pst.setString(2, name);
            pst.setInt(3,courseID);
            pst.setInt(4,level);
            pst.executeUpdate();
            pst.close();
            System.out.println();
            System.out.println("Success!! Module added successfully!!");
            System.out.println();
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
    }

    /**
     * update course
     */
    public void updateCourse(){
        Course co = new Course();
        co.displayCourses();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter course ID: ");
            int courseID = Integer.parseInt(sc.nextLine());
            Course co1 = new Course();
            if(co1.isInDatabase(courseID)==true) {
                System.out.print("Enter the new name for the course: ");
                String courseName = sc.nextLine();
                String sql = "update course set course_name=? where course_id=?";
                try {
                    PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
                    pstmt.setString(1, courseName);
                    pstmt.setInt(2, courseID);
                    pstmt.executeUpdate();
                    pstmt.close();
                    System.out.println();
                    System.out.println("Course updated successfully!!");
                    System.out.println();
                } catch (SQLException ex) {
                    System.out.println();
                    System.out.println("Error: "+ ex.getMessage());
                }
            }else{
                System.out.println("Error!! Course ID doesn't exists!!");
                System.out.println();
            }
        }catch (InputMismatchException ex){
            System.out.println("Error:"+ex.getMessage());
        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
        }
    }

    /**
     * update module
     */
    public void updateModule(){
        try {
            Course co = new Course();
            co.displayCourses();
            System.out.println();

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter course ID: ");
            int courseID = Integer.parseInt(sc.nextLine());
            System.out.println();
            System.out.print("Enter the level of the course: ");
            int level = Integer.parseInt(sc.nextLine());
            System.out.println();
            co.displayModules(courseID, level);
            System.out.print("Enter the ID of module you want to update: ");
            int moduleID = Integer.parseInt(sc.nextLine());
            System.out.println();
            Module mo = new Module();
            if(mo.isInDatabase(moduleID)==true) {
                System.out.print("Enter the new name for the module: ");
                String moduleName = sc.nextLine();
                System.out.println();

                String sql = "update module set module_name=? where module_id=? and level=?";
                try {
                    PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
                    pstmt.setString(1, moduleName);
                    pstmt.setInt(2, moduleID);
                    pstmt.setInt(3, level);
                    pstmt.executeUpdate();
                    pstmt.close();
                    System.out.println();
                    System.out.println("Module updated successfully!!!");
                    System.out.println();
                } catch (SQLException ex) {
                    System.out.println();
                    System.out.println("Error: "+ ex.getMessage());
                }
            }else{
                System.out.println("Error!! Module ID doesn't exists!!");
            }
        }catch (InputMismatchException ex){
            System.out.println("Error: "+ex.getMessage());
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    /**
     * assign new instructor
     */
    public void assignNewInstructor(){
        try {
            System.out.print("Enter Instructor Name: ");
            Scanner sc = new Scanner(System.in);
            String instructorName = sc.nextLine();
            System.out.print("Enter Instructor ID: ");
            int instructorID = Integer.parseInt(sc.nextLine());
            System.out.print("Enter module ID: ");
            int moduleID = Integer.parseInt(sc.nextLine());
            System.out.print("Enter instructor email: ");
            String email = sc.nextLine();
            System.out.println();

            Instructor ins = new Instructor(instructorID, instructorName, moduleID, email);
            Module mo = new Module();
        /*
        checking if module Id exists in the database and instructor is teaching specified student
         */
            if (mo.isInDatabase(moduleID) == true) {
                if (ins.checkInstructor(moduleID, instructorID) == false) {
                    ins.insertInstructorIntoDB();
                } else {
                    System.out.println("That instructor is already teaching the specified module!");
                }
                updateInstructorOnModule(moduleID, instructorID);
            } else {
                System.out.println("Module ID doesn't exists!");
            }
        }catch (InputMismatchException e){
            System.out.println("Error: "+e.getMessage());
        }catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     * assign instructor to course
     */
    public void assignInstructorToCourse(){

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter instructor ID:");
            int instructorID = Integer.parseInt(sc.nextLine());
            System.out.println("Enter module ID:");
            int moduleID = Integer.parseInt(sc.nextLine());
            Instructor ins = new Instructor();
            ins.setModuleID(moduleID);
            if (ins.isInDatabase(instructorID) == true) {
                Module mo = new Module();
                if (mo.isInDatabase(moduleID) == true) {
                    if (ins.checkInstructor(moduleID, instructorID) == false) {
                        ins.setModuleID(moduleID);
                        ins.insertInstructorIntoDB();
                    } else {
                        System.out.println("That instructor is already teaching specified module!");
                    }
                    updateInstructorOnModule(moduleID, instructorID);
                } else {
                    System.out.println("Module ID doesn't exist!");
                }
            } else {
                System.out.println("Instructor ID doesn't exist");
            }
        }catch (InputMismatchException ex){
            System.out.println("Error! Please enter integer type value!");
        }
    }

    /**
     * update instructor on module
     */
    public void updateInstructorOnModule(int moduleID,int instructorID){

        String sql = "update module set instructor_id=? where module_id=?";
        try {
            PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
            pstmt.setInt(1, instructorID);
            pstmt.setInt(2,moduleID);
            pstmt.executeUpdate();
            pstmt.close();
            System.out.println("Instructor updated successfully on module!!");
            System.out.println();
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
    }
    /**
     * update module
     */
    public void updateInstructor() {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter instructor ID: ");
            int instructorID = Integer.parseInt(sc.nextLine());
            Instructor ins = new Instructor();
            if(ins.isInDatabase(instructorID)==true) {
                System.out.print("Enter the new email of the instructor: ");
                String email = sc.nextLine();

                String sql = "update instructor set instructor_email=? where instructor_id=?";
                try {
                    PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
                    pstmt.setString(1, email);
                    pstmt.setInt(2, instructorID);
                    pstmt.executeUpdate();
                    pstmt.close();
                    System.out.println("Instructor updated successfully on instructor table!!");
                } catch (SQLException ex) {
                    System.out.println();
                    System.out.println("Error: "+ ex.getMessage());
                }
            }else{
                System.out.println("Instructor ID doesn't exists!!!");
                System.out.println();
            }
        }catch (InputMismatchException ex){
            System.out.println("Enter integer type value!!");
        }catch (Exception e){
            System.out.println("Error:"+e.getMessage());
        }
    }

    /**
     * result slip
     */
    public void resultSlip() {

        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Student ID to make result slip: ");
            int studentID = Integer.parseInt(sc.nextLine());
            System.out.println("\n\n\n");
            Student st = new Student();
            if (st.isInDatabase(studentID) == true) {
                String sql = "select student_id,student_name,module_id,marks from student where student_id=?";
                try {
                    pst = Database.getConnection().prepareStatement(sql);
                    pst.setInt(1, studentID);
                    ResultSet rs = pst.executeQuery();
                    ArrayList<String> marks= new ArrayList<>();
                    System.out.println("--------------------------------------");
                    System.out.println("           Result Slip                ");
                    System.out.println("--------------------------------------");
                    int total=0;
                    int pass=0;
                    int fail=0;

                    while(rs.next()){
                        this.name=rs.getString(2);
                        if(Integer.parseInt(rs.getString(4))>39) {
                            pass++;
                            marks.add("Course ID: "+rs.getString(3)+"\t"+"Marks: "+rs.getString(4)+"\t"+"Result: Pass\n");
                        }else{
                            marks.add("Course ID:"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+"Result: Fail\n");
                            fail++;
                        }
                    }
                    System.out.println();
                    System.out.println("Student Name: "+getName());
                    System.out.println("Student ID: "+studentID);
                    System.out.println();
                    System.out.println("--------------------------------------");
                    Iterator itr = marks.iterator();
                    while(itr.hasNext()){
                        System.out.println(itr.next());
                    }
                    System.out.println("--------------------------------------");
                    System.out.println();
                    if(pass>=fail){
                        System.out.println("Congratulations! You can move to the next level!");
                    }else{
                        System.out.println("Sorry! You can't move to next level!");
                    }
                    System.out.println("--------------------------------------");

                } catch (SQLException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Student ID doesn't exist!!");
            }
        }catch (InputMismatchException e){
            System.out.println("Error! Please enter integer type value!!");
        }
        catch (Exception ex){
            System.out.println("Error: "+ex.getMessage());
        }
    }
}
