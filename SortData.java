import java.time.YearMonth;

public class SortData {
  public void sortByMonth(Payroll[] payrolls, boolean ascending) {
    if (payrolls == null)
      return;
    int n = payrolls.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (payrolls[j] == null || payrolls[j + 1] == null)
          continue;
        YearMonth m1 = payrolls[j].getMonth();
        YearMonth m2 = payrolls[j + 1].getMonth();
        boolean cond = ascending ? (m1.isAfter(m2)) : (m1.isBefore(m2));
        if (cond) {
          Payroll tmp = payrolls[j];
          payrolls[j] = payrolls[j + 1];
          payrolls[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByBaseSalary(Payroll[] payrolls, boolean ascending) {
    if (payrolls == null)
      return;
    int n = payrolls.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (payrolls[j] == null || payrolls[j + 1] == null)
          continue;
        double s1 = payrolls[j].getBaseSalary();
        double s2 = payrolls[j + 1].getBaseSalary();
        boolean cond = ascending ? (s1 > s2) : (s1 < s2);
        if (cond) {
          Payroll tmp = payrolls[j];
          payrolls[j] = payrolls[j + 1];
          payrolls[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByBonus(Payroll[] payrolls, boolean ascending) {
    if (payrolls == null)
      return;
    int n = payrolls.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (payrolls[j] == null || payrolls[j + 1] == null)
          continue;
        double b1 = payrolls[j].getBonus();
        double b2 = payrolls[j + 1].getBonus();
        boolean cond = ascending ? (b1 > b2) : (b1 < b2);
        if (cond) {
          Payroll tmp = payrolls[j];
          payrolls[j] = payrolls[j + 1];
          payrolls[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByNetSalary(Payroll[] payrolls, boolean ascending) {
    if (payrolls == null)
      return;
    int n = payrolls.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (payrolls[j] == null || payrolls[j + 1] == null)
          continue;
        double n1 = payrolls[j].calculateNetSalary();
        double n2 = payrolls[j + 1].calculateNetSalary();
        boolean cond = ascending ? (n1 > n2) : (n1 < n2);
        if (cond) {
          Payroll tmp = payrolls[j];
          payrolls[j] = payrolls[j + 1];
          payrolls[j + 1] = tmp;
        }
      }
    }
  }
}
