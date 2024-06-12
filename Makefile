.PHONY: run-dist clean build test checkstyle report

run-dist: clean build test checkstyle report
	./app/build/install/app/bin/app

clean:
	./app/gradlew.bat -p app clean

build:
	./app/gradlew.bat -p app installDist

test:
	./app/gradlew.bat -p app test

checkstyle:
	./app/gradlew.bat -p app checkstyleMain

report:
	./app/gradlew.bat -p app jacocoTestReport