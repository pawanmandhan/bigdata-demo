package com.demo.util.hive;

import static com.demo.constant.FileLocationConstants.INPUT_DIRECTORY_PATH;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.util.PartitionUtil;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class HiveUtils {

	public static void loadLocalFileToStagingTable(String file) throws Exception {
		Map<Long, List<String>> map = new HashMap<>();
		FileReader filereader = new FileReader(INPUT_DIRECTORY_PATH + file);

		CSVReader csvReader = new CSVReaderBuilder(filereader)
				// .withSkipLines(1)
				.build();

		List<String[]> allData = csvReader.readAll();

		for (String[] row : allData) {
			Long lastQuarter = PartitionUtil.lastQuarter(Long.valueOf(row[1]));
			List<String> list = map.get(lastQuarter);
			if (list != null) {
				list.add(String.join(",", row));
			} else {
				List<String> val = new ArrayList<>();
				val.add(String.join(",", row));
				map.put(lastQuarter, val);
			}
		}
		// print map
		map.forEach((k, v) -> {
			System.out.println("key : " + k);
			v.forEach(System.out::println);
		});
	}

}
