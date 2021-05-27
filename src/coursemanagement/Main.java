package coursemanagement;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Dibas Sigdel
 */
public class Main {
    public static void mainMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************");
        System.out.println("Welcome to Herald College Kathmandu");
        System.out.println("************************************");
        int selectedOption = 0;
        System.out.println();
        System.out.println("What is your role?");
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("          1. Student                   ");
        System.out.println("          2. Instructor                ");
        System.out.println("          3. Course Administrator      ");
        System.out.println("          4. Exit                      ");
        System.out.println("---------------------------------------");
        System.out.print("\nEnter Option: ");
        try{
            selectedOption = scan.nextInt();
            switch (selectedOption){
                case 1:
                    System.out.println("\n\n\n");
                    studentMenu();
                    break;

                case 2:
                    System.out.println("\n\n\n");
                    instructorMenu();
                    break;

                case 3:
                    System.out.println("\n\n\n");
                    adminMenu();
                    break;

                case 4:
                    System.out.println("Exiting the application...");
                    System.exit(0);

                default:
                    System.out.println("Error! Please input only the number options available above!!!");
                    System.out.println("\n\n");
                    mainMenu();
            }
        }catch(InputMismatchException e) {
            scan.next();
            System.out.println("Please input only the number options available above!!!");
            System.out.println();
            System.out.println();
            System.out.println();
            mainMenu();
        }
    }
    public static void studentMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************");
        System.out.println("         Student Menu                ");
        System.out.println("************************************");
        int selectedOption = 0;
        System.out.println();
        System.out.println("Are you enrolled?");
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("1. Yes");
        System.out.println("2. No");
        System.out.println("3. Return to Main menu");
        System.out.println("4. Exit");
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.print("\nEnter Option: ");
        try{
            selectedOption = scan.nextInt();
            switch (selectedOption){
                case 1:
                    System.out.println("\n\n\n");
                    enrolledStudentMenu();
                    break;

                case 2:
                    System.out.println("\n\n\n");
                    Student st1 = new Student();
                    st1.enroll();
                    System.out.println("---------------------------------------");
                    break;

                case 3:
                    System.out.println("\n\n\n");
                    mainMenu();
                    break;

                case 4:
                    System.out.println("Exiting the application...");
                    System.exit(0);

                default:
                    System.out.println("Error! Please input only the number options available above!!!");
                    System.out.println("\n\n");
                    studentMenu();
            }
        }catch(InputMismatchException e) {
            scan.next();
            System.out.println("Please input only the number options available above!!!");
            System.out.println();
            System.out.println();
            System.out.println();
            studentMenu();
        }

    }
    public static void enrolledStudentMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************");
        System.out.println("     Enrolled Student Menu          ");
        System.out.println("************************************");
        int selectedOption = 0;
        System.out.println();
        System.out.println("");
        System.out.println("--------------------------------------");
        System.out.println("        1. View Instructors           ");
        System.out.println("        2. Return to Student Menu     ");
        System.out.println("        3. Return to Main Menu        ");
        System.out.println("        4. Exit                       ");
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.print("\nEnter Option: ");
        try{
            selectedOption = scan.nextInt();
            switch (selectedOption){
                case 1:
                    System.out.println("\n\n");
                    System.out.print("Enter student ID:");
                    Scanner sc = new Scanner(System.in);
                    int studentID=sc.nextInt();
                    Student st = new Student();
                    if(st.isInDatabase(studentID)){
                        System.out.println("---------------------------------------");
                        System.out.println("              Instructor List          ");
                        System.out.println("---------------------------------------");
                        st.displayInstructorsOnStudent();
                    }else{
                        System.out.println("Student ID doesn't exist!!");
                        System.out.println("\n\n\n");
                        studentMenu();
                    }
                    break;

                case 2:
                    System.out.println("\n\n\n");
                    studentMenu();
                    break;

                case 3:
                    System.out.println("\n\n\n");
                    mainMenu();
                    break;

                case 4:
                    System.out.println("Exiting the application...");
                    System.exit(0);

                default:
                    System.out.println("Error! Please input only the number options available above!!!");
                    System.out.println("\n\n");
                    enrolledStudentMenu();
            }
        }catch(InputMismatchException e) {
            scan.next();
            System.out.println("Please input only the number options available above!!!");
            System.out.println();
            System.out.println();
            System.out.println();
            enrolledStudentMenu();
        }
    }

    public static void instructorMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************");
        System.out.println("          Instructor Menu           ");
        System.out.println("************************************");
        int selectedOption = 0;
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("          1. View Modules              ");
        System.out.println("          2. View Students             ");
        System.out.println("          3. Add Marks                 ");
        System.out.println("          4. Return to Main Menu       ");
        System.out.println("          5. Exit                      ");
        System.out.println("---------------------------------------");
        System.out.print("\nEnter Option: ");
        try{
            selectedOption = scan.nextInt();
            switch (selectedOption){
                case 1:
                    System.out.println("\n\n\n");
                    System.out.print("Enter Instructor ID: ");
                    Scanner sc = new Scanner(System.in);
                    int instructorID = Integer.parseInt(sc.nextLine());
                    Instructor ins = new Instructor();
                    if(ins.isInDatabase(instructorID)==true) {
                        System.out.println("\n\n\n");
                        System.out.println("          Modules List                 ");
                        System.out.println("---------------------------------------");
                        ins.displayModules();
                        System.out.println("---------------------------------------");
                    }
                    break;

                case 2:
                    System.out.println("\n\n\n");
                    System.out.print("Enter Instructor ID: ");
                    Scanner sc1 = new Scanner(System.in);
                    int instructorID1 = Integer.parseInt(sc1.nextLine());
                    Instructor ins1 = new Instructor();
                    if(ins1.isInDatabase(instructorID1)==true) {
                        System.out.println("\n\n\n");
                        System.out.println("          Students List                 ");
                        System.out.println("---------------------------------------");
                        ins1.displayStudentFromInstructor();
                        System.out.println("---------------------------------------");
                    }
                    break;

                case 3:
                    System.out.println("\n\n\n");
                    System.out.print("Enter Instructor ID: ");
                    Scanner sc2 = new Scanner(System.in);
                    int instructorID2 = Integer.parseInt(sc2.nextLine());
                    Instructor ins2 = new Instructor();
                    if(ins2.isInDatabase(instructorID2)==true) {
                        System.out.println("\n\n\n");
                        System.out.println("---------------------------------------");
                        System.out.println("          Add Marks                 ");
                        ins2.addGrade();
                        System.out.println();
                        System.out.println("---------------------------------------");
                    }
                    break;

                case 4:
                    System.out.println("\n\n\n");
                    mainMenu();
                    break;

                case 5:
                    System.out.println("Exiting the application...");
                    System.exit(0);

                default:
                    System.out.println("Error! Please input only the number options available above!!!");
                    System.out.println("\n\n");
                    instructorMenu();
            }
        }catch(InputMismatchException e) {
            scan.next();
            System.out.println("Please input only the number options available above!!!");
            System.out.println();
            System.out.println();
            System.out.println();
            instructorMenu();
        }
    }

    public static void adminMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************");
        System.out.println("          Admin Menu                ");
        System.out.println("************************************");
        int selectedOption = 0;
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("          1. Log In                   ");
        System.out.println("          2. Return to Main Menu       ");
        System.out.println("          3. Exit                      ");
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.print("\nEnter Option: ");
        try{
            selectedOption = scan.nextInt();
            switch (selectedOption){
                case 1:
                    System.out.println("\n\n\n");
                    System.out.println("---------------------------------------");
                    System.out.println("          Admin Login                   ");
                    System.out.println("---------------------------------------");
                    Scanner sc = new Scanner(System.in);
                    Administrator ad = new Administrator();
                    if(ad.adminLogin()==true){
                        System.out.println();
                        System.out.println("login successful....");
                        System.out.println("\n\n\n");
                        adminLoggedInMenu();
                    }else{
                        System.out.println("\n\n\n");
                        adminMenu();
                    }
                    break;

                case 2:
                    System.out.println("\n\n\n");
                    mainMenu();
                    break;

                case 3:
                    System.out.println("Exiting the application...");
                    System.exit(0);

                default:
                    System.out.println("Error! Please input only the number options available above!!!");
                    System.out.println("\n\n");
                    adminMenu();
            }
        }catch(InputMismatchException e) {
            scan.next();
            System.out.println("Please input only the number options available above!!!");
            System.out.println();
            System.out.println();
            System.out.println();
            adminMenu();
        }
    }
    public static void adminLoggedInMenu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("************************************");
        System.out.println("          Admin Panel               ");
        System.out.println("************************************");
        int selectedOption = 0;
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println("          1. Add Course               ");
        System.out.println("          2. Add module               ");
        System.out.println("          3. Delete Course            ");
        System.out.println("          4. Edit Course              ");
        System.out.println("          5. Edit Module              ");
        System.out.println("          6. Add New Instructor       ");
        System.out.println("          7. Assign Instructor"        );
        System.out.println("          8. Cancel Course            ");
        System.out.println("          9. Resume Course            ");
        System.out.println("          10. Update Instructor       ");
        System.out.println("          11. Generate Report Slip    ");
        System.out.println("          12. Log Out                 ");
        System.out.println("          13. Return to Main menu     ");
        System.out.println("          14. Exit                    ");
        System.out.println("");
        System.out.println("---------------------------------------");
        System.out.print("\nEnter Option: ");
        try{
            Administrator ad = new Administrator();
            selectedOption = scan.nextInt();
            switch (selectedOption){
                case 1:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("        Add New Course                ");
                    System.out.println("--------------------------------------");
                    Course co = new Course();
                    co.addCourse();
                    System.out.println("--------------------------------------");
                    System.out.println();
                    break;

                case 2:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("          Add New Module              ");
                    System.out.println("--------------------------------------");
                    ad.addModule();
                    System.out.println("--------------------------------------");
                    System.out.println();
                    break;

                case 3:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("           Delete Course              ");
                    System.out.println("--------------------------------------");
                    Course co1 = new Course();
                    co1.deleteCourse();
                    System.out.println("--------------------------------------");
                    System.out.println();
                    break;

                case 4:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("           Update Course              ");
                    System.out.println("--------------------------------------");
                    ad.updateCourse();
                    System.out.println();
                    System.out.println("--------------------------------------");
                    break;

                case 5:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("            Update Module             ");
                    System.out.println("--------------------------------------");
                    ad.updateModule();
                    System.out.println();
                    System.out.println("--------------------------------------");
                    break;

                case 6:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("       Add New Instructor             ");
                    System.out.println("--------------------------------------");
                    ad.assignNewInstructor();
                    System.out.println();
                    System.out.println("--------------------------------------");
                    break;

                case 7:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("        Assign Instructor             ");
                    System.out.println("--------------------------------------");
                    ad.assignInstructorToCourse();
                    System.out.println();
                    System.out.println("--------------------------------------");
                    break;

                case 8:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("            Cancel Course             ");
                    System.out.println("--------------------------------------");
                    ad.cancelCourse();
                    System.out.println();
                    System.out.println("--------------------------------------");
                    break;

                case 9:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("          Resume Course               ");
                    System.out.println("--------------------------------------");
                    ad.resumeCourse();
                    System.out.println();
                    System.out.println("--------------------------------------");
                    break;

                case 10:
                    System.out.println("\n\n\n");
                    System.out.println("--------------------------------------");
                    System.out.println("       Update Instructor              ");
                    System.out.println("--------------------------------------");
                    ad.updateInstructor();
                    System.out.println();
                    System.out.println("--------------------------------------");
                    break;

                case 11:
                    System.out.println("\n\n\n");
                    ad.resultSlip();
                    break;

                case 12:
                    System.out.println("\n\n\n");
                    adminMenu();
                    break;

                case 13:
                    System.out.println("\n\n\n");
                    mainMenu();
                    break;

                case 14:
                    System.out.println("Exiting the application...");
                    System.exit(0);

                default:
                    System.out.println("Error! Please input only the number options available above!!!");
                    System.out.println("\n\n");
                    adminLoggedInMenu();
            }
        }catch(InputMismatchException e) {
            scan.next();
            System.out.println("Please input only the number options available above!!!");
            System.out.println();
            System.out.println();
            System.out.println();
            adminLoggedInMenu();
        }
    }
    //main method to run whole program
    public static void main(String[] args) {
        mainMenu();
    }
}
