.DEFAULT_GOAL := run-dist

run-dist:
	./app/build/install/app/bin/app

clean:
	./app/gradlew.bat clean

build:
	./app/gradlew.bat installDist

test:
	./app/gradlew.bat test

report:
	./app/gradlew.bat jacocoTestReport

checkstyle:
	./app/gradlew.bat checkstyleMain

.PHONY: clean build test report checkstyle run-dist