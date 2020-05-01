package almu.testing.utils;

public interface Builder {
    void splitSentenceByWordGroup(String sentence);
    void filterGroupsWithSmallSize(Integer minSize);
    void sortWordsInGroup();
}
