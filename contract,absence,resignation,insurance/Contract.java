import java.time.LocalDate;

public class Contract {
    private LocalDate startDate;
    private LocalDate endDate;
    private ContracType type;
    private ContractStatus status;

    public Contract(LocalDate startDate, LocalDate endDate, ContractType type) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = ContractStatus.DRAFT;
    }

    public Contract(LocalDate startDate, LocalDate endDate, ContracType type, ContractStatus status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ContracType getType() {
        return type;
    }

    public void setType(ContracType type) {
        this.type = type;
    }

    public ContractStatus getStatus() {
        return status;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }
}