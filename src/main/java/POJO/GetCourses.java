package POJO;

public class GetCourses {

    private String url;
    private String services;
    private String expertise;
    private Courses Courses;
    // Note this retrun type is class not string anymore , because it will come from another class
    private String instructor;
    private String linkedIn;

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public POJO.Courses getCourses() {
        return Courses;
    }

    public void setCourses(POJO.Courses courses) {
        Courses = courses;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }




}
