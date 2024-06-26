package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.robot;

@TeleOp
public class MainDrive extends LinearOpMode {

    public static boolean reverseIntake = false;
    public static double intakeLimitSpeed = 0.7;
    public static double conveyorSpeed = 0.3;
    public static boolean conveyorReversed = false;
    public static double conveyorStationaryPosition = 0.00;

    @Override
    public void runOpMode() throws InterruptedException {

        Gamepad cgp = new Gamepad();
        Gamepad pgp = new Gamepad();

        robot robot = new robot(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            pgp.copy(cgp);


            // Drivetrain
            double y = -cgp.left_stick_y;
            double x = cgp.left_stick_x * 1.1;
            double rx = cgp.right_stick_x;

            robot.dt.drive(x, y, rx);

            // Intake
            double intakePower = Math.min(cgp.right_trigger, intakeLimitSpeed) - Math.max(cgp.left_trigger, -intakeLimitSpeed);

            robot.intake.run(reverseIntake ? -intakePower : intakePower);

            // Conveyor

            double conveyorPower = (conveyorReversed ? -1 : 1) * ((cgp.left_bumper ? -conveyorSpeed : 0) + (cgp.right_bumper ? conveyorSpeed : 0)) + conveyorStationaryPosition;
            robot.conveyor.run(conveyorPower);

            cgp.copy(gamepad1);

        }
    }
}