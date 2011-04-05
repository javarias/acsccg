#*******************************************************************************
# ACS Component Code Generator - http://code.google.com/p/acsccg/
# Copyright (C) 2010
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
# atejeda 	   2011-28-03  	    Created
# 
# $Id$
# 

# GENERAL VARS
# ------------
ANT=ant
RM=rm
JCLI=java
ENVDIR=$(shell pwd)

# EXAMPLE TEST VAR
# -----------------
BINF=lib
EXF=examples
SCXML=$(BINF)/acsscxmlcg.jar

# INSTALL SCRIPTS
# ----------------
INSTALL	 =

all: build

build:
	@echo ""
	@echo " == Building the project (build)"
	@echo ""
	@# Calling the ant build script
	@$(ANT) -f $(ENVDIR)/build.xml build
	@echo ""

install:
	@echo ""
	@echo " == Creating/Installing the JAR file"
	@echo ""
	@# Calling the ant build script
	@$(ANT) -f $(ENVDIR)/build.xml install
	@echo ""
	@echo " == Project binary (Jar) build into:"
	@echo "          ..bin/acsscxmlcg.jar"
	@echo ""
	@echo " == Done"
	@echo ""
	
clean:
	@echo ""
	@echo " == Cleaning out the project"
	@echo ""
	@echo ""
	@# Calling the ant clean script
	@$(ANT) -f $(ENVDIR)/build.xml clean