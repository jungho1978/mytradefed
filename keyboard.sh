#!/bin/bash

CUR_DIR=$(dirname $0)

if [ ! -d "${CUR_DIR}/out/host/darwin-x86" ]; then
	echo "ERROR: please make sure that you built source successfully"
fi

OUT_HOME_DIR="${CUR_DIR}/out/host/darwin-x86/"
JAR_DIR="${OUT_HOME_DIR}/tradefed"
BIN_DIR="${OUT_HOME_DIR}/bin"

cp -r ${JAR_DIR} ${BIN_DIR}
mkdir ${BIN_DIR}/tools
cp "${CUR_DIR}/tools/"*"apk" ${BIN_DIR}/tools

cd ${BIN_DIR}
./tradefed.sh run keyboard/keyboard


