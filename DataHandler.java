import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.Scanner;

public class DataHandler {
  private int maxEvaluations;
  private int maxContracts;
  private int maxPayrolls;
  private int maxAttendances;
  private int maxAbsences;
  private int maxInsurances;
  private int maxResignations;

  private Account[] accounts;
  private Employee[] employees;
  private Department[] departments;
  private Position[] positions;

  private String currentDirectory = System.getProperty("user.dir");
  private String directoryPath = currentDirectory + File.separator + "data";

  private String accountPath = directoryPath + File.separator + "accounts.txt";
  private String employeePath = directoryPath + File.separator + "employees.txt";
  private String departmentPath = directoryPath + File.separator + "departments.txt";
  private String positionPath = directoryPath + File.separator + "positions.txt";

  public DataHandler(
      int maxAccounts,
      int maxEmployees,
      int maxDepartments,
      int maxPositions,
      int maxEvaluations,
      int maxContracts,
      int maxPayrolls,
      int maxAttendances,
      int maxAbsences,
      int maxInsurances,
      int maxResignations) {
    this.accounts = new Account[maxAccounts];
    this.employees = new Employee[maxEmployees];
    this.departments = new Department[maxDepartments];
    this.positions = new Position[maxPositions];

    this.maxEvaluations = maxEvaluations;
    this.maxContracts = maxContracts;
    this.maxPayrolls = maxPayrolls;
    this.maxAttendances = maxAttendances;
    this.maxAbsences = maxAbsences;
    this.maxResignations = maxResignations;
    this.maxInsurances = maxInsurances;
  }

  private Account[] readAccountsData() {
    try (Scanner sc = new Scanner(new File(accountPath))) {
      int i = 0;
      while (sc.hasNextLine() && i < accounts.length) {
        String type = sc.nextLine();
        String username = sc.nextLine();
        String password = sc.nextLine();
        Account.AccountStatus status = Account.AccountStatus.valueOf(sc.nextLine());

        if (type.equals("admin")) {
          this.accounts[i] = new AdminAccount(username, password, status);
        } else {
          this.accounts[i] = new EmployeeAccount(username, password, status);
        }

        i++;
        sc.nextLine();
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return this.accounts;
  }

  private Employee[] readEmployeesData() {
    try (Scanner sc = new Scanner(new File(employeePath))) {
      int i = 0;
      while (sc.hasNextLine() && i < employees.length) {
        this.employees[i] = new Employee();
        Employee thisEmployee = this.employees[i];

        thisEmployee.setEvaluations(new Evaluation[maxEvaluations]);
        thisEmployee.setContracts(new Contract[maxContracts]);
        thisEmployee.setPayrolls(new Payroll[maxPayrolls]);
        thisEmployee.setAttendances(new Attendance[maxAttendances]);
        thisEmployee.setAbsences(new Absence[maxAbsences]);
        thisEmployee.setResignations(new Resignation[maxResignations]);
        thisEmployee.setInsurances(new Insurance[maxInsurances]);

        String tmp;
        int idx = 0;

        thisEmployee.setFirstName(sc.nextLine());
        thisEmployee.setLastName(sc.nextLine());
        thisEmployee.setEmail(sc.nextLine());
        thisEmployee.setPhone(sc.nextLine());
        thisEmployee.setDateOfBirth(LocalDate.parse(sc.nextLine()));
        thisEmployee.setGender(sc.nextLine());

        sc.nextLine();
        thisEmployee.setAddress(new Address());
        Address thisAddress = thisEmployee.getAddress();
        thisAddress.setNumber(sc.nextLine());
        thisAddress.setStreet(sc.nextLine());
        thisAddress.setWard(sc.nextLine());
        thisAddress.setDistrict(sc.nextLine());
        thisAddress.setCity(sc.nextLine());
        sc.nextLine();

        thisEmployee.setId(sc.nextLine());
        thisEmployee.setHireDate(LocalDate.parse(sc.nextLine()));
        thisEmployee.setSpecialization(sc.nextLine());
        thisEmployee.setStatus(Employee.EmployeeStatus.valueOf(sc.nextLine()));

        sc.nextLine();
        tmp = sc.nextLine();

        Evaluation[] thisEvaluations = thisEmployee.getEvaluations();

        while (!tmp.equals("}")) {
          tmp = sc.nextLine();
          thisEvaluations[idx] = new Evaluation();
          thisEvaluations[idx].setMonth(YearMonth.parse(tmp));
          thisEvaluations[idx].setComment(sc.nextLine());
          thisEvaluations[idx].setWorkScore(Double.parseDouble(sc.nextLine()));
          thisEvaluations[idx].setAttitudeScore(Double.parseDouble(sc.nextLine()));
          thisEvaluations[idx].setDisciplineScore(Double.parseDouble(sc.nextLine()));
          thisEvaluations[idx].setWorkQuality(Double.parseDouble(sc.nextLine()));
          thisEvaluations[idx].setProductivity(Double.parseDouble(sc.nextLine()));
          thisEvaluations[idx].setTeamWork(Double.parseDouble(sc.nextLine()));

          idx++;

          sc.nextLine();
          tmp = sc.nextLine();
        }
        idx = 0;

        sc.nextLine();
        tmp = sc.nextLine();

        Contract[] thisContracts = thisEmployee.getContracts();

        while (!tmp.equals("}")) {
          tmp = sc.nextLine();
          thisContracts[idx] = new Contract();
          thisContracts[idx].setStartDate(LocalDate.parse(tmp));
          thisContracts[idx].setEndDate(LocalDate.parse(sc.nextLine()));
          thisContracts[idx].setType(Contract.ContractType.valueOf(sc.nextLine()));
          thisContracts[idx].setStatus(Contract.ContractStatus.valueOf(sc.nextLine()));

          sc.nextLine();
          idx++;

          tmp = sc.nextLine();
        }
        idx = 0;

        sc.nextLine();
        tmp = sc.nextLine();

        Payroll[] thisPayrolls = thisEmployee.getPayrolls();

        while (!tmp.equals("}")) {
          tmp = sc.nextLine();
          thisPayrolls[idx] = new Payroll();
          thisPayrolls[idx].setMonth(YearMonth.parse(tmp));
          thisPayrolls[idx].setBaseSalary(Double.parseDouble(sc.nextLine()));
          thisPayrolls[idx].setBonus(Double.parseDouble(sc.nextLine()));
          thisPayrolls[idx].setAllowance(Double.parseDouble(sc.nextLine()));
          thisPayrolls[idx].setDeduction(Double.parseDouble(sc.nextLine()));
          thisPayrolls[idx].setTaxAmount(Double.parseDouble(sc.nextLine()));
          thisPayrolls[idx].setInsuranceAmount(Double.parseDouble(sc.nextLine()));
          thisPayrolls[idx].setWorkedHours(Integer.parseInt(sc.nextLine()));
          thisPayrolls[idx].setOvertimeHours(Integer.parseInt(sc.nextLine()));
          thisPayrolls[idx].setLastSalaryIncreaseDate(LocalDate.parse(sc.nextLine()));

          sc.nextLine();
          idx++;

          tmp = sc.nextLine();
        }
        idx = 0;

        sc.nextLine();
        tmp = sc.nextLine();

        Attendance[] thisAttendances = thisEmployee.getAttendances();

        while (!tmp.equals("}")) {
          tmp = sc.nextLine();

          System.out.println("Reading attendance: " + tmp);

          thisAttendances[idx] = new Attendance();
          thisAttendances[idx].setDate(LocalDate.parse(tmp));
          thisAttendances[idx].setClockIn(LocalTime.parse(sc.nextLine()));
          String outStr = sc.nextLine();
          thisAttendances[idx].setClockOut(outStr.equals("null") ? null : LocalTime.parse(outStr));

          sc.nextLine();
          idx++;

          tmp = sc.nextLine();
        }
        idx = 0;
        System.out.println("Next line: " + tmp);

        sc.nextLine();
        tmp = sc.nextLine();

        Absence[] thisAbsences = thisEmployee.getAbsences();

        while (!tmp.equals("}")) {
          tmp = sc.nextLine();
          thisAbsences[idx] = new Absence();
          thisAbsences[idx].setReason(tmp);
          thisAbsences[idx].setFromDate(LocalDate.parse(sc.nextLine()));
          thisAbsences[idx].setToDate(LocalDate.parse(sc.nextLine()));
          thisAbsences[idx].setStatus(Absence.AbsenceStatus.valueOf(sc.nextLine()));

          sc.nextLine();
          idx++;

          tmp = sc.nextLine();
        }
        idx = 0;

        sc.nextLine();
        tmp = sc.nextLine();

        Resignation[] thisResignations = thisEmployee.getResignations();

        while (!tmp.equals("}")) {
          tmp = sc.nextLine();
          thisResignations[idx] = new Resignation();
          thisResignations[idx].setReason(tmp);
          thisResignations[idx].setDate(LocalDate.parse(sc.nextLine()));
          thisResignations[idx].setStatus(Resignation.ResignationStatus.valueOf(sc.nextLine()));

          sc.nextLine();
          idx++;

          tmp = sc.nextLine();
        }
        idx = 0;

        sc.nextLine();
        tmp = sc.nextLine();

        Insurance[] thisInsurances = thisEmployee.getInsurances();

        while (!tmp.equals("}")) {
          tmp = sc.nextLine();
          thisInsurances[idx] = new Insurance();
          thisInsurances[idx].setInsuranceNumber(tmp);
          thisInsurances[idx].setType(Insurance.InsuranceType.valueOf(sc.nextLine()));
          thisInsurances[idx].setStartDate(LocalDate.parse(sc.nextLine()));
          thisInsurances[idx].setEndDate(LocalDate.parse(sc.nextLine()));
          thisInsurances[idx].setRate(Double.parseDouble(sc.nextLine()));

          sc.nextLine();
          idx++;

          tmp = sc.nextLine();
        }
        idx = 0;

        thisEmployee.setTaxId(sc.nextLine());

        thisEmployee.setPosition(positions[Integer.parseInt(sc.nextLine())]);
        thisEmployee.setDepartment(departments[Integer.parseInt(sc.nextLine())]);

        sc.nextLine();

        i++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return this.employees;
  }

  private Department[] readDepartmentsData() {
    int idx = 0;
    try (Scanner sc = new Scanner(new File(departmentPath))) {
      while (sc.hasNextLine() && idx < departments.length) {
        departments[idx] = new Department();
        Department thisDepartment = departments[idx];
        thisDepartment.setName(sc.nextLine());
        thisDepartment.setPhone(sc.nextLine());
        thisDepartment.setPosition(sc.nextLine());
        sc.nextLine();

        idx++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return this.departments;
  }

  private Position[] readPositionsData() {
    int idx = 0;
    try (Scanner sc = new Scanner(new File(positionPath))) {
      while (sc.hasNextLine() && idx < positions.length) {
        positions[idx] = new Position();
        Position thisPosition = positions[idx];
        thisPosition.setName(sc.nextLine());
        thisPosition.setPositionCoefficient(Double.parseDouble(sc.nextLine()));
        thisPosition.setResponsibilities(sc.nextLine());
        sc.nextLine();
        idx++;
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return this.positions;
  }

  public DataHandler readData() {
    readDepartmentsData();
    readPositionsData();
    readEmployeesData();
    readAccountsData();
    return this;
  }

  private String writeAccountData(Account[] accounts) {
    String data = "";

    for (int i = 0; i < accounts.length; i++) {
      Account currentAccount = accounts[i];
      if (currentAccount == null) {
        continue;
      }

      if (currentAccount instanceof AdminAccount) {
        data += "admin\n";
      } else {
        data += "employee\n";
      }

      data += currentAccount.getUsername() + "\n";
      data += currentAccount.getPassword() + "\n";
      data += currentAccount.getStatus() + "\n";
      data += "\n";
    }

    return data;
  }

  private String writeEmployeeData(Employee[] employees) {
    String data = "";

    for (int i = 0; i < employees.length; i++) {
      Employee currentEmployee = employees[i];
      if (currentEmployee == null) {
        continue;
      }

      data += currentEmployee.getFirstName() + "\n";
      data += currentEmployee.getLastName() + "\n";
      data += currentEmployee.getEmail() + "\n";
      data += currentEmployee.getPhone() + "\n";
      data += currentEmployee.getDateOfBirth() + "\n";
      data += currentEmployee.getGender() + "\n";

      data += "{\n";
      data += currentEmployee.getAddress().getNumber() + "\n";
      data += currentEmployee.getAddress().getStreet() + "\n";
      data += currentEmployee.getAddress().getWard() + "\n";
      data += currentEmployee.getAddress().getDistrict() + "\n";
      data += currentEmployee.getAddress().getCity() + "\n";
      data += "}\n";

      data += currentEmployee.getId() + "\n";
      data += currentEmployee.getHireDate() + "\n";
      data += currentEmployee.getSpecialization() + "\n";
      data += currentEmployee.getStatus() + "\n";

      data += "{\n";
      Evaluation[] evaluations = currentEmployee.getEvaluations();
      for (int j = 0; j < evaluations.length; j++) {
        Evaluation currentEvaluation = evaluations[j];
        if (currentEvaluation == null) {
          break;
        }

        data += "{\n";

        data += currentEvaluation.getMonth() + "\n";
        data += currentEvaluation.getComment() + "\n";
        data += currentEvaluation.getWorkScore() + "\n";
        data += currentEvaluation.getAttitudeScore() + "\n";
        data += currentEvaluation.getDisciplineScore() + "\n";
        data += currentEvaluation.getWorkQuality() + "\n";
        data += currentEvaluation.getProductivity() + "\n";
        data += currentEvaluation.getTeamWork() + "\n";

        data += "}\n";
      }
      data += "}\n";

      data += "{\n";
      Contract[] contracts = currentEmployee.getContracts();
      for (int j = 0; j < contracts.length; j++) {
        Contract currentContract = contracts[j];
        if (currentContract == null) {
          break;
        }

        data += "{\n";

        data += currentContract.getStartDate() + "\n";
        data += currentContract.getEndDate() + "\n";
        data += currentContract.getType() + "\n";
        data += currentContract.getStatus() + "\n";

        data += "}\n";
      }
      data += "}\n";

      data += "{\n";
      Payroll[] payrolls = currentEmployee.getPayrolls();
      for (int j = 0; j < payrolls.length; j++) {
        Payroll currentPayroll = payrolls[j];
        if (currentPayroll == null) {
          break;
        }

        data += "{\n";

        data += currentPayroll.getMonth() + "\n";
        data += currentPayroll.getBaseSalary() + "\n";
        data += currentPayroll.getBonus() + "\n";
        data += currentPayroll.getAllowance() + "\n";
        data += currentPayroll.getDeduction() + "\n";
        data += currentPayroll.getTaxAmount() + "\n";
        data += currentPayroll.getInsuranceAmount() + "\n";
        data += currentPayroll.getWorkedHours() + "\n";
        data += currentPayroll.getOvertimeHours() + "\n";
        data += currentPayroll.getLastSalaryIncreaseDate() + "\n";

        data += "}\n";
      }
      data += "}\n";

      data += "{\n";
      Attendance[] attendances = currentEmployee.getAttendances();
      for (int j = 0; j < attendances.length; j++) {
        Attendance currentAttendance = attendances[j];
        if (currentAttendance == null) {
          break;
        }

        data += "{\n";

        data += currentAttendance.getDate() + "\n";
        data += currentAttendance.getClockIn() + "\n";
        data += currentAttendance.getClockOut() + "\n";

        data += "}\n";
      }
      data += "}\n";

      data += "{\n";
      Absence[] absences = currentEmployee.getAbsences();
      for (int j = 0; j < absences.length; j++) {
        Absence currentAbsence = absences[j];
        if (currentAbsence == null) {
          break;
        }

        data += "{\n";

        data += currentAbsence.getReason() + "\n";
        data += currentAbsence.getFromDate() + "\n";
        data += currentAbsence.getToDate() + "\n";
        data += currentAbsence.getStatus() + "\n";

        data += "}\n";
      }
      data += "}\n";

      data += "{\n";
      Resignation[] resignations = currentEmployee.getResignations();
      for (int j = 0; j < resignations.length; j++) {
        Resignation currentResignation = resignations[j];
        if (currentResignation == null) {
          break;
        }

        data += "{\n";

        data += currentResignation.getReason() + "\n";
        data += currentResignation.getDate() + "\n";
        data += currentResignation.getStatus() + "\n";

        data += "}\n";
      }
      data += "}\n";

      data += "{\n";
      Insurance[] insurances = currentEmployee.getInsurances();
      for (int j = 0; j < insurances.length; j++) {
        Insurance currentInsurance = insurances[j];
        if (currentInsurance == null) {
          break;
        }

        data += "{\n";

        data += currentInsurance.getInsuranceNumber() + "\n";
        data += currentInsurance.getType() + "\n";
        data += currentInsurance.getStartDate() + "\n";
        data += currentInsurance.getEndDate() + "\n";
        data += currentInsurance.getRate() + "\n";

        data += "}\n";
      }
      data += "}\n";

      data += currentEmployee.getTaxId() + "\n";

      int positionIdx = -1;
      for (int j = 0; j < positions.length; j++) {
        if (positions[j] != null && currentEmployee.getPosition().getName().equals(positions[j].getName())) {
          positionIdx = j;
          break;
        }
      }

      data += positionIdx + "\n";

      int departmentIdx = -1;
      for (int j = 0; j < departments.length; j++) {
        if (departments[j] != null && currentEmployee.getDepartment().getName().equals(departments[j].getName())) {
          departmentIdx = j;
          break;
        }
      }

      data += departmentIdx + "\n";

      data += "\n";
    }

    return data;
  }

  private String writeDepartmentData(Department[] departments) {
    String data = "";

    for (int i = 0; i < departments.length; i++) {
      Department currentDepartment = departments[i];
      if (currentDepartment == null) {
        continue;
      }

      data += currentDepartment.getName() + "\n";
      data += currentDepartment.getPhone() + "\n";
      data += currentDepartment.getPosition() + "\n";
      data += "\n";
    }

    return data;
  }

  private String writePositionData(Position[] positions) {
    String data = "";

    for (int i = 0; i < positions.length; i++) {
      Position currentPosition = positions[i];
      if (currentPosition == null) {
        continue;
      }

      data += currentPosition.getName() + "\n";
      data += currentPosition.getPositionCoefficient() + "\n";
      data += currentPosition.getResponsibilities() + "\n";
      data += "\n";
    }

    return data;
  }

  public void writeData() {
    try {
      FileWriter accountFileWriter = new FileWriter(accountPath);
      FileWriter employeeFileWriter = new FileWriter(employeePath);
      FileWriter departmentFileWriter = new FileWriter(departmentPath);
      FileWriter positionFileWriter = new FileWriter(positionPath);

      accountFileWriter.write(writeAccountData(accounts));
      employeeFileWriter.write(writeEmployeeData(employees));
      departmentFileWriter.write(writeDepartmentData(departments));
      positionFileWriter.write(writePositionData(positions));

      accountFileWriter.close();
      employeeFileWriter.close();
      departmentFileWriter.close();
      positionFileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String writeSampleAccountData() {
    String data = "";

    String[][] samples = {
        { "admin", "admin01", "admin123", "ACTIVE" },
        { "employee", "EMP001", "john123", "ACTIVE" },
        { "employee", "EMP002", "mary123", "SUSPENDED" },
        { "employee", "EMP003", "david123", "INACTIVE" },
    };

    for (String[] sample : samples) {
      data += String.join("\n", sample) + "\n\n";
    }

    return data;
  }

  private String writeSampleEmployeeData() {
    String data = "";

    String[][] samples = {
        // John Smith
        {
            "John", "Smith",
            "john.smith@example.com",
            "0912345678",
            "1998-06-15", "Male",
            "{",
            "25", "Nguyen Hue", "Ben Nghe",
            "District 1", "Ho Chi Minh City",
            "}",
            "EMP001",
            "2025-01-01",
            "Backend Development",
            "WORKING",

            // Evaluations
            "{",
            "{",
            "2025-01",
            "Excellent performance in backend development",
            "9.0", "8.5", "9.0", "9.5", "9.0", "8.5",
            "}",
            "}",

            // Contracts
            "{",
            "{",
            "2025-01-01",
            "2026-01-01",
            "FULL_TIME",
            "ACTIVE",
            "}",
            "}",

            // Payrolls
            "{",
            "{",
            "2025-01",
            "15000000", "2000000", "1000000",
            "500000", "1000000", "500000",
            "176", "10",
            "2025-01-01",
            "}",
            "}",

            // Attendances
            "{",
            "{",
            "2025-01-02",
            "08:00", "17:00",
            "}",
            "}",

            // Absences
            "{",
            "{",
            "Personal leave",
            "2025-01-10",
            "2025-01-10",
            "REJECTED",
            "}",
            "}",

            // Resignations
            "{",
            "{",
            "Personal reasons",
            "2025-01-15",
            "REJECTED",
            "}",
            "}",

            // Insurances
            "{",
            "{",
            "INS001",
            "HEALTH",
            "2025-01-01",
            "2025-12-31",
            "4.5",
            "}",
            "}",

            "TAX001",
            "2",
            "0"
        },

        // Mary Jones
        {
            "Mary", "Jones",
            "mary.jones@example.com",
            "0987654321",
            "1999-03-20", "Female",
            "{",
            "12", "Le Loi", "Ben Thanh",
            "District 1", "Ho Chi Minh City",
            "}",
            "EMP002",
            "2025-02-01",
            "Human Resources",
            "WORKING",

            // Evaluations
            "{",
            "{",
            "2025-02",
            "Excellent communication and teamwork",
            "9.5", "9.0", "9.0", "9.0", "8.5", "9.5",
            "}",
            "}",

            // Contracts
            "{",
            "{",
            "2025-02-01",
            "2026-02-01",
            "FULL_TIME",
            "ACTIVE",
            "}",
            "}",

            // Payrolls
            "{",
            "{",
            "2025-02",
            "12000000", "1500000", "800000",
            "300000", "800000", "400000",
            "168", "5",
            "2025-02-01",
            "}",
            "}",

            // Attendances
            "{",
            "{",
            "2025-02-03",
            "08:15", "17:15",
            "}",
            "}",

            // Absences
            "{",
            "{",
            "Medical appointment",
            "2025-02-10",
            "2025-02-10",
            "REJECTED",
            "}",
            "}",

            // Resignations
            "{",
            "{",
            "Career change",
            "2025-02-15",
            "REJECTED",
            "}",
            "}",

            // Insurances
            "{",
            "{",
            "INS002",
            "HEALTH",
            "2025-02-01",
            "2026-01-31",
            "4.5",
            "}",
            "}",

            "TAX002",
            "4",
            "1"
        },

        // David Wilson
        {
            "David", "Wilson",
            "david.wilson@example.com",
            "0901234567",
            "1997-08-10", "Male",
            "{",
            "30", "Tran Hung Dao", "Cau Kho",
            "District 1", "Ho Chi Minh City",
            "}",
            "EMP003",
            "2025-03-01",
            "Software Development",
            "WORKING",

            // Evaluations
            "{",
            "{",
            "2025-03",
            "Good progress in software development",
            "8.0", "8.5", "9.0", "8.0", "8.5", "8.0",
            "}",
            "}",

            // Contracts
            "{",
            "{",
            "2025-03-01",
            "2026-03-01",
            "FULL_TIME",
            "ACTIVE",
            "}",
            "}",

            // Payrolls
            "{",
            "{",
            "2025-03",
            "10000000", "1000000", "500000",
            "200000", "600000", "300000",
            "176", "8",
            "2025-03-01",
            "}",
            "}",

            // Attendances
            "{",
            "{",
            "2025-03-03",
            "08:00", "17:00",
            "}",
            "}",

            // Absences
            "{",
            "{",
            "Family matters",
            "2025-03-12",
            "2025-03-12",
            "REJECTED",
            "}",
            "}",

            // Resignations
            "{",
            "{",
            "Personal reasons",
            "2025-03-20",
            "REJECTED",
            "}",
            "}",

            // Insurances
            "{",
            "{",
            "INS003",
            "HEALTH",
            "2025-03-01",
            "2026-02-28",
            "4.5",
            "}",
            "}",

            "TAX003",
            "3",
            "0"
        }
    };

    for (String[] sample : samples) {
      data += String.join("\n", sample) + "\n\n";
    }

    return data;
  }

  private String writeSampleDepartmentData() {
    String data = "";

    String[][] samples = {
        { "Information Technology", "02812345678", "C.A206" },
        { "Human Resources", "02812345679", "1.B302" },
        { "Financial Management", "02812345680", "2.D103" },
        { "Marketing and Communications", "02812345681", "C.E403" },
        { "Sales and Business Development", "02812345682", "C.A209" }
    };

    for (String[] sample : samples) {
      data += String.join("\n", sample) + "\n\n";
    }

    return data;
  }

  private String writeSamplePositionData() {
    String data = "";

    String[][] samples = {
        { "Manager", "3.0", "Department management" },
        { "Team Leader", "2.5", "Team supervision and coordination" },
        { "Senior Developer", "2.0", "Senior software development" },
        { "Junior Developer", "1.5", "Junior software development" },
        { "HR Specialist", "1.8", "Human resources management" }
    };

    for (String[] sample : samples) {
      data += String.join("\n", sample) + "\n\n";
    }

    return data;
  }

  public void writeSampleData() {
    try {
      FileWriter accountFileWriter = new FileWriter(accountPath);
      FileWriter employeeFileWriter = new FileWriter(employeePath);
      FileWriter departmentFileWriter = new FileWriter(departmentPath);
      FileWriter positionFileWriter = new FileWriter(positionPath);

      accountFileWriter.write(writeSampleAccountData());
      employeeFileWriter.write(writeSampleEmployeeData());
      departmentFileWriter.write(writeSampleDepartmentData());
      positionFileWriter.write(writeSamplePositionData());

      accountFileWriter.close();
      employeeFileWriter.close();
      departmentFileWriter.close();
      positionFileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public Account[] getAccounts() {
    return accounts;
  }

  public void setAccounts(Account[] accounts) {
    this.accounts = accounts;
  }

  public Employee[] getEmployees() {
    return employees;
  }

  public void setEmployees(Employee[] employees) {
    this.employees = employees;
  }

  public Department[] getDepartments() {
    return departments;
  }

  public void setDepartments(Department[] departments) {
    this.departments = departments;
  }

  public Position[] getPositions() {
    return positions;
  }

  public void setPositions(Position[] positions) {
    this.positions = positions;
  }
}
