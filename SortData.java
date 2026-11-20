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

  public void sortByMonth(Evaluation[] evaluations, boolean ascending) {
    if (evaluations == null)
      return;
    int n = evaluations.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (evaluations[j] == null || evaluations[j + 1] == null)
          continue;
        YearMonth m1 = evaluations[j].getMonth() != null ? evaluations[j].getMonth() : null;
        YearMonth m2 = evaluations[j + 1].getMonth() != null ? evaluations[j + 1].getMonth() : null;
        if (ascending ? (m1.isAfter(m2)) : (m1.isBefore(m2))) {
          Evaluation tmp = evaluations[j];
          evaluations[j] = evaluations[j + 1];
          evaluations[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByAverageScore(Evaluation[] evaluations, boolean ascending) {
    if (evaluations == null)
      return;
    int n = evaluations.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (evaluations[j] == null || evaluations[j + 1] == null)
          continue;
        double s1 = evaluations[j].calculateEvaluationScore();
        double s2 = evaluations[j + 1].calculateEvaluationScore();
        boolean cond = ascending ? (s1 > s2) : (s1 < s2);
        if (cond) {
          Evaluation tmp = evaluations[j];
          evaluations[j] = evaluations[j + 1];
          evaluations[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByWorkScore(Evaluation[] evaluations, boolean ascending) {
    if (evaluations == null)
      return;
    int n = evaluations.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (evaluations[j] == null || evaluations[j + 1] == null)
          continue;
        double s1 = evaluations[j].getWorkScore();
        double s2 = evaluations[j + 1].getWorkScore();
        boolean cond = ascending ? (s1 > s2) : (s1 < s2);
        if (cond) {
          Evaluation tmp = evaluations[j];
          evaluations[j] = evaluations[j + 1];
          evaluations[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByAttitudeScore(Evaluation[] evaluations, boolean ascending) {
    if (evaluations == null)
      return;
    int n = evaluations.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (evaluations[j] == null || evaluations[j + 1] == null)
          continue;
        double s1 = evaluations[j].getAttitudeScore();
        double s2 = evaluations[j + 1].getAttitudeScore();
        boolean cond = ascending ? (s1 > s2) : (s1 < s2);
        if (cond) {
          Evaluation tmp = evaluations[j];
          evaluations[j] = evaluations[j + 1];
          evaluations[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByDisciplineScore(Evaluation[] evaluations, boolean ascending) {
    if (evaluations == null)
      return;
    int n = evaluations.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (evaluations[j] == null || evaluations[j + 1] == null)
          continue;
        double s1 = evaluations[j].getDisciplineScore();
        double s2 = evaluations[j + 1].getDisciplineScore();
        boolean cond = ascending ? (s1 > s2) : (s1 < s2);
        if (cond) {
          Evaluation tmp = evaluations[j];
          evaluations[j] = evaluations[j + 1];
          evaluations[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByWorkQuality(Evaluation[] evaluations, boolean ascending) {
    if (evaluations == null)
      return;
    int n = evaluations.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (evaluations[j] == null || evaluations[j + 1] == null)
          continue;
        double s1 = evaluations[j].getWorkQuality();
        double s2 = evaluations[j + 1].getWorkQuality();
        boolean cond = ascending ? (s1 > s2) : (s1 < s2);
        if (cond) {
          Evaluation tmp = evaluations[j];
          evaluations[j] = evaluations[j + 1];
          evaluations[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByProductivity(Evaluation[] evaluations, boolean ascending) {
    if (evaluations == null)
      return;
    int n = evaluations.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (evaluations[j] == null || evaluations[j + 1] == null)
          continue;
        double s1 = evaluations[j].getProductivity();
        double s2 = evaluations[j + 1].getProductivity();
        boolean cond = ascending ? (s1 > s2) : (s1 < s2);
        if (cond) {
          Evaluation tmp = evaluations[j];
          evaluations[j] = evaluations[j + 1];
          evaluations[j + 1] = tmp;
        }
      }
    }
  }

  public void sortByTeamWork(Evaluation[] evaluations, boolean ascending) {
    if (evaluations == null)
      return;
    int n = evaluations.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (evaluations[j] == null || evaluations[j + 1] == null)
          continue;
        double s1 = evaluations[j].getTeamWork();
        double s2 = evaluations[j + 1].getTeamWork();
        boolean cond = ascending ? (s1 > s2) : (s1 < s2);
        if (cond) {
          Evaluation tmp = evaluations[j];
          evaluations[j] = evaluations[j + 1];
          evaluations[j + 1] = tmp;
        }
      }
    }
  }
}
