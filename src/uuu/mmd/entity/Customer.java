package uuu.mmd.entity;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Customer {

//    public static final char MALE = 'M';
//    public static final char FEMALE = 'F';

    //attribute(屬性), member variable(成員變數), field(欄位)
    private String name; //required
    private String email; //PKey required 
    private String password; //required 6~20英數字 regular expresssion
    private LocalDate birthday;

    public Customer() {
    }

    public Customer( String name,String email, String password) throws MMDException {
        this.name = name;
        this.setEmail(email);
        this.password = password;
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws MMDException {
        name = name.trim();
        if (name != null && name.length() > 0) {
            this.name = name;
        } else {
            System.out.println("必須輸入客戶姓名");
            throw new MMDException("必須輸入客戶姓名");
        }
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws MMDException {
        if (email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            this.email = email;
        } else {
            System.out.println("必須輸入格式正確的客戶Email");
            throw new MMDException("必須輸入格式正確的客戶Email");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws MMDException {
        if (password != null && password.length() >= 6 && password.length() <= 20) {
            this.password = password;
        } else {
            System.out.println("必須輸入6~20碼的密碼");
            throw new MMDException("必須輸入6~20碼的密碼");
        }
    }

    public LocalDate getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday) throws MMDException {
        if (birthday != null && birthday.isBefore(LocalDate.now())) {
            this.birthday = birthday;
        } else {
            System.out.println("客戶生日必須有值且小於今天!");
            throw new MMDException("客戶生日必須有值且小於今天!");
        }
    }

    public void setBirthday(int year, int month, int day) throws MMDException {
        LocalDate date = LocalDate.of(year, month, day);	//轉型, int→LocalDate
        this.setBirthday(date);

    }

    public void setBirthday(String date) throws MMDException {
        if (date != null) {
            date = date.replace('/', '-');
            System.out.println("date = " + date);
        }
        try {
            this.setBirthday(date != null ? LocalDate.parse(date) : null);  //轉型, String→LocalDate
        } catch (DateTimeParseException ex) {
            System.out.println("客戶生日日期格式不正確");
            throw new MMDException("客戶生日日期格式不正確");
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + "{" + "電子信箱=" + email
                + ", 密碼=" + password
                + ", 姓名=" + name
                + ", 生日=" + birthday + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Customer) {  //因為Customer可能會變成VIP(不同型別但還是同一個人)
            Customer other = (Customer) obj;
            return this.email != null && this.email.equals(other.email);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 19;
        hash = hash * (this.email != null ? this.email.hashCode() : 0);

        return hash;
    }
}
