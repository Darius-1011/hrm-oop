import java.time.LocalDate;
import java.time.YearMonth;

public class Payroll {
    private YearMonth month;
    private double baseSalary;
    private double bonus;
    private double allowance;
    private double deduction;
    private double taxAmount;
    private double insuranceAmount;
    private int workedHours;
    private int overtimeHours;
    private double overtimePay;
    private LocalDate lastSalaryIncreaseDate;
    private double netSalary;

    public Payroll() {
        this.month = null;
        this.baseSalary = 0;
        this.bonus = 0;
        this.allowance = 0;
        this.deduction = 0;
        this.taxAmount = 0;
        this.insuranceAmount = 0;
        this.workedHours = 0;
        this.overtimeHours = 0;
        this.overtimePay = 0;
        this.lastSalaryIncreaseDate = null;
        this.netSalary = 0;
    }

    public Payroll(YearMonth month, double baseSalary,
            double bonus, double allowance,
            double deduction, double taxAmount,
            double insuranceAmount, int workedHours,
            int overtimeHours,
            LocalDate lastSalaryIncreaseDate) {
        this.month = month;
        this.baseSalary = baseSalary;
        this.bonus = bonus;
        this.allowance = allowance;
        this.deduction = deduction;
        this.taxAmount = taxAmount;
        this.insuranceAmount = insuranceAmount;
        this.workedHours = workedHours;
        this.overtimeHours = overtimeHours;
        this.overtimePay = calculateOvertimePay();
        this.lastSalaryIncreaseDate = lastSalaryIncreaseDate;
        this.netSalary = calculateNetSalary();
    }

    public LocalDate getLastSalaryIncreaseDate() {
        return lastSalaryIncreaseDate;
    }

    public void setLastSalaryIncreaseDate(LocalDate date) {
        this.lastSalaryIncreaseDate = date;
    }

    // Tiền thưởng = lương cơ bản * hệ số thưởng từ Evaluation
    public double calculateBonus(Evaluation evaluation) {
        return baseSalary * evaluation.getBonusRate();
    }

    // Giờ làm việc tiêu chuẩn trong tháng: 26 ngày x 8 giờ = 208 giờ.
    // Tiền tăng ca được tính bằng 150% đơn giá lương một giờ.
    public double calculateOvertimePay() {
        final int STANDARD_WORKING_HOURS = 208;
        final double OVERTIME_RATE = 1.5;

        double hourlySalary = baseSalary / STANDARD_WORKING_HOURS;
        overtimePay = hourlySalary * OVERTIME_RATE * overtimeHours;
        return overtimePay;
    }

    public double calculateNetSalary() {
        netSalary = baseSalary + bonus + allowance + calculateOvertimePay()
                - deduction - taxAmount - insuranceAmount;
        return netSalary;
    }

    public String getPayroll() {
        String content = "";
        content += ("Tháng: " + month.getMonthValue() + "/" + month.getYear()) + "\n";
        content += ("Lương cơ bản: " + baseSalary) + "\n";
        content += ("Tiền thưởng: " + bonus) + "\n";
        content += ("Phụ cấp: " + allowance) + "\n";
        content += ("Khấu trừ: " + deduction) + "\n";
        content += ("Tiền thuế: " + taxAmount) + "\n";
        content += ("Tiền bảo hiểm: " + insuranceAmount) + "\n";
        content += ("Số giờ làm việc: " + workedHours) + "\n";
        content += ("Số giờ tăng ca: " + overtimeHours) + "\n";
        content += ("Tiền lương tăng ca: " + calculateOvertimePay()) + "\n";
        content += ("Ngày tăng lương gần nhất: "
                + (lastSalaryIncreaseDate == null ? "Chưa có" : lastSalaryIncreaseDate)) + "\n";
        content += ("Lương thực nhận: " + calculateNetSalary()) + "\n";

        return content;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public int getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(int overtimeHours) {
        this.overtimeHours = overtimeHours;
    }
}
