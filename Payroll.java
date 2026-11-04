import java.time.LocalDate;

public class Payroll {
    private int month;
    private int year;
    private double baseSalary;
    private double bonus;
    private double allowance;
    private double deduction;
    private double taxAmount;
    private double insuranceAmount;
    private int workedHours;
    private int overtimeHours;
    private LocalDate lastSalaryIncreaseDate;

    public Payroll(int month, int year, double baseSalary,
            double bonus, double allowance,
            double deduction, double taxAmount,
            double insuranceAmount, int workedHours,
            int overtimeHours,
            LocalDate lastSalaryIncreaseDate) {
        this.month = month;
        this.year = year;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.allowance = allowance;
        this.deduction = deduction;
        this.taxAmount = taxAmount;
        this.insuranceAmount = insuranceAmount;
        this.workedHours = workedHours;
        this.overtimeHours = overtimeHours;
        this.lastSalaryIncreaseDate = lastSalaryIncreaseDate;
    }

    public LocalDate getLastSalaryIncreaseDate() {
        return lastSalaryIncreaseDate;
    }

    public void setLastSalaryIncreaseDate(LocalDate date) {
        this.lastSalaryIncreaseDate = date;
    }

    // Tien thuong = luong co ban * he so thuong tu Evaluation
    public double calculateBonus(Evaluation evaluation) {
        return baseSalary * evaluation.getBonusRate();
    }

    // Gio lam viec chuan trong thang: 26 ngay x 8 gio = 208 gio.
    // Tien tang ca duoc tinh bang 150% don gia luong mot gio.
    public double calculateOvertimePay() {
        final int STANDARD_WORKING_HOURS = 208;
        final double OVERTIME_RATE = 1.5;

        double hourlySalary = baseSalary / STANDARD_WORKING_HOURS;
        return hourlySalary * OVERTIME_RATE * overtimeHours;
    }

    public double calculateNetSalary() {
        return baseSalary + bonus + allowance + calculateOvertimePay()
                - deduction - taxAmount - insuranceAmount;
    }

    public void displayPayroll() {
        System.out.println("Thang: " + month + "/" + year);
        System.out.println("Luong co ban: " + baseSalary);
        System.out.println("Tien thuong: " + bonus);
        System.out.println("Phu cap: " + allowance);
        System.out.println("Khau tru: " + deduction);
        System.out.println("Tien thue: " + taxAmount);
        System.out.println("Tien bao hiem: " + insuranceAmount);
        System.out.println("So gio lam: " + workedHours);
        System.out.println("So gio tang ca: " + overtimeHours);
        System.out.println("Tien luong tang ca: " + calculateOvertimePay());
        System.out.println("Tang luong gan nhat: "
                + (lastSalaryIncreaseDate == null ? "Chua co" : lastSalaryIncreaseDate));
        System.out.println("Luong thuc nhan: " + calculateNetSalary());
    }
}
