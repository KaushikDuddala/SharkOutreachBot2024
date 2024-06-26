package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.subsystems.robotconfig;

public class robot {

    public drivetrain dt;


    public robot(HardwareMap hwmap)
    {
        DcMotor FLMotor = hwmap.dcMotor.get(robotconfig.FLMotor);
        DcMotor BLMotor = hwmap.dcMotor.get(robotconfig.BLMotor);
        DcMotor FRMotor = hwmap.dcMotor.get(robotconfig.FRMotor);
        DcMotor BRMotor = hwmap.dcMotor.get(robotconfig.BRMotor);

        FRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.dt = new drivetrain(FLMotor, BLMotor, FRMotor, BRMotor);
    }
}