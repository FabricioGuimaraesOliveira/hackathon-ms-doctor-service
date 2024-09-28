FROM ubuntu:latest
LABEL authors="sgome"

ENTRYPOINT ["top", "-b"]