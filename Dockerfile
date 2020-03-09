FROM adoptopenjdk/openjdk11-openj9:alpine-jre

ENV APP_TARGET target
ENV APP app.jar

RUN mkdir -p /opt
COPY ${APP_TARGET}/${APP} /opt

CMD java -Xshareclasses -Xquickstart -jar /opt/${APP}
