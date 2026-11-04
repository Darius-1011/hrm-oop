public class EmployeeAccount extends Account {
    public EmployeeAccount(String username, String password, AccountStatus status) {
        super(username, password, status);
    }

    public void submitAbsence() {
        System.out.println("Nhân viên gửi đơn xin nghỉ phép.");
    }

    public void submitResignation() {
        System.out.println("Nhân viên nộp đơn từ chức/thôi việc.");
    }

    public void checkIn() {
        System.out.println("Nhân viên chấm công vào (Check-in).");
    }

    public void checkOut() {
        System.out.println("Nhân viên chấm công ra (Check-out).");
    }

    public void viewAttendance() {
        System.out.println("Xem lịch sử chấm công.");
    }
}
