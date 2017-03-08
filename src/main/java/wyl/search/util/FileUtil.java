package wyl.search.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfsj0317 on 2017/3/8.
 */
public class FileUtil {
    public static List<String> readFile(String path) throws Exception {
        List<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(path)));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            list.add(line);
        }
        br.close();
        return list;
    }
}
