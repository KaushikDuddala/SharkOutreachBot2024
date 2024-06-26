package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class intake {
    private DcMotor intakeMotor;

    public intake(DcMotor intakeMotor)
    {
        this.intakeMotor = intakeMotor;
    }

    public void run(double power)
    {
        intakeMotor.setPower(power);
    }
}