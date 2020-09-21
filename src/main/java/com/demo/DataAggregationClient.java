package com.demo;

import static com.demo.constant.FileLocationConstants.INPUT_DIRECTORY_PATH;

import com.demo.watcher.FileWatcher;

public class DataAggregationClient {

	public static void main(String[] args) throws Exception {
		FileWatcher.watchForFiles(INPUT_DIRECTORY_PATH);
	}

}
