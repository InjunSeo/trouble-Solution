package jpaProject.trouble_Solution.domain;

public enum SolvedStatus {
    READY("미해결"), SOLVED("해결 완료");

    private final String description;


    SolvedStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
