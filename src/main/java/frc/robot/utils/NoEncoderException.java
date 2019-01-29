package frc.robot.utils;

public class NoEncoderException extends RuntimeException{
    public NoEncoderException(String msg) {
        super(msg);
    }

    public NoEncoderException() {
        this("");
    }
}