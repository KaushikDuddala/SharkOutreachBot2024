package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

public class turret {
    private CRServo axonServo;
    private AnalogInput axonPosInput;

    private double axonPos = 0.00;

    private double power = 0;
    private int stage = 1;
    private int previousStage = 1;

    private int fullRots = 0;
    private double technicalPos = 0.00;
    private double targetPos = 0.00;

    private PIDController pid;

    public turret(CRServo axonServo1, AnalogInput axonPosInput1, double kp, double ki, double kd)
    {
        axonServo = axonServo1;
        axonPosInput = axonPosInput1 ;
        axonPos = axonPosInput.getVoltage() / 3.3;
        technicalPos = axonPos;
        pid = new PIDController(kp, ki, kd);
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
        power = pid.calculate(technicalPos, targetPos);
        if(power < 0)
        {
            power = Math.max(power, -1);
        }
        else
        {
            power = Math.min(power, 1);
        }
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
    public int returnStage() {return stage;}
    public int returnFullRots() { return fullRots; }
    public double returnActPos() { return axonPos; }
    public double returnPower() { return power; }
}
