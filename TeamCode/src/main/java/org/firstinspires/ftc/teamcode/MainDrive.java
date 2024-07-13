package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.robot;

@Config
@TeleOp
public class MainDrive extends LinearOpMode {

    public static boolean reverseIntake = false;
    public static double intakeLimitSpeed = 0.7;
    public static double conveyorSpeed = 0.3;
    public static boolean conveyorReversed = false;
    public static double conveyorStationaryPosition = 0.00;
    public static double feederSpeed = 0.6;
    public static boolean feederReversed = false;
    public static double shooterSpeed = 0.7;
    public static boolean shooterReversed = false;

    public static double shooterTargetPos = 0.0;

    public static double kp = -2.5, ki = 0, kd = 0;

    public static double gear1 = 100, gear2 = 30;

    @Override
    public void runOpMode() throws InterruptedException {

        Gamepad cgp = new Gamepad();
        Gamepad pgp = new Gamepad();

        robot robot = new robot(hardwareMap, kp, ki, kd);
        long timeSinceLastShooterMove = System.currentTimeMillis();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            pgp.copy(cgp);


            // Drivetrain
            double y = -cgp.left_stick_y;
            double x = cgp.left_stick_x * 1.1;
            double rx = cgp.right_stick_x;

            // Intake
            double intakePower = Math.min(cgp.right_trigger, intakeLimitSpeed) - Math.max(cgp.left_trigger, -intakeLimitSpeed);


            // Single-item buttons


            // Conveyor

            double conveyorPower = (conveyorReversed ? -1 : 1) * ((cgp.left_bumper ? -conveyorSpeed : 0) + (cgp.right_bumper ? conveyorSpeed : 0)) + conveyorStationaryPosition;

            // Feeder

            double feederPower = (feederReversed ? -1 : 1) * ((cgp.dpad_left ? -feederSpeed : 0) + (cgp.dpad_right ? feederSpeed : 0));

            // Shooter
            double shooterPower = (shooterReversed ? -1 : 1) * ( (cgp.dpad_up ? shooterSpeed : 0) + (cgp.dpad_down ? -shooterSpeed : 0));

            // Turret

            if(System.currentTimeMillis() - timeSinceLastShooterMove > 1500)
            {
                if(cgp.b)
                {
                    shooterTargetPos -= 0.1;
                }
                else if(cgp.x)
                {
                    shooterTargetPos += 0.1;
                }
                robot.turret.setTarget(shooterTargetPos * (gear1 / gear2));
                timeSinceLastShooterMove = System.currentTimeMillis();
            }


            // Multi-item buttons

            if(cgp.a) // Run conveyor, feeder, and shooter (Shoot mechanism)
            {
                conveyorPower = (conveyorReversed ? -1 : 1) * (conveyorSpeed);
                feederPower = (feederReversed ? -1 : 1) * (feederSpeed);
                shooterPower = (shooterReversed ? -1 : 1) * (shooterSpeed);
            }

            // Convey final variables and commands to robot

            //robot.dt.drive(x, y, rx);
            //robot.intake.run(reverseIntake ? -intakePower : intakePower);
            robot.conveyor.run(conveyorPower);
            robot.feed.run(feederPower);
            robot.shooter.run(shooterPower);
            robot.turret.trackPos();
            robot.turret.calc();

            cgp.copy(gamepad1);

            telemetry.addData("PositionAxon", robot.turret.getPos());
            telemetry.addData("AxonTarget", shooterTargetPos);
            telemetry.addData("AxonStage", robot.turret.returnStage());
            telemetry.addData("axonFullRots", robot.turret.returnFullRots());
            telemetry.addData("TechnicalPos", robot.turret.returnActPos());
            telemetry.addData("axonPower", robot.turret.returnPower());
            telemetry.update();
        }
    }
}