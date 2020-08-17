package com.demo;

import com.demo.constant.FileLocationConstants;
import com.demo.watcher.FileWatcher;

public class DataAggregationClient {

	public static void main(String[] args) throws Exception {
		FileWatcher.watchForFiles(FileLocationConstants.INPUT_DIRECTORY_PATH);
	}

}
