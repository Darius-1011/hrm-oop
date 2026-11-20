import java.time.LocalDate;

public class Absence {
    public enum AbsenceStatus {
        PENDING,
        REJECTED,
        APPROVED
    }

    private String reason;
    private LocalDate fromDate;
    private LocalDate toDate;
    private AbsenceStatus status;

    public Absence() {
        this.reason = "";
        this.fromDate = null;
        this.toDate = null;
        this.status = null;
    }

    public Absence(String reason, LocalDate fromDate, LocalDate toDate) {
        this.reason = reason;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = AbsenceStatus.PENDING;
    }

    public Absence(String reason, LocalDate fromDate, LocalDate toDate, AbsenceStatus status) {
        this.reason = reason;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = status;
    }

    public static void sortByFromDate(Absence[] absences, boolean ascending) {
        if (absences == null)
            return;
        int n = absences.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (absences[j] == null || absences[j + 1] == null)
                    continue;
                if (absences[j].getFromDate() == null || absences[j + 1].getFromDate() == null)
                    continue;
                int cmp = absences[j].getFromDate().compareTo(absences[j + 1].getFromDate());
                if (ascending ? cmp > 0 : cmp < 0) {
                    Absence tmp = absences[j];
                    absences[j] = absences[j + 1];
                    absences[j + 1] = tmp;
                }
            }
        }
    }

    public static void sortByToDate(Absence[] absences, boolean ascending) {
        if (absences == null)
            return;
        int n = absences.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (absences[j] == null || absences[j + 1] == null)
                    continue;
                if (absences[j].getToDate() == null || absences[j + 1].getToDate() == null)
                    continue;
                int cmp = absences[j].getToDate().compareTo(absences[j + 1].getToDate());
                if (ascending ? cmp > 0 : cmp < 0) {
                    Absence tmp = absences[j];
                    absences[j] = absences[j + 1];
                    absences[j + 1] = tmp;
                }
            }
        }
    }

    public static void sortByReason(Absence[] absences, boolean ascending) {
        if (absences == null)
            return;
        int n = absences.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (absences[j] == null || absences[j + 1] == null)
                    continue;
                String r1 = absences[j].getReason() != null ? absences[j].getReason() : "";
                String r2 = absences[j + 1].getReason() != null ? absences[j + 1].getReason() : "";
                int cmp = r1.compareToIgnoreCase(r2);
                if (ascending ? cmp > 0 : cmp < 0) {
                    Absence tmp = absences[j];
                    absences[j] = absences[j + 1];
                    absences[j + 1] = tmp;
                }
            }
        }
    }

    public static void sortByStatus(Absence[] absences, boolean ascending) {
        if (absences == null)
            return;
        int n = absences.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (absences[j] == null || absences[j + 1] == null)
                    continue;
                String s1 = absences[j].getStatus() != null ? absences[j].getStatus().name() : "";
                String s2 = absences[j + 1].getStatus() != null ? absences[j + 1].getStatus().name() : "";
                int cmp = s1.compareToIgnoreCase(s2);
                if (ascending ? cmp > 0 : cmp < 0) {
                    Absence tmp = absences[j];
                    absences[j] = absences[j + 1];
                    absences[j + 1] = tmp;
                }
            }
        }
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public AbsenceStatus getStatus() {
        return status;
    }

    public void setStatus(AbsenceStatus status) {
        this.status = status;
    }
}
