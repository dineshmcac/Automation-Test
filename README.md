# Automation-Test

Goal : We need to process a list of people (provided in csv file format) and output those individuals who are related to one another based on our search criteria. A test file is provided with the expected column names and a few entries.

Maven Project with below depedency
1) OpenCSV
2) TestNG
3) Sure-fire plugin

Steps to clone execute the tests
1. git clone https://github.com/dineshmcac/Automation-Test
2. cd Automation-Test
3. mvn clean test

Output:
Find the output file in the root with the name output.txt

Data:
1. resource/simple_test_file.csv
2. testng.xml

1. Test Class - ValidateCSV.java
2. PageObject Class - BaseClass, ReusableWrapper, TestCasePO
