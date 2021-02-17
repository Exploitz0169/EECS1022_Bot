// Created by: Exploitz#0169
package main;

import commands.fun.HelloCommand;
import commands.general.ExampleCommand;
import commands.general.HelpCommand;
import commands.meta.BaseCommand;

public class CommandList {
    public static BaseCommand[] commands = {
      // In order for your command to be implemented, you must create a new instance of your command here
            new ExampleCommand(),
            new HelpCommand(),
            new HelloCommand()
    };
}
