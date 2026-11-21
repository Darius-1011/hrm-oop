import java.time.YearMonth;

public class Evaluation {
    private YearMonth month;
    private String comment;
    private double workScore;
    private double attitudeScore;
    private double disciplineScore;
    private double workQuality;
    private double productivity;
    private double teamWork;

    public Evaluation() {
        this.month = null;
        this.comment = null;
        this.workScore = 0;
        this.attitudeScore = 0;
        this.disciplineScore = 0;
        this.workQuality = 0;
        this.productivity = 0;
        this.teamWork = 0;
    }

    public Evaluation(YearMonth month, String comment,
            double workScore,
            double attitudeScore,
            double disciplineScore,
            double workQuality,
            double productivity,
            double teamWork) {
        this.month = month;
        this.comment = comment;
        this.workScore = workScore;
        this.attitudeScore = attitudeScore;
        this.disciplineScore = disciplineScore;
        this.workQuality = workQuality;
        this.productivity = productivity;
        this.teamWork = teamWork;
    }

    public double getWorkQuality() {
        return workQuality;
    }

    public double getProductivity() {
        return productivity;
    }

    public double getTeamWork() {
        return teamWork;
    }

    public double calculateEvaluationScore() {
        return (workScore + attitudeScore + disciplineScore
                + workQuality + productivity + teamWork) / 6;
    }

    public double getBonusRate() {
        double score = calculateEvaluationScore();
        if (score >= 9)
            return 0.30;
        if (score >= 8)
            return 0.20;
        if (score >= 7)
            return 0.10;
        if (score >= 5)
            return 0.05;
        return 0.0;
    }

    public void displayEvaluation() {
        System.out.println("Tháng đánh giá: " + month);
        System.out.println("Nhận xét: " + comment);
        System.out.println("Điểm công việc: " + workScore);
        System.out.println("Điểm thái độ: " + attitudeScore);
        System.out.println("Điểm kỷ luật: " + disciplineScore);
        System.out.println("Chất lượng công việc: " + workQuality);
        System.out.println("Năng suất: " + productivity);
        System.out.println("Khả năng làm việc nhóm: " + teamWork);
        System.out.println("Điểm đánh giá trung bình: " + calculateEvaluationScore());
        System.out.println("Tỷ lệ thưởng: " + getBonusRate());
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getWorkScore() {
        return workScore;
    }

    public void setWorkScore(double workScore) {
        this.workScore = workScore;
    }

    public double getAttitudeScore() {
        return attitudeScore;
    }

    public void setAttitudeScore(double attitudeScore) {
        this.attitudeScore = attitudeScore;
    }

    public double getDisciplineScore() {
        return disciplineScore;
    }

    public void setDisciplineScore(double disciplineScore) {
        this.disciplineScore = disciplineScore;
    }

    public void setWorkQuality(double workQuality) {
        this.workQuality = workQuality;
    }

    public void setProductivity(double productivity) {
        this.productivity = productivity;
    }

    public void setTeamWork(double teamWork) {
        this.teamWork = teamWork;
    }
}
