package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;

public class conveyor {

    CRServo servo;
    public conveyor(CRServo conveyorServo)
    {
        this.servo = conveyorServo;
    }

    public void run(double power)
    {
        servo.setPower(power);
    }


}