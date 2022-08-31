#Arturo Salazar
#CSPC-Java Final
echo Remove old byte-code files if any exist
rm *.class

echo View list of source files
ls -l *.java

echo Compile Frame.java
javac FinalFrame.java

echo Compile Quad.java
javac FinalGraphicPanel.java

echo Compile Main.java
javac FinalTest.java

echo Execute the Solar System program
java FinalTest

echo End of execution.  Have a nice day.
