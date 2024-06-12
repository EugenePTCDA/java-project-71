.DEFAULT_GOAL := run-dist

run-dist:
	./app/build/install/app/bin/app

clean:
	make clean -C app

build:
	make installDist -C app

test:
	make test -C app

report:
	make jacocoTestReport -C app

checkstyle:
	make checkstyleMain

.PHONY: clean build test report checkstyle run-dist