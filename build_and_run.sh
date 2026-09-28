#!/usr/bin/env bash
# Сборка и запуск лабораторной работы 1.
# Использование: ./build_and_run.sh [staff|tracker|all]
set -e
cd "$(dirname "$0")"

# Если системной Java нет — используем переносимый JDK из ~/tools.
if ! command -v javac >/dev/null 2>&1; then
  for j in "$HOME"/tools/jdk-*/bin; do
    [ -x "$j/javac" ] && export PATH="$j:$PATH" && break
  done
fi

echo "=== Компиляция (javac -d out) ==="
javac -encoding UTF-8 -d out $(find src -name "*.java")

case "${1:-all}" in
  staff)
    echo; echo "=== Задача 1: company.hrapp.My_class ==="
    java -cp out company.hrapp.My_class
    ;;
  tracker)
    echo; echo "=== Задача 2: company.trackerapp.My_class ==="
    java -cp out company.trackerapp.My_class
    ;;
  *)
    echo; echo "=== Задача 1: company.hrapp.My_class ==="
    java -cp out company.hrapp.My_class
    echo; echo "=== Задача 2: company.trackerapp.My_class ==="
    java -cp out company.trackerapp.My_class
    ;;
esac
