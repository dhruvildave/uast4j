.DEFAULT_GOAL := all
.PHONY: all upgrade

GRADLE = ./gradlew
GRADLE_FLAGS = --no-build-cache --no-daemon --no-configuration-cache --no-watch-fs --no-scan -i

all:
	$(GRADLE) $(GRADLE_FLAGS) clean jar

upgrade:
	$(GRADLE) wrapper --gradle-version latest
	$(GRADLE) --stop
