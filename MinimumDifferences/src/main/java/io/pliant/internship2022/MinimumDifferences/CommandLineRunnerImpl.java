package io.pliant.internship2022.MinimumDifferences;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.pliant.internship2022.MinimumDifferences.pojo.InputData;
import io.pliant.internship2022.MinimumDifferences.pojo.OutputData;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final ObjectMapper objectMapper;
    private final BufferedReader bufferedReader;


    public CommandLineRunnerImpl() {
        this.objectMapper = new ObjectMapper();
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {

        String json = bufferedReader.readLine();// { "n": 3, "pnt": [11, 14, 10, 12], "pwe": [12, 9, 8, 11, 9]}

        while (!json.equalsIgnoreCase("Exit")) {
            InputData inputData = objectMapper.readValue(json, InputData.class);

            validateInputData(inputData);
            OutputData outputData = stretchSprints(inputData);

            printResult(outputData.toString());
            System.out.println("Please, paste new input or type Exit!");
            json = bufferedReader.readLine();
        }


    }

    private OutputData stretchSprints(InputData inputData) {
        int n = inputData.getN();
        int minSum = Integer.MAX_VALUE;
        int[] positioning = new int[2];


        List<int[]> variablesOfPnt = new ArrayList<>();
        List<int[]> variablesOfPwe = new ArrayList<>();

        int[] pnt = inputData.getPnt();
        int[] pwe = inputData.getPwe();

        fillVariantsOfStretch((inputData.getPnt().length - n) + 1, n, variablesOfPnt, pnt);
        fillVariantsOfStretch((inputData.getPwe().length - n) + 1, n, variablesOfPwe, pwe);

        for (int i = 0; i < variablesOfPnt.size(); i++) {
            for (int j = 0; j < variablesOfPwe.size(); j++) {
                int[] variantPnt = variablesOfPnt.get(i);
                int[] variantPwe = variablesOfPwe.get(j);
                int sum = 0;
                boolean isChanged = false;
                for (int k = 0; k < variantPnt.length; k++) {
                    sum += Math.abs(variantPnt[k] - variantPwe[k]);
                }

                if (minSum > sum) {
                    minSum = sum;
                    positioning[0] = i;
                    positioning[1] = j;
                }
            }
        }

        return new OutputData()
                .setN(minSum)
                .setSumPnt(positioning[0])
                .setSumPwe(positioning[1]);
    }

    private void fillVariantsOfStretch(int length, int n, List<int[]> variablesOfPwe, int[] pnt) {
        for (int i = 0; i < length; i++) {
            int[] var = new int[n];//n

            for (int j = 0; j < n; j++) {
                var[j] = pnt[j + i];
            }

            variablesOfPwe.add(var);
        }
    }


    private void validateInputData(InputData inputData) throws JSONException {
        StringBuilder sb = new StringBuilder();
        sb.append("{ \"error\": ");
        if (inputData.getN() < 2) {
            sb.append("\"N is less than 2\"}");
            printErrorMessage(sb);
            return;
        } else if (inputData.getPnt().length < inputData.getN()) {
            sb.append("\"length of pnt array is less than ").append(inputData.getN()).append("\"}");
            printErrorMessage(sb);
            return;
        } else if (inputData.getPwe().length < inputData.getN()) {
            sb.append("\"length of pwe array is less than ").append(inputData.getN()).append("\"}");
            printErrorMessage(sb);
            return;
        }
    }

    private void printResult(String toString) {
        System.out.println(toString);
    }

    private void printErrorMessage(StringBuilder sb) throws JSONException {
        JSONObject jsonObj = new JSONObject(sb.toString());
        System.out.print(jsonObj);
    }
}
