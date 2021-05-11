
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.ArrayList;
// import jdk.internal.foreign.Utils;

// MAIN PUBLIC CLASS CONTAINING void main()
public class Army_Management_System {

  // ---------------- MAIN FUNCTION --------------
  public static void main(String[] args) {
    Database DB = new Database();

    DB.createArmyTable();

    Main_menu MENU = new Main_menu(DB);
    MENU.armyManagement_Menu();
  }
}

// MAIN MENU - switch_case
class Main_menu {

  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  Scanner scan = new Scanner(System.in); // Create a Scanner object
  Database db; // variable declare

  Main_menu(Database data_base) { // now db <= data_base <= DB
    db = data_base;
  }

  // ------------------ ALL METHODS/FUNCTION -------------------------
  public void createArmyRecord() {
    try {
      String name, age, self_rank, gender, location_zone, phone_num, address;

      System.out.println("\n---- Creating Records for an Army:  ----");
      System.out.print("Enter Army name: ");
      name = br.readLine();
      System.out.print("Enter age: ");
      age = br.readLine();
      System.out.print("Enter gender: ");
      gender = br.readLine();
      System.out.print("Enter rank: ");
      self_rank = br.readLine();
      System.out.print("Enter duty (site)location: ");
      location_zone = br.readLine();
      System.out.print("Enter phone number: ");
      phone_num = br.readLine();
      System.out.print("Enter address: ");
      address = br.readLine();

      // System.out.println(army.tostring());
      Army_schema newArmy = db.createArmyStorage(name, age, gender, self_rank, location_zone, phone_num, address); // pass
                                                                                                                   // ->
      System.out.println(newArmy.obj_show());
      System.out.println(ConsoleColors.GREEN_BOLD + "-----  Successfully Created Details ------" + ConsoleColors.RESET);
    }

    catch (Exception e) {
      System.out.println(e);
    }
  }

  public void getArmyById() {
    String index;
    try {
      System.out.println("\n----  Army Details using Index : ----");
      System.out.print("Enter index of the Army: ");
      index = br.readLine();
      Army_schema army = db.getArmyByIdStorage("ID", index);
      System.out.println(army.obj_show());
      System.out.println(ConsoleColors.GREEN_BOLD + "-----  Successfully Fetched Details of Army " + index + " ------"
          + ConsoleColors.RESET);
    }

    catch (Exception e) {
      System.out.println(e);
    }
  }

  public void updateArmyDetails() {

    try {
      String name, age, rank, gender, location_zone, phone_num, address, ID;

      System.out.println("\n---- Update Army Details:  ----");
      System.out.print("Enter Index of the Army for Updation: ");
      ID = br.readLine();
      System.out.print("Enter Army name: ");
      name = br.readLine();
      System.out.print("Enter Updated age: ");
      age = br.readLine();
      System.out.print("Enter Updated gender: ");
      gender = br.readLine();
      System.out.print("Enter Updated rank: ");
      rank = br.readLine();
      System.out.print("Enter Updated duty (site)location: ");
      location_zone = br.readLine();
      System.out.print("Enter Updated phone number: ");
      phone_num = br.readLine();
      System.out.print("Enter Updated address: ");
      address = br.readLine();

      Army_schema updateTroop = db.updateArmyStorage(ID, name, age, rank, gender, location_zone, phone_num, address);
      System.out.println(updateTroop.obj_show());
      System.out.println(ConsoleColors.GREEN_BOLD + "-----  Successfully Updated Details ------" + ConsoleColors.RESET);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public void deleteArmyDetails() {
    try {
      String ID;
      System.out.println("\n---- Delete Army Details:  ----");
      System.out.print("Enter Index of the Army for Deletion: ");
      ID = br.readLine();
      db.deleteArmyStorage(ID);
      System.out.println(
          ConsoleColors.GREEN_BOLD + "-----  Successfully Created Details " + ID + " ------" + ConsoleColors.RESET);
    }

    catch (Exception e) {
      System.out.println(e);
    }
  }

  public void fetchAllArmyDetails() {
    try {
      System.out.println("\n---- Fetch All Army Details:  ----\n");

      ArrayList<Army_schema> armies = db.getAllArmyStorage();
      armies.forEach((element) -> System.out.println(element.obj_show()));

      System.out.println(
          ConsoleColors.GREEN_BOLD + "-----  Successfully Display All Army Details  ------" + ConsoleColors.RESET);

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // Menu Driven --- Switch_Case
  public void armyManagement_Menu() {
    int option;

    do {
      System.out.println(
          ConsoleColors.YELLOW_BOLD_BRIGHT + "\n\n----------------------------------------------------------------");
      System.out.println(
          ConsoleColors.PURPLE_BOLD_BRIGHT + "**************     ARMY MANAGEMENT SYSTEM      *****************");
      System.out.println(
          ConsoleColors.YELLOW_BOLD_BRIGHT + "----------------------------------------------------------------\n");
      System.out.println(ConsoleColors.CYAN_BOLD_BRIGHT + "1)  Creating Records for a new Army ");
      System.out.println("2)  Show Army records using Index");
      System.out.println("3)  Update Army Details");
      System.out.println("4)  Delete Army Details");
      System.out.println("5)  Display all Army Details");
      System.out.println("6)  Exit\n" + ConsoleColors.RESET);
      System.out.print(ConsoleColors.RED_BOLD + "Select an option: " + ConsoleColors.RESET);
      option = scan.nextInt();

      boolean shouldExit = false;
      switch (option) {
        case 1:
          createArmyRecord();
          break;

        case 2:
          getArmyById();
          break;

        case 3:
          updateArmyDetails();
          break;

        case 4:
          deleteArmyDetails();
          break;

        case 5:
          fetchAllArmyDetails();
          break;

        case 6:
          shouldExit = true;
          break;
        default:
          System.out.println("Wrong option!");
          break;
      }

      System.out.println();
      if (shouldExit)
        return;
    } while ((option >= 1) && (option <= 10));
  }
}

// MAIN VARIABLES DECLARED FOR STRUCTURE
class Army_schema {

  // instance variables
  String id;
  String name;
  String age;
  String gender;
  String self_rank;
  String location_zone;
  String phone_num;
  String address;

  // object of the class created using CONSTRUCTOR
  Army_schema(String id, String name, String age, String gender, String self_rank, String location_zone,
      String phone_num, String address) {
    this.id = id;
    this.name = name;
    this.age = age; // local variable --> inside the METHOD
    this.gender = gender;
    this.self_rank = self_rank;
    this.location_zone = location_zone;
    this.phone_num = phone_num;
    this.address = address;
  }

  public Army_schema updateArmyDetails(String NAME, String AGE, String GENDER, String SELF_RANK, String DUTY_SITE,
      String PHONE, String ADDRESS) {
    if (!NAME.isBlank())
      name = NAME;
    if (!AGE.isBlank())
      age = AGE;
    if (!GENDER.isBlank())
      gender = GENDER;
    if (!SELF_RANK.isBlank())
      SELF_RANK = self_rank;
    if (!DUTY_SITE.isBlank())
      location_zone = DUTY_SITE;
    if (!PHONE.isBlank())
      phone_num = PHONE;
    if (!ADDRESS.isBlank())
      address = ADDRESS;
    return this;
  }

  public String toString() {
    return (this.id + ", " + this.name + ", " + this.age + ", " + this.gender + ", " + this.self_rank + ", "
        + this.location_zone + ", " + this.phone_num + ", " + this.address);
  }

  public String obj_show() {
    return ("ID : " + this.id + "\t" + "Name : " + this.name + "\t" + "Age : " + this.age + "\t" + "Gender : "
        + this.gender + "\t" + "Rank : " + this.self_rank + "\t" + "Location of Duty : " + this.location_zone + "\t"
        + "Phone : " + this.phone_num + "\t" + "Address : " + this.address + "\n");
  }
}

// DATABASE -- using Linked List
class Database {

  private Connection CONN;
  static int index = 0;

  Database() {
    this.CONN = getConnection();
  }

  public void createArmyTable() {
    try {
      String query = "CREATE TABLE IF NOT EXISTS users(id int NOT NULL AUTO_INCREMENT, name varchar(255), age varchar(255), gender varchar(255), self_rank varchar(255), location_zone varchar(255),phone_num varchar(255), address varchar(255),PRIMARY KEY(id))";
      PreparedStatement q = this.CONN.prepareStatement(query);
      q.executeUpdate();

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public Army_schema createArmyStorage(String name, String age, String gender, String self_rank, String location_zone,
      String phone_num, String address) {

    try {
      if (name.isBlank())
        return null;
      else {
        String query = String.format(
            "INSERT INTO users(name,age , gender, self_rank, location_zone, phone_num, address) VALUES ('%s','%s','%s','%s', '%s','%s', '%s');",
            name, age, gender, self_rank, location_zone, phone_num, address, " ");
        PreparedStatement p = this.CONN.prepareStatement(query);
        p.executeUpdate();
        return getArmyByIdStorage("name", name);
      }
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public Army_schema getArmyByIdStorage(String id, String ID_index) {
    try {
      String query = String.format("SELECT * FROM users WHERE %s='%s'", id, ID_index);
      PreparedStatement q = CONN.prepareStatement(query);
      ResultSet res = q.executeQuery();
      while (res.next()) {
        Army_schema army = new Army_schema(res.getString("id"), res.getString("name"), res.getString("age"),
            res.getString("gender"), res.getString("self_rank"), res.getString("location_zone"),
            res.getString("phone_num"), res.getString("address"));
        System.out.println("\nArmy Name : " + army.name);
        return army;
      }
      return null;
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }

  public Army_schema updateArmyStorage(String ID_index, String name, String age, String rank, String gender,
      String location_zone, String phone_num, String address) throws SQLException {

    if (ID_index.isBlank())
      return null;

    String query = String.format("UPDATE users SET %s %s %s %s WHERE id='%d'",
        !name.isBlank() ? "name=" + "'" + name + "'," : "", !age.isBlank() ? "age=" + "'" + age + "'," : "",
        !rank.isBlank() ? "rank=" + "'" + rank + "'," : "", !gender.isBlank() ? "gender=" + "'" + gender + "'" : "",
        !location_zone.isBlank() ? "location_zone=" + "'" + location_zone + "'" : "",
        !phone_num.isBlank() ? "phone_num=" + "'" + phone_num + "'" : "",
        !address.isBlank() ? "address=" + "'" + address + "'" : "", ID_index);

    PreparedStatement q = CONN.prepareStatement(query);
    q.executeUpdate();
    return getArmyByIdStorage("id", ID_index);
  }

  public void deleteArmyStorage(String id) {
    try {
      String query = String.format("DELETE FROM users WHERE id='%s'", id);
      PreparedStatement q = CONN.prepareStatement(query);
      q.executeUpdate();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public ArrayList<Army_schema> getAllArmyStorage() {
    ArrayList<Army_schema> ARMIES = new ArrayList<Army_schema>();
    try {
      String query = "SELECT * from users";
      PreparedStatement q = CONN.prepareStatement(query);
      ResultSet res = q.executeQuery();
      while (res.next()) {
        Army_schema single_army = new Army_schema(res.getString("id"), res.getString("name"), res.getString("age"),
            res.getString("gender"), res.getString("self_rank"), res.getString("location_zone"),
            res.getString("phone_num"), res.getString("address"));

        ARMIES.add(single_army);
      }
      return ARMIES;
    } catch (Exception e) {
      System.out.println(e);
      return ARMIES;
    }
  }

  public Connection getConnection() {
    try {
      String driver = "com.mysql.cj.jdbc.Driver";
      String url = "jdbc:mysql://remotemysql.com:3306/gzvnRBxYho";
      String username = "gzvnRBxYho";
      String password = "R4JFnJGxgF";
      Class.forName(driver);

      Connection conn = DriverManager.getConnection(url, username, password);
      System.out.println("SQL Database Connected Successfully");
      return conn;
    } catch (Exception e) {
      System.out.println(e);
    }

    return null;
  }

}

// CONSOLE COLORS
class ConsoleColors {
  // Reset
  public static final String RESET = "\033[0m"; // Text Reset

  // Regular Colors
  public static final String BLACK = "\033[0;30m"; // BLACK
  public static final String RED = "\033[0;31m"; // RED
  public static final String GREEN = "\033[0;32m"; // GREEN
  public static final String YELLOW = "\033[0;33m"; // YELLOW
  public static final String BLUE = "\033[0;34m"; // BLUE
  public static final String PURPLE = "\033[0;35m"; // PURPLE
  public static final String CYAN = "\033[0;36m"; // CYAN
  public static final String WHITE = "\033[0;37m"; // WHITE

  // Bold
  public static final String BLACK_BOLD = "\033[1;30m"; // BLACK
  public static final String RED_BOLD = "\033[1;31m"; // RED
  public static final String GREEN_BOLD = "\033[1;32m"; // GREEN
  public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
  public static final String BLUE_BOLD = "\033[1;34m"; // BLUE
  public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
  public static final String CYAN_BOLD = "\033[1;36m"; // CYAN
  public static final String WHITE_BOLD = "\033[1;37m"; // WHITE

  // Underline
  public static final String BLACK_UNDERLINED = "\033[4;30m"; // BLACK
  public static final String RED_UNDERLINED = "\033[4;31m"; // RED
  public static final String GREEN_UNDERLINED = "\033[4;32m"; // GREEN
  public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
  public static final String BLUE_UNDERLINED = "\033[4;34m"; // BLUE
  public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
  public static final String CYAN_UNDERLINED = "\033[4;36m"; // CYAN
  public static final String WHITE_UNDERLINED = "\033[4;37m"; // WHITE

  // Background
  public static final String BLACK_BACKGROUND = "\033[40m"; // BLACK
  public static final String RED_BACKGROUND = "\033[41m"; // RED
  public static final String GREEN_BACKGROUND = "\033[42m"; // GREEN
  public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
  public static final String BLUE_BACKGROUND = "\033[44m"; // BLUE
  public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
  public static final String CYAN_BACKGROUND = "\033[46m"; // CYAN
  public static final String WHITE_BACKGROUND = "\033[47m"; // WHITE

  // High Intensity
  public static final String BLACK_BRIGHT = "\033[0;90m"; // BLACK
  public static final String RED_BRIGHT = "\033[0;91m"; // RED
  public static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN
  public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
  public static final String BLUE_BRIGHT = "\033[0;94m"; // BLUE
  public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
  public static final String CYAN_BRIGHT = "\033[0;96m"; // CYAN
  public static final String WHITE_BRIGHT = "\033[0;97m"; // WHITE

  // Bold High Intensity
  public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
  public static final String RED_BOLD_BRIGHT = "\033[1;91m"; // RED
  public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
  public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
  public static final String BLUE_BOLD_BRIGHT = "\033[1;94m"; // BLUE
  public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
  public static final String CYAN_BOLD_BRIGHT = "\033[1;96m"; // CYAN
  public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

  // High Intensity backgrounds
  public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
  public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
  public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
  public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
  public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
  public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
  public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m"; // CYAN
  public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m"; // WHITE
}