#!/bin/bash

echo ""
echo "Загрузка приложения в телефон..."
adb="$HOME/android_sdk/platform-tools/adb"

$adb install -r build/pokupkiNote.apk
# Загрузка в REDMI 9.
# $adb -s FUGYAAQWCQUCFIBQ install build/pokupkiNote.apk

# $adb devices -l
# $adb -s AMAYLJWO7DGYPJBY install build/pokupkiNote.apk
