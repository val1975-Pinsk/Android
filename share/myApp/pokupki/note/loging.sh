#!/bin/bash

echo ""
echo "Запись логов в файл logo.txt ..."
adb="$HOME/android_sdk/platform-tools/adb"

# Вывод логов в терминал
$adb logcat ->./logo.txt
