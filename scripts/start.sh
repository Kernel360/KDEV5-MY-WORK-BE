#!/bin/bash
JAR_PATH=/home/ec2-user/deploy/mywork-be-0.0.1-SNAPSHOT.jar
APP_NAME=mywork-be-0.0.1-SNAPSHOT

# 기존 프로세스 종료
PID=$(pgrep -f $APP_NAME)
if [ -n "$PID" ]; then
  kill -9 $PID
fi

mkdir -p ../log/info
mkdir -p ../log/warn
mkdir -p ../log/error

sudo chown -R ec2-user:ec2-user ../log

# 백그라운드에서 실행
nohup java -jar $JAR_PATH --spring.profiles.active=prod --spring.config.location=file:/home/ec2-user/config/application-prod.yml > /home/ec2-user/deploy/nohup.out 2>&1 &
