.DEFAULT_GOAL := run-dist

run-dist:
	./app/build/install/app/bin/app

clean:
	gradlew.bat clean -C app

build:
	gradlew.bat installDist -C app

test:
	gradlew.bat test -C app

report:
	gradlew.bat jacocoTestReport -C app

checkstyle:
	gradlew.bat checkstyleMain

.PHONY: clean build test report checkstyle run-dist