public class Attendance {
    private String date;
    private int clockInHour;
    private int clockInMinute;
    private int clockOutHour;
    private int clockOutMinute;

    public Attendance(String date, int clockInHour,
            int clockInMinute, int clockOutHour,
            int clockOutMinute) {
        this.date = date;
        this.clockInHour = clockInHour;
        this.clockInMinute = clockInMinute;
        this.clockOutHour = clockOutHour;
        this.clockOutMinute = clockOutMinute;
    }

    public double calculateWorkedHours() {
        int totalClockInMinutes = clockInHour * 60 + clockInMinute;
        int totalClockOutMinutes = clockOutHour * 60 + clockOutMinute;
        int workedMinutes = totalClockOutMinutes - totalClockInMinutes;
        return workedMinutes / 60.0;
    }

    public void displayAttendance() {
        System.out.println("Ngay: " + date);
        System.out.println("Gio vao: " + clockInHour + ":" + clockInMinute);
        System.out.println("Gio ra: " + clockOutHour + ":" + clockOutMinute);
        System.out.println("So gio lam: " + calculateWorkedHours());
    }
}
