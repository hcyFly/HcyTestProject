package com.cn.burus.hcytestproject.modle;

/**
 * Created by chengyou.huang on 2017/4/25.
 */

public class UserT implements Cloneable {

    private String name;
    private int age;
    private Address address;
    private String email;
    private int high;

    public UserT() {

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "UserT{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", high=" + high +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserT)) return false;

        UserT t = (UserT) o;

        if (getAge() != t.getAge()) return false;
        if (getHigh() != t.getHigh()) return false;
        if (!getName().equals(t.getName())) return false;
        if (!getAddress().equals(t.getAddress())) return false;
        return getEmail().equals(t.getEmail());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getAge();
        result = 31 * result + getAddress().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getHigh();
        return result;
    }

    @Override
    public UserT clone() {
        UserT userT = null;
        try {
            userT = (UserT) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return userT;
    }
}
