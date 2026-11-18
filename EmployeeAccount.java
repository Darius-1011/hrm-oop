import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EmployeeAccount extends Account {
    private Employee employee;

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public EmployeeAccount(String username, String password, AccountStatus status) {
        super(username, password, status);
    }

    public EmployeeAccount(String username, String password, AccountStatus status, Employee employee) {
        super(username, password, status);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void submitAbsence(Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Gửi đơn xin nghỉ phép";
        String subtitle = "";
        String content = "";

        if (employee == null) {
            console.printResult(title, "Tài khoản chưa được liên kết với hồ sơ nhân viên nào.", sc);
            return;
        }

        String reason = console.printUI(title, subtitle, content, "Lý do nghỉ", sc);
        content += "Lý do: " + reason + "\n";

        LocalDate fromDate;
        LocalDate toDate;
        try {
            String fromStr = console.printUI(title, subtitle, content, "Từ ngày (dd/MM/yyyy)", sc);
            fromDate = LocalDate.parse(fromStr, dateFormatter);
            content += "Từ ngày: " + fromDate + "\n";

            String toStr = console.printUI(title, subtitle, content, "Đến ngày (dd/MM/yyyy)", sc);
            toDate = LocalDate.parse(toStr, dateFormatter);
        } catch (DateTimeParseException e) {
            console.printResult(title, "Sai định dạng ngày! Vui lòng nhập theo chuẩn dd/MM/yyyy (VD: 20/11/2026).", sc);
            return;
        }

        Absence[] absences = employee.getAbsences();
        if (absences == null) {
            console.printResult(title, "Danh sách đơn nghỉ phép chưa được khởi tạo.", sc);
            return;
        }

        for (int i = 0; i < absences.length; i++) {
            if (absences[i] == null) {
                absences[i] = new Absence(reason, fromDate, toDate, Absence.AbsenceStatus.PENDING);
                console.printResult(title, "Gửi đơn xin nghỉ phép thành công! Trạng thái: PENDING", sc);
                return;
            }
        }
        console.printResult(title, "Danh sách đơn nghỉ phép đã đầy, không thể thêm mới.", sc);
    }

    public void submitResignation(Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Gửi đơn thôi việc";

        if (employee == null) {
            console.printResult(title, "Tài khoản chưa được liên kết với hồ sơ nhân viên nào.", sc);
            return;
        }

        String reason = console.printUI(title, "", "", "Lý do thôi việc", sc);
        LocalDate date = LocalDate.now();

        Resignation[] resignations = employee.getResignations();
        if (resignations == null) {
            console.printResult(title, "Danh sách đơn thôi việc chưa được khởi tạo.", sc);
            return;
        }

        for (int i = 0; i < resignations.length; i++) {
            if (resignations[i] == null) {
                resignations[i] = new Resignation(reason, date, Resignation.ResignationStatus.PENDING);
                console.printResult(title, "Gửi đơn thôi việc thành công ngày " + date + "! Trạng thái: PENDING", sc);
                return;
            }
        }
        console.printResult(title, "Danh sách đơn thôi việc đã đầy, không thể thêm mới.", sc);
    }

    public void checkIn(Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Chấm công vào (Check-in)";

        if (employee == null) {
            console.printResult(title, "Tài khoản chưa được liên kết với hồ sơ nhân viên nào.", sc);
            return;
        }

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now().withNano(0);
        Attendance[] attendances = employee.getAttendances();

        if (attendances == null) {
            console.printResult(title, "Danh sách chấm công chưa được khởi tạo.", sc);
            return;
        }

        for (int i = 0; i < attendances.length; i++) {
            if (attendances[i] != null && attendances[i].getDate() != null && attendances[i].getDate().equals(today)) {
                if (attendances[i].getClockIn() != null) {
                    console.printResult(title,
                            "Bạn đã check-in vào lúc " + attendances[i].getClockIn() + " hôm nay rồi.", sc);
                    return;
                } else {
                    attendances[i].setClockIn(now);
                    console.printResult(title, "Check-in thành công lúc: " + now, sc);
                    return;
                }
            }
        }

        for (int i = 0; i < attendances.length; i++) {
            if (attendances[i] == null) {
                attendances[i] = new Attendance(today, now, null);
                console.printResult(title, "Check-in thành công ngày " + today + " lúc " + now, sc);
                return;
            }
        }
        console.printResult(title, "Mảng chấm công đã đầy.", sc);
    }

    public void checkOut(Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Chấm công ra (Check-out)";

        if (employee == null) {
            console.printResult(title, "Tài khoản chưa được liên kết với hồ sơ nhân viên nào.", sc);
            return;
        }

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now().withNano(0);
        Attendance[] attendances = employee.getAttendances();

        if (attendances == null) {
            console.printResult(title, "Danh sách chấm công chưa được khởi tạo.", sc);
            return;
        }

        for (int i = 0; i < attendances.length; i++) {
            if (attendances[i] != null && attendances[i].getDate() != null && attendances[i].getDate().equals(today)) {
                if (attendances[i].getClockIn() == null) {
                    console.printResult(title, "Bạn chưa thực hiện Check-in hôm nay!", sc);
                    return;
                }
                attendances[i].setClockOut(now);
                console.printResult(title, "Check-out thành công lúc: " + now, sc);
                return;
            }
        }
        console.printResult(title, "Không tìm thấy bản ghi check-in hôm nay. Vui lòng check-in trước.", sc);
    }

    public void viewAttendance(Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Lịch sử chấm công";

        if (employee == null) {
            console.printResult(title, "Tài khoản chưa được liên kết với hồ sơ nhân viên nào.", sc);
            return;
        }

        Attendance[] attendances = employee.getAttendances();
        if (attendances == null) {
            console.printResult(title, "Danh sách chấm công trống.", sc);
            return;
        }

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        StringBuilder table = new StringBuilder();
        table.append(String.format("%-5s | %-15s | %-12s | %-12s | %-10s%n", "STT", "Ngày", "Check-in", "Check-out",
                "Số giờ làm"));
        table.append("-----------------------------------------------------------------------\n");

        boolean found = false;
        int count = 1;
        for (int i = 0; i < attendances.length; i++) {
            if (attendances[i] != null && attendances[i].getDate() != null) {
                String dateStr = attendances[i].getDate().format(dateFormatter);
                String clockInStr = attendances[i].getClockIn() != null
                        ? attendances[i].getClockIn().format(timeFormatter)
                        : "Chưa CI";
                String clockOutStr = attendances[i].getClockOut() != null
                        ? attendances[i].getClockOut().format(timeFormatter)
                        : "Chưa CO";

                String hoursStr = "N/A";
                if (attendances[i].getClockIn() != null && attendances[i].getClockOut() != null) {
                    hoursStr = String.format("%.2f h", attendances[i].calculateWorkedHours());
                }

                table.append(String.format("%-5d | %-15s | %-12s | %-12s | %-10s%n", count++, dateStr, clockInStr,
                        clockOutStr, hoursStr));
                found = true;
            }
        }

        if (!found) {
            table.append("Chưa có dữ liệu chấm công nào.\n");
        }

        console.printResult(title, table.toString(), sc);
    }
}
