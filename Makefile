.PHONY: run-dist clean build test checkstyle report

run-dist: clean build test checkstyle report
	./app/build/install/app/bin/app

clean:
	./app/gradlew clean

build:
	./app/gradlew installDist

test:
	./app/gradlew test

checkstyle:
	./app/gradlew checkstyleMain

report:
	./app/gradlew jacocoTestReport