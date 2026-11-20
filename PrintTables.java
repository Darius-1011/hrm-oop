import java.util.Scanner;

public class PrintTables {
  private static String fit(String value, int width) {
    String text = value == null ? "" : value;
    if (text.length() > width)
      text = text.substring(0, width);
    return String.format("%-" + width + "s", text);
  }

  private static void appendRow(StringBuilder out, int[] widths, String... values) {
    out.append("|");
    for (int i = 0; i < widths.length; i++) {
      String value = i < values.length ? values[i] : "";
      out.append(" ").append(fit(value, widths[i])).append(" |");
    }
    out.append('\n');
  }

  private static void appendSeparator(StringBuilder out, int[] widths) {
    out.append("+");
    for (int width : widths)
      out.append("-".repeat(width + 2)).append("+");
    out.append('\n');
  }

  private static void show(String title, String content, Scanner sc) {
    ConsoleUI console = new ConsoleUI();
    console.printResult(title, content, sc);
  }

  public static void printEvaluations(Employee employee, String id, Scanner sc) {
    StringBuilder content = new StringBuilder();
    if (employee == null) {
      content.append("Employee not found: ").append(id);
    } else {
      int[] w = { 12, 8, 10, 12, 9, 14, 10, 10 };
      appendSeparator(content, w);
      appendRow(content, w, "Month", "Work", "Attitude", "Discipline",
          "Quality", "Productivity", "Teamwork", "Average");
      appendSeparator(content, w);
      Evaluation[] rows = employee.getEvaluations();
      boolean found = false;
      if (rows != null)
        for (Evaluation e : rows) {
          if (e != null) {
            appendRow(content, w, String.valueOf(e.getMonth()),
                String.format("%.1f", e.getWorkScore()),
                String.format("%.1f", e.getAttitudeScore()),
                String.format("%.1f", e.getDisciplineScore()),
                String.format("%.1f", e.getWorkQuality()),
                String.format("%.1f", e.getProductivity()),
                String.format("%.1f", e.getTeamWork()),
                String.format("%.2f", e.calculateEvaluationScore()));
            found = true;
          }
        }
      appendSeparator(content, w);
      if (!found)
        content.append("No evaluation data found.\n");
    }
    show("EVALUATION TABLE - " + id, content.toString(), sc);
  }

  public static void printContracts(Employee employee, String id, Scanner sc) {
    StringBuilder content = new StringBuilder();
    if (employee == null) {
      content.append("Employee not found: ").append(id);
    } else {
      int[] w = { 18, 18, 25, 20 };
      appendSeparator(content, w);
      appendRow(content, w, "Start Date", "End Date", "Contract Type", "Status");
      appendSeparator(content, w);
      Contract[] rows = employee.getContracts();
      boolean found = false;
      if (rows != null)
        for (Contract c : rows) {
          if (c != null) {
            appendRow(content, w, String.valueOf(c.getStartDate()),
                String.valueOf(c.getEndDate()), String.valueOf(c.getType()),
                String.valueOf(c.getStatus()));
            found = true;
          }
        }
      appendSeparator(content, w);
      if (!found)
        content.append("No contract data found.\n");
    }
    show("CONTRACT TABLE - " + id, content.toString(), sc);
  }

  public static void printPayrolls(Employee employee, String id, Scanner sc) {
    StringBuilder content = new StringBuilder();
    if (employee == null) {
      content.append("Employee not found: ").append(id);
    } else {
      int[] w = { 12, 14, 12, 12, 12, 14, 14 };
      appendSeparator(content, w);
      appendRow(content, w, "Month/Year", "Base Salary", "Bonus",
          "Allowance", "Deduction", "Overtime Pay", "Net Salary");
      appendSeparator(content, w);
      Payroll[] rows = employee.getPayrolls();
      boolean found = false;
      if (rows != null)
        for (Payroll p : rows) {
          if (p != null) {
            appendRow(content, w, p.getMonth().getMonthValue() + "/" + p.getMonth().getYear(),
                String.format("%.2f", p.getBaseSalary()),
                String.format("%.2f", p.getBonus()),
                String.format("%.2f", p.getAllowance()),
                String.format("%.2f", p.getDeduction()),
                String.format("%.2f", p.calculateOvertimePay()),
                String.format("%.2f", p.calculateNetSalary()));
            found = true;
          }
        }
      appendSeparator(content, w);
      if (!found)
        content.append("No payroll data found.\n");
    }
    show("PAYROLL TABLE - " + id, content.toString(), sc);
  }

  public static void printAttendances(Employee employee, String id, Scanner sc) {
    StringBuilder content = new StringBuilder();
    if (employee == null) {
      content.append("Employee not found: ").append(id);
    } else {
      int[] w = { 20, 20, 20, 16 };
      appendSeparator(content, w);
      appendRow(content, w, "Date", "Clock In", "Clock Out", "Worked Hours");
      appendSeparator(content, w);
      Attendance[] rows = employee.getAttendances();
      boolean found = false;
      if (rows != null)
        for (Attendance a : rows) {
          if (a != null) {
            appendRow(content, w, String.valueOf(a.getDate()),
                String.valueOf(a.getClockIn()), String.valueOf(a.getClockOut()),
                String.format("%.2f", a.calculateWorkedHours()));
            found = true;
          }
        }
      appendSeparator(content, w);
      if (!found)
        content.append("No attendance data found.\n");
    }
    show("ATTENDANCE TABLE - " + id, content.toString(), sc);
  }

  public static void printAbsences(Employee employee, String id, Scanner sc) {
    StringBuilder content = new StringBuilder();
    if (employee == null) {
      content.append("Employee not found: ").append(id);
    } else {
      int[] w = { 35, 18, 18, 16 };
      appendSeparator(content, w);
      appendRow(content, w, "Reason", "From Date", "To Date", "Status");
      appendSeparator(content, w);
      Absence[] rows = employee.getAbsences();
      boolean found = false;
      if (rows != null)
        for (Absence a : rows) {
          if (a != null) {
            appendRow(content, w, String.valueOf(a.getReason()),
                String.valueOf(a.getFromDate()), String.valueOf(a.getToDate()),
                String.valueOf(a.getStatus()));
            found = true;
          }
        }
      appendSeparator(content, w);
      if (!found)
        content.append("No absence data found.\n");
    }
    show("ABSENCE TABLE - " + id, content.toString(), sc);
  }

  public static void printResignations(Employee employee, String id, Scanner sc) {
    StringBuilder content = new StringBuilder();
    if (employee == null) {
      content.append("Employee not found: ").append(id);
    } else {
      int[] w = { 45, 20, 18 };
      appendSeparator(content, w);
      appendRow(content, w, "Reason", "Date", "Status");
      appendSeparator(content, w);
      Resignation[] rows = employee.getResignations();
      boolean found = false;
      if (rows != null)
        for (Resignation r : rows) {
          if (r != null) {
            appendRow(content, w, String.valueOf(r.getReason()),
                String.valueOf(r.getDate()), String.valueOf(r.getStatus()));
            found = true;
          }
        }
      appendSeparator(content, w);
      if (!found)
        content.append("No resignation data found.\n");
    }
    show("RESIGNATION TABLE - " + id, content.toString(), sc);
  }

  public static void printInsurances(Employee employee, String id, Scanner sc) {
    StringBuilder content = new StringBuilder();
    if (employee == null) {
      content.append("Employee not found: ").append(id);
    } else {
      int[] w = { 22, 20, 18, 18, 12 };
      appendSeparator(content, w);
      appendRow(content, w, "Insurance Number", "Type", "Start Date", "End Date", "Rate");
      appendSeparator(content, w);
      Insurance[] rows = employee.getInsurances();
      boolean found = false;
      if (rows != null)
        for (Insurance i : rows) {
          if (i != null) {
            appendRow(content, w, String.valueOf(i.getInsuranceNumber()),
                String.valueOf(i.getType()), String.valueOf(i.getStartDate()),
                String.valueOf(i.getEndDate()), String.format("%.2f", i.getRate()));
            found = true;
          }
        }
      appendSeparator(content, w);
      if (!found)
        content.append("No insurance data found.\n");
    }
    show("INSURANCE TABLE - " + id, content.toString(), sc);
  }
}
