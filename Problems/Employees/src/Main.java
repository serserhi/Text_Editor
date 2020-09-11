class Employee {

    // write fields
    protected String name;
    protected String email;
    protected int experience;

    // write constructor
    Employee(String name, String email, int experience) {
        this.name = name;
        this.email = email;
        this.experience = experience;
    }

    // write getters
    String getName() {
        return this.name;
    }
    String getEmail() {
        return this.email;
    }
    int getExperience() {
        return this.experience;
    }
}

class Developer extends Employee {

    // write fields
    String mainLanguage;
    String[] skills;

    // write constructor
    Developer(String name, String email, int experience, String mainLanguage, String[] skills){
        super(name, email, experience);
        this.mainLanguage = mainLanguage;
        this.skills = skills;
    }

    // write getters
    String getMainLanguage() {
        return this.mainLanguage;
    }
    String[] getSkills() {
        return this.skills;
    }
}

class DataAnalyst extends Employee {

    // write fields
    boolean phd;
    String[] methods;

    // write constructor
    DataAnalyst(String name, String email, int experience, boolean phd, String[] methods) {
        super(name, email, experience);
        this.phd = phd;
        this.methods = methods;
    }

    // write getters
    boolean isPhd() {
        return this.phd;
    }
    String[] getMethods() {
        return this.methods;
    }
}