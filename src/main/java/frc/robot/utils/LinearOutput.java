package frc.robot.utils;

import edu.wpi.first.wpilibj.PIDOutput;
import frc.robot.subsystems.Drivetrain;

public class LinearOutput implements PIDOutput {
    Drivetrain drivetrain;

    public LinearOutput(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }

    private double headingCorrection = 0;

    public void setHeadingCorrection(double headingCorrection) {
        this.headingCorrection = headingCorrection;
    }

    @Override
    public void pidWrite(double output) {
        drivetrain.tankDrive(output - headingCorrection, output + headingCorrection);
    }
}
