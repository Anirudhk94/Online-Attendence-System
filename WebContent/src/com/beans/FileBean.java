package com.beans;

import java.io.Serializable;
import java.io.File;

public class FileBean implements Serializable{
			
		private String filepath, filename;
		private File file;
		
		public String getFilename() {
		    return filename;
		  }

		 public String getFilepath() {
		    return filepath;
		  }

		public void setFilename(String filename) {
			   this.filename = filename;
		}
		
		public void setFilepath(String filepath) {
			   this.filepath = filepath;
		}
		 
		public File getFile() {
		    return file;
		  }

		public void setFile(File file) {
			   this.file = file;
		}	

	}
