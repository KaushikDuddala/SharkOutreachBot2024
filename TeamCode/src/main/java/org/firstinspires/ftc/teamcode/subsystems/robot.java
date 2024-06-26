package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.subsystems.robotconfig;

public class robot {

    public drivetrain dt;
    public intake intake;
    public conveyor conveyor;
    public feeder feed;
    public shooter shooter;
    public turret turret;


    public robot(HardwareMap hwmap)
    {

        // Drivetrain

        DcMotor FLMotor = hwmap.dcMotor.get(robotconfig.FLMotor);
        DcMotor BLMotor = hwmap.dcMotor.get(robotconfig.BLMotor);
        DcMotor FRMotor = hwmap.dcMotor.get(robotconfig.FRMotor);
        DcMotor BRMotor = hwmap.dcMotor.get(robotconfig.BRMotor);

        FRMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BRMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        this.dt = new drivetrain(FLMotor, BLMotor, FRMotor, BRMotor);

        // Intake

        DcMotor intakeMotor = hwmap.dcMotor.get(robotconfig.intake);

        this.intake = new intake(intakeMotor);

        // Conveyor

        CRServo conveyorServo = hwmap.crservo.get(robotconfig.conveyorServo);
        this.conveyor = new conveyor(conveyorServo);

        // Feed

        CRServo feedServo = hwmap.crservo.get(robotconfig.feedingServo);
        this.feed = new feeder(feedServo);

        // Shooter

        DcMotor shooterMotor = hwmap.dcMotor.get(robotconfig.shooterMotor);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        this.shooter = new shooter(shooterMotor);

        CRServo axon = hwmap.crservo.get(robotconfig.aimingServo);
        AnalogInput axonInput = hwmap.analogInput.get(robotconfig.axonAnalog);
        this.turret = new turret(axon, axonInput);

    }
}