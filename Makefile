.DEFAULT_GOAL := run-dist

run-dist:
	./app/build/install/app/bin/app

clean:
	./gradlew clean -C app

build:
	./gradlew installDist -C app

test:
	./gradlew test -C app

report:
	./gradlew jacocoTestReport -C app

checkstyle:
	./gradlew checkstyleMain

.PHONY: clean build test report checkstyle run-dist