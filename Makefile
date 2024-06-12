.PHONY: run-dist clean build test checkstyle report

run-dist: clean build test checkstyle report
	./app/build/install/app/bin/app

clean:
	./app/gradlew -p app clean

build:
	./app/gradlew -p app installDist

test:
	./app/gradlew -p app test

checkstyle:
	./app/gradlew -p app checkstyleMain

report:
	./app/gradlew -p app jacocoTestReport