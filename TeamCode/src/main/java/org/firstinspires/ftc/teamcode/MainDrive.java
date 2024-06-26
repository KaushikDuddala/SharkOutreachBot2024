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

    @Override
    public void runOpMode() throws InterruptedException {

        Gamepad cgp = new Gamepad();
        Gamepad pgp = new Gamepad();

        robot robot = new robot(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
21
            pgp.copy(cgp);


            // Drivetrain
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x * 1.1;
            double rx = gamepad1.right_stick_x;

            robot.dt.drive(x, y, rx);

            // Intake
            double intakePower = Math.min(cgp.right_trigger, intakeLimitSpeed) - Math.max(cgp.left_trigger, -intakeLimitSpeed);

            robot.intake.run(reverseIntake ? -intakePower : intakePower);

            cgp.copy(gamepad1);

        }
    }
}