#!/bin/bash

echo ""
echo "Загрузка приложения в телефон..."
adb="$HOME/android_sdk/platform-tools/adb"

$adb install -r build/calculator.apk

# $adb devices -l
# $adb -s AMAYLJWO7DGYPJBY install build/calculator.apk
