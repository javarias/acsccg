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
#    Makefile for unit test and general tests.
#-------------------------------------------------------------------------------

#*******************************************************************************
# Makefile configuration
#-------------------------------------------------------------------------------
ANT=ant
RM=rm
JCLI=java
MAKECLI=make
EXTRAFLAGS=-d
LIBF=../bin
ENVDIR=$(shell pwd)
COMODO=$(LIBF)/comodo.jar $(EXTRAFLAGS)

#*******************************************************************************
# Code Generation Testing variables
#-------------------------------------------------------------------------------
PROFILE_XMI=EProfile.profile.uml
OUTPUT_PATH=output
XMI_PATH=example/xmi
MODEL_PATH=example/model
PROFILE_PATH =../include/xmi
#-------------------------------------------------------------------------------


#
# Model list target, modelname_emodule
#-------------------------------------
MODELS_TARGETS= eltins_prototype eltins_pinger \
				example_notificationChannel \
				example_singleComponent
								
#
# TARGETS
# -------
all:

test: generate_all

clean: clean_output
	@echo " . . . clean done"

clean_xmi:
		@echo ""
		@rm -rf $(XMI_PATH)/*
		@echo "== XMI folders cleaned"
		@echo " . . . clean_xmi done"

clean_output:
		@echo ""
		@rm -rf $(OUTPUT_PATH)/*
		@echo "== Output folders cleaned"
		@echo " . . . clean_output done"

generate_all:
		@echo ""
		@echo "== Generating the models"
		@for modeltarget in $(MODELS_TARGETS); do \
			$(MAKECLI) -C $(ENVDIR) $$modeltarget; \
		done
		@echo " . . . generate_all done"

#
# Testing targets
#----------------

eltins_prototype:
		@echo ""
		@echo "== Running eltins prototype"
		@echo ""
		@echo "$(JCLI) -jar $(COMODO) -d -m $(XMI_PATH)/ELT/ELT-InsPrototype.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e prototype" 
		@$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/ELT/ELT-InsPrototype.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e prototype
		@echo ""
		@echo "== eltins prototype generated"
		@echo " . . . eltins_prototype done"

eltins_pinger:
		@echo ""
		@echo "== Running eltins pinger"
		@echo ""
		@echo "$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/ELT/ELT-InsPrototype.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e PINGER_M" 
		@$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/ELT/ELT-InsPrototype.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e PINGER_M
		@echo ""
		@echo "== eltins PINGER_M generated"
		@echo " . . . eltins_pinger done"

example_singleComponent:
		@echo ""
		@echo "== Running Example singleComponent"
		@echo ""
		@echo "$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e singleComponent" 
		@$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e singleComponent
		@echo ""
		@echo "== singleComponent generated"
		@echo " . . . example_singleComponent done"

example_characteristicComponent:
		@echo ""
		@echo "== Running Example characteristicComponent"
		@echo ""
		@echo "$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e characteristicComponent" 
		@$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e characteristicComponent
		@echo ""
		@echo "== characteristicComponent generated"
		@echo " . . . example_characteristicComponent done"

example_javaInterfaces:
		@echo ""
		@echo "== Running Example javaInterfaces"
		@echo ""
		@echo "$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e javaInterfaces" 
		@$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e javaInterfaces
		@echo ""
		@echo "== javaInterfaces generated"
		@echo " . . . example_javaInterfaces done"

example_inheritance:
		@echo ""
		@echo "== Running Example inheritance"
		@echo ""
		@echo "$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e inheritance" 
		@$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e inheritance
		@echo ""
		@echo "== inheritance generated"
		@echo " . . . example_inheritance done"

example_notificationChannel:
		@echo ""
		@echo "== Running Example notificationChannel"
		@echo ""
		@echo "$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e notificationChannel" 
		@$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/examples/examples.uml -p $(PROFILE_PATH)/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e notificationChannel
		@echo ""
		@echo "== notificationChannel generated"
		@echo " . . . example_notificationChannel done"

acsscxml_masterComponent:
		@echo ""
		@echo "== Running Example singleComponent using the MasterComponent state machine"
		@echo ""
		@echo "$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/acsscxml/acscsxml.uml -p $(XMI_PATH)/acsscxml/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e singleComponent" 
		@$(JCLI) -jar $(COMODO) -m $(XMI_PATH)/acsscxml/acscsxml.uml -p $(XMI_PATH)/acsscxml/$(PROFILE_XMI) -o $(OUTPUT_PATH) -e singleComponent
		@echo ""
		@echo "== singleComponent using state machine generated"
		@echo " . . . acscsxml_masterComponent done"		

#___o0o___