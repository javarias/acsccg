#
# Makefile
# ACS Component Code Generator
# Created Alexis Tejeda 27/06/2010
# Updated
#

ANT=ant
RM=rm
EXAMPLES= example1 example2 example3 example4 example5 example6

all: clean install doc

install:
	$(ANT) -f src/build.xml clean build install

clean:
	$(ANT) -f src/build.xml clean
	$(MAKE) -C doc/src clean
	for example in $(EXAMPLES); do \
		$(MAKE) -C examples/$$example clean; \
	done
	
example:
	for example in $(EXAMPLES); do \
		$(MAKE) -C examples/$$example; \
	done
	
doc:
	$(MAKE) -C doc/src clean

