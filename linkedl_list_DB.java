
import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Scanner;


public class linkedl_list_DB {
    
  LinkedList<Army_schema> linkedList = new LinkedList<Army_schema>(); // ll of the Army
  static int index = 0;

  // Database() {index = 1; }

  // Methods For Data Manupulation using LL
  public Army_schema createArmyStorage(String name, Army_schema army) {
    if (name.isBlank())
      return null;

    else {
      System.out.println("INDEX -> " + index++);
      linkedList.addLast(/* index++, */ army); // Syntax : add(int index, Object)
      // System.out.println("running method");
      return army; // return type - Army_schema
    }
  }

  public Army_schema getArmyByIdStorage(int id) {
    return linkedList.get(id); // LinkedList.get(int index)
  }

  public Army_schema updateArmyStorage(int ID_index, String name, String age, String rank, String gender,
      String location_zone, String phone_num, String address) {

    // System.out.println("why ur NOT running");
    return linkedList.get(ID_index).updateArmyDetails(name, age, gender, rank, location_zone, phone_num, address);
  }

  public Army_schema deleteArmyStorage(int index) {
    return linkedList.remove(index);
  }

  public void getAllArmyStorage() {
    int len = linkedList.size(), i;

    System.out.println("Length of LinkedList = " + len);
    for (i = 0; i < len; i++) {
      Army_schema troop = linkedList.get(i);
      System.out.println(troop.obj_show());
    }
  }

}
