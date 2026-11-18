import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdminAccount extends Account {
    private final String DEFAULT_PASSWORD = "Password123@";

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private static int validateInt(String str) {
        if (str.matches("[0-9]+")) {
            return Integer.parseInt(str);
        }

        return -1;
    }

    public AdminAccount(String username, String password, AccountStatus status) {
        super(username, password, status);
    }

    @Override
    public void getInfo(Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String result = "";
        result += "Username: " + this.getUsername() + "\n";
        result += "Status: " + this.getStatus() + "\n";

        console.printResult("Employee Information", result, sc);
    }

    public void addEmployee(Employee[] employees, Employee employee, Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Thêm nhân viên";

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;

                String result = "Da them nhan vien: " + employee.getId()
                        + " - " + employee.getFirstName() + " " + employee.getLastName();
                console.printResult(title, result, sc);
                return;
            }
        }

        console.printResult(title, "Mang nhan vien da day, khong them duoc.", sc);
    }

    public void addEmployeeAccount(Account[] accounts, Employee emp) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i].setUsername(emp.getId());
                accounts[i].setPassword(DEFAULT_PASSWORD);
                accounts[i].setStatus(Account.AccountStatus.ACTIVE);
            }
        }
    }

    public void listEmployee(Account[] accounts, Scanner sc) {
        System.out.println("Danh sach tai khoan:");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                System.out.print((i + 1) + ". ");
                accounts[i].getInfo(sc);
            }
        }
    }

    public void removeEmployee(Employee[] employees, String id, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Remove Employee";

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId().equals(id)) {
                employees[i] = null;
                console.printResult(title, "Da xoa tai khoan: " + id, sc);
                return;
            }
        }

        console.printResult(title, "Khong tim thay nhan vien: " + id, sc);
    }

    public void removeAccount(Account[] accounts, String id, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Remove Account";

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getUsername().equals(id)) {
                accounts[i] = null;
                console.printResult(title, "Da xoa tai khoan: " + id, sc);
                return;
            }
        }

        console.printResult(title, "Khong tim thay tai khoan: " + id, sc);
    }

    public void replyRequest(Account[] accounts, String username, String reply, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Reply Request";

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getUsername().equals(username)) {
                console.printResult(title, "Admin phan hoi " + username + ": " + reply, sc);
                return;
            }
        }
        console.printResult(title, "Khong tim thay tai khoan: " + username, sc);
    }

    public Employee searchEmployee(Employee[] employees, String id) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId().equals(id)) {
                return employees[i];
            }
        }
        return null;
    }

    public String getPositions(Position[] positions) {
        final int TABLE_WIDTH = 109;
        final int NUMBER_WIDTH = 3;
        final int NAME_WIDTH = 30;
        final int POSITION_COEFFICIENT_WIDTH = 25;
        final int RESPONSIBILITY_WIDTH = TABLE_WIDTH - NUMBER_WIDTH - NAME_WIDTH - POSITION_COEFFICIENT_WIDTH -
                (3 * 2 - 2);

        int nameLeftPadding = (NAME_WIDTH - 4) / 2;
        int positionCoefficientLeftPadding = (POSITION_COEFFICIENT_WIDTH - 20) / 2;
        int responsibilityLeftPadding = (RESPONSIBILITY_WIDTH - 16) / 2;
        int nameRightPadding = NAME_WIDTH - nameLeftPadding - 4;
        int positionCoefficientRightPadding = POSITION_COEFFICIENT_WIDTH - positionCoefficientLeftPadding - 20;
        int responsibilityRightPadding = RESPONSIBILITY_WIDTH - responsibilityLeftPadding - 16;

        String header = "| " + " ".repeat(NUMBER_WIDTH) + " | " +
                " ".repeat(nameLeftPadding) + "Name" + " ".repeat(nameRightPadding) + " | " +
                " ".repeat(positionCoefficientLeftPadding) + "Position coefficient"
                + " ".repeat(positionCoefficientRightPadding)
                + " | " +
                " ".repeat(responsibilityLeftPadding) + "Responsibilities" + " ".repeat(responsibilityRightPadding)
                + " |\n";

        String separateLine = "| " + "-".repeat(NUMBER_WIDTH) + " | " +
                "-".repeat(NAME_WIDTH) + " | " +
                "-".repeat(POSITION_COEFFICIENT_WIDTH) + " | " +
                "-".repeat(RESPONSIBILITY_WIDTH) + " |\n";

        String content = "";

        content += "Danh sach vi tri\n\n";
        content += header;
        content += separateLine;
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] != null) {
                Position current = positions[i];

                int numberLength = Integer.toString(i).length();
                int nameLength = current.getName().length();
                int positionCoefficientLength = Double.toString(current.getPositionCoefficient()).length();
                int responsibilityLength = current.getResponsibilities().length();

                int numberPadding = NUMBER_WIDTH - numberLength;
                int namePadding = NAME_WIDTH - nameLength;
                int positionCoefficientPadding = POSITION_COEFFICIENT_WIDTH - positionCoefficientLength;
                int responsibilityPadding = RESPONSIBILITY_WIDTH - responsibilityLength;

                content += "| " + " ".repeat(numberPadding) + (i + 1) + " | " +
                        current.getName() + " ".repeat(namePadding) + " | " +
                        " ".repeat(positionCoefficientPadding) + current.getPositionCoefficient() + " | " +
                        current.getResponsibilities() + " ".repeat(responsibilityPadding) + " |\n";
            }
        }

        return content;
    }

    public String getDepartments(Department[] departments) {
        final int TABLE_WIDTH = 109;
        final int NUMBER_WIDTH = 3;
        final int NAME_WIDTH = 30;
        final int PHONE_WIDTH = 11;
        final int POSITION_WIDTH = TABLE_WIDTH - NUMBER_WIDTH - NAME_WIDTH - PHONE_WIDTH -
                (3 * 2 - 2);

        int nameLeftPadding = (NAME_WIDTH - 4) / 2;
        int phoneLeftPadding = (PHONE_WIDTH - 5) / 2;
        int positionLeftPadding = (POSITION_WIDTH - 8) / 2;
        int nameRightPadding = NAME_WIDTH - nameLeftPadding - 4;
        int phoneRightPadding = PHONE_WIDTH - phoneLeftPadding - 5;
        int positionRightPadding = POSITION_WIDTH - positionLeftPadding - 8;

        String header = "| " + " ".repeat(NUMBER_WIDTH) + " | " +
                " ".repeat(nameLeftPadding) + "Name" + " ".repeat(nameRightPadding) + " | " +
                " ".repeat(phoneLeftPadding) + "Phone" + " ".repeat(phoneRightPadding) + " | " +
                " ".repeat(positionLeftPadding) + "Position" + " ".repeat(positionRightPadding)
                + " |\n";

        String separateLine = "| " + "-".repeat(NUMBER_WIDTH) + " | " +
                "-".repeat(NAME_WIDTH) + " | " +
                "-".repeat(PHONE_WIDTH) + " | " +
                "-".repeat(POSITION_WIDTH) + " |\n";

        String content = "";

        content += "Danh sach phong ban\n\n";
        content += header;
        content += separateLine;
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null) {
                Department current = departments[i];

                int numberLength = Integer.toString(i).length();
                int nameLength = current.getName().length();
                int phoneLength = current.getPhone().length();
                int positionLength = current.getPosition().length();

                int numberPadding = NUMBER_WIDTH - numberLength;
                int namePadding = NAME_WIDTH - nameLength;
                int phonePadding = PHONE_WIDTH - phoneLength;
                int positionPadding = POSITION_WIDTH - positionLength;

                content += "| " + " ".repeat(numberPadding) + (i + 1) + " | " +
                        current.getName() + " ".repeat(namePadding) + " | " +
                        " ".repeat(phonePadding) + current.getPhone() + " | " +
                        current.getPosition() + " ".repeat(positionPadding) + " |\n";
            }
        }

        return content;
    }

    private void editEmployeeHireDate(Scanner sc, Employee employee) {
        ConsoleUI console = new ConsoleUI();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String title = "Edit Employee";
        String subtitle = "Edit Hire Date";
        String content = "Current hire date: " + employee.getHireDate();
        String input;

        input = "Hire date (dd/MM/yyyy)";
        LocalDate hireDate = LocalDate.parse(console.printUI(title, subtitle, content, input, sc), dateFormatter);
        employee.setHireDate(hireDate);

        console.printResult(title, "Set hire date successfully", sc);
    }

    private void editEmployeeSpecialization(Scanner sc, Employee employee) {
        ConsoleUI console = new ConsoleUI();

        String title = "Edit Employee";
        String subtitle = "Set Specialization";
        String content = "Current specialization: " + employee.getSpecialization();

        String input = "Specialization";
        employee.setSpecialization(console.printUI(title, subtitle, content, input, sc));

        console.printResult(title, "Set specialization successfully", sc);
    }

    private void editEmployeeStatus(Scanner sc, Employee employee) {
        ConsoleUI console = new ConsoleUI();

        String title = "Edit Employee";
        String subtitle = "Set Status";
        String content = "Current status: " + employee.getStatus();

        int statusNum = 0;
        do {
            String[] options = { "Working", "On leave", "Resigned" };
            statusNum = Integer.parseInt(console.printUI(title, subtitle, content, options, sc));
        } while (statusNum < 0 || 2 < statusNum);

        switch (statusNum) {
            case 1:
                employee.setStatus(Employee.EmployeeStatus.WORKING);
                break;
            case 2:
                employee.setStatus(Employee.EmployeeStatus.ON_LEAVE);
                break;
            case 3:
                employee.setStatus(Employee.EmployeeStatus.RESIGNED);
                break;

            default:
                break;
        }

        console.printResult(title, "Set status successfully", sc);
    }

    private void editEmployeeTaxId(Scanner sc, Employee employee) {
        ConsoleUI console = new ConsoleUI();

        String title = "Edit Employee";
        String subtitle = "Set Tax Id";
        String content = "Current tax id: " + employee.getTaxId();

        String input = "Tax id";
        employee.setTaxId(console.printUI(title, subtitle, content, input, sc));

        console.printResult(title, "Set tax id successfully", sc);
    }

    private void editEmployeePosition(Scanner sc, Employee employee, Position[] positions) {
        ConsoleUI console = new ConsoleUI();

        String title = "Edit Employee";
        String subtitle = "Set Position";
        String content = "Current position: " + employee.getPosition().getName() + "\n";

        int choice;
        do {
            content += getPositions(positions);
            choice = Integer.parseInt(console.printUI(title, subtitle, content, sc));

            if (positions[choice - 1] == null) {
                console.printResult(title, "Position not exist", sc);
            }
        } while (positions[choice - 1] == null);

        console.printResult(title, "Set position successfully", sc);
    }

    private void editEmployeeDepartment(Scanner sc, Employee employee, Department[] departments) {
        ConsoleUI console = new ConsoleUI();

        String title = "Edit Employee";
        String subtitle = "Set Department";
        String content = "Current department: " + employee.getDepartment().getName() + "\n";

        int choice;
        do {
            content += getDepartments(departments);
            choice = Integer.parseInt(console.printUI(title, subtitle, content, sc));

            if (departments[choice - 1] == null) {
                console.printResult(title, "Department not exist", sc);
            }
        } while (departments[choice - 1] == null);

        console.printResult(title, "Set department successfully", sc);
    }

    public void editEmployee(Scanner sc, Employee[] employees, String id, Position[] positions,
            Department[] departments) {
        ConsoleUI console = new ConsoleUI();
        String title = "Edit Employee";
        String subtitle = "Choose an option";

        Employee employee = this.searchEmployee(employees, id);
        if (employee == null) {
            console.printResult(title, "Khong tim thay nhan vien: " + id, sc);
            return;
        }

        int setChoice;
        do {
            String[] options = { "Exit", "Hire date", "Specialization", "Status", "Tax id", "Position", "Department" };
            setChoice = Integer.parseInt(console.printUI(title, subtitle, options, sc));

            switch (setChoice) {
                case 0:
                    break;
                case 1:
                    editEmployeeHireDate(sc, employee);
                    break;
                case 2:
                    editEmployeeSpecialization(sc, employee);
                    break;
                case 3:
                    editEmployeeStatus(sc, employee);
                    break;
                case 4:
                    editEmployeeTaxId(sc, employee);
                    break;
                case 5:
                    editEmployeePosition(sc, employee, positions);
                    break;
                case 6:
                    editEmployeeDepartment(sc, employee, departments);
                    break;

                default:
                    console.printResult(title, "Invalid choice", sc);
                    break;
            }
        } while (setChoice != 0);

        console.printResult(title, "Da sua nhan vien: " + id, sc);
    }

    public void printEmployees(Employee[] employees, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Remove Employee";
        String subtitle = "";
        String content = "";

        content += "Danh sach nhan vien:\n";
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                content += ((i + 1) + ". " + employees[i].getId()
                        + " | " + employees[i].getFirstName() + " " + employees[i].getLastName()
                        + " | " + employees[i].getDepartment().getName()
                        + " | " + employees[i].getStatus()) + "\n";
            }
        }

        console.printResult(title, content, sc);
    }

    public String printPayrolls(Employee[] employees, String id, Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Print Payrolls";
        String content = "";

        Employee employee = this.searchEmployee(employees, id);
        if (employee == null) {
            return "Khong tim thay nhan vien: " + id;
        }

        content += "Bang luong cua " + employee.getId() + ":\n";

        Payroll[] payrolls = employee.getPayrolls();
        boolean found = false;
        for (int i = 0; i < payrolls.length; i++) {
            if (payrolls[i] != null) {
                content += payrolls[i].getPayroll();
                found = true;
            }
        }
        if (!found) {
            return "Chua co bang luong";
        }

        return content;
    }

    public void searchEmployee(Employee[] employees, Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Search Employee";
        String subtitle = "";
        String content = "";
        String input = "Id";

        String id = console.printUI(title, subtitle, content, input, sc).toLowerCase();

        boolean found = false;
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null && employees[i].getId().toLowerCase().equals(id)) {
                content = employees[i].getInfo(sc);
                found = true;
            }
        }

        if (found) {
            console.printResult(title, content, sc);
        } else {
            console.printResult(title, "Employee not exists", sc);
        }
    }

    public void searchDepartment(Department[] departments, Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Search departments";
        String subtitle = "";
        String content = "";
        String input = "Id";

        String name = console.printUI(title, subtitle, content, input, sc).toLowerCase();

        boolean found = false;
        for (int i = 0; i < departments.length; i++) {
            if (departments[i] != null && departments[i].getName().toLowerCase().equals(name)) {
                content = departments[i].getInfo(sc);
                found = true;
            }
        }

        if (found) {
            console.printResult(title, content, sc);
        } else {
            console.printResult(title, "Department not exists", sc);
        }
    }

    public Account createAccount(Scanner sc) {
        System.out.print("\033[H\033[2J");
        System.out.println("Create Account");
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        int statusNum = 0;
        Account.AccountStatus status = Account.AccountStatus.ACTIVE;
        do {
            System.out.println("[1] Active");
            System.out.println("[2] Inactive");
            System.out.println("[3] Suspended");
            System.out.println("[4] Pending");
            System.out.println("[5] Locked");
            System.out.println("[6] Terminated");
            System.out.print("Status:");

            statusNum = Integer.parseInt(sc.nextLine());
        } while (statusNum < 1 || 6 < statusNum);

        switch (statusNum) {
            case 1:
                status = Account.AccountStatus.ACTIVE;
                break;
            case 2:
                status = Account.AccountStatus.INACTIVE;
                break;
            case 3:
                status = Account.AccountStatus.SUSPENDED;
                break;
            case 4:
                status = Account.AccountStatus.PENDING;
                break;
            case 5:
                status = Account.AccountStatus.LOCKED;
                break;
            case 6:
                status = Account.AccountStatus.TERMINATED;
                break;

            default:
                break;
        }

        int type;
        do {
            System.out.println("[1] Admin");
            System.out.println("[2] Employee");
            System.out.print("Choose: ");
            type = Integer.parseInt(sc.nextLine());
        } while (type != 1 && type != 2);

        if (type == 1) {
            return new AdminAccount(username, password, status);
        } else {
            return new EmployeeAccount(username, password, status);
        }
    }

    public Department createDepartment(Scanner sc, Department[] departments) {
        ConsoleUI console = new ConsoleUI();
        String title = "Create Department";
        String subtitle = "";
        String content = "";

        String input = "Name";
        String name = console.printUI(title, subtitle, subtitle, input, sc);
        content += "Name: " + name + "\n";

        input = "Phone";
        String phone = console.printUI(title, subtitle, content, input, sc);
        content += "Phone: " + phone + "\n";

        input = "Position";
        String position = console.printUI(title, subtitle, content, input, sc);
        content += "Position: " + position + "\n";

        console.printResult(title, content, sc);

        return new Department(name, phone, position);
    }

    public Position createPosition(Scanner sc, Position[] positions) {
        ConsoleUI console = new ConsoleUI();
        String title = "Create Position";
        String subtitle = "";
        String content = "";

        String input = "Name";
        String name = console.printUI(title, subtitle, subtitle, input, sc);
        content += "Name: " + name + "\n";

        input = "Position coefficient";
        double positionCoefficient = Double.parseDouble(console.printUI(title, subtitle, content, input, sc));
        content += "Position coefficient: " + positionCoefficient + "\n";

        input = "Responsibilities";
        String responsibilities = console.printUI(title, subtitle, content, input, sc);
        content += "Responsibilities: " + responsibilities + "\n";

        console.printResult(title, content, sc);

        return new Position(responsibilities, name, positionCoefficient);
    }

    public Address createAddress(Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Address";
        String[] options = {};
        String input;

        input = "Number";
        String number = console.printUI(title, subtitle, options, input, sc);

        input = "Street";
        String street = console.printUI(title, subtitle, options, input, sc);

        input = "Ward";
        String ward = console.printUI(title, subtitle, options, input, sc);

        input = "District";
        String district = console.printUI(title, subtitle, options, input, sc);

        input = "City";
        String city = console.printUI(title, subtitle, options, input, sc);

        return new Address(number, street, ward, district, city);
    }

    public Evaluation createEvaluation(Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Evaluation";
        String[] options = {};
        String input;

        input = "Month: ";
        String month = console.printUI(title, subtitle, options, input, sc);

        input = "Comment";
        String comment = console.printUI(title, subtitle, options, input, sc);

        input = "Work score: ";
        double workScore = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Attitude score: ";
        double attitudeScore = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Discipline score: ";
        double disciplineScore = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Work quality: ";
        double workQuality = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Productivity: ";
        double productivity = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Team work: ";
        double teamWork = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        return new Evaluation(month, comment, workScore, attitudeScore, disciplineScore, workQuality, productivity,
                teamWork);
    }

    public Contract createContract(Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Contracts";
        String[] options = {};
        String input;

        LocalDate startDate;
        LocalDate endDate;
        do {
            input = "Start date: ";
            startDate = LocalDate.parse(console.printUI(title, subtitle, options, input, sc), dateFormatter);

            input = "End date: ";
            endDate = LocalDate.parse(console.printUI(title, subtitle, options, input, sc), dateFormatter);

            if (startDate.isAfter(endDate)) {
                System.out.println("Start date should be before end date");
            }
        } while (startDate.isBefore(endDate));

        int typeNum = 0;
        Contract.ContractType type = Contract.ContractType.TEMPORARY;
        do {
            String[] typeOptions = { "Probation", "Full time", "Part time", "Temporary", "Fixed term", "Permanent" };
            typeNum = validateInt(console.printUI(title, subtitle, typeOptions, input, sc));

            if (typeNum < 0 || 5 < typeNum) {
                console.printResult(title, "Invalid choice", sc);
            }
        } while (typeNum < 0 || 5 < typeNum);

        switch (typeNum) {
            case 0:
                type = Contract.ContractType.PROBATION;
                break;
            case 1:
                type = Contract.ContractType.FULL_TIME;
                break;
            case 2:
                type = Contract.ContractType.PART_TIME;
                break;
            case 3:
                type = Contract.ContractType.TEMPORARY;
                break;
            case 4:
                type = Contract.ContractType.FIXED_TERM;
                break;
            case 5:
                type = Contract.ContractType.PERMANENT;
                break;

            default:
                break;
        }

        int statusNum = 0;
        Contract.ContractStatus status = Contract.ContractStatus.ACTIVE;
        do {
            String[] statusOptions = { "Draft", "Active", "Expired" };
            statusNum = validateInt(console.printUI(title, subtitle, statusOptions, input, sc));

            if (statusNum < 0 || 2 < statusNum) {
                console.printResult(title, "Invalid choice", sc);
            }
        } while (statusNum < 0 || 2 < statusNum);

        switch (statusNum) {
            case 0:
                status = Contract.ContractStatus.DRAFT;
                break;
            case 1:
                status = Contract.ContractStatus.ACTIVE;
                break;
            case 2:
                status = Contract.ContractStatus.EXPIRED;
                break;

            default:
                break;
        }

        return new Contract(startDate, endDate, type, status);
    }

    public Payroll createPayroll(Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Payrolls";
        String[] options = {};
        String input;

        input = "Month: ";
        int month = validateInt(console.printUI(title, subtitle, options, input, sc));

        input = "Year: ";
        int year = validateInt(console.printUI(title, subtitle, options, input, sc));

        input = "Base Salary";
        double baseSalary = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Bonus: ";
        double bonus = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Allowance: ";
        double allowance = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Deduction: ";
        double deduction = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Tax amount: ";
        double taxAmount = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Insurance amount: ";
        double insuranceAmount = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        input = "Worked hours: ";
        int workedHours = validateInt(console.printUI(title, subtitle, options, input, sc));

        input = "Overtime hours: ";
        int overtimeHours = validateInt(console.printUI(title, subtitle, options, input, sc));

        input = "Last salary increase (dd/MM/yyyy): ";
        LocalDate lastSalaryIncreaseDate = LocalDate.parse(console.printUI(title, subtitle, options, input, sc),
                dateFormatter);

        return new Payroll(month, year, baseSalary, bonus, allowance, deduction, taxAmount, insuranceAmount,
                workedHours,
                overtimeHours, lastSalaryIncreaseDate);
    }

    public Attendance createAttendance(Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Attendance";
        String[] options = {};
        String input;

        input = "Date (dd/MM/yyyy): ";
        LocalDate date = LocalDate.parse(console.printUI(title, subtitle, options, input, sc), dateFormatter);

        LocalTime clockIn;
        LocalTime clockOut;
        do {
            input = "Clock in (HH:mm): ";
            clockIn = LocalTime.parse(console.printUI(title, subtitle, options, input, sc), timeFormatter);

            input = "Clock out (HH:mm): ";
            clockOut = LocalTime.parse(console.printUI(title, subtitle, options, input, sc), timeFormatter);

            if (clockIn.isAfter(clockOut)) {
                console.printResult(title, "Clock in time should be before clock out time", sc);
            }
        } while (clockIn.isBefore(clockOut));

        return new Attendance(date, clockIn, clockOut);
    }

    public Insurance createInsurance(Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Insurance";
        String[] options = {};
        String input;

        input = "Insurance number: ";
        String insuranceNumber = console.printUI(title, subtitle, options, input, sc);

        int typeNum;
        Insurance.InsuranceType type = Insurance.InsuranceType.HEALTH;
        do {
            String[] typeOptions = { "Life", "Health", "Social", "Unemployment" };
            typeNum = validateInt(console.printUI(title, subtitle, typeOptions, sc));
        } while (typeNum < 0 || 3 < typeNum);

        switch (typeNum) {
            case 0:
                type = Insurance.InsuranceType.LIFE;
                break;
            case 1:
                type = Insurance.InsuranceType.HEALTH;
                break;
            case 2:
                type = Insurance.InsuranceType.SOCIAL;
                break;
            case 3:
                type = Insurance.InsuranceType.UNEMPLOYMENT;
                break;

            default:
                break;
        }

        LocalDate startDate;
        LocalDate endDate;
        do {
            input = "Start date (dd/MM/yyyy): ";
            startDate = LocalDate.parse(console.printUI(title, subtitle, options, input, sc), dateFormatter);

            input = "End date (dd/MM/yyyy): ";
            endDate = LocalDate.parse(console.printUI(title, subtitle, options, input, sc), dateFormatter);

            if (startDate.isAfter(endDate)) {
                console.printResult(title, "Start date should be before end date", sc);
            }
        } while (startDate.isBefore(endDate));

        input = "Rate: ";
        double rate = Double.parseDouble(console.printUI(title, subtitle, options, input, sc));

        return new Insurance(insuranceNumber, type, startDate, endDate, rate);
    }

    public Employee.EmployeeStatus chooseEmployeeStatus(Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "";
        String[] options = { "Working", "On leave", "Resigned" };

        int statusChoice;
        do {
            statusChoice = validateInt(console.printUI(title, subtitle, options, sc));

            if (statusChoice < 0 || 2 < statusChoice) {
                console.printResult(title, "Invalid choice", sc);
            }
        } while (statusChoice < 0 || 2 < statusChoice);

        switch (statusChoice) {
            case 0:
                return Employee.EmployeeStatus.WORKING;
            case 1:
                return Employee.EmployeeStatus.ON_LEAVE;
            case 2:
                return Employee.EmployeeStatus.RESIGNED;

            default:
                break;
        }

        return Employee.EmployeeStatus.RESIGNED;
    }

    public Evaluation[] createEvaluations(LimitHandler limits, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Evaluation";
        String[] options = { "Exit", "Create an evaluation" };

        int index = 0;
        Evaluation[] evaluations = new Evaluation[limits.getMaxEvaluations()];

        int choice;
        do {
            choice = validateInt(console.printUI(title, subtitle, options, sc));

            if (choice < 0 || 1 < choice) {
                console.printResult(title, "Invalid choice", sc);
                continue;
            }

            if (choice == 0) {
                break;
            }

            if (index + 1 < limits.getMaxEvaluations()) {
                String result = "Evaluation storage is full\n" + "Max: %d" + limits.getMaxEvaluations();
                console.printResult(title, result, sc);
            }

            evaluations[index] = createEvaluation(sc);
            index++;
        } while (choice < 0 || 1 < choice);

        return evaluations;
    }

    public Contract[] createContracts(LimitHandler limits, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Contracts";
        String[] options = { "Exit", "Create a contract" };

        int index = 0;
        Contract[] contracts = new Contract[limits.getMaxContracts()];

        int choice;
        do {
            choice = validateInt(console.printUI(title, subtitle, options, sc));

            if (choice < 0 || 1 < choice) {
                console.printResult(title, "Invalid choice", sc);
                continue;
            }

            if (choice == 0) {
                break;
            }

            if (index + 1 < limits.getMaxContracts()) {
                String result = "Contracts storage is full\n" + "Max: %d" + limits.getMaxContracts();
                console.printResult(title, result, sc);
            }

            contracts[index] = createContract(sc);
            index++;
        } while (choice < 0 || 1 < choice);

        return contracts;
    }

    public Payroll[] createPayrolls(LimitHandler limits, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Payrolls";
        String[] options = { "Exit", "Create a Payroll" };

        int index = 0;
        Payroll[] payrolls = new Payroll[limits.getMaxPayrolls()];

        int choice;
        do {
            choice = validateInt(console.printUI(title, subtitle, options, sc));

            if (choice < 0 || 1 < choice) {
                console.printResult(title, "Invalid choice", sc);
                continue;
            }

            if (choice == 0) {
                break;
            }

            if (index + 1 < limits.getMaxPayrolls()) {
                String result = "Payrolls storage is full\n" + "Max: %d" + limits.getMaxPayrolls();
                console.printResult(title, result, sc);
            }

            payrolls[index] = createPayroll(sc);
            index++;
        } while (choice < 0 || 1 < choice);

        return payrolls;
    }

    public Attendance[] createAttendances(LimitHandler limits, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Attendances";
        String[] options = { "Exit", "Create a attendance" };

        int index = 0;
        Attendance[] attendances = new Attendance[limits.getMaxAttendances()];

        int choice;
        do {
            choice = validateInt(console.printUI(title, subtitle, options, sc));

            if (choice < 0 || 1 < choice) {
                console.printResult(title, "Invalid choice", sc);
                continue;
            }

            if (choice == 0) {
                break;
            }

            if (index + 1 < limits.getMaxAttendances()) {
                String result = "Attendance storage is full\n" + "Max: %d" + limits.getMaxAttendances();
                console.printResult(title, result, sc);
            }

            attendances[index] = createAttendance(sc);
            index++;
        } while (choice < 0 || 1 < choice);

        return attendances;
    }

    public Resignation createResignation(Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Resignation";
        String[] options = {};
        String input;

        input = "Reason: ";
        String reason = sc.nextLine();

        input = "Date (dd/MM/yyyy): ";
        LocalDate date = LocalDate.parse(sc.nextLine(), dateFormatter);

        int statusNum;
        do {
            String[] statusOptions = { "Pending", "Rejected", "Approved" };
            statusNum = validateInt(console.printUI(title, subtitle, statusOptions, sc));

            if (statusNum < 0 || 2 < statusNum) {
                console.printResult(title, "Invalid choice", sc);
            }
        } while (statusNum < 0 || 2 < statusNum);

        Resignation.ResignationStatus resignationStatus = Resignation.ResignationStatus.PENDING;
        switch (statusNum) {
            case 0:
                resignationStatus = Resignation.ResignationStatus.PENDING;
                break;
            case 1:
                resignationStatus = Resignation.ResignationStatus.REJECTED;
                break;
            case 2:
                resignationStatus = Resignation.ResignationStatus.APPROVED;
                break;

            default:
                break;
        }

        return new Resignation(reason, date, resignationStatus);
    }

    public Resignation[] createResignations(LimitHandler limits, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Resignations";
        String[] options = { "Exit", "Create a resignation" };

        int index = 0;
        Resignation[] resignations = new Resignation[limits.getMaxResignations()];

        int choice;
        do {
            choice = validateInt(console.printUI(title, subtitle, options, sc));

            if (choice < 0 || 1 < choice) {
                console.printResult(title, "Invalid choice", sc);
                continue;
            }

            if (choice == 0) {
                break;
            }

            if (index + 1 < limits.getMaxResignations()) {
                String result = "Resignation storage is full\n" + "Max: %d" + limits.getMaxResignations();
                console.printResult(title, result, sc);
            }

            resignations[index] = createResignation(sc);
            index++;
        } while (choice < 0 || 1 < choice);

        return resignations;
    }

    public Insurance[] createInsurances(LimitHandler limits, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Create Insurances";
        String[] options = { "Exit", "Create a insurance" };

        int index = 0;
        Insurance[] insurances = new Insurance[limits.getMaxInsurances()];

        int choice;
        do {
            choice = validateInt(console.printUI(title, subtitle, options, sc));

            if (choice < 0 || 1 < choice) {
                console.printResult(title, "Invalid choice", sc);
                continue;
            }

            if (choice == 0) {
                break;
            }

            if (index + 1 < limits.getMaxInsurances()) {
                String result = "Insurance storage is full\n" + "Max: %d" + limits.getMaxInsurances();
                console.printResult(title, result, sc);
            }

            insurances[index] = createInsurance(sc);
            index++;
        } while (choice < 0 || 1 < choice);

        return insurances;
    }

    public Position choosePosition(DataHandler data, LimitHandler limits, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Choose Position";
        String[] options = { "Exit", "Choose a position" };

        int positionsOccupied = 0;
        for (int i = 0; i < data.getPositions().length; i++) {
            if (data.getPositions()[i] != null) {
                positionsOccupied++;
            }
        }

        if (positionsOccupied < 0) {
            console.printResult(title, "No positions available", sc);
            return null;
        }

        int index;
        do {
            String[] positionOptions = new String[limits.getMaxPositions()];

            for (int i = 0; i < data.getPositions().length; i++) {
                if (data.getPositions()[i] == null) {
                    break;
                }

                positionOptions[i] = data.getPositions()[i].getName();
            }

            index = validateInt(console.printUI(title, subtitle, options, sc));

            if (index < 0 || index > positionsOccupied) {
                console.printResult(title, "Invalid choice", sc);
            }
        } while (index < 0 || index > positionsOccupied);

        return data.getPositions()[index];
    }

    public Department chooseDepartment(DataHandler data, LimitHandler limits, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "Choose Department";
        String[] options = { "Exit", "Choose a department" };

        int departmentsOccupied = 0;
        for (int i = 0; i < data.getDepartments().length; i++) {
            if (data.getDepartments()[i] != null) {
                departmentsOccupied++;
            }
        }

        if (departmentsOccupied < 0) {
            console.printResult(title, "No departments available", sc);
            return null;
        }

        int index;
        do {
            String[] departmentOptions = new String[limits.getMaxDepartments()];

            for (int i = 0; i < data.getDepartments().length; i++) {
                if (data.getDepartments()[i] == null) {
                    break;
                }

                departmentOptions[i] = data.getDepartments()[i].getName();
            }

            index = validateInt(console.printUI(title, subtitle, options, sc));

            if (index < 0 || index > departmentsOccupied) {
                console.printResult(title, "Invalid choice", sc);
            }
        } while (index < 0 || index > departmentsOccupied);

        return data.getDepartments()[index];
    }

    public Employee createEmployee(DataHandler data, LimitHandler limits, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Create Employee";
        String subtitle = "";
        String[] options = {};
        String input;

        input = "First name";
        String firstName = console.printUI(title, subtitle, options, input, sc);

        input = "Last name";
        String lastName = console.printUI(title, subtitle, options, input, sc);

        input = "Email";
        String email = console.printUI(title, subtitle, options, input, sc);

        input = "Phone number";
        String phone = console.printUI(title, subtitle, options, input, sc);

        input = "Date of birth (dd/MM/yyyy)";
        LocalDate dateOfBirth = LocalDate.parse(console.printUI(title, subtitle, options, input, sc), dateFormatter);

        input = "Gender";
        String gender = console.printUI(title, subtitle, options, input, sc);

        input = "Address";
        Address address = createAddress(sc);

        input = "Id";
        String id = console.printUI(title, subtitle, options, input, sc);

        input = "Hire date (dd/MM/yyyy)";
        LocalDate hireDate = LocalDate.parse(console.printUI(title, subtitle, options, input, sc), dateFormatter);

        input = "Specialization";
        String specialization = console.printUI(title, subtitle, options, input, sc);

        Employee.EmployeeStatus status = chooseEmployeeStatus(sc);

        Evaluation[] evaluations = createEvaluations(limits, sc);
        Contract[] contracts = createContracts(limits, sc);
        Payroll[] payrolls = createPayrolls(limits, sc);
        Attendance[] attendances = createAttendances(limits, sc);
        Resignation[] resignations = createResignations(limits, sc);

        input = "Tax id";
        String taxId = console.printUI(title, subtitle, options, input, sc);

        Insurance[] insurances = createInsurances(limits, sc);

        Position position = choosePosition(data, limits, sc);
        Department department = chooseDepartment(data, limits, sc);

        return new Employee(firstName, lastName, email, phone, dateOfBirth, gender, address, id, hireDate,
                specialization,
                status, evaluations, contracts, payrolls, attendances, resignations, taxId, insurances, position,
                department);
    }
}
