package sequence;

import java.io.File;

public class Template{
    private File input;
    private File output;

    public Template(String input, String output){
        this.setInput(new File(input));
        this.setOutput(new File(output));
    }

    public File getInput() {
        return input;
    }

    private void setInput(File input) {
        this.input = input;
    }

    public File getOutput() {
        return output;
    }

    private void setOutput(File output) {
        this.output = output;
    }
}
