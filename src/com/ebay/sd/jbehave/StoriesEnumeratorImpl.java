package com.ebay.sd.jbehave;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mmerhav on 20/3/2017.
 */
public class StoriesEnumeratorImpl implements StoriesEnumerator {

	@Override
	public void enumerateStory(String path, int startFrom) throws IOException {
		File backupFile = backupOriginalFile(path);

		FileReader fileReader = new FileReader(backupFile);
		FileWriter fileWriter = new FileWriter(path);

		enumerate(startFrom, fileReader, fileWriter);
	}

	private void enumerate(int startFrom, FileReader fileReader, FileWriter fileWriter) throws IOException {
		Pattern pattern = Pattern.compile("Scenario:\\[[^0].*\\]");

		String line;
		String newScenarioLine = "Scenario:[%d]";
		int scenarioNum = startFrom;

		BufferedReader bufferedReader = new BufferedReader(fileReader);

		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		while ((line = bufferedReader.readLine()) != null) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.find()) {
				String enumeratedScenario = String.format(newScenarioLine, scenarioNum);
				String enumeratedLine = line.replaceFirst("Scenario:\\[(.*)\\]", enumeratedScenario);
				bufferedWriter.write(enumeratedLine);
				bufferedWriter.newLine();
				scenarioNum++;
			} else {
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
		}

		bufferedReader.close();
		bufferedWriter.close();
	}

	private File backupOriginalFile(String path) {
		File oldFile = new File(path);
		File backupFile = new File(path + "_backup");

		oldFile.renameTo(backupFile);
		return backupFile;
	}
}
