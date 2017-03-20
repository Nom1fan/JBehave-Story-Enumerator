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
        FileReader fileReader = new FileReader(path);
        FileWriter fileWriter = new FileWriter(path+"_enumerated");

        Pattern pattern = Pattern.compile("Scenario:\\[[1-9]\\]");

        String line;
        String newScenarioLine = "Scenario:[%d]";
        int scenarioNum = startFrom;

        try {

            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            while((line = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String enumeratedScenario = String.format(newScenarioLine, scenarioNum);
                    String enumeratedLine = line.replaceFirst("Scenario:\\[(.*)\\]", enumeratedScenario);
                    bufferedWriter.write(enumeratedLine);
                    bufferedWriter.newLine();
                    scenarioNum++;
                }
                else {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
            }

            bufferedReader.close();
            bufferedWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            path + "'");
        }

    }
}
