.DEFAULT_GOAL := all
.PHONY: all

GRADLE_FLAGS = --no-build-cache --no-daemon --no-configuration-cache --no-watch-fs --no-scan -i

all:
	./gradlew $(GRADLE_FLAGS) clean jar
