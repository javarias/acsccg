#*******************************************************************************
# ACS Component Code Generator - http://code.google.com/p/acsccg/
# Copyright (C) 2010  Alexis Tejeda, alexis.tejeda@gmail.com
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
#  who       	when      		what
# 
# atejeda 		2010-00-00  	Created
# 

# General Vars
ANT=ant
RM=rm
JCLI=java
ENVDIR=$(shell pwd)

# Example-test var
LIBF=lib
EXF=examples
ACSCCG=$(LIBF)/acsComponentCodeGenerator.jar

# These modules exists in the example file, others will not work
# Upper and Lower case are sensitive.
EMODULES=	singleComponent	\
			MARS

all: clean build install

build:
	@echo ""
	@echo "###########################################"
	@echo "# Ant script clean and build."
	@echo "###########################################"
	@# Calling the ant build script
	@$(ANT) -f src/build.xml clean build
	@echo ""
	@echo "###########################################"
	@echo "# Now run: make install"
	@echo "###########################################"
	@echo ""

install:
	@echo ""
	@echo "###########################################"
	@echo "# Ant script install."
	@echo "###########################################"
	@# Calling the ant build script
	@$(ANT) -f src/build.xml install
	@echo ""
	@echo "###########################################"
	@echo "# Project binary (Jar) build into:"
	@echo "#         lib/acsComponentCodeGenerator.jar"
	@echo "# test the examples: make example"
	@echo "###########################################"
	@echo ""
	
clean:
	@echo ""
	@echo "###########################################"
	@echo "# Cleaning the project"
	@echo "###########################################"
	@echo ""
	@# Calling the ant clean script
	@$(ANT) -f src/build.xml clean

example:
	@rm -rf $(EXF)/generated
	@mkdir $(EXF)/generated
	@for module in $(EMODULES); do \
		echo $(JCLI) -jar $(ACSCCG) -m $(ENVDIR)/$(EXF)/xmi/ACSCodeGenerator.uml -p $(ENVDIR)/$(EXF)/xmi/EProfile.profile.uml -o $(ENVDIR)/$(EXF)/generated/ -e $$module;\
    	$(JCLI) -jar $(ACSCCG) -m $(ENVDIR)/$(EXF)/xmi/ACSCodeGenerator.uml -p $(ENVDIR)/$(EXF)/xmi/EProfile.profile.uml -o $(ENVDIR)/$(EXF)/generated/ -e $$module;\
    done
	@echo ""
	@echo "###########################################"
	@echo "# Done"
	@echo "###########################################"
	@echo ""

