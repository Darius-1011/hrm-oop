import java.util.Scanner;

public class ConsoleUI {
  private static final int CONTENT_WIDTH = 78;

  private void printCentered(String content) {
    int leftPadding = (CONTENT_WIDTH - content.length()) / 2;
    int rightPadding = CONTENT_WIDTH - content.length() - leftPadding;

    System.out.printf("║%" + leftPadding + "s" +
        "\u001B[1m" + "%s" + "\u001B[0m" +
        "%" + rightPadding + "s║%n",
        "", content, "");
  }

  public void printHeader(String title) {
    System.out.print("\033[H\033[2J");
    System.out.println("╔" + "═".repeat(CONTENT_WIDTH) + "╗");
    printCentered(title);
    System.out.println("╠" + "═".repeat(CONTENT_WIDTH) + "╣");
  }

  private void printSubtitle(String subtitle) {
    int rightPadding = CONTENT_WIDTH - 1 - subtitle.length();
    System.out.printf("║ " +
        "\u001B[3m" + "%s" + "\u001B[0m" +
        " ".repeat(rightPadding) + "║%n", subtitle);
  }

  private void printContent(String content) {
    int rightPadding = CONTENT_WIDTH - 1 - content.length();
    System.out.printf("║ %s" + " ".repeat(rightPadding) + "║%n", content);
  }

  private int printChoice(Scanner sc) {
    int rightPadding = CONTENT_WIDTH - 1 - 8;
    System.out.printf("║ Choice: " + " ".repeat(rightPadding) + "║%n");
    System.out.println("╚" + "═".repeat(CONTENT_WIDTH) + "╝");

    System.out.print("\u001B[1A");
    System.out.print("\u001B[1A");
    System.out.print("\u001B[10C");
    System.out.flush();

    return Integer.parseInt(sc.nextLine());
  }

  public int printMenu(String subtitle, String content, Scanner sc) {
    String[] lines = content.split("\n");
    int height = 4 + lines.length;

    System.out.println("║" + " ".repeat(CONTENT_WIDTH) + "║");
    printSubtitle(subtitle);
    System.out.println("║" + " ".repeat(CONTENT_WIDTH) + "║");
    for (int i = 0; i < lines.length; i++) {
      printContent(lines[i]);
    }
    System.out.println("║" + " ".repeat(CONTENT_WIDTH) + "║");
    System.out.println("╠" + "═".repeat(CONTENT_WIDTH) + "╣");
    return printChoice(sc);
  }

  public void printResult(String title, String result, Scanner sc) {
    System.out.print("\033[H\033[2J");
    System.out.println("╔" + "═".repeat(CONTENT_WIDTH) + "╗");
    printCentered(title);
    System.out.println("╠" + "═".repeat(CONTENT_WIDTH) + "╣");

    String[] lines = result.split("\n");
    int height = 4 + lines.length;

    System.out.println("║" + " ".repeat(CONTENT_WIDTH) + "║");
    for (int i = 0; i < lines.length; i++) {
      printContent(lines[i]);
    }
    System.out.println("║" + " ".repeat(CONTENT_WIDTH) + "║");
    printContent("Press Enter to go back");
    System.out.println("║" + " ".repeat(CONTENT_WIDTH) + "║");
    System.out.println("╚" + "═".repeat(CONTENT_WIDTH) + "╝");

    sc.nextLine();
  }
}
