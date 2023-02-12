SOURCES = $(wildcard */*.java)

CLASSES = $(SOURCES:.java=.class)

default: $(CLASSES)

%.class: %.java
	javac -cp $(CUP_DIR)/java-cup-11b.jar:. $<

clean:
	rm -f */*.class */*~

test:
	for i in $$(seq 1 12); do \
		echo ======== t$$i ======== ; \
		./plan t$$i; \
	done

testt:
	for i in $$(seq 1 8); do \
		echo ======== t$$i ======== ; \
		./plan t$$i; \
	done
