package com.demo.watcher;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.demo.util.hive.HiveUtils;

public class FileWatcher {

	public static void watchForFiles(String directory) throws Exception {
		WatchService watchService = FileSystems.getDefault().newWatchService();
		Path path = Paths.get(directory);
		WatchKey key = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				String fileNameInLFS = event.context().toString();
				System.out.println("new file to be loaded in staging_table" + fileNameInLFS);
				HiveUtils.loadLocalFileToStagingTable(fileNameInLFS);
			}
			key.reset();
		}

	}

}
