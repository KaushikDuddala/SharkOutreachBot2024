package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class drivetrain {

    private DcMotor FLMotor, BLMotor, FRMotor, BRMotor;
    public drivetrain(DcMotor FLMotor, DcMotor BLMotor, DcMotor FRMotor, DcMotor BRMotor)
    {
        this.FLMotor = FLMotor;
        this.BLMotor = BLMotor;
        this.FRMotor = FRMotor;
        this.BRMotor = BRMotor;
    }

    public void drive(double x, double y, double rx)
    {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        FLMotor.setPower(frontLeftPower);
        BLMotor.setPower(backLeftPower);
        FRMotor.setPower(frontRightPower);
        BRMotor.setPower(backRightPower);
    }
}