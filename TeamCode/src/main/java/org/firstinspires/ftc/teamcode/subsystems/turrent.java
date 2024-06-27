package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;

public class turrent {
    private CRServo axonServo;
    private AnalogInput axonPosInput;

    private double axonPos = 0.00;

    private int stage = 0;
    private int fullRots = 0;

    public turrent(CRServo axonServo, AnalogInput axonPosInput)
    {
        axonServo = axonServo;
        axonPosInput = axonPosInput;
    }

    public void trackPos()
    {
        if(stage == 0) // last reported under 0.5 position
        {

        }
    }
}
