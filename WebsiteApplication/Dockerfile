FROM nginx:1.23.3

ENV VERSION="0.0.1-SNAPSHOT"

ENV API_URL http://localhost:8082/

EXPOSE 80

RUN rm -rf /user/share/nginx/html/*

ADD dist/website-application/browser /usr/share/nginx/html/
ADD docker/etc/nginx/templates/ /etc/nginx/templates/
