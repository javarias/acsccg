#
# Makefile
# ACS Component Code Generator
# Created Alexis Tejeda 27/06/2010
# Updated
#
GEN=../../bin/acsComponentCodeGenerator.jar
ANT=ant
RM=rm
MK=mkdir
pwd=$(shell pwd)

MODEL= $(pwd)/xmi/Model.uml
OUTPUT= $(pwd)/generated
PROFILE= $(pwd)/xmi/AlmaGenerator.profile.uml

all: clean generate

generate:
	$(MK) generated
	java -jar $(GEN) -o $(OUTPUT) -m $(MODEL) -p $(PROFILE)

clean:
	$(RM) -rf $(OUTPUT)
