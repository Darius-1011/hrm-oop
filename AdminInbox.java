import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Scanner;

public class AdminInbox {
  private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter SHORT_FMT = DateTimeFormatter.ofPattern("dd/MM/yy");
  private static final Map<Object, String> replyNotes = new IdentityHashMap<>();

  private static class PendingItem {
    Employee employee;
    Absence absence;
    Resignation resignation;

    PendingItem(Employee employee, Absence absence) {
      this.employee = employee;
      this.absence = absence;
    }

    PendingItem(Employee employee, Resignation resignation) {
      this.employee = employee;
      this.resignation = resignation;
    }

    boolean isAbsence() {
      return absence != null;
    }
  }

  private static int validateInt(String str) {
    if (str != null && str.matches("[0-9]+")) {
      return Integer.parseInt(str);
    }
    return -1;
  }

  public static String fit(String value, int width) {
    String text = (value == null) ? "" : value.replace("\r", " ").replace("\n", " ").trim();
    if (text.length() > width) {
      text = (width > 3) ? text.substring(0, width - 3) + "..." : text.substring(0, width);
    }
    return String.format("%-" + width + "s", text);
  }

  public static String getReplyNote(Object request) {
    String note = replyNotes.get(request);
    return (note == null || note.isEmpty()) ? "-" : note;
  }

  private static String safeName(Employee emp) {
    String first = emp.getFirstName() != null ? emp.getFirstName() : "";
    String last = emp.getLastName() != null ? emp.getLastName() : "";
    return (first + " " + last).trim();
  }

  private static String formatDateRange(Absence a) {
    String from = a.getFromDate() != null ? a.getFromDate().format(SHORT_FMT) : "N/A";
    String to = a.getToDate() != null ? a.getToDate().format(SHORT_FMT) : "N/A";
    return from + " -> " + to;
  }

  private static String formatDate(LocalDate d) {
    return d != null ? d.format(DATE_FMT) : "N/A";
  }

  public static int countPending(Employee[] employees) {
    if (employees == null)
      return 0;

    int total = 0;
    for (Employee emp : employees) {
      if (emp == null)
        continue;
      if (emp.getAbsences() != null) {
        for (Absence a : emp.getAbsences()) {
          if (a != null && a.getStatus() == Absence.AbsenceStatus.PENDING) {
            total++;
          }
        }
      }
      if (emp.getResignations() != null) {
        for (Resignation r : emp.getResignations()) {
          if (r != null && r.getStatus() == Resignation.ResignationStatus.PENDING) {
            total++;
          }
        }
      }
    }
    return total;
  }

  private static PendingItem[] collectPending(Employee[] employees) {
    int total = countPending(employees);
    PendingItem[] items = new PendingItem[total];
    if (total == 0)
      return items;

    int idx = 0;
    for (Employee emp : employees) {
      if (emp == null)
        continue;
      if (emp.getAbsences() != null) {
        for (Absence a : emp.getAbsences()) {
          if (a != null && a.getStatus() == Absence.AbsenceStatus.PENDING) {
            items[idx++] = new PendingItem(emp, a);
          }
        }
      }
      if (emp.getResignations() != null) {
        for (Resignation r : emp.getResignations()) {
          if (r != null && r.getStatus() == Resignation.ResignationStatus.PENDING) {
            items[idx++] = new PendingItem(emp, r);
          }
        }
      }
    }
    return items;
  }

  private static String buildInboxTable(PendingItem[] items) {
    StringBuilder sb = new StringBuilder();
    String sep = "+-----+-------------+----------+--------------------+----------------------+----------------------------+\n";
    sb.append(sep);
    sb.append("| ").append(fit("STT", 3))
        .append(" | ").append(fit("Loại", 11))
        .append(" | ").append(fit("Mã NV", 8))
        .append(" | ").append(fit("Tên nhân viên", 18))
        .append(" | ").append(fit("Ngày", 20))
        .append(" | ").append(fit("Lý do", 26))
        .append(" |\n");
    sb.append(sep);

    for (int i = 0; i < items.length; i++) {
      PendingItem item = items[i];
      String type = item.isAbsence() ? "NGHỈ PHÉP" : "THÔI VIỆC";
      String empId = item.employee.getId() != null ? item.employee.getId() : "N/A";
      String empName = safeName(item.employee);
      String dateStr = item.isAbsence() ? formatDateRange(item.absence) : formatDate(item.resignation.getDate());
      String reason = item.isAbsence() ? item.absence.getReason() : item.resignation.getReason();

      sb.append("| ").append(fit(String.valueOf(i + 1), 3))
          .append(" | ").append(fit(type, 11))
          .append(" | ").append(fit(empId, 8))
          .append(" | ").append(fit(empName, 18))
          .append(" | ").append(fit(dateStr, 20))
          .append(" | ").append(fit(reason, 26))
          .append(" |\n");
    }
    sb.append(sep);
    sb.append("Tổng yêu cầu đang chờ: ").append(items.length).append("\n\n(Enter 0 to go back)");
    return sb.toString();
  }

  private static String buildDetail(PendingItem item) {
    StringBuilder sb = new StringBuilder();
    sb.append("Mã nhân viên: ").append(fit(item.employee.getId(), 95).trim()).append("\n");
    sb.append("Họ và tên: ").append(fit(safeName(item.employee), 95).trim()).append("\n");
    if (item.isAbsence()) {
      sb.append("Request Loại: NGHỈ PHÉP (Leave Request)\n");
      sb.append("Từ ngày: ").append(formatDate(item.absence.getFromDate())).append("\n");
      sb.append("Đến ngày: ").append(formatDate(item.absence.getToDate())).append("\n");
      sb.append("Lý do: ").append(fit(item.absence.getReason(), 95).trim()).append("\n");
      sb.append("Trạng thái: ").append(item.absence.getStatus());
    } else {
      sb.append("Request Loại: THÔI VIỆC\n");
      sb.append("Ngày: ").append(formatDate(item.resignation.getDate())).append("\n");
      sb.append("Lý do: ").append(fit(item.resignation.getReason(), 95).trim()).append("\n");
      sb.append("Trạng thái: ").append(item.resignation.getStatus());
    }
    return sb.toString();
  }

  public static void syncEmployeeStatus(Employee emp) {
    if (emp == null || emp.getStatus() == Employee.EmployeeStatus.RESIGNED) {
      return;
    }

    LocalDate today = LocalDate.now();

    if (emp.getResignations() != null) {
      for (Resignation r : emp.getResignations()) {
        if (r != null && r.getStatus() == Resignation.ResignationStatus.APPROVED
            && r.getDate() != null && !today.isBefore(r.getDate())) {
          emp.setStatus(Employee.EmployeeStatus.RESIGNED);
          return;
        }
      }
    }

    boolean inActiveLeave = false;
    boolean hasApprovedAbsence = false;
    if (emp.getAbsences() != null) {
      for (Absence a : emp.getAbsences()) {
        if (a != null && a.getStatus() == Absence.AbsenceStatus.APPROVED) {
          hasApprovedAbsence = true;
          LocalDate from = a.getFromDate();
          LocalDate to = a.getToDate();
          if (from != null && to != null && !today.isBefore(from) && !today.isAfter(to)) {
            inActiveLeave = true;
            break;
          }
        }
      }
    }

    if (inActiveLeave) {
      emp.setStatus(Employee.EmployeeStatus.ON_LEAVE);
    } else if (emp.getStatus() == Employee.EmployeeStatus.ON_LEAVE && hasApprovedAbsence) {
      emp.setStatus(Employee.EmployeeStatus.WORKING);
    }
  }

  public static void syncEmployeeStatuses(Employee[] employees) {
    if (employees == null) {
      return;
    }
    for (Employee emp : employees) {
      syncEmployeeStatus(emp);
    }
  }

  public static void openInbox(Employee[] employees, Scanner sc) {
    ConsoleUI console = new ConsoleUI();
    String title = "Hộp thư quản trị - Yêu cầu đang chờ";
    boolean handledAny = false;

    while (true) {
      PendingItem[] pending = collectPending(employees);
      if (pending.length == 0) {
        if (!handledAny) {
          console.printResult(title, "Hộp thư trống! Không có đơn nghỉ phép hoặc thôi việc đang chờ.", sc);
        } else {
          console.printResult(title, "Đã xử lý tất cả yêu cầu đang chờ!", sc);
        }
        return;
      }

      String table = buildInboxTable(pending);
      int pick = validateInt(console.printUI(title, "Chọn yêu cầu cần xử lý", table, "STT", sc));

      if (pick == 0) {
        return;
      }
      if (pick < 1 || pick > pending.length) {
        console.printResult(title, "Lựa chọn không hợp lệ!", sc);
        continue;
      }

      PendingItem selected = pending[pick - 1];
      String detail = buildDetail(selected);
      String[] actions = { "Quay lại", "Duyệt", "Từ chối" };

      int action;
      do {
        action = validateInt(console.printUI(title, "Chi tiết yêu cầu", detail, actions, sc));
        if (action < 0 || action > 2) {
          console.printResult(title, "Lựa chọn không hợp lệ!", sc);
        }
      } while (action < 0 || action > 2);

      if (action == 0) {
        continue;
      }

      boolean approved = (action == 1);
      String replyMsg = console.printUI(title, "Nhập phản hồi (nhấn Enter để bỏ qua)", "", "Phản hồi", sc).trim();
      String statusNote;

      if (selected.isAbsence()) {
        selected.absence.setStatus(approved ? Absence.AbsenceStatus.APPROVED : Absence.AbsenceStatus.REJECTED);
        if (!replyMsg.isEmpty()) {
          replyNotes.put(selected.absence, replyMsg);
        }
        if (approved) {
          syncEmployeeStatus(selected.employee);
          if (selected.employee.getStatus() == Employee.EmployeeStatus.ON_LEAVE) {
            statusNote = "APPROVED absence request of " + selected.employee.getId() + " (Trạng thái -> ON_LEAVE)";
          } else if (selected.absence.getFromDate() != null
              && LocalDate.now().isBefore(selected.absence.getFromDate())) {
            statusNote = "APPROVED absence request of " + selected.employee.getId()
                + " (Auto ON_LEAVE from " + formatDate(selected.absence.getFromDate()) + ")";
          } else {
            statusNote = "APPROVED absence request of " + selected.employee.getId();
          }
        } else {
          statusNote = "REJECTED absence request of " + selected.employee.getId();
        }
      } else {
        selected.resignation
            .setStatus(approved ? Resignation.ResignationStatus.APPROVED : Resignation.ResignationStatus.REJECTED);
        if (!replyMsg.isEmpty()) {
          replyNotes.put(selected.resignation, replyMsg);
        }
        if (approved) {
          selected.employee.setStatus(Employee.EmployeeStatus.RESIGNED);
          statusNote = "APPROVED resignation request of " + selected.employee.getId()
              + " (Nhân viên status -> RESIGNED)";
        } else {
          statusNote = "REJECTED resignation request of " + selected.employee.getId();
        }
      }

      if (!replyMsg.isEmpty()) {
        statusNote += "\nPhản hồi: " + fit(replyMsg, 95).trim();
      }
      handledAny = true;
      console.printResult(title, statusNote, sc);
    }
  }
}
