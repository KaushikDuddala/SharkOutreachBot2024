package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

public class turrent {
    private CRServo axonServo;
    private AnalogInput axonPosInput;

    private double axonPos = 0.00;

    private int stage = 1;
    private int previousStage = 1;

    private int fullRots = 0;
    private double technicalPos = 0.00;
    private double targetPos = 0.00;

    private double kP = 0.2, kI = 0, kD = 0;

    private PIDController pid = new PIDController(kP, kI, kD);

    public turrent(CRServo axonServo, AnalogInput axonPosInput)
    {
        axonServo = axonServo;
        axonPosInput = axonPosInput;
        axonPos = axonPosInput.getVoltage() / 3.3;
        technicalPos = axonPos;
    }

    public void trackPos()
    {
        axonPos = axonPosInput.getVoltage() / 3.3;
        if(axonPos <= 0.3)
        {
            stage = 1;
        }
        else if(axonPos > 0.3 && axonPos < 0.6)
        {
            stage = 2;
        }
        else
        {
            stage = 3;
        }

        if(previousStage == 3 && stage == 1)
        {
            fullRots++;
        }
        else if(previousStage == 1 && stage == 3)
        {
            fullRots--;
        }

        previousStage = stage;
        technicalPos = fullRots + axonPos;
    }

    public void calc()
    {
        double power = pid.calculate(technicalPos, targetPos);
        axonServo.setPower(power);
    }

    public double getPos()
    {
        return technicalPos;
    }

    public void setTarget(double target)
    {
        targetPos = target;
    }
}
