#*******************************************************************************
# COMODO - Multiplatform Component Code Generator 
# Copyright (C) 2010/2011  Alexis Tejeda, alexis.tejeda@gmail.com
# in colaboration with ESO.
#
# This library is free software; you can redistribute it and/or
# modify it under the terms of the GNU Lesser General Public
# License as published by the Free Software Foundation; either
# version 2.1 of the License, or (at your option) any later version.
# 
# This library is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
# Lesser General Public License for more details.
# 
# You should have received a copy of the GNU Lesser General Public
# License along with this library; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307  USA
# 
#  "$Id$"
#
# Makefile of COMODO SW.
#
# who          when      		what
# ---------    -----------      ------------------------------------------------
# atejeda 	   2011-03-29  	    Created
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