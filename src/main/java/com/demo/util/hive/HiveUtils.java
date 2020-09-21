package com.demo.util.hive;

import static com.demo.constant.FileLocationConstants.INPUT_DIRECTORY_PATH;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.util.PartitionUtil;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class HiveUtils {
	
	public static void loadLocalFileToStagingTable(String file) throws ClassNotFoundException, SQLException {
		Map<Long, List<String>> map = new HashMap<>();
		try {
			FileReader filereader = new FileReader(INPUT_DIRECTORY_PATH + file);

			CSVReader csvReader = new CSVReaderBuilder(filereader)
					// .withSkipLines(1)
					.build();

			List<String[]> allData = csvReader.readAll();

			// print Data
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
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
