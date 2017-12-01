# 321658387 
# shani herskowitz

compile: bin
	javac -d bin -cp biuoop-1.4.jar src/*/*.java src/*.java

run:
	java -cp bin:biuoop-1.4.jar RunGame

jar:
	jar cfm space-invaders.jar Manifest.mf -C bin .

bin:
	mkdir bin