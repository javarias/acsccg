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
#  who         when      		hat
# ---------    -----------      ----------------------------------
# atejeda 	   2010-00-00  	    Created
# 
# $Id$
# 

# General Vars
ANT=ant
RM=rm
JCLI=java
ENVDIR=$(shell pwd)

# Example-test var
BINF=lib
EXF=examples
ACSCCG=$(BINF)/acsComponentCodeGenerator.jar

all: clean build install message_done

build:
	@echo ""
	@echo " == Compiling the project (build)"
	@echo ""
	@# Calling the ant build script
	@$(ANT) -f $(ENVDIR)/build.xml clean build
	@echo ""

install:
	@echo ""
	@echo " == Building the JAR file"
	@echo ""
	@# Calling the ant build script
	@$(ANT) -f $(ENVDIR)/build.xml install
	@cp acsccgdiff ../bin/acsccgdiff
	@chmod +x ../bin/acsccgdiff
	@echo ""
	@echo "# Project binary (Jar) build into:"
	@echo "          bin/acsComponentCodeGenerator.jar"
	@echo "###########################################"
	@echo ""
	
clean:
	@echo ""
	@echo " == Cleaning out the project"
	@echo ""
	@echo ""
	@# Calling the ant clean script
	@$(ANT) -f $(ENVDIR)/build.xml clean
	
man:
	@echo ""
	@echo " == Generating the code documentation"
	@echo ""
	@echo ""
	@# Calling the ant doc script
	@$(ANT) -f $(ENVDIR)/build.xml man
	
message_done:
	@echo ""
	@echo " == Done"
	@echo ""
