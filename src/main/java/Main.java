import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        //Открываю xmlMapper, чтобы в дальнейшем получить данные с файла sample.xml
        XmlMapper xmlMapper = new XmlMapper();
        System.out.println("Введите путь до файла, если он находится в проекте - его название");
        File xml = new File(new Scanner(System.in).nextLine());// при желании можно использовать относительный путь, а не абсолютный
        Map<String, Object> objectTree = xmlMapper.readValue(xml, new TypeReference<Map<String, Object>>() {
        });
        try {
            File yamlFile = new File("result.yaml");
            Charset charset = StandardCharsets.UTF_8;
            Path filePath = Paths.get("result.yaml");

            if (yamlFile.createNewFile()) {
                System.out.println("Файл создан: " + yamlFile.getName());
            } else {
                System.out.println("Файл уже существует");
            }
          } catch (IOException e) {
            System.out.println("Ошибка.");
            e.getMessage();
        }

        Yaml yaml = new Yaml();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("result.yaml"));
        String dump = yaml.dump(objectTree);

        String refactorTechRegs = dump.replace("TechRegs: {TechReg: Технический регламент на табачную продукцию (Федеральный\n" +
                "          Закон № 268-ФЗ от 22 декабря 2008г.), TechRegInfo: null}","");
        bufferedWriter.write(refactorTechRegs);
        bufferedWriter.close();
    }

}