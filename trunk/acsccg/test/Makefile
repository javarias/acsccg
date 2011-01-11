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
# atejeda 		2011-01-09  	Created
# 

# General Vars
ENVDIR=$(shell pwd)

# Model list (xmi files)
MODELS_XMI= ELT/ELT-InsPrototype.uml MARS/MARS.uml awu2010/awu2010.uml examples/examples.uml prsModule/prsModule
PROFILE_XMI = EEProfile.profile.uml

# Code Generation Testing variables
OUTPUT_PATH = output
XMI_PATH = xmi 
MODEL_PATH = model
PROFILE_PATH = ../../include

all:
		@echo ""
		@echo "Run: make test - to generate the code from the models"
		@echo ""

test: clean_output generate_models

cleanxmi:
		@echo ""
		@rm -rf $(XMI_PATH)/
		@echo "# XMI folders cleaned"
		@echo "###########################################"
		@echo ""

clean_output:
		@rm -rf $(OUTPUT_PATH)/
		@echo "# Output folders cleaned"
		@echo "###########################################"
		@echo ""

clean_md:
		@echo ""
		@find $(MODEL_PATH) -name '.mdr' | xargs rm -r
		@find $(MODEL_PATH) -name '.mdzip.back' | xargs rm -r
		@find $(PROFILE_PATH) '.mdr' | xargs rm -r 
		@find $(PROFILE_PATH) '.mdzip.back' | xargs rm -r
		@echo "# MagicDraw .mdr and .mdzip.back of models"
		@echo "# folders and profile path cleaned"
		@echo "###########################################"

generate_models:
		@echo ""
		@echo "# Generating the models"
		@echo "###########################################"
		@echo "Done."
		@echo ""
