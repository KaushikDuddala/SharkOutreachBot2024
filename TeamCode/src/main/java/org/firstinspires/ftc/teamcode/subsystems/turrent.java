package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

public class turrent {
    private CRServo axonServo;
    private AnalogInput axonPosInput;

    private double axonPos = 0.00;

    private int stage = 0;
    private int fullRots = 0;
    private double targetPos = 0.00;

    private double technicalPos = axonPos;

    private double kP = 0.2, kI = 0, kD = 0;

    private PIDController pid = new PIDController(kP, kI, kD);

    public turrent(CRServo axonServo, AnalogInput axonPosInput)
    {
        axonServo = axonServo;
        axonPosInput = axonPosInput;
    }

    public void trackPos()
    {
        axonPos = axonPosInput.getVoltage() / 3.3;
        if(stage == 0) // last reported under 0.5 position
        {

        }
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
        target = target;
    }
}
