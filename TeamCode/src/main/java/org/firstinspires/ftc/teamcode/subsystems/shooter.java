package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class shooter {
    private DcMotor motor;
    public shooter(DcMotor motor)
    {
        this.motor = motor;
    }

    public void run(double speed)
    {
        motor.setPower(speed);
    }
}