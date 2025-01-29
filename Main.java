import java.io.*;
import java.util.Scanner;

/**
 * Author: Amanda DiFalco
 * The purpose of this class is to store information (color, zipcode, and state)
 * inside a file and be able to modify it
 */
class Main {
  /**
   * These three variables are used to set the lengths of the elements
   * when they are written to the file
   */
  public static final int colorLength = 8;
  public static final int zipLength = 5;
  public static final int stateLength = 2;

  /**
   * Creates the Scanner and asks the user if they
   * want to add or modify an item
   */
  public static void main(String[] args) throws IOException {
    RandomAccessFile raf = new RandomAccessFile("fun.dat", "rw");
    Scanner sc = new Scanner(System.in);
    String AM = "a";

    label:
    while (AM.equals("a") || AM.equals("m")) {
      System.out.println("Would you like to add an item, modify an item, or exit? a, m, or e:");
      AM = sc.nextLine();
      switch (AM) {
        case "a":
          addItems(sc, raf);
          break;
        case "m":
          modifyItems(sc, raf);
          break;
        case "e":
          break label;
      }
    }
  }

  /**
   * This method runs if the user wants to add an item,
   * it asks color, zipcode, and state. Once the user
   * finishes the questions it adds/writes the new line
   * at the end of the file
   * @param s the scanner
   * @param raf the file
   * @throws IOException throws
   */
  public static void addItems(Scanner s, RandomAccessFile raf) throws IOException {
    System.out.println("Color?: ");
    String color = s.nextLine();
    String newColor = updateText(color, colorLength);

    System.out.println("ZipCode?: ");
    String zipCode = s.nextLine();
    String newZip = updateText(zipCode, zipLength);

    System.out.println("State?: ");
    String state = s.nextLine();
    String newState = updateText(state, stateLength);

    String line = "Color: " + newColor + " ZipCode: "
            + newZip + " State: " + newState;
    raf.seek(raf.length());
    raf.write((line).getBytes());
    raf.write("\n".getBytes());
  }

  /**
   * This method is used for formatting ensuring that every input
   * is the correct length
   * @param text input for an element from the user previously
   * @param length desired length of the text
   * @return the updated length
   */
  public static String updateText(String text, int length) {
    String newText;
    if (text.length() < length) {
      for (int i = text.length(); i < length; i++) {
        text += " ";
      }
      newText = text;
    } else {
      newText = text.substring(0, length);
    }
    return newText;
  }

  /**
   * This method modifies the specified line without reading the whole file
   * @param s the scanner
   * @param raf the file
   * @throws IOException throws
   */
  public static void modifyItems(Scanner s, RandomAccessFile raf) throws IOException {
    System.out.println("What line would you like to modify?: ");
    int lineNum = Integer.parseInt(s.nextLine()) - 1;

    System.out.println("What element would you like to modify? c, z, or s: ");
    String elementChoice = s.nextLine();

    System.out.println("What would you like to change it to?: ");
    String changeElement = s.nextLine();

    raf.seek(0);//start at beginning
    long pointer = 0;
    int currentLine = 0;
    String line;

    while ((line = raf.readLine()) != null) {
      if (currentLine == lineNum) {
        break;
      }
      pointer = raf.getFilePointer();
      currentLine++;
    }

    if (currentLine == lineNum) {
      raf.seek(pointer);
      String updatedLine = getString(line, elementChoice, changeElement);
      raf.write(updatedLine.getBytes());
    } else {
      System.out.println("Invalid line number.");
    }
  }

  /**
   * This method is used to put all the elements together to create
   * the lines to be written in the file
   * @param lineToUpdate is the line that is being modified
   * @param elementChoice is the element being modified
   * @param changeElement is the new element
   * @return the new line
   */
  private static String getString(String lineToUpdate, String elementChoice, String changeElement) {
   if(lineToUpdate != null) {
     String color = getElement(lineToUpdate, "c");
     String zipCode = getElement(lineToUpdate, "z");
     String state = getElement(lineToUpdate, "s");
     switch (elementChoice) {
       case "c" -> color = updateText(changeElement, colorLength);
       case "z" -> zipCode = updateText(changeElement, zipLength);
       case "s" -> state = updateText(changeElement, stateLength);
     }
     return "Color: " + color + " ZipCode: " + zipCode + " State: " + state;
   }
   return "";
  }

  /**
   * Gets the needed elements by creating a hard-coded substring of each.
   * If this were to be a lasting project I would refactor this method to avoid
   * the hard-coded values
   * @param line is the target line that we need elements from
   * @param element the element that we need from the line
   * @return substring of the target element
   */
  public static String getElement(String line, String element) {
    if(line != null) {
      return switch (element) {
        case "c" -> line.substring(7, 15);//color
        case "z" -> line.substring(25, 30);//zipcode
        case "s" -> line.substring(38, 40);//state
        default -> "";
      };
    }
    return "";
  }
}
