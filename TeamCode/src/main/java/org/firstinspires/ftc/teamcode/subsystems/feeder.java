package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;

public class feeder {
    private CRServo servo;
    public feeder(CRServo servo)
    {
        this.servo = servo;
    }

    public void run(double power)
    {
        servo.setPower(power);
    }
}