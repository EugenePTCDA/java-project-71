.PHONY: run-dist clean build test checkstyle report

run-dist: clean build test checkstyle report
	./app/build/install/app/bin/app

clean:
	./gradlew.bat -p app clean

build:
	./gradlew.bat -p app installDist

test:
	./gradlew.bat -p app test

checkstyle:
	./gradlew.bat -p app checkstyleMain

report:
	./gradlew.bat -p app jacocoTestReport