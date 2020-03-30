Installation of a JDK on Microsoft Windows Platforms:

 1) System Requirements for Installing the JDK on 64-Bit Windows Platform
For supported processors and browsers, see Oracle JDK Certified Systems Configurations (https://www.oracle.com/technetwork/java/javase/documentation/jdk12certconfig-5293013.html). To check your Device Specifications you can open -> "Control Panel\System and Security\System"

 2) Open the following Link to reach the "JDK Installation Instructions for Windows" page -> https://www.oracle.com/java/technologies/

 3) Choose a version which needs you (Java SE 13 in our case). Observe redirect to a "Java SE Downloads" page. Find needed Java version with ".exe" file in a corresponding section and Click on a link to Download it. In the opened page find your System Platform and Download JDK. Accept License Agreement and Click on the "Download" button in the opened modal. Change installation folder if needed.

 4) Running the JDK Installer. (You must have administrator privilege to install the JDK on Microsoft Windows).

- Start the JDK installer by double-clicking the installer's icon or file name in the download location.
- Follow the instructions provided by the Installation wizard.
- After the installation is complete, delete the downloaded file to recover the disk space.

5) Setting the PATH and CLASSPATH Environment Variables.
It is useful to set the PATH and CLASSPATH variables permanently for JDK so that it is persistent after rebooting.

To set the PATH variable on Microsoft Windows:
- Open "Control Panel\System and Security\System" -> "Advanced system settings".
- Open "Advanced" tab and then "Environment Variables".
- Add the location of the bin folder of the JDK installation to the PATH variable in System Variables. The following is a typical value for the PATH variable:
e.g. [C:\WINDOWS\system32;C:\WINDOWS;"C:\Program Files\Java\jdk.13.0_231\bin"].
- Add another CLASSPATH variable with ".;" value in System Variables.

_________________________________
Installation of the IntelliJ IDEA on Windows:

1) Download the IntelliJ IDEA for Windows from https://www.jetbrains.com/idea/download/#section=windows
(Change installation folder if needed)

2) Start the IdeaIU installer by double-clicking the installer's icon or file name in the download location. Follow the instructions provided by the Installation wizard. After the installation is complete, delete the downloaded file to recover the disk space

3) Import settings if needed after running Idea. Accept License Agreement and proceed further
________________________________________
Installation of the JDK on macOS:

 The simplest way to install Homebrew is through ruby and curl, accomplished with a single command.

Install Homebrew:
- Open the “Terminal” application, found in /Applications/Utilities/
- Enter the following command into a single line of the terminal:
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
- Hit Return and you’ll see a series of lines about what the script will install and where, hit Return again to agree or hit Escape to cancel
- Enter the administrator password (required by sudo execution) to begin installation. When complete, you will see an “Installation successful!” message.
Also, there is the posibility to check Brew version -> "brew --version"

Install JDK:
- Open the “Terminal” application, found in /Applications/Utilities/
- Do the Brew update first (if needed) -> "brew update"
- Enter the following command into a single line of the terminal -> "brew install homebrew/cask/java"

Also, there is the posibility to verify Java version -> "brew cask info java"