**Project contains automated tests for Petstore:**

`client-api`  

Petstore contains three main Endpoints:

`pet` - describes everything about your Pets
`store` - describes access to Petstore orders
`user` - describes operations about user


**Setup on OS X:**
  
 1. Install Homebrew package manager if not installed (https://brew.sh/)
 2. Install JDK 11 via Homebrew
 - `brew cask uninstall java`
 - `brew tap caskroom/versions`
 - `brew cask install java11`
 3. Verify that JDK 11 was installed 
 `java -version`
 4. Install Maven via Homebrew 
 `brew install maven`
 5. Verify that Maven was installed 
 `mvn -version`
  
**View Serenity HTML report:**
 - `mvn serenity:aggregate`