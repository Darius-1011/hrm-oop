import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdminAccount extends Account {
    public AdminAccount(String username, String password, AccountStatus status) {
        super(username, password, status);
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

    public void listEmployee(Account[] accounts, Scanner sc) {
        System.out.println("Danh sach tai khoan:");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                System.out.print((i + 1) + ". ");
                accounts[i].getInfo(sc);
            }
        }
    }

    public void removeEmployee(Account[] accounts, String username, Scanner sc) {
        ConsoleUI console = new ConsoleUI();

        String title = "Remove Employee";

        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getUsername().equals(username)) {
                accounts[i] = null;
                console.printResult(title, "Da xoa tai khoan: " + username, sc);
                return;
            }
        }

        console.printResult(title, "Khong tim thay tai khoan: " + username, sc);
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
        String content = "";
        content += "Danh sach vi tri:\n";
        for (int i = 0; i < positions.length; i++) {
            if (positions[i] != null) {
                content += ("[" + (i + 1) + "] " + positions[i].getName()
                        + " | Position Coefficient: " + positions[i].getPositionCoefficient()
                        + " | Responsibilities: " + positions[i].getResponsibilities()) + "\n";
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

        content += "Danh sach phong ban\n";
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
}
