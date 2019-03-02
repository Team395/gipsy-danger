package frc.robot.enums;

public enum TargetType {
    kHighTarget(41.787),
    kLowTarget(33.893);

    double height;

    public double getHeight() {
        return height;
    }

    private TargetType(double height) {
        this.height = height;
    }
}