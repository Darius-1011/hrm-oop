public class AdminAccount extends Account {
    public AdminAccount(String username, String password, AccountStatus status) {
        super(username, password, status);
    }

    public void addEmployee(Account[] accounts, Account account) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] == null) {
                accounts[i] = account;
                System.out.println("Da them tai khoan: " + account.getUsername());
                return;
            }
        }
        System.out.println("Mang tai khoan da day, khong them duoc.");
    }

    public void listEmployee(Account[] accounts) {
        System.out.println("Danh sach tai khoan:");
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null) {
                System.out.print((i + 1) + ". ");
                accounts[i].getInfo();
            }
        }
    }

    public void removeEmployee(Account[] accounts, String username) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getUsername().equals(username)) {
                accounts[i] = null;
                System.out.println("Da xoa tai khoan: " + username);
                return;
            }
        }
        System.out.println("Khong tim thay tai khoan: " + username);
    }

    public void replyRequest(Account[] accounts, String username, String reply) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getUsername().equals(username)) {
                System.out.println("Admin phan hoi " + username + ": " + reply);
                return;
            }
        }
        System.out.println("Khong tim thay tai khoan: " + username);
    }

    public void createAccount(Account[] accounts, String username, String password, AccountStatus status) {
        this.addEmployee(accounts, new EmployeeAccount(username, password, status));
    }

    public void resetPassword(Account[] accounts, String username, String password) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i] != null && accounts[i].getUsername().equals(username)) {
                accounts[i].changePassword(password);
                System.out.println("Da doi mat khau cho: " + username);
                return;
            }
        }
        System.out.println("Khong tim thay tai khoan: " + username);
    }
}
