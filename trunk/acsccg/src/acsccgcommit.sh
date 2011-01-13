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
#  who          when            what
# -------		----------		----------------------------
# atejeda       2011-13-01      Created
# 
# $Id$
# 

#!/bin/bash

# This file is to commit everything and add the svn properties
# on all files. Also if you want commit your own files, is better
# source this file and then commit.

# For safe, source the bash profile - if exists
if [ -f ~/.bash_profile ]; then
	. ~/.bash_profile
fi

# Replace by your favorite editor
SVN_EDITOR=vim

# add svn:keywords Id Revision Author Date property recursively
function addsvnps 
{
	echo "--Adding keywords";
	
	KLIST="Id Revision Author Date";	

	for kword in `echo $KLIST`; do \
		echo ""
		echo "Adding $kword svn:keyword"\	
		find . \( -name "*.ext" -o \
		-name "*.mwe" -o \
		-name "*.java" -o \
		-name "*.xpt" -o \
		-name "*.xml" -o \
		-name "Makefile" -o \
		-name "*.sh" -o \
		-name "*.properties" \) | xargs svn propset svn:keywords $kword; \
	done
}

# Remove logs to commit
function removelog 
{
	echo "--Removing logs"
        find ../ \( -name "*.log" \) | xargs rm 
}

# execute functions
removelog;
addsvnps;

# Now Commit the files

