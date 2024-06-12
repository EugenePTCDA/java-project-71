run-dist: clean build checkstyle report
	build\\install\\app\\bin\\app

checkstyle:
	gradlew.bat checkstyleMain

clean:
	gradlew.bat clean

build:
	gradlew.bat installDist

report:
	gradlew.bat jacocoTestReport
