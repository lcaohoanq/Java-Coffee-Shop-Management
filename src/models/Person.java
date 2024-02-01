package models;

abstract class Person {
    private String code;
    private String name;
    private int yob;  // Year of Birth
    private String sex;

    public Person(String code, String name, int yob, String sex) {
        this.code = code;
        this.name = name;
        this.yob = yob;
        this.sex = sex;
    }

    //Normal case, the customer will not provide their information, data will set to not provided
    public Person(){
        this.code = "Not provided";
        this.name = "Not provided";
        this.yob = 0;
        this.sex = "Not provided";
    }
    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getYob() {
        return yob;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return String.format("%10d,%20s,%6d,%8s",code,name,yob,sex);
    }
}
