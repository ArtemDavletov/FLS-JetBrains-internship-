package domain.entity;

public class Act {
    private Profile profile;
    private String date;
    private String act;

    public Act(Profile profile, String date, String act){
    }

    public Profile detProfile() { return profile; }

    public String detDate() { return date; }

    public String detAct() { return act; }
}
