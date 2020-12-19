JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
    Main.java \
	Config.java \
	Fenetre.java \
	JFrameMieux.java \
	Launch.java \
	Mouse.java

default: classes

classes: $(CLASSES:.java=.class)

test : Main.class
	$(JVM) Main

clean:
	$(RM) *.class