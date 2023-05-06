# Виселица
Второй вариант проекта - попытка реализовать в ООП стиле.

Исполнитель: *halftimedeus*  || https://github.com/halftimedeus

Техническое задание: https://zhukovsd.github.io/java-backend-learning-course/Projects/Hangman/ 

---
__Реализованный функционал:__
* поиск случайного слова в словаре (dictionary.txt)
* вывод всех букв, загаданных пользователем
* вывод ASCII виселицы

### TODO
* игровое меню перед началом игры
* возможность ознакомиться с краткими правилами
* возможность посмотреть статистику угаданных слов (запись в файл?)
* локализация на русский язык с возможностью выбора языка в начале игры
* выбор сложности — показывать или не показывать подсказки

Для запуска из консоли скомпилировать .jar:
- `javac src/*.java -d classes`
- `jar -cvfm WordGame.jar resources/META-INF/MANIFEST.MF -C resources dictionary.txt classes`
- `java -jar WordGame.jar`