package almu.testing.utils;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StructureBuilderTest {

    @Test
    public void shouldSplitSentenceByWordGroup() {
        // given
        StructureBuilder builder = new StructureBuilder();
        String inputSentence = "bbbbb aaaa a bbbbb cccc bb aaaaa";
        Map<String, List<String>> expectedStructure = new TreeMap<>();
        expectedStructure.put("a", Arrays.asList("aaaaa", "a", "aaaa"));
        expectedStructure.put("b", Arrays.asList("bb", "bbbbb", "bbbbb"));
        expectedStructure.put("c", Collections.singletonList("cccc"));

        // when
        builder.splitSentenceByWordGroup(inputSentence);
        TreeMap<String, List<String>> structure = (TreeMap<String, List<String>>) ReflectionTestUtils.getField(builder, "structure");

        // then
        assertEquals("Created structure size not equal to expected", expectedStructure.size(), structure.size());
        for (Map.Entry<String, List<String>> expectedItem: expectedStructure.entrySet()) {
            assertNotNull(
                    "Not found words array for letter '"+expectedItem.getKey()+"'",
                    structure.get(expectedItem.getKey())
            );
            List<String> actualValues = structure.get(expectedItem.getKey());
            assertTrue(
                    expectedItem.getValue() + " does not contain the same elements as " + actualValues,
                    this.listEqualsIgnoreOrder(expectedItem.getValue(), actualValues)
            );
        }
    }

    @Test
    public void shouldFilterGroupsWithSmallSize() {
        // given
        StructureBuilder builder = new StructureBuilder();
        TreeMap<String, List<String>> structure = new TreeMap<>();
        List<String> value1 = Collections.singletonList("item1");
        List<String> value2 = Arrays.asList("item1", "item2");
        List<String> value3 = Arrays.asList("item1", "item2", "item3");
        List<String> value4 = Collections.emptyList();
        structure.put("key1", value1);
        structure.put("key2", value2);
        structure.put("key3", value3);
        structure.put("key4", value4);
        ReflectionTestUtils.setField(builder, "structure", structure);

        // when
        builder.filterGroupsWithSmallSize(1);
        TreeMap<String, List<String>> filteredStructure = (TreeMap<String, List<String>>) ReflectionTestUtils.getField(builder, "structure");

        // then
        assertEquals("Filtered structure size larger than expected", filteredStructure.size(), 2);
        assertTrue("Filtered structure should contain value 2", filteredStructure.containsValue(value2));
        assertTrue("Filtered structure should contain value 3", filteredStructure.containsValue(value3));
    }

    @Test
    public void shouldSortWordsInGroup() {
        // given
        Map<String, List<String>> testCases = new HashMap<>();
        testCases.put("[ddddd, aaaa, bbb, cc]", Arrays.asList("bbb", "aaaa", "cc", "ddddd"));
        testCases.put("[aaa, bbb, ccc, ddd]", Arrays.asList("ccc", "aaa", "bbb", "ddd"));
        testCases.put("[Three, One, Two]",  Arrays.asList("Two", "One", "Three"));

        StructureBuilder builder = new StructureBuilder();
        for (Map.Entry<String, List<String>> testCase: testCases.entrySet()) {
            String key = "key";
            TreeMap<String, List<String>> structure = new TreeMap<>();
            structure.put(key, testCase.getValue());
            ReflectionTestUtils.setField(builder, "structure", structure);

            // when
            builder.sortWordsInGroup();
            TreeMap<String, List<String>> sortedStructure = (TreeMap<String, List<String>>) ReflectionTestUtils.getField(builder, "structure");

            // then
            String errorMessage = "Incorrect result for {"+"key: "+testCase.getKey()+", value: "+testCase.getValue()+"}";
            assertEquals(errorMessage, testCase.getKey(), sortedStructure.get(key).toString());
        }
    }

    @Test
    public void shouldBuildStringResult() {
        // given
        Map<String, String> wordGroups = new LinkedHashMap<>();
        wordGroups.put("a", "[aaa, aa, a]");
        wordGroups.put("b", "[bbb, bb, b]");
        wordGroups.put("c", "[ccc, cc, c]");

        StructureBuilder builder = new StructureBuilder();
        TreeMap<String, List<String>> structure = new TreeMap<>();

        List<String> wordGroupLines = new ArrayList<>();
        for (Map.Entry<String, String> wordGroupEntry:wordGroups.entrySet()) {
            List wordGroup = mock(List.class);
            when(wordGroup.toString()).thenReturn(wordGroupEntry.getValue());
            structure.put(wordGroupEntry.getKey(), wordGroup);

            wordGroupLines.add(wordGroupEntry.getKey() + "=" + wordGroupEntry.getValue());
        }

        String expected = wordGroupLines.toString();
        ReflectionTestUtils.setField(builder, "structure", structure);

        // when
        String result = builder.getResult();

        // then
        assertEquals(expected, result);
    }

    public <T> boolean listEqualsIgnoreOrder(List<T> list1, List<T> list2) {
        return new HashSet<>(list1).equals(new HashSet<>(list2));
    }
}
