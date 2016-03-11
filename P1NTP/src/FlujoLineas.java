/*
 * Copyright 2016 Alejandro Alcalde
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.sun.javafx.collections.MappingChange;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by Alejandro Alcalde <contacto@elbauldelprogramador.com> on 3/7/16.
 */
public class FlujoLineas {
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("\\s+");


        Files.lines(Paths.get("./alicia.txt"), StandardCharsets.ISO_8859_1)
                .forEach(System.out::println);

        // E2
        Map<String, Long> contadores =
                Files.lines(Paths.get("alicia.txt"))
    }
}
