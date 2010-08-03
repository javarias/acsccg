#
# Makefile
# ACS Component Code Generator
# Created Alexis Tejeda 27/06/2010
# Updated
#

ANT=ant
RM=rm
EXAMPLES= example1 example2 example3 example4 example5 example6

all: clean install doc example

install:
	$(ANT) -f src/build.xml clean build install

clean:
	$(ANT) -f src/build.xml clean
	
doc:
	$(MAKE) -C doc/src clean

