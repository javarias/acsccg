#*******************************************************************************
# COMODO - Multiplatform Component Code Generator 
#    (c) European Southern Observatory, 2011 
#    Copyright by ESO 
#    All rights reserved 
# 
# Author: Alexis Tejeda
# 
# This library is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# $Id$
#
 

#*******************************************************************************
# REMARKS
#    This Makefile also build several java packages using Ant.
#-------------------------------------------------------------------------------

#*******************************************************************************
# Makefile configuration
#-------------------------------------------------------------------------------
ANT=ant
RM=rm
JCLI=java
ENVDIR=$(shell pwd)
#-------------------------------------------------------------------------------

#
# TARGETS
# -------
all: build
	@echo " . . . 'all' done" 
	
build: 
	@echo ""
	@echo "== Compiling the project (build)"
	@echo ""
	@# Calling the ant build script
	@$(ANT) -f $(ENVDIR)/build.xml build
	@echo ""
	@echo " . . . 'build' done" 

clean:
	@echo ""
	@echo "== Cleaning the project"
	@echo ""
	@# Calling the ant clean script
	@$(ANT) -f $(ENVDIR)/build.xml clean
	@echo " . . . clean done"

man:
	@echo ""
	@echo "== Generating the code documentation"
	@echo ""
	@# Calling the ant doc script
	@$(ANT) -f $(ENVDIR)/build.xml man > /dev/null
	@echo " . . . man page(s) done"

install:
	@echo ""
	@echo "== Building the JAR file"
	@echo ""
	@# Calling the ant build script
	@$(ANT) -f $(ENVDIR)/build.xml install
	@echo " . . . installation done"
	
#___oOo___