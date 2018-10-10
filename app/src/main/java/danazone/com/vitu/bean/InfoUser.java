package danazone.com.vitu.bean;

public class InfoUser {
    private int id;
    private String phone;
    private String email;
    private String name;
    private int coin;
    private int type;
    private String gender;
    private String birthday;
    private String school;
    private String homeTown;
    private int experience;
    private int verify;
    private String avatar;
    private String degree;

    public InfoUser(){

    }

    public InfoUser(int id, String phone, String email, String name, int coin, int type, String gender, String birthday, String school, String homeTown, int experience, int verify, String avatar, String degree) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.coin = coin;
        this.type = type;
        this.gender = gender;
        this.birthday = birthday;
        this.school = school;
        this.homeTown = homeTown;
        this.experience = experience;
        this.verify = verify;
        this.avatar = avatar;
        this.degree = degree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getVerify() {
        return verify;
    }

    public void setVerify(int verify) {
        this.verify = verify;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
