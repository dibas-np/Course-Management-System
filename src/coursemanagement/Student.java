package coursemanagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Dibas Sigdel
 */
public class Student extends Account{

    /**
     * creates new instance of student class
     */
    public Student(){

    }

    public String getName(){
        return name;
    }

    public int getID() {
        return ID;
    }

    /**
     * check if the student is in database
     * @param id Student ID to check
     * @return
     */
    @Override
    public boolean isInDatabase(int id) {
        String sql = "select student_id,student_name from student where student_id = ?";
        try {
            pst = Database.getConnection().prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                initializeAccount(rs);
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
        return false;
    }

    /**
     * Initialize all fields for student account
     * @param rs Result set from query
     */
    private void initializeAccount(ResultSet rs) {

        try {
            this.ID = rs.getInt(1);
            this.name = rs.getString(2);
        } catch (SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }

    /**
     * Enroll in the course
     */
    public void enroll(){
        System.out.println("************************************");
        System.out.println("      Student Registration          ");
        System.out.println("************************************");
        System.out.println();
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter student ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            /*
            checking if the student ID already exists in the database
             */
            if (isInDatabase(id) == true) {
                System.out.println("Student ID already exists!");
                return;
            }
            System.out.println("\n");
            Course co = new Course();
            /*
            displaying available courses which are Active to choose from
             */
            System.out.println("Available Courses");
            co.displayCourses();
            System.out.println();

            System.out.print("Enter the course ID you want to enroll: ");
            int courseID = Integer.parseInt(sc.nextLine());
            /*
            checking if the course ID exists in database
             */
            if(co.isInDatabase(courseID)==true) {
                Module mo1 = new Module();
                if (mo1.checkModulesInDataBase(courseID) == true) {
                    System.out.print("Enter the level you want to enroll: ");
                    int level = Integer.parseInt(sc.nextLine());
                /*
                making sure the level entered in 4,5 or 6
                 */
                    if (level == 4 || level == 5 || level == 6) {
                    /*
                    special condition when the level is 6 as they have option to choose module
                     */
                        if (level == 6) {
                            String sql2 = "select module_id,module_name from module where course_id=? and level=?";
                            try {
                                PreparedStatement pst = Database.getConnection().prepareStatement(sql2);
                                pst.setInt(1, courseID);
                                pst.setInt(2, level);
                                ResultSet rs = pst.executeQuery();
                                ArrayList<String> levelSix = new ArrayList<>();
                                while (rs.next()) {
                                    levelSix.add("Module ID: " + rs.getString(1) + " - Module Name: " + rs.getString(2));
                                }
                                System.out.println();
                            /*
                            displaying available options they can choose from
                             */
                                System.out.println("Available Options for Level 6");
                                System.out.println("--------------------------------");
                                Iterator itr = levelSix.iterator();//getting the Iterator
                                while (itr.hasNext()) {//check if iterator has the elements
                                    System.out.println(itr.next());//printing the element and move to next
                                }
                                System.out.println("----------------------------------");

                                System.out.println();
                                System.out.print("Enter ID of your first choice module: ");
                                int choice1 = Integer.parseInt(sc.nextLine());
                                Module mo = new Module();
                                try {
                                /*
                                making sure both module ID entered is in the database
                                 */
                                    if (mo.isInDatabase(choice1) == true) {

                                        System.out.println();
                                        System.out.print("Enter ID of your second choice module: ");
                                        int choice2 = Integer.parseInt(sc.nextLine());
                                        if (mo.isInDatabase(choice2) == true) {
                                            int[] choices = {choice1, choice2};

                                            for (int i = 0; i < 2; i++) {
                                                String sql3 = "INSERT into student (student_id,student_name,module_id) values (?,?,?)";
                                                try {
                                                    PreparedStatement pstm = Database.getConnection().prepareStatement(sql3);
                                                    pstm.setInt(1, id);
                                                    pstm.setString(2, name);
                                                    pstm.setInt(3, choices[i]);
                                                    pstm.executeUpdate();
                                                    pstm.close();
                                                } catch (SQLException ex) {
                                                    System.out.println("Error: " + ex.getMessage());
                                                }
                                            }
                                            System.out.println();
                                            System.out.println("Congratulations!! You have been successfully enrolled!!");

                                        } else {
                                            System.out.println("Module ID doesn't exist!!");
                                        }
                                    } else {
                                        System.out.println("Module ID doesn't exist!!");
                                    }
                                } catch (NumberFormatException ex) {
                                    System.out.println("Error! Enter integer type value!!");
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            } catch (SQLException exc) {
                                System.out.println();
                                System.out.println("Error: " + exc.getMessage());
                            }
                        }
                    /*
                    condition if the level is 4 or 5
                     */
                        if (level == 4 || level == 5) {
                            String sql = "select module_id  from module where course_id=? and level=?";
                            try {
                                PreparedStatement pst = Database.getConnection().prepareStatement(sql);
                                pst.setInt(1, courseID);
                                pst.setInt(2, level);
                                ResultSet rs = pst.executeQuery();
                                while (rs.next()) {
                                    String sql1 = "INSERT into student (student_id,student_name,module_id) values (?,?,?)";
                                    try {
                                        PreparedStatement pstm = Database.getConnection().prepareStatement(sql1);
                                        pstm.setInt(1, id);
                                        pstm.setString(2, name);
                                        pstm.setInt(3, Integer.parseInt(rs.getString(1)));
                                        pstm.executeUpdate();
                                        pstm.close();
                                    } catch (SQLException ex) {
                                        System.out.println();
                                        System.out.println("Error: " + ex.getMessage());
                                    }
                                }
                                System.out.println();
                                System.out.println("Congratulations!! You have been successfully enrolled!!");
                            } catch (SQLException e) {
                                System.out.println();
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Level must be 4,5 or 6!!");
                    }
                }else{
                    System.out.println();
                    System.out.println("Sorry! There is no modules in that course!");
                    System.out.println();
                }
            } else {
                    System.out.println("Course ID doesn't exist!!");
            }
        }catch(NumberFormatException e){
            System.out.println("Error! Please enter integer type value!!");
        }catch(InputMismatchException ex){
            System.out.println("Error! Enter integer type value!!");
        }catch(Exception exe){
            System.out.println("Error:"+ exe.getMessage());
        }
    }

    /**
     * display student in certain module
     * @param id Instructor ID
     */
    public void displayStudents(int id){
        String sql = "select module_id from instructor where instructor_id=?";
        try {
            PreparedStatement pstmt = Database.getConnection().prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            ArrayList<String> students = new ArrayList<String>();
            while (rs.next()) {
                try {
                    String sql1 = "select student_id, student_name,module_id from student where module_id ="
                                 + rs.getString(1);
                    pst = Database.getConnection().prepareStatement(sql1);
                    ResultSet studentInClass = pst.executeQuery();

                    if (studentInClass.next()) {
                        students.add("Student ID: " + studentInClass.getString(1) + " - " + "Student Name: "
                                + studentInClass.getString(2) + " - " + "Module ID: " +
                                studentInClass.getString(3));
                    }
                }catch (SQLException e){
                    System.out.println();
                    System.out.println("Error: "+ e.getMessage());
                }
            }
            /*
            displaying students
             */
            Iterator itr = students.iterator();//getting the Iterator
            while (itr.hasNext()) {//check if iterator has the elements
                System.out.println(itr.next());//printing the element and move to next
            }
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
    }

    /**
     * Displaying Instructors teaching specified modules
     */
    public void displayInstructorsOnStudent(){
        Instructor st = new Instructor();
        st.displayInstructors(getID());
    }

    /**
     * Checks if student is enrolled in specified module
     * @param moduleID Module ID to check
     * @param studentID Student ID to check
     * @return boolean type
     */
    public boolean checkStudent(int moduleID,int studentID){
        String sql = "select * from student where student_id = ? and module_id=?";
        try {
            pst = Database.getConnection().prepareStatement(sql);
            pst.setInt(1, studentID);
            pst.setInt(2,moduleID);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println();
            System.out.println("Error: "+ ex.getMessage());
        }
        return false;
    }
}
