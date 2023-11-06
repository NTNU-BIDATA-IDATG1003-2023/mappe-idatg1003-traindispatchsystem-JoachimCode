package edu.ntnu.stud.gui;

import java.util.Scanner;

public class Input {
  Scanner scanner = new Scanner(System.in);

  public int scan_input(){
    scanner.nextLine();
    return 0;
  }
  public int getInput(){
    return scan_input();
  }
}

