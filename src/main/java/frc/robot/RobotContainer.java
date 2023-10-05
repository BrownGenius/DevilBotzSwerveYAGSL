// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.drive.swerve.AbsoluteDrive;
import frc.robot.subsystems.drive.SwerveSubsystem;
import java.io.File;

public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase =
      new SwerveSubsystem(new File(Filesystem.getDeployDirectory(), "swerve"));
  private final XboxController controller = new XboxController(0);

  public RobotContainer() {
    configureBindings();

    AbsoluteDrive closedAbsoluteDrive =
        new AbsoluteDrive(
            drivebase,
            // Applies deadbands and inverts controls because joysticks
            // are back-right positive while robot
            // controls are front-left positive
            () -> MathUtil.applyDeadband(controller.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
            () -> MathUtil.applyDeadband(controller.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
            () -> -controller.getRightX(),
            () -> -controller.getRightY(),
            false);

    drivebase.setDefaultCommand(closedAbsoluteDrive);
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
