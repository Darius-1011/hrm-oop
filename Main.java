import java.awt.desktop.OpenFilesEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
  private static Account[] accounts = new Account[500];
  private static Employee[] employees = new Employee[500];
  private static Department[] departments = new Department[20];
  private static Position[] positions = new Position[20];

  private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

  private enum CreatingType {
    ACCOUNT,
    EMPLOYEE,
    DEPARTMENT,
    POSITION
  }

  private static Account createAccount(Scanner sc) {
    System.out.println("Username: ");
    String username = sc.nextLine();

    System.out.println("Password: ");
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
      System.out.println("Status:");

      statusNum = Integer.parseInt(sc.nextLine());
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
    } while (statusNum < 1 || 6 < statusNum);

    System.out.println("[1] Admin");
    System.out.println("[2] Employee");
    int type = Integer.parseInt(sc.nextLine());

    if (type == 1) {
      return new AdminAccount(username, password, status);
    } else {
      return new EmployeeAccount(username, password, status);
    }
  }

  private static Address createAddress(Scanner sc) {
    System.out.println("Number: ");
    int number = Integer.parseInt(sc.nextLine());

    System.out.println("Street: ");
    String street = sc.nextLine();

    System.out.println("Ward: ");
    String ward = sc.nextLine();

    System.out.println("District: ");
    String district = sc.nextLine();

    System.out.println("City: ");
    String city = sc.nextLine();

    return new Address(number, street, ward, district, city);
  }

  private static Evaluation createEvaluation(Scanner sc) {
    System.out.println("Month: ");
    String month = sc.nextLine();

    System.out.println("Comment");
    String comment = sc.nextLine();

    System.out.println("Work score: ");
    double workScore = Double.parseDouble(sc.nextLine());

    System.out.println("Attitude score: ");
    double attitudeScore = Double.parseDouble(sc.nextLine());

    System.out.println("Discipline score: ");
    double disciplineScore = Double.parseDouble(sc.nextLine());

    System.out.println("Work quality: ");
    double workQuality = Double.parseDouble(sc.nextLine());

    System.out.println("Productivity: ");
    double productivity = Double.parseDouble(sc.nextLine());

    System.out.println("Team work: ");
    double teamWork = Double.parseDouble(sc.nextLine());

    return new Evaluation(month, comment, workScore, attitudeScore, disciplineScore, workQuality, productivity,
        teamWork);
  }

  private static Contract createContract(Scanner sc) {
    System.out.println("Start date: ");
    LocalDate startDate = LocalDate.parse(sc.nextLine(), dateFormatter);

    System.out.println("End date: ");
    LocalDate endDate = LocalDate.parse(sc.nextLine(), dateFormatter);

    int typeNum = 0;
    Contract.ContractType type = Contract.ContractType.TEMPORARY;
    do {
      System.out.println("[1] Probation");
      System.out.println("[2] Full time");
      System.out.println("[3] Part time");
      System.out.println("[4] Temporary");
      System.out.println("[5] Fixed term");
      System.out.println("[6] Permanent");
      System.out.println("Type: ");

      typeNum = Integer.parseInt(sc.nextLine());
      switch (typeNum) {
        case 1:
          type = Contract.ContractType.PROBATION;
          break;
        case 2:
          type = Contract.ContractType.FULL_TIME;
          break;
        case 3:
          type = Contract.ContractType.PART_TIME;
          break;
        case 4:
          type = Contract.ContractType.TEMPORARY;
          break;
        case 5:
          type = Contract.ContractType.FIXED_TERM;
          break;
        case 6:
          type = Contract.ContractType.PERMANENT;
          break;

        default:
          break;
      }
    } while (typeNum < 1 || 6 < typeNum);

    int statusNum = 0;
    Contract.ContractStatus status = Contract.ContractStatus.ACTIVE;
    do {
      System.out.println("[1] Draft");
      System.out.println("[2] Active");
      System.out.println("[3] Expired");
      System.out.println("Status: ");

      statusNum = Integer.parseInt(sc.nextLine());
      switch (statusNum) {
        case 1:
          status = Contract.ContractStatus.DRAFT;
          break;
        case 2:
          status = Contract.ContractStatus.ACTIVE;
          break;
        case 3:
          status = Contract.ContractStatus.EXPIRED;
          break;

        default:
          break;
      }
    } while (typeNum < 1 || 3 < typeNum);

    return new Contract(startDate, endDate, type, status);
  }

  private static Payroll createPayroll(Scanner sc) {
    System.out.println("Month: ");
    int month = Integer.parseInt(sc.nextLine());

    System.out.println("Year: ");
    int year = Integer.parseInt(sc.nextLine());

    System.out.println("Base Salary");
    double baseSalary = Double.parseDouble(sc.nextLine());

    System.out.println("Bonus: ");
    double bonus = Double.parseDouble(sc.nextLine());

    System.out.println("Allowance: ");
    double allowance = Double.parseDouble(sc.nextLine());

    System.out.println("Deduction: ");
    double deduction = Double.parseDouble(sc.nextLine());

    System.out.println("Tax amount: ");
    double taxAmount = Double.parseDouble(sc.nextLine());

    System.out.println("Insurance amount: ");
    double insuranceAmount = Double.parseDouble(sc.nextLine());

    System.out.println("Worked hours: ");
    int workedHours = Integer.parseInt(sc.nextLine());

    System.out.println("Overtime hours: ");
    int overtimeHours = Integer.parseInt(sc.nextLine());

    System.out.println("Last salary increase (dd/MM/yyyy): ");
    LocalDate lastSalaryIncreaseDate = LocalDate.parse(sc.nextLine(), dateFormatter);

    return new Payroll(month, year, baseSalary, bonus, allowance, deduction, taxAmount, insuranceAmount, workedHours,
        overtimeHours, lastSalaryIncreaseDate);
  }

  private static Attendance createAttendance(Scanner sc) {
    System.out.println("Date (dd/MM/yyyy): ");
    LocalDate date = LocalDate.parse(sc.nextLine(), dateFormatter);

    System.out.println("Clock in (HH:mm): ");
    LocalTime clockIn = LocalTime.parse(sc.nextLine(), timeFormatter);

    System.out.println("Clock out (HH:mm): ");
    LocalTime clockOut = LocalTime.parse(sc.nextLine(), timeFormatter);

    return new Attendance(date, clockIn, clockOut);
  }

  private static Insurance createInsurance(Scanner sc) {
    System.out.println("Insurance number: ");
    String insuranceNumber = sc.nextLine();

    int typeNum;
    Insurance.InsuranceType type = Insurance.InsuranceType.HEALTH;
    do {
      System.out.println("[1] Life");
      System.out.println("[2] Health");
      System.out.println("[3] Social");
      System.out.println("[4] Unemployment");
      System.out.println("Type: ");
      typeNum = Integer.parseInt(sc.nextLine());

      switch (typeNum) {
        case 1:
          type = Insurance.InsuranceType.LIFE;
          break;
        case 2:
          type = Insurance.InsuranceType.HEALTH;
          break;
        case 3:
          type = Insurance.InsuranceType.SOCIAL;
          break;
        case 4:
          type = Insurance.InsuranceType.UNEMPLOYMENT;
          break;

        default:
          break;
      }
    } while (typeNum < 1 || 4 < typeNum);

    System.out.println("Start date (dd/MM/yyyy): ");
    LocalDate startDate = LocalDate.parse(sc.nextLine(), dateFormatter);

    System.out.println("End date (dd/MM/yyyy): ");
    LocalDate endDate = LocalDate.parse(sc.nextLine(), dateFormatter);

    System.out.println("Rate: ");
    double rate = Double.parseDouble(sc.nextLine());

    return new Insurance(insuranceNumber, type, startDate, endDate, rate);
  }

  private static Employee createEmployee(Scanner sc) {
    System.out.println("First name: ");
    String firstName = sc.nextLine();

    System.out.println("Last name: ");
    String lastName = sc.nextLine();

    System.out.println("Email: ");
    String email = sc.nextLine();

    System.out.println("Phone number: ");
    String phone = sc.nextLine();

    System.out.println("Date of birth (dd/MM/yyyy): ");
    LocalDate dateOfBirth = LocalDate.parse(sc.nextLine(), dateFormatter);

    System.out.println("Gender: ");
    String gender = sc.nextLine();

    System.out.println("Address");
    Address address = createAddress(sc);

    System.out.println("Id: ");
    String id = sc.nextLine();

    System.out.println("Hire date (dd/MM/yyyy): ");
    LocalDate hireDate = LocalDate.parse(sc.nextLine(), dateFormatter);

    System.out.println("Specialization: ");
    String specialization = sc.nextLine();

    System.out.println("[1] Working");
    System.out.println("[2] On leave");
    System.out.println("[3] Resigned");
    System.out.println("Status:");

    Employee.EmployeeStatus status = Employee.EmployeeStatus.WORKING;
    switch (Integer.parseInt(sc.nextLine())) {
      case 1:
        status = Employee.EmployeeStatus.WORKING;
        break;
      case 2:
        status = Employee.EmployeeStatus.ON_LEAVE;
        break;
      case 3:
        status = Employee.EmployeeStatus.RESIGNED;
        break;

      default:
        break;
    }

    System.out.println("How many evaluations? ");
    int evaluationCount = Integer.parseInt(sc.nextLine());
    Evaluation[] evaluations = new Evaluation[1000];
    for (int i = 0; i < evaluationCount; i++) {
      evaluations[i] = createEvaluation(sc);
    }

    System.out.println("How many contracts? ");
    int contractCount = Integer.parseInt(sc.nextLine());
    Contract[] contracts = new Contract[1000];
    for (int i = 0; i < contractCount; i++) {
      contracts[i] = createContract(sc);
    }

    System.out.println("How many payrolls? ");
    int payrollCount = Integer.parseInt(sc.nextLine());
    Payroll[] payrolls = new Payroll[1000];
    for (int i = 0; i < payrollCount; i++) {
      payrolls[i] = createPayroll(sc);
    }

    System.out.println("How many attendances? ");
    int attendanceCount = Integer.parseInt(sc.nextLine());
    Attendance[] attendances = new Attendance[1000];
    for (int i = 0; i < attendanceCount; i++) {
      attendances[i] = createAttendance(sc);
    }

    System.out.println("[0] No");
    System.out.println("[1] Yes");
    System.out.println("Have resignation: ");
    int resignationChoice = Integer.parseInt(sc.nextLine());

    String reason;
    LocalDate date;
    Resignation.ResignationStatus resigntationStatus = Resignation.ResignationStatus.REJECTED;

    if (resignationChoice == 1) {
      System.out.println("Reason: ");
      reason = sc.nextLine();

      System.out.println("Date (dd/MM/yyyy): ");
      date = LocalDate.parse(sc.nextLine(), dateFormatter);

      int statusNum;
      do {
        System.out.println("[1] Pending");
        System.out.println("[2] Rejected");
        System.out.println("[3] Approved");
        System.out.println("Status: ");
        statusNum = Integer.parseInt(sc.nextLine());

        switch (statusNum) {
          case 1:
            resigntationStatus = Resignation.ResignationStatus.PENDING;
            break;
          case 2:
            resigntationStatus = Resignation.ResignationStatus.REJECTED;
            break;
          case 3:
            resigntationStatus = Resignation.ResignationStatus.APPROVED;
            break;

          default:
            break;
        }
      } while (statusNum < 1 || 3 < statusNum);
    } else {
      reason = "";
      date = LocalDate.parse("01/01/1000", dateFormatter);
    }

    Resignation resignation = new Resignation(reason, date, resigntationStatus);

    System.out.println("Tax id: ");
    String taxId = sc.nextLine();

    System.out.println("How many insurances? ");
    int insuranceCount = Integer.parseInt(sc.nextLine());
    Insurance[] insurances = new Insurance[1000];
    for (int i = 0; i < insuranceCount; i++) {
      insurances[i] = createInsurance(sc);
    }

    for (int i = 0; i < positions.length; i++) {
      System.out.printf("[%d] %s\n", i + 1, positions[i].getName());
    }
    System.out.println("Choice positions: ");
    Position position = positions[Integer.parseInt(sc.nextLine()) - 1];

    for (int i = 0; i < departments.length; i++) {
      System.out.printf("[%d] %s\n", i, departments[i].getName());
    }
    System.out.println("Choice department: ");
    Department department = departments[Integer.parseInt(sc.nextLine()) - 1];

    return new Employee(firstName, lastName, email, phone, dateOfBirth, gender, address, id, hireDate, specialization,
        status, evaluations, contracts, payrolls, attendances, resignation, taxId, insurances, position, department);
  }

  private static Department createDepartment(Scanner sc) {
    System.out.println("Name: ");
    String name = sc.nextLine();

    System.out.println("Phone: ");
    String phone = sc.nextLine();

    System.out.println("Position: ");
    String position = sc.nextLine();

    return new Department(name, phone, position);
  }

  private static Position createPosition(Scanner sc) {
    System.out.println("Name: ");
    String name = sc.nextLine();

    System.out.println("Position coefficient: ");
    Double positionCoefficient = Double.parseDouble(sc.nextLine());

    System.out.println("Responsibilities: ");
    String responsibilities = sc.nextLine();

    return new Position(responsibilities, name, positionCoefficient);
  }

  private static CreatingType choiceType(Scanner sc) {
    System.out.println("[0] Exit");
    System.out.println("[1] Account");
    System.out.println("[2] Employee");
    System.out.println("[3] Department");
    System.out.println("[4] Position");
    System.out.println("Create: ");
    int choice;

    do {
      choice = Integer.parseInt(sc.nextLine());
      switch (choice) {
        case 0:
          return null;
        case 1:
          return CreatingType.ACCOUNT;
        case 2:
          return CreatingType.EMPLOYEE;
        case 3:
          return CreatingType.DEPARTMENT;
        case 4:
          return CreatingType.POSITION;

        default:
          break;
      }
    } while (choice < 0 || 4 < choice);

    return null;
  }

  private static int choiceCount(Scanner sc) {
    System.out.println("How many: ");
    int count = Integer.parseInt(sc.nextLine());
    return count;
  }

  private static void createFirstN(Scanner sc) {
    CreatingType type = choiceType(sc);
    int count = choiceCount(sc);

    int accountsOccupied = -1;
    int employeesOccupied = -1;
    int departmentsOccupied = -1;
    int positionsOccupied = -1;

    for (int i = 0; i < count; i++) {
      switch (type) {
        case ACCOUNT:
          accounts[accountsOccupied + 1] = createAccount(sc);
          accountsOccupied++;
          break;
        case EMPLOYEE:
          employees[employeesOccupied + 1] = createEmployee(sc);
          employeesOccupied++;
          break;
        case DEPARTMENT:
          departments[departmentsOccupied + 1] = createDepartment(sc);
          departmentsOccupied++;
          break;
        case POSITION:
          positions[positionsOccupied + 1] = createPosition(sc);
          positionsOccupied++;
          break;

        default:
          break;
      }
    }
  }

  private static int getIndex(String username) {
    int index = -1;

    for (int i = 0; i < accounts.length; i++) {
      if (accounts[i].getUsername() == username) {
        index = i;
        break;
      }
    }

    return index;
  }

  private static boolean logIn(int index, Scanner sc) {
    if (index == -1) {
      System.out.println("Username not exists");
      return false;
    }

    System.out.println("Password: ");
    String password = sc.nextLine();

    if (password == accounts[index].getPassword()) {
      return true;
    } else {
      return false;
    }
  }

  private static void runEmployeeUI(EmployeeAccount account, Scanner sc) {
    String[] options = { "Logout", "Change password", "Get info", "Set info", "Submit absence", "Submit resignation",
        "Check in", "Check out", "View attendance" };

    int choice;
    do {
      for (int i = 0; i < options.length; i++) {
        System.out.printf("[%d] %s\n", i, options[i]);
      }
      System.out.println("Choice: ");
      choice = Integer.parseInt(sc.nextLine());

      switch (choice) {
        case 0:
          return;
        case 1:
          System.out.println("Change password: ");
          account.changePassword(sc.nextLine());
          break;
        case 2:
          account.getInfo();
          break;
        case 3:
          System.out.println("Set username: ");
          String username = sc.nextLine();

          int statusNum = 0;
          Account.AccountStatus status = Account.AccountStatus.ACTIVE;
          do {
            System.out.println("[1] Active");
            System.out.println("[2] Inactive");
            System.out.println("[3] Suspended");
            System.out.println("[4] Pending");
            System.out.println("[5] Locked");
            System.out.println("[6] Terminated");
            System.out.println("Status:");

            statusNum = Integer.parseInt(sc.nextLine());
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
          } while (statusNum < 1 || 6 < statusNum);

          account.setInfo(username, status);
          break;
        case 4:
          account.submitAbsence();
          break;
        case 5:
          account.submitResignation();
          break;
        case 6:
          account.checkIn();
          break;
        case 7:
          account.checkOut();
          break;
        case 8:
          account.viewAttendance();
          break;

        default:
          break;
      }
    } while (choice < 0 || options.length < choice);
  }

  private static void runAdminUI(AdminAccount account, Scanner sc) {
    String[] options = { "Logout", "Change password", "Get info", "Set info", "Add employee", "Edit employee",
        "Remove employee", "Print employees", "Print departments", "Print payrolls", "Reply request", "Search employee",
        "Search department" };

    int choice;
    do {
      for (int i = 0; i < options.length; i++) {
        System.out.printf("[%d] %s\n", i + 1, options[i]);
      }
      System.out.println("Choice: ");
      choice = Integer.parseInt(sc.nextLine());

      switch (choice) {
        case 0:
          return;
        case 1:
          System.out.println("Change password: ");
          account.changePassword(sc.nextLine());
          break;
        case 2:
          account.getInfo();
          break;
        case 3:
          System.out.println("Set username: ");
          String username = sc.nextLine();

          int statusNum = 0;
          Account.AccountStatus status = Account.AccountStatus.ACTIVE;
          do {
            System.out.println("[1] Active");
            System.out.println("[2] Inactive");
            System.out.println("[3] Suspended");
            System.out.println("[4] Pending");
            System.out.println("[5] Locked");
            System.out.println("[6] Terminated");
            System.out.println("Status:");

            statusNum = Integer.parseInt(sc.nextLine());
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
          } while (statusNum < 1 || 6 < statusNum);

          account.setInfo(username, status);
          break;
        case 4:
          account.addEmployee(accounts, createEmployee(sc));
          break;
        case 5:
          String editUsername = sc.nextLine();
          account.editEmployee(accounts, editUsername);
          break;
        case 6:
          String removeEmployee = sc.nextLine();
          account.removeEmployee(accounts, removeEmployee);
          break;
        case 7:
          account.printEmployee(accounts);
          break;
        case 8:
          account.printDepartments(departments);
          break;
        case 9:
          account.printPayrolls();
          break;
        case 10:
          System.out.println("Reply to username: ");
          String replyUsername = sc.nextLine();

          System.out.println("Reply: ");
          String reply = sc.nextLine();

          account.replyRequest(accounts, username, reply);
          break;
        case 11:
          account.searchEmployee(accounts);
          break;
        case 12:
          account.searchDepartment(departments);
          break;

        default:
          break;
      }
    } while (choice < 0 || options.length < choice);
  }

  private static void runUI(Scanner sc) {
    int choice;
    do {
      System.out.println("[0] Exit");
      System.out.println("[1] Create first N");
      System.out.println("[2] Login");
      System.out.println("Choice: ");
      choice = Integer.parseInt(sc.nextLine());

      switch (choice) {
        case 0:
          return;
        case 1:
          createFirstN(sc);
          break;
        case 2:
          System.out.println("Username: ");
          String username = sc.nextLine();
          int index = getIndex(username);
          boolean isLoggedIn = logIn(index, sc);

          if (isLoggedIn) {
            if (accounts[index] instanceof EmployeeAccount) {
              runEmployeeUI((EmployeeAccount) accounts[index], sc);
            } else {
              runAdminUI((AdminAccount) accounts[index], sc);
            }
          }
          break;

        default:
          break;
      }
    } while (choice < 0 || 2 < choice);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    runUI(sc);

    sc.close();
  }
}
