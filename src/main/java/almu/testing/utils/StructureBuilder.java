package almu.testing.utils;

import java.util.*;
import java.util.stream.Collectors;

public class StructureBuilder implements Builder {

    private TreeMap<String, List<String>> structure = new TreeMap<>();

    @Override
    public void splitSentenceByWordGroup(String sentence) {
        String[] words = sentence.split("\\s");
        this.structure = Arrays
                .stream(words)
                .collect(Collectors.groupingBy((String s) -> s.substring(0,1), TreeMap::new, Collectors.toList()));
    }

    @Override
    public void filterGroupsWithSmallSize(Integer minSize) {
        structure.entrySet().removeIf(entry -> entry.getValue().size() <= minSize);
    }

    @Override
    public void sortWordsInGroup() {
        for (List<String> list : structure.values()) {
            list.sort((String s1, String s2) -> {
                if (s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                }
                return s2.length() - s1.length();
            });
        }
    }

    public String getResult() {
        List<String> answer = new ArrayList<>();

        for (Map.Entry<String, List<String>> entity:structure.entrySet()) {
            answer.add(entity.getKey()+"="+entity.getValue());
        }

        return answer.toString();
    }
}
