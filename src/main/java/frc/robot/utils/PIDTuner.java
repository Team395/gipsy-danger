package frc.robot.utils;

public interface PIDTuner {

    public void initializePreferences();
    public void updateGains();
    public void publishTelemetry();

}