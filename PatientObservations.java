import java.sql.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class q2_application {
    private static boolean isRunning = true;
    private static final String url = "xxxxxx";
    private static final String username  = "xxxxxx";
    private static final String password = "xxxxxx";

    // attributes to enter
    private static String hospital_id_enter;
    private static String record_id_enter;
    private static String observation_timestamp_enter;
    private static String observation_text_enter;

    private static Connection conn = null;
    private static Statement st = null;
    private static ResultSet rs = null;

    public static void main(String args[]) {
        //register a driver
       try {
           Class.forName("org.postgresql.Driver");
       }catch(ClassNotFoundException e){
           System.out.println("class not find");
           return;
       }

       try {
           //initialize the connection object and the statement object
           conn = DriverManager.getConnection(url,username,password);
           st = conn.createStatement();
           if (hospitalIdCheck(args[0])) {
               while (isRunning) {
                   System.out.println("Successfully Logged In.");
                   System.out.println("---------------------Application---------------------");
                   System.out.println("Please see the options below");
                   System.out.println("1 - enter a patient's observation");
                   System.out.println("2 - quit");
                   System.out.println("Please enter the option number:");
                   Scanner sc = new Scanner(System.in);
                   int input_num = sc.nextInt();
                   switch (input_num) {
                       case 1:
                           addObservation();
                           break;
                       case 2:
                           System.out.println("Quiting...See you next time!");
                           isRunning = false;
                           break;
                   }
               }
           } else {
               System.out.println("See you next time!");
           }
       }catch(SQLException e){
           System.out.println("Warning! Database access error occurs.");
           //e.printStackTrace();
       }finally{
           try { rs.close(); } catch (Exception e) { /* ignored */ }
           try { st.close(); } catch (Exception e) { /* ignored */ }
           try { conn.close(); } catch (Exception e) { /* ignored */ }
       }
    }

    //methods for option 1
    private static void addObservation() throws SQLException {

        Scanner sc= new Scanner(System.in);

        System.out.println("Please enter a hospital id:");
        String hospital_id_enter = sc.nextLine(); // nextline will return a string
        hospitalIdCheck(hospital_id_enter)
        System.out.println("Please enter a record id:");
        String record_id_enter = sc.nextLine();
        recordIdCheck(record_id_enter)
        System.out.println("Please enter a timestamp (yyyy-mm-dd hh:mm:ss) :");
        String observation_timestamp_enter = sc.nextLine();
        System.out.println("Please enter the content (500 words):");
        String observation_text_enter = sc.nextLine();



        rs = st.executeQuery("SELECT hospital_id, record_id, ob_timestamp FROM OBSERVATION;");
        if(rs!=null){
            while(rs.next()){
                String cur_hospital_id = Integer.toString(rs.getInt("hospital_id"));
                String cur_record_id = Integer.toString(rs.getString("record_id"));
                String cur_timestamp = rs.getString("ob_timestamp";
                //check if we have it in the system
                if((hospital_id_enter.equals(cur_hospital_id)) && (record_id_enter.eqlas(cur_record_id)) && (observation_timestamp_enter.equals(cur_timestamp))){
                    System.out.println("You have already added the observation recorded!");
                    return;
                }

            }else{
                int result = st.executeUpdate("INSERT INTO OBSERVATION VALUES (" + observation_timestamp_enter + "," + record_id_enter + ","  + hospital_id_enter + ","  + observation_text_enter + ");");
                if(result==1) {
                    System.out.println("You have added the record!");
                }else{
                    System.out.println("Something went wrong when addeing the observation.");
                }
            }
        }
        return;
    }


    //check if the client is a health professional in the platform
    private static boolean hospitalIdCheck(String input_hospital_id) throws SQLException {
        boolean check = false;
        //give a while loop if the user want to try again
        while(!check){
            int i = Integer.parseInt(input_hospital_id);
            rs = st.executeQuery("SELECT hospital_id FROM HEALTH_PROFESSIONAL");
            //loop through the result of the query
            while(rs.next()){
                int cur_id = rs.getInt(1);
                if(i==cur_id){
                    check=true;
                    cur_id = i;
                    return check;
                }
            }
            //didn't find the userid in the result
            System.out.println("Sorry, the hospital id is not found in our database.");
            return check;
        }
    }

   //check if the record id in the platform
    private static boolean recordIdCheck(String input_record_id) throws SQLException {
        boolean check = false;
        //give a while loop if the user want to try again
        while(!check){
            int i = Integer.parseInt(input_record_id);
            rs = st.executeQuery("SELECT record_id FROM CONFIRMED_PATIENT");
            //loop through the result of the query
            while(rs.next()){
                int cur_id = rs.getInt(1);
                if(i==cur_id){
                    check=true;
                    cur_id = i;
                    return check;
                }
            }
            //didn't find the userid in the result
            System.out.println("Sorry, the record id is not found in our database.");
            return check;
        }
    }