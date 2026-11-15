import java.util.Scanner;

public abstract class Account {
    public enum AccountStatus {
        ACTIVE,
        INACTIVE,
        SUSPENDED,
        PENDING,
        LOCKED,
        TERMINATED
    }

    private String username;
    private String password;
    private AccountStatus status;

    public Account(String username, String password, AccountStatus status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public boolean login(Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Login";
        String[] options = {};
        String input = "Password";

        String password = console.printUI(title, "", options, input, sc);

        if (!password.equals(getPassword())) {
            console.printResult(title, "Wrong password", sc);
            return false;
        }

        if (getStatus() != Account.AccountStatus.ACTIVE) {
            console.printResult(title, "Account is not active", sc);
            return false;
        }

        console.printResult(title, "Login successfully", sc);
        return true;
    }

    public void logout() {
        System.out.println(this.username + " logout");
    }

    public void resetPassword(Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String title = "Reset Password";
        String subtitle = "";
        String[] options = {};
        String input;

        String oldPassword;
        do {
            input = "Old password";

            oldPassword = console.printUI(title, subtitle, options, input, sc);

            if (!getPassword().equals(oldPassword)) {
                console.printResult(title, "Wrong password", sc);
            }
        } while (!getPassword().equals(oldPassword));

        input = "New password";
        setPassword(console.printUI(title, subtitle, options, input, sc));

        console.printResult(title, "Reset password successfully", sc);
    }

    public void getInfo(Scanner sc) {
        ConsoleUI console = new ConsoleUI();
        String result = "Username: " + this.username + "\n" +
                "Status: " + this.status;
        console.printResult("Employee Information", result, sc);
    }

    private boolean usernameExist(Account[] accounts, String username) {
        for (Account account : accounts) {
            if (account != null && account.getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    public void setAccountName(Scanner sc, Account[] accounts, Account current) {
        ConsoleUI console = new ConsoleUI();
        String title = "Set New Username";
        String[] options = {};

        String username;
        username = console.printUI(title, "", options, "New username", sc);
        if (usernameExist(accounts, username)) {
            console.printResult(title, username + " already exists", sc);
            return;
        }

        current.setUsername(username);
        console.printResult(title, "Set new username successfully", sc);
    }

    public void setInfo(String username, AccountStatus status) {
        this.username = username;
        this.status = status;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getStatus() {
        return this.status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
