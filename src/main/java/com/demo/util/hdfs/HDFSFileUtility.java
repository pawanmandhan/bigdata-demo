package com.demo.util.hdfs;

import java.io.File;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HDFSFileUtility {
	private static final String HDFS_URI = "hdfs://localhost:9000";
	private static final Configuration CONFIG = new Configuration();

	private void createDirectory(String hdfsDirectory) throws Exception {
		FileSystem fs = FileSystem.get(URI.create(HDFS_URI), CONFIG);
		Path pt = new Path(hdfsDirectory);
		boolean isCreated = fs.mkdirs(pt);
		if (isCreated) {
			System.out.println("Directory created & name of the directory is : " + hdfsDirectory);
		}
	}

	public void copyFromLFS(String localFSPath, String hdfsPath) throws Exception {
		String arguments = HDFS_URI + File.separator + hdfsPath;
		createDirectory(arguments);
		FileSystem fs = FileSystem.get(URI.create(HDFS_URI), CONFIG);
		fs.copyFromLocalFile(new Path(localFSPath), new Path(arguments));
	}
}
