# EECS1022_Bot

Hello, welcome to the EECS1022 discord server bot

## Introduction

The goal of this bot is for members of the class to create commands and contribute to the bot. This will teach how to collaborate with peers, use git, and interact with the discord API. No command is too small. Make whatever you want.

Obviously, you cannot create a command that harms the discord server. It will not be added to the bot. Commands must comply with discord rate limits. Commands that are meant to spam will not be added.

A command handler has been put in place to make creating commands much easier for you. The token to the bot will not be available to anyone. To test your commands you should create a bot account in the discord developer portal and generate a token.

Here is a guide on generating a token and adding the bot to your private guild. Make sure not to leave the discord bot token in any files when you upload your code.
https://www.writebots.com/discord-bot-token/

## Getting started

This guide will assume you have git installed on your computer already. We will be using eclipse since that is what is being taught in this class.

This is a step-by-step on the basics for how to contribute to a github repository. 

1. On the main page https://github.com/Exploitz0169/EECS1022_Bot click the fork button:

![](https://i.imgur.com/47vQOJm.png)

2. Create a clone of your fork on your computer

I right click on my desktop and open git bash 

![](https://i.imgur.com/BqaXU7p.png)

Get the link to your git repository. On the fork you created click the code button then copy the url to your clipboard

![](https://i.imgur.com/4o2WGFI.png)

Go back to the git bash you opened and type in git clone gitURLGoesHere

![](https://i.imgur.com/omUR3FQ.png)

Hit enter. The repository will be cloned on to your desktop

3. Move into the newly created directory  
```bash
cd EECS1022_Bot
``` 

4. Add upstream remote. Type:
```bash
git remote add upstream https://github.com/Exploitz0169/EECS1022_Bot.git
```

5. Pull from master branch to ensure local repository is up to date
```bash
git pull upstream master
```

6. Let's import the project into eclipse now

Click import in eclipse:

![](https://i.imgur.com/MYSy9RE.png)

Head to gradle -> existing gradle project then click next

![](https://i.imgur.com/xOkRobc.png)

Click browse on the project root directory and select the EECS1022_Bot folder we created earlier

![](https://i.imgur.com/Wd1IRun.png)

Click the finish button and gradle will set up the project for you

7. Locate the constants file in src/main/java/main/Constants.java and paste your discord bot token in the token variable

![](https://i.imgur.com/DczRZq3.png)

8. Head to the EECSBot.java folder and click the run button at the top left of eclipse

The bot will now login and will be able to interact with you in your server. Try to run ?help for a list of commands

## How to add your new commands to the official bot

1. After you are done playing around with the bot, you may want to start coding and contributing. 

2. In this guide, we will create an example command 

3. Go to the commands folder and create a command in the category you wish to do so:

We will be creating a general command here.

Right click on commands.general -> new -> java class

![](https://i.imgur.com/dGpcinx.png)

We will name the file ExampleCommand

`When you create a command, please name it CommandNameCommand ex: if the command is named help, the file will be named HelpCommand`

You should also leave your name or discord identifier at the top of the commands you create :)

4. Commands must implement the BaseCommand interface 

You can do so by adding "implements BaseCommand" in your command file

![](https://i.imgur.com/NkNAVRm.png)

5. There will be an error because there are some required methods we must create first.

Hover over your class name and click add unimplemented methods

![](https://i.imgur.com/8cCwFUY.png)

6. This will implement two methods `getName` and `onCommand`

Your file should look something like this:

![](https://i.imgur.com/TB6XmuK.png)

7. Change the name of the command to whatever you want your command to be named

Ex:

![](https://i.imgur.com/93yxZqg.png)

This means when a user types ?example this command will be run

8. Have fun creating your command!

The onCommand method will be run whenever someone runs your command on discord. Add your code in there. The message event, channel, and arguments are provided to you.

The args are an array of all the words in a users message

For example: if a user types: ?example hey there man

The args will be [hey, there, man]

9. If you want your command to work you must add it to the command list file

Head to: CommandList.java and add an instance of your class here.

![](https://i.imgur.com/MqKAjuw.png)

If there were multiple commands here it would look like: 

![](https://i.imgur.com/CEM1f1r.png)

10. Make sure to test your commands before creating a pull request

11. Please remove your discord bot token before creating a pull request

## Creating a pull request

It is now time to create a pull request and try to have your features added to the main bot

1. Go back to git bash we opened earlier

2. Type 
```bash
git commit -m "Briefly describe what you added" -- src
```
This will only stage and commit the changes you made within the src folder. Everything else will be ignored

3. Type
```bash
git push origin master
```
This will push the changes to the master branch of the fork you created.

4. After you have pushed your changes to your fork, it is time to create a pull request.

Head to the repository you forked, then click pull requests -> new pull request

![](https://i.imgur.com/i9p5K3p.png)

5. Ensure the base repository is this one, set the base to development.

Ensure the head repository is the one you forked.

Click create pull request.

![](https://i.imgur.com/mu5kGAm.png)

6. If your command complies with the rules, it will be merged into the development branch of the bot for it to be tested. It will then be merged into the master branch provided it is valid.

7. It is a good habit to pull new changes from the master branch everytime to ensure you are up to date. Once again, in the git bash terminal run:

```bash
git pull upstream master
```

## All done

Congratulations on contributing to the bot! Of course if you have any further questions please join us in the 1022 discord server. (https://discord.gg/G8M3bbuFUJ)
