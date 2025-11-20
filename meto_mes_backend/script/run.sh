#!/bin/bash

MYSH_DIR=$(dirname $(realpath $BASH_SOURCE))

pidfile="${MYSH_DIR}/.tmp.pid"

# mvn clean install
run_start(){
run_stop
nohup java -jar ../target/mes-0.0.1-SNAPSHOT.jar  --server.port=5555 &
}

run_stop(){
lsof -t -i :5555 | xargs kill -9
}

main(){
	if [ "$1" = "start" ]; then
		run_start $@
	elif [ "$1" = "stop" ]; then
		run_stop $@
	else
		echo "please given an sub-command:start/stop"
	fi
}

main $@

