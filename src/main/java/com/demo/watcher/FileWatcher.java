package com.demo.watcher;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import com.demo.util.cron.LocalFSToStagingJobScheduler;
import com.demo.util.hive.HiveUtils;

public class FileWatcher {

	public static List<String> NEW_FILES_TO_BE_LOADED = new ArrayList<String>();
	public static long LAST_TABLE_ENTRY = 0;

	public static void watchForFiles(String directory) throws Exception {
		LocalFSToStagingJobScheduler.startScheduling();
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Path path = Paths.get(directory);

		WatchKey key = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		while ((key = watchService.take()) != null) {
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.println("event");
				String FileNameInLFS = event.context().toString();
				System.out.println(FileNameInLFS);
				NEW_FILES_TO_BE_LOADED.add(FileNameInLFS);
				System.out.println("tabl list : " + NEW_FILES_TO_BE_LOADED);
				HiveUtils.createPartitionTable(FileNameInLFS);
			}
			key.reset();
		}

	}

}
