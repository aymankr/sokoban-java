import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class FileBoardBuilder implements BoardBuilder {

    private final String path;

    public FileBoardBuilder(String path) {
        this.path = path;
    }

    @Override
    public Board build() throws FileNotFoundException {

    }

}
