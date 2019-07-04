package militaryElite.entities;

public class Mission {
    private String codeName;
    private String state;

    public Mission(String codeName, String state) {
        this.codeName = codeName;
        this.state = state;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getState() {
        return state;
    }

    private void setState(String state) {
        this.state = state;
    }

    public void completeMission() {
        this.setState("finished");
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s"
        , this.getCodeName(), this.getState());
    }
}
