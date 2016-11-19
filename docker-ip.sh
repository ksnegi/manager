#!/usr/bin/env bash

containers=( manager_user_1 manager_config-server_1 manager_cassandra-sidecar_1 manager_eureka_1 manager_cassandra_1 )
for container in ${containers[@]}
do
    ip=`docker inspect --format='{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' $container`
	echo $container": "$ip
done

exit 0