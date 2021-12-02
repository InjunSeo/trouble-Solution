package jpaProject.trouble_Solution.domain;

public enum GenerationStatus {
    TEEN("10대"), TWENTYToTHIRTY("20-30대"), ELDER("40-50대");

    private final String description;

    GenerationStatus(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
