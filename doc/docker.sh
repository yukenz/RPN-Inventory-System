docker container list;


docker container create \
--name mysql \
--hostname docker-mysql \
--interactive \
--tty \
--publish 3306:3306 \
--env MARIADB_ROOT_PASSWORD=awan \
mariadb