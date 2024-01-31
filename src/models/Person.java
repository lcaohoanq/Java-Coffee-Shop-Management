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

}
