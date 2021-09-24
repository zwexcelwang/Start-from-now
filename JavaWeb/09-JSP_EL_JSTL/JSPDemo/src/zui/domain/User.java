package zui.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String name;
    private int age;
    private Date birthday;


    public User(String name, int age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 逻辑视图，并没有对应成员变量
     * @return
     */
    public String getBirthdayStr()  {
        if(birthday != null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return simpleDateFormat.format(birthday);
        }else {
            return "";
        }

    }
}
