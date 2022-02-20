package domain;

public class User {

    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private double balance;
    private int id;
    private boolean isAdmin;

    public User(String firstName, String lastName, String userName, String passworld, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = passworld;
        this.balance = balance;
        this.isAdmin = false;
    }

    public User(String firstName, String lastName, String userName, String password, double balance, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.isAdmin = isAdmin;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passworld) {
        this.password = passworld;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", id=" + id +
                ", isAdmin= "+isAdmin+'\''+
                '}';
    }
}

