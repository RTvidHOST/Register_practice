package sample;

public class User {
    private String name;
    private String gender;
    private String birth_date;
    private String birth_plase;
    private String residence_address;
    private String registration_address;

    public User(String name, String gender, String birth_date, String birth_plase, String residence_address,
                String registration_address) {
        this.name = name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.birth_plase = birth_plase;
        this.residence_address = residence_address;
        this.registration_address = registration_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getBirth_plase() {
        return birth_plase;
    }

    public void setBirth_plase(String birth_plase) {
        this.birth_plase = birth_plase;
    }

    public String getResidence_address() {
        return residence_address;
    }

    public void setResidence_address(String residence_address) {
        this.residence_address = residence_address;
    }

    public String getRegistration_address() {
        return registration_address;
    }

    public void setRegistration_address(String registration_address) {
        this.registration_address = registration_address;
    }
}
