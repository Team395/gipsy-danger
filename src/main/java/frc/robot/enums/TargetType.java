package frc.robot.enums;

public enum TargetType {
    kHighTarget(35.962),
    kLowTarget(28.337);
    
    double heightInches;
    
    public double getHeightInches() {
        return heightInches;
    }

    private TargetType(double heightInches) {
        this.heightInches = heightInches;
    }
}
