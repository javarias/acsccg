#
# Basic GUI for the Component Code Generator
# Copyright (C) 2010-08-10  Alexis Tejeda <alexis.tejeda@gmail.com>
# http://almasw.hq.eso.org/almasw/bin/view/JAO/ACSCodeGeneration
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
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
#
# Modified (developer, date):
#

#!/usr/bin/env python

import pygtk
pygtk.require('2.0')
import gtk
import subprocess, os

"""Basic GUI for the Component Code Generator
    this only run a Jar file located in the lib folder of 
    the project or in the same folder of this script, runs on
    python 2.6.4"""

class ACSccgBasicPGUI:
    """Basic GUI for the Component Code Generator
    this only run a Jar file located in the lib folder of 
    the project or in the same folder of this script, runs on
    python 2.6.4"""

    def main(self):
        """main function of the GUI GTK """
        gtk.main()
    
    def __init__(self):
        """ initial function, config and general settings for the GUI """
        # Main window
        self.window = gtk.Window(gtk.WINDOW_TOPLEVEL)
        self.window.set_border_width(2)
        self.window.set_title("ACSCCG Basic GUI")

        self.model = ""
        self.profile = ""
        self.output = ""
        self.generator = ""
    
        #Buttons
        self.generateButton = gtk.Button("Generate Code")
        self.modelFileShow = gtk.Button("Model File selection")
        self.profileFileShow = gtk.Button("Profile  File selection")
        self.folderFileShow = gtk.Button("Output Folder selection")
        self.exitButton = gtk.Button("Quit")
        
        # Text Area
        self.sw = gtk.ScrolledWindow()
        self.sw.set_policy(gtk.POLICY_AUTOMATIC, gtk.POLICY_AUTOMATIC)
        self.sw.set_placement(gtk.CORNER_BOTTOM_LEFT)
        self.sw.set_size_request(640, 480)
        self.textarea = gtk.TextView()
        self.sw.add(self.textarea)
        self.textarea.set_editable(False)
        
        #File Filer
        filter = gtk.FileFilter()
        filter.set_name('*.UML Files')
        filter.add_pattern('*.UML')
        filter.add_pattern('*.uml')
        
        #Connectors
        self.window.connect("delete_event", self.delete_event)
        self.window.connect("destroy", self.destroy)
        
        #Buttons handlers
        self.generateButton.connect("clicked", self.generate_code, None)
        self.modelFileShow.connect("clicked", self.select_model, None)
        self.profileFileShow.connect("clicked", self.select_profile, None)
        self.folderFileShow.connect("clicked", self.select_folder, None)
        self.exitButton.connect_object("clicked", gtk.Widget.destroy, self.window)

        #Pack     
        guipack = gtk.VBox(homogeneous=False, spacing=1)
        guipack.pack_start(self.modelFileShow, False, True, 2)
        guipack.pack_start(self.profileFileShow, False, True, 2)
        guipack.pack_start(self.folderFileShow, False, True, 2)
        guipack.pack_start(self.sw, False, True, 2)
        guipack.pack_start(self.generateButton, False, True, 2)
        guipack.pack_start(self.exitButton, False, True, 2)

        self.window.add(guipack)
        self.window.show_all() 
            
    # Select the model file
    def select_model(self, widget, data=None):
        """ Model file selection method """
        self.modelFile = gtk.FileChooserDialog('Open Model File...',
                                               None,
                                               gtk.FILE_CHOOSER_ACTION_OPEN,
                                               (gtk.STOCK_CANCEL, gtk.RESPONSE_CANCEL,
                                                gtk.STOCK_OPEN, gtk.RESPONSE_OK))
        self.modelFile.set_default_response(gtk.RESPONSE_OK)
        response = self.modelFile.run()
        if response == gtk.RESPONSE_OK:
            self.add_text(self.modelFile.get_filename() + ' model selected')
            self.model = self.modelFile.get_filename()
        elif response == gtk.RESPONSE_CANCEL:
            pass
        self.modelFile.destroy()

    #Select the profile file path
    def select_profile(self, widget, data=None):
        """' Profile file selection method """
        self.profileFile = gtk.FileChooserDialog('Open Profile File...',
                                               None,
                                               gtk.FILE_CHOOSER_ACTION_OPEN,
                                              (gtk.STOCK_CANCEL, gtk.RESPONSE_CANCEL,
                                               gtk.STOCK_OPEN, gtk.RESPONSE_OK))
        self.profileFile.set_default_response(gtk.RESPONSE_OK)
        response = self.profileFile.run()
        if response == gtk.RESPONSE_OK:
            self.add_text(self.profileFile.get_filename() + ' profile selected')
            self.profile = self.profileFile.get_filename()
        elif response == gtk.RESPONSE_CANCEL:
            pass
        self.profileFile.destroy()

    #Select the output folder path
    def select_folder(self, widget, data=None):
        """ Output folder selection """
        self.folderFile = gtk.FileChooserDialog('Select Output Folder...',
                                               None,
                                               gtk.FILE_CHOOSER_ACTION_SELECT_FOLDER,
                                                (gtk.STOCK_CANCEL, gtk.RESPONSE_CANCEL,
                                                 gtk.STOCK_OPEN, gtk.RESPONSE_OK))
        self.folderFile.set_default_response(gtk.RESPONSE_OK)
        response = self.folderFile.run()
        if response == gtk.RESPONSE_OK:
            self.add_text(self.folderFile.get_filename() + ' output folder selected')
            self.output = self.folderFile.get_filename()
        elif response == gtk.RESPONSE_CANCEL:
            pass
        self.folderFile.destroy()

    def delete_event(self, widget, event, data=None):
        """ delete a event """
        pass
        return False

    def destroy(self, widget, data=None):
        """ Destroy window """
        gtk.main_quit()
        
    def generate_code(self, widget, event, data=None):
        """ Run the generator Jar """
        process = subprocess.Popen(["/usr/bin/java", "-jar"], stdout=subprocess.PIPE, shell=True)
        os.waitpid(process.pid, 0)
        while True:
            output = process.stdout.readline()
            if not output:
                break
            self.add_text(output)

    def add_text(self, text):
        """ add a output text to the text area
        and set the automatic scrollbar position """
        buffer = self.textarea.get_buffer()
        iter = buffer.get_iter_at_mark(buffer.get_insert())
        buffer.insert(iter, text + "\n")
        vadjustment = self.sw.get_vadjustment()
        vadjustment.set_value(vadjustment.get_upper())
        
#Show GUI
if __name__ == "__main__":
    gui = ACSccgBasicPGUI()
    gui.main()








