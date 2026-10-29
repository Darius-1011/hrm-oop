import java.time.LocalDate;

public class absence {
    private String reason;
    private LocalDate fromDate;
    private LocalDate toDate;
    private absencestatus status;
        public Absence(String reason, LocalDate fromDate, LocalDate toDate) {
        this.reason = reason;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.status = AbsenceStatus.PENDING;
    }

    public absence(String reason,LocalDate fromDate,LocalDate toDate,absencestatus status){
        this.reason =reason;
        this.fromDate =fromDate;
        this.toDate= toDate;
        this.status = status;
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
    public absencestatus getStatus() {
        return status;
    }
    public void setStatus(absencestatus status) {
        this.status = status;
    }
}